package w2d1;

import java.io.IOException;

public interface SerializerXml {
    void serialize (Object object, String file);

    Object deSerialize(String file) throws IOException, ClassNotFoundException;
}
