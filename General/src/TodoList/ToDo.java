package TodoList;

import java.util.*;
import java.io.*;

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

    public static void addTask() throws IOException {
        System.out.print("Enter task name: ");
        String name = input.nextLine();
        Variable.storeVariable(name, "incomplete");
        System.out.printf(ascii.green + "Added '%s'%n" + ascii.reset, name);
    }

    public static void addTag() throws IOException {
        System.out.print("Which task? ");
        String name = input.nextLine();
        if (tasks.containsKey(name)) {
            System.out.print("Enter tag: ");
            String tag = "[" + input.nextLine() + "]";
            Variable.addValueToKey(name, tag);
            System.out.println(ascii.green + "Added tag" + ascii.reset);
        } else {
            System.out.printf("%sNo task named '%s'%s%n", ascii.red, name, ascii.reset);
        }
    }

    public static void completeTask() throws IOException {
        System.out.print("Which task? ");
        String name = input.nextLine();
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

    public static void removeTask() throws IOException {
        System.out.print("Enter task name: ");
        String name = input.nextLine();
        if (tasks.containsKey(name)) {
            Variable.removeVariable(name);
            System.out.printf("%sRemoved '%s'%s%n", ascii.red, name, ascii.reset);
        } else {
            System.out.println("Task does not exist!");
        }
    }

    public static void addDate() {

    }

    public static void searchTags() throws IOException {
        loadTasks();
        System.out.print("Enter tag/status: ");
        String tag = input.nextLine();
        if (tasks.values().toString().toLowerCase().contains(tag.toLowerCase())) {
            for (String name : tasks.stringPropertyNames()) {
                String status = tasks.getProperty(name);
                String[] values = tasks.getProperty(name).split(",");
                if (tag.equalsIgnoreCase("complete")) {
                    if (!status.contains("incomplete"))
                        if (status.contains("[")) {
                            for (int i = 1; i < values.length; i++) {
                                System.out.print(ascii.blue + values[i] + " " + ascii.reset);
                            }
                            System.out.printf("%s : %sComplete%s%n", name, ascii.green, ascii.reset);
                        }
                } else if (tag.equalsIgnoreCase("incomplete")) {
                    if (status.contains("incomplete")) {
                        if (status.contains("[")) {
                            for (int i = 1; i < values.length; i++) {
                                System.out.print(ascii.blue + values[i] + " " + ascii.reset);
                            }
                            System.out.printf("%s : %sIncomplete%s%n", name, ascii.red, ascii.reset);
                        }
                    }
                } else if (tasks.getProperty(name).toString().toLowerCase().contains("["+tag.toLowerCase()+"]")){
                    for (int i = 1; i < values.length; i++) {
                        if (values[i].equalsIgnoreCase(tag)) {
                            System.out.print(ascii.blue + ascii.bold + values[i] + " " + ascii.reset);
                        } else {
                            System.out.print(ascii.blue + values[i] + " " + ascii.reset);
                        }
                    }
                    if (status.contains("incomplete")) {
                        System.out.printf("%s : %sIncomplete%s%n", name, ascii.red, ascii.reset);
                    } else if (status.contains("complete")) {
                        System.out.printf("%s : %sComplete%s%n", name, ascii.green, ascii.reset);
                    }
                }
            }
        } else {
            System.out.println(ascii.red + "No tasks found" + ascii.reset);
        }
    }

    public static void displayTasks() throws IOException {
        loadTasks();
        if (!tasks.isEmpty()) {
            System.out.println("To do list:");
            for (String name : tasks.stringPropertyNames()) {
                String status = tasks.getProperty(name);
                String[] values = tasks.getProperty(name).split(",");
                if (status.contains("[")) {
                    for (int i = 1; i < values.length; i++) {
                        System.out.print(ascii.blue + values[i] + " " + ascii.reset);
                    }
                }
                if (status.contains("incomplete")) {
                    System.out.printf("%s : %sIncomplete%s%n", name, ascii.red, ascii.reset);
                } else if (status.contains("complete")){
                    System.out.printf("%s : %sComplete%s%n", name, ascii.green, ascii.reset);
                }
            }
        } else {
            System.out.println("There are no tasks");
        }
    }
}
