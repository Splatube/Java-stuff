package TodoList;

import java.io.*;
import java.util.*;

public class Variable {
    static Properties prop = new Properties();
    static String propPath = "/home/deck/Documents/Programs/Java/General/src/TodoList/tasks.properties";

    public static void loadProperties() throws IOException {
        try (FileInputStream input = new FileInputStream(propPath)) {
            prop.load(input);
        }
    }

    public static void addValueToKey(String key, String newValue) throws IOException {
        loadProperties();
        String currentValue = prop.getProperty(key);

        if (currentValue != null) {
            if (!currentValue.contains(newValue)) {
                currentValue += "," + newValue;
            }
        } else {
            currentValue = newValue;
        }

        prop.setProperty(key, currentValue);

        try (FileWriter writer = new FileWriter(propPath)) {
            prop.store(writer, null);
        }
    }

    public static void clear() throws IOException {
        prop.clear();
        try (FileWriter writer = new FileWriter(propPath)) {
            prop.store(writer, null);
        }
    }

    public static void storeVariable(String name, String value) throws IOException {
        loadProperties();
        prop.setProperty(name, value);
        try (FileWriter writer = new FileWriter(propPath)) {
            prop.store(writer, null);
        }
    }

    public static void removeVariable(String name) throws IOException {
        loadProperties();
        prop.remove(name);
        try (FileWriter writer = new FileWriter(propPath)) {
            prop.store(writer, null);
        }
    }
}
