package TodoList;

import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

public class ToDo {
    static Properties tasks = new Properties();
    static Scanner input = new Scanner(System.in);
    static String choice;
    static String propPath = "/home/deck/Documents/Programs/Java/General/src/TodoList/tasks.properties";

    private static void loadTasks() throws IOException {
        tasks.load(new FileInputStream(propPath));
    }

    public static void clearTasks() throws IOException {
        System.out.print("Are you sure? ");
        choice = input.nextLine();
        if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
            Variable.clear();
            System.out.printf("%sCleared tasks%s%n", ascii.red, ascii.reset);
        }
    }

    public static void addTask(String name) throws IOException {
        Variable.storeVariable(name, "incomplete");
        System.out.printf(ascii.green + "Added '%s'%n" + ascii.reset, name);
    }

    public static void addTag() throws IOException {
        System.out.print("Which task? ");
        String name = input.nextLine();
        System.out.print("Enter tag: ");
        String tag = "[" + input.nextLine() + "]";
        Variable.addValueToKey(name, tag);
    }

    public static void completeTask(String name) throws IOException {
        if (tasks.containsKey(name)) {
            if ("incomplete".equals(tasks.getProperty(name))) {
                Variable.storeVariable(name, "complete");
                System.out.printf("%s'%s' completed!%s%n", ascii.green, name, ascii.reset);
            } else {
                System.out.println("Task is already completed!");
            }
        } else {
            System.out.println("Task does not exist!");
        }
    }

    public static void removeTask(String name) throws IOException {
        if (tasks.containsKey(name)) {
            Variable.removeVariable(name);
            System.out.printf("%sRemoved '%s'%s%n", ascii.red, name, ascii.reset);
        } else {
            System.out.println("Task does not exist!");
        }
    }

    public static void displayTasks() throws IOException {
        loadTasks();
        if (!tasks.isEmpty()) {
            System.out.println("To do list:");
            for (String name : tasks.stringPropertyNames()) {
                String status = tasks.getProperty(name);
                if ("incomplete".equals(status)) {
                    System.out.printf("%s : %sIncomplete%s%n", name, ascii.red, ascii.reset);
                } else {
                    System.out.printf("%s : %sComplete%s%n", name, ascii.green, ascii.reset);
                }
            }
        } else {
            System.out.println("There are no tasks");
        }
    }
}
