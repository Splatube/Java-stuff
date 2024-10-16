import java.util.*;
import java.io.*;

public class Variable {
    OutputStream output = new FileOutputStream("/home/deck/Documents/Programs/Java/General/src/app.properties");
    static Properties prop = new Properties();
    public Variable() throws FileNotFoundException {}

    public static Object getVariable(String name) {
        return prop.getProperty(name);
    }

    public static void storeVariable(String name, String value) {
        prop.setProperty(name, value);
    }
}
