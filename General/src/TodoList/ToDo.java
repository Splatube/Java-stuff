package TodoList;

import java.util.*;
import java.io.*;


public class ToDo {
    static Properties tasks = new Properties();
    static Scanner input = new Scanner(System.in);
    static String choice;
    static String propPath = "General/src/TodoList/tasks.properties";

    public static void printSeparator() {
        System.out.println("\n========================================\n");
    }

    private static void loadTasks() throws IOException {
        tasks.load(new FileInputStream(propPath));
    }

    public static void addTask() throws IOException {
        System.out.print("Enter task name: "); String name = input.nextLine();
        if (!tasks.containsKey(name)) {
            System.out.print("Enter priority level: "); String priority = input.nextLine();
            Variable.storeVariable(name, "incomplete");
            Variable.addValueToKey(name, "`" + priority);
            System.out.print("Add due date? "); choice = input.nextLine();
            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                System.out.print("Enter date: (dd/mm) "); choice = input.nextLine();
                Variable.addValueToKey(name, "%"+choice+"/2024");
            }
            System.out.printf(ansi.green + "Added '%s'%n" + ansi.reset, name);
        } else {
            System.out.printf(ansi.red + "'%s' already exists%n" + ansi.reset, name);
        }
    }

    public static void addTag() throws IOException {
        loadTasks();
        System.out.print("Which task? "); String name = input.nextLine();
        if (tasks.containsKey(name)) {
            System.out.print("Enter tag: "); String tag = "[" + input.nextLine() + "]";
            Variable.addValueToKey(name, tag);
            System.out.println(ansi.green + "Added tag" + ansi.reset);
        } else {
            System.out.printf("%sNo task named '%s'%s%n", ansi.red, name, ansi.reset);
        }
    }

    public static void completeTask() throws IOException {
        loadTasks();
        System.out.print("Which task? "); String name = input.nextLine();
        if (tasks.containsKey(name)) {
            if (tasks.getProperty(name).contains("incomplete")) {
                String complete = tasks.getProperty(name).replace("incomplete,", "complete,");
                Variable.storeVariable(name, complete);
                System.out.printf("%s'%s' completed!%s%n", ansi.green, name, ansi.reset);
            } else {
                System.out.println("Task is already completed!");
            }
        } else {
            System.out.println("Task does not exist!");
        }
    }

    public static void removeTask() throws IOException {
        loadTasks();
        System.out.print("Enter task name: "); String name = input.nextLine();
        if (tasks.containsKey(name)) {
            Variable.removeVariable(name);
            System.out.printf("%sRemoved '%s'%s%n", ansi.red, name, ansi.reset);
        } else {
            System.out.println("Task does not exist!");
        }

    }

    private static void displayTaskName(String name) {
        System.out.print(name);
    }

    private static void displayTaskPriority(String[] values) {
        System.out.print(ansi.orange + "(" + values[1].replace("`", "") + ") " + ansi.reset);
    }

    private static void displayTaskDueDate(String[] values) {
        if (values.length > 2 && values[2].startsWith("%")) {
            System.out.print(ansi.white + values[2].substring(1, values[2].length()-5) + " " + ansi.reset);
        }
    }

    private static void displayTaskTags(String[] values) {
        for (int i = 2; i < values.length; i++) {
            if (values[i].startsWith("[")) {
                System.out.print(ansi.blue + values[i] + " " + ansi.reset);
            }
        }
    }

    private static void displayTaskStatus(String status) {
        if (status.contains("incomplete")) {
            System.out.print(": " + ansi.red + "Incomplete" + ansi.reset);
        } else if (status.contains("complete")) {
            System.out.print(": " + ansi.green + "Complete" + ansi.reset);
        }
    }

    public static void displayTasks(Set<String> attributesToShow) throws IOException {
        loadTasks();
        printSeparator();
        if (!tasks.isEmpty()) {
            System.out.println(ansi.white + "To do list:" + ansi.reset);
            for (String name : tasks.stringPropertyNames()) {
                String status = tasks.getProperty(name);
                String[] values = status.split(",");
                if (attributesToShow.toString().toLowerCase().contains("priority")) {
                    displayTaskPriority(values);
                }
                if (attributesToShow.toString().toLowerCase().contains("date")) {
                    displayTaskDueDate(values);
                }
                if (attributesToShow.toString().toLowerCase().contains("tag")) {
                    displayTaskTags(values);
                }
                displayTaskName(name);
                if (attributesToShow.toString().toLowerCase().contains("status")) {
                    displayTaskStatus(status);
                }
                System.out.println(); // New line after each task
            }
        } else {
            System.out.println("There are no tasks");
        }
    }

    public static void searchTags() throws IOException {
        loadTasks();
        System.out.print("Filter tasks: "); String tag = input.nextLine();
        printSeparator();
        if (tag.matches("[0-3]")) {
            System.out.printf(ansi.white + "Priority %s tasks:%n" + ansi.reset, tag);
            for (String name : tasks.stringPropertyNames()) {
                String[] values = tasks.getProperty(name).split(",");
                if (values[1].replace("`", "").equals(tag)) {
                    System.out.print(ansi.orange + "(" + values[1].replace("`", "") + ") " + ansi.reset);
                    if (tasks.getProperty(name).contains("incomplete")) {
                        System.out.printf("%s : %sIncomplete%s%n", name, ansi.red, ansi.reset);
                    } else if (tasks.getProperty(name).contains("complete")) {
                        System.out.printf("%s : %sComplete%s%n", name, ansi.green, ansi.reset);
                    }
                }
            }
        } else if (tag.equalsIgnoreCase("complete") || tag.equalsIgnoreCase("completed") || tag.equalsIgnoreCase("incomplete")) {
            if (tag.equalsIgnoreCase("complete")) {
                System.out.println(ansi.white + "Completed tasks:" + ansi.reset);
            } else {
                System.out.println(ansi.white + "Incomplete tasks:" + ansi.reset);
            }
            for (String name : tasks.stringPropertyNames()) {
                String status = tasks.getProperty(name);
                String[] values = tasks.getProperty(name).split(",");

                if (tag.equalsIgnoreCase("complete")) {
                    System.out.printf("%s : %sComplete%s%n", name, ansi.green, ansi.reset);
                } else if (tag.equalsIgnoreCase("incomplete")) {
                    System.out.printf("%s : %sIncomplete%s%n", name, ansi.red, ansi.reset);
                }
            }
        } else if (tasks.values().toString().toLowerCase().contains(tag.toLowerCase())) {
            System.out.printf(ansi.white + "Tasks tagged \"%s\":%n" + ansi.reset, tag);
            for (String name : tasks.stringPropertyNames()) {
                String status = tasks.getProperty(name);
                String[] values = tasks.getProperty(name).split(",");

                // Logic for checking tag in task values
                if (tasks.getProperty(name).toLowerCase().contains("[" + tag.toLowerCase() + "]")) {
                    for (int i = 2; i < values.length; i++) {
                        if (values[i].equalsIgnoreCase("[" + tag + "]")) {
                            System.out.print(ansi.blue_bold + values[i] + " " + ansi.reset);
                        } else {
                            System.out.print(ansi.blue + values[i] + " " + ansi.reset);
                        }
                    }
                    if (status.contains("incomplete")) {
                        System.out.printf("%s : %sIncomplete%s%n", name, ansi.red, ansi.reset);
                    } else if (status.contains("complete")) {
                        System.out.printf("%s : %sComplete%s%n", name, ansi.green, ansi.reset);
                    }
                } else if (tag.matches("[0-3]") && tag.matches("\\d\\D")) {
                    if (Arrays.toString(values).contains(tag)) {
                        System.out.print(ansi.orange + "(" + values[1].replace("`", "") + ") " + ansi.reset);
                        if (status.contains("incomplete")) {
                            System.out.printf("%s : %sIncomplete%s%n", name, ansi.red, ansi.reset);
                        } else if (status.contains("complete")) {
                            System.out.printf("%s : %sComplete%s%n", name, ansi.green, ansi.reset);
                        }
                    }
                }
            }
        } else if (tasks.keySet().toString().contains(tag)) {
            String[] values = tasks.getProperty(tag).split(",");
            String status = tasks.getProperty(tag);
            if (status.contains("incomplete")) {
                System.out.printf("%s : %sIncomplete%s%n", tag, ansi.red, ansi.reset);
            } else if (status.contains("complete")) {
                System.out.printf("%s : %sComplete%s%n", tag, ansi.green, ansi.reset);
            }
        } else {
            System.out.println(ansi.red + "No tasks found" + ansi.reset);
        }
    }

    public static void sortTasks(String option) throws IOException {
        loadTasks();
        TreeMap<String, String> sortedTasks = new TreeMap<>();
        String sortType = "";
        printSeparator();
        if (option.equalsIgnoreCase("priority")) {
            System.out.println(ansi.white + "Tasks (priority ↓):" + ansi.reset);
            for (String name : tasks.stringPropertyNames()) {
                String[] values = tasks.getProperty(name).split(",");
                sortedTasks.put(values[1].replace("`", "") + name, tasks.getProperty(name));
                sortType = "priority";
            }
        } else if (option.equalsIgnoreCase("alphabetical")) {
            System.out.println(ansi.white + "Tasks (a-z ↓):" + ansi.reset);
            for (String name : tasks.stringPropertyNames()) {
                sortedTasks.put(name, tasks.getProperty(name));
            }
            sortType = "alphabet";
        } else if (option.equalsIgnoreCase("completion")) {
            System.out.println(ansi.white + "Tasks (completion ↓):" + ansi.reset);
            for (String name : tasks.stringPropertyNames()) {
                String[] values = tasks.getProperty(name).split(",");
                if (values[0].contains("complete")) {
                    sortedTasks.put("1" + name, tasks.getProperty(name));
                } else {
                    sortedTasks.put("0" + name, tasks.getProperty(name));
                }
            }
            sortType = "status";
        }

        for (Map.Entry<String, String> entry : sortedTasks.entrySet()) {
            String name = entry.getKey().replaceFirst("\\d", "");
            String status = entry.getValue();
            String[] values = entry.getValue().split(",");
            if (sortType.equals("priority")) {
                System.out.print(ansi.orange + "(" + values[1].replace("`", "") + ") " + ansi.reset);
            }
            if (status.contains("incomplete")) {
                System.out.printf("%s : %sIncomplete%s%n", name, ansi.red, ansi.reset);
            } else if (status.contains("complete")) {
                System.out.printf("%s : %sComplete%s%n", name, ansi.green, ansi.reset);
            }
        }
    }

    public static void clearTasks() throws IOException {
        System.out.print("Are you sure? "); choice = input.nextLine();
        if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
            Variable.clear();
            System.out.printf("%sCleared tasks%s%n", ansi.red, ansi.reset);
        }
    }

}
