package TodoList;

import java.util.*; import java.io.*;

public class Variable {
    static Properties prop = new Properties();
    static String propPath = "/home/deck/Documents/Programs/Java/General/src/TodoList/tasks.properties";

    public static Properties loadProperties() throws IOException {
        prop.load(new FileInputStream(propPath));
        return prop;
    }

    public static void clear() throws IOException {
        prop.store(new FileWriter(propPath), null);
    }

    public static void storeVariable(String name, String value) throws IOException {
        prop.load(new FileInputStream(propPath));
        prop.setProperty(name, value);
        prop.store(new FileWriter(propPath), null);
    }

    public static void removeVariable(String name) throws IOException {
        prop.load(new FileInputStream(propPath));
        prop.remove(name);
        prop.store(new FileWriter(propPath), null);
    }
}
