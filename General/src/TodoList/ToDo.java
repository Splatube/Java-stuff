package TodoList;

import java.util.*;
import java.io.*;

public class ToDo {
    static Properties tasks = new Properties();
    static Scanner input = new Scanner(System.in);
    static String choice;
    static String propPath = "General/src/TodoList/tasks.properties";

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
        if (!tasks.containsKey(name)) {
            System.out.print("Enter priority level: ");
            String priority = input.nextLine();
            Variable.storeVariable(name, "incomplete");
            Variable.addValueToKey(name, "`" + priority);
            System.out.printf(ascii.green + "Added '%s'%n" + ascii.reset, name);
        } else {
            System.out.printf(ascii.red + "'%s' already exists%n" + ascii.reset, name);
        }
    }

    public static void addTag() throws IOException {
        loadTasks();
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
        loadTasks();
        System.out.print("Which task? ");
        String name = input.nextLine();
        if (tasks.containsKey(name)) {
            if (tasks.getProperty(name).contains("incomplete")) {
                String complete = tasks.getProperty(name).replace("incomplete,", "complete,");
                Variable.storeVariable(name, complete);
                System.out.printf("%s'%s' completed!%s%n", ascii.green, name, ascii.reset);
            } else {
                System.out.println("Task is already completed!");
            }
        } else {
            System.out.println("Task does not exist!");
        }
    }

    public static void removeTask() throws IOException {
        loadTasks();
        System.out.print("Enter task name: ");
        String name = input.nextLine();
        if (tasks.containsKey(name)) {
            Variable.removeVariable(name);
            System.out.printf("%sRemoved '%s'%s%n", ascii.red, name, ascii.reset);
        } else {
            System.out.println("Task does not exist!");
        }
    }

    public static void sortTasks(String option) throws IOException {
        loadTasks();
        TreeMap<String, String> sortedTasks = new TreeMap<>();

        if (option.equalsIgnoreCase("priority")) {
            for (String name : tasks.stringPropertyNames()) {
                String[] values = tasks.getProperty(name).split(",");
                sortedTasks.put(values[1].replace("`", "") + name, tasks.getProperty(name));
            }
        } else if (option.equalsIgnoreCase("alphabetical")) {
            for (String name : tasks.stringPropertyNames()) {
                sortedTasks.put(name, tasks.getProperty(name));
            }
        } else if (option.equalsIgnoreCase("completion")) {
            for (String name : tasks.stringPropertyNames()) {
                String[] values = tasks.getProperty(name).split(",");
                if (values[0].contains("complete")) {
                    sortedTasks.put("1" + name, tasks.getProperty(name));
                } else {
                    sortedTasks.put("0" + name, tasks.getProperty(name));
                }
            }
        }

        for (Map.Entry<String, String> entry : sortedTasks.entrySet()) {
            String name = entry.getKey().replaceFirst("\\d", "");
            String status = entry.getValue();
            String[] values = entry.getValue().split(",");
            System.out.print(ascii.orange + "(" + values[1].replace("`", "") + ") " + ascii.reset);
            if (status.contains("[")) {
                for (int i = 2; i < values.length; i++) {
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

    public static void searchTags() throws IOException {
        loadTasks();
        System.out.print("Filter tasks: ");
        String tag = input.nextLine();
        if (tag.matches("[0-3]")) {
            for (String name : tasks.stringPropertyNames()) {
                String[] values = tasks.getProperty(name).split(",");
                if (values[1].replace("`", "").equals(tag)) {
                    System.out.print(ascii.orange + "(" + values[1].replace("`", "") + ") " + ascii.reset);
                    if (tasks.getProperty(name).contains("[")) {
                        for (int i = 2; i < values.length; i++) {
                            System.out.print(ascii.blue + values[i] + " " + ascii.reset);
                        }
                    }
                    if (tasks.getProperty(name).contains("incomplete")) {
                        System.out.printf("%s : %sIncomplete%s%n", name, ascii.red, ascii.reset);
                    } else if (tasks.getProperty(name).contains("complete")) {
                        System.out.printf("%s : %sComplete%s%n", name, ascii.green, ascii.reset);
                    }
                }
            }
        } else if (tasks.values().toString().toLowerCase().contains(tag.toLowerCase())) {
            for (String name : tasks.stringPropertyNames()) {
                String status = tasks.getProperty(name);
                String[] values = tasks.getProperty(name).split(",");
                if (tag.equalsIgnoreCase("complete")) {
                    if (!status.contains("incomplete")) {
                        System.out.print(ascii.orange + "(" + values[1].replace("`", "") + ") " + ascii.reset);
                        if (status.contains("[")) {
                            for (int i = 2; i < values.length; i++) {
                                System.out.print(ascii.blue + values[i] + " " + ascii.reset);
                            }
                        }
                        System.out.printf("%s : %sComplete%s%n", name, ascii.green, ascii.reset);
                    }
                } else if (tag.equalsIgnoreCase("incomplete")) {
                    if (status.contains("incomplete")) {
                        System.out.print(ascii.orange + "(" + values[1].replace("`", "") + ") " + ascii.reset);
                        if (status.contains("[")) {
                            for (int i = 2; i < values.length; i++) {
                                System.out.print(ascii.blue + values[i] + " " + ascii.reset);
                            }
                        }
                        System.out.printf("%s : %sIncomplete%s%n", name, ascii.red, ascii.reset);
                    }
                } else if (tasks.getProperty(name).toLowerCase().contains("[" + tag.toLowerCase() + "]")) {
                    System.out.print(ascii.orange + "(" + values[1].replace("`", "") + ") " + ascii.reset);
                    for (int i = 2; i < values.length; i++) {
                        if (values[i].equalsIgnoreCase("[" + tag + "]")) {
                            System.out.print(ascii.blue_bold + values[i] + " " + ascii.reset);
                        } else {
                            System.out.print(ascii.blue + values[i] + " " + ascii.reset);
                        }
                    }
                    if (status.contains("incomplete")) {
                        System.out.printf("%s : %sIncomplete%s%n", name, ascii.red, ascii.reset);
                    } else if (status.contains("complete")) {
                        System.out.printf("%s : %sComplete%s%n", name, ascii.green, ascii.reset);
                    }
                } else if (tag.matches("[0-3]") && tag.matches("\\d\\D")) {
                    if (Arrays.toString(values).contains(tag)) {
                        System.out.print(ascii.orange + "(" + values[1].replace("`", "") + ") " + ascii.reset);
                        for (int i = 2; i < values.length; i++) {
                            if (values[i].equalsIgnoreCase("[" + tag + "]")) {
                                System.out.print(ascii.blue_bold + values[i] + " " + ascii.reset);
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
            }
        } else if (tasks.keySet().toString().contains(tag)) {
            String[] values = tasks.getProperty(tag).split(",");
            String status = tasks.getProperty(tag);
            if (tasks.getProperty(tag).contains("[")) {
                for (int i = 2; i < values.length; i++) {
                    System.out.print(ascii.blue + values[i ] + " " + ascii.reset);
                }
            }
            System.out.print(ascii.orange + "(" + values[1].replace("`", "") + ") " + ascii.reset);
            if (status.contains("incomplete")) {
                System.out.printf("%s : %sIncomplete%s%n", tag, ascii.red, ascii.reset);
            } else if (status.contains("complete")) {
                System.out.printf("%s : %sComplete%s%n", tag, ascii.green, ascii.reset);
            }
        } else {
            System.out.println(ascii.red + "No tasks found" + ascii.reset);
        }
    }

    public static void displayTasks() throws IOException {
        loadTasks();
        if (!tasks.isEmpty()) {
            System.out.println(ascii.white + "To do list:" + ascii.reset);
            for (String name : tasks.stringPropertyNames()) {
                String status = tasks.getProperty(name);
                String[] values = tasks.getProperty(name).split(",");
                System.out.print(ascii.orange + "(" + values[1].replace("`", "") + ") " + ascii.reset);
                if (status.contains("[")) {
                    for (int i = 2; i < values.length; i++) {
                        System.out.print(ascii.blue + values[i] + " " + ascii.reset);
                    }
                }
                if (status.contains("incomplete")) {
                    System.out.printf("%s : %sIncomplete%s%n", name, ascii.red, ascii.reset);
                } else if (status.contains("complete")) {
                    System.out.printf("%s : %sComplete%s%n", name, ascii.green, ascii.reset);
                }
            }
        } else {
            System.out.println("There are no tasks");
        }
    }
}
