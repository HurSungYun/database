package simpledatabase;

import java.io.*;

public class Serializer {
    public static Object fromBytes(byte[] b) {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] toBytes(Object o) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            return baos.toByteArray();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
