package w2d1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Класс для сериализации объектов в XML и обратно
 */
public class XmlSerializer implements SerializerXml {

    /**
     * Метод сериализации объекта через рефлексию и записываем всю информацию в XML файл
     *
     * @param object - экземпляр класса для разбора
     * @param file   - выходной файл
     */
    @Override
    public void serialize(Object object, String file) {
        File xmlFile = new File(file);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xmlFile))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            Class cl = object.getClass();
            String superClass = String.valueOf(cl.getSuperclass());
            String className = cl.getName();
            writer.write("<class name=\"" + className + "\">\n");
            Field[] fields = cl.getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                Object fieldName = f.get(object);
                writer.write("\t<field name=\"" + f.getName() + "\" type=\"" + f.getType().getSimpleName() + "\">"
                        + fieldName + "</field>\n");
            }
            writer.write("</class>\n");
        } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Completed serialeze of object");
    }

    /**
     * Метод десериализации
     * На входи получаем XML файл с объектом. Парсим его название клсса и полей записываем в переменные.
     * Создаем объект на основании указанного в нем класса и выставляем значения полей
     *
     * @param file - строка с путем до файла
     * @return - созданные объект на основе данных из XML файла
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public Object deSerialize(String file) throws IOException, ClassNotFoundException {
        File xmlFile = new File(file);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Object instance = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            Element rootElement = document.getDocumentElement();

            String className = rootElement.getAttribute("name");
            Class<?> clazz = Class.forName(className);

            instance = clazz.getDeclaredConstructor().newInstance();

            NodeList fields = rootElement.getElementsByTagName("field");

            for (int i = 0; i < fields.getLength(); i++) {
                Element fielsElement = (Element) fields.item(i);

                String filedType = fielsElement.getAttribute("type");
                String filedName = fielsElement.getAttribute("name");
                String value = fielsElement.getTextContent();

                Method setterMethod = null;

                if ("int".equals(filedType)) {
                    setterMethod = clazz.getMethod("set" + capsFirstLetter(filedName), Integer.TYPE);
                    setterMethod.invoke(instance, Integer.valueOf(value));
                } else if ("String".equals(filedType)) {
                    setterMethod = clazz.getMethod("set" + capsFirstLetter(filedName), String.class);
                    setterMethod.invoke(instance, value);
                }
            }
        } catch (ParserConfigurationException | SAXException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error: " + e);
        }
        return instance;
    }

    /**
     * Метод для написания первого символа в строке заглавным
     * @param inSt
     * @return
     */
    private String capsFirstLetter(String inSt) {
        return Character.toUpperCase(inSt.charAt(0)) + inSt.substring(1);
    }
}
