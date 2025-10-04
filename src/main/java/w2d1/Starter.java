package w2d1;

import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String file = "/home/maksim/tmp/w2d1.xml";
        Courier courier = new Courier("Name1", 001);
        XmlSerializer xmlSerializer = new XmlSerializer();
        xmlSerializer.serialize(courier, file);
        System.out.println(xmlSerializer.deSerialize(file).toString());
    }
}
