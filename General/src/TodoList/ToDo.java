package TodoList;

import java.util.*;
import java.io.*;

public class ToDo {
    static List<Object[]> Tasks = new ArrayList<>();

    public static void loadTasks() throws IOException {
        Tasks.clear();
        Properties prop = Variable.loadProperties();
        for (String name : prop.stringPropertyNames()) {
            String status = prop.getProperty(name);
            Tasks.add(new Object[] {name, status});
        }
    }

    public static void addTask(String name) throws IOException {
        Tasks.add(new Object[] {name, "incomplete"});
        Variable.storeVariable(name, "incomplete");
        System.out.printf(ascii.green + "Added '%s'%n" + ascii.reset, name);
    }

    public static void completeTask(int num) throws IOException {
        if (!"complete".equals(Tasks.get(num - 1)[1])) {
            Object[] task = Tasks.get(num - 1);
            task[1] = "complete";
            Tasks.set(num - 1, task);
            Variable.storeVariable((String) task[0], "complete");
            System.out.printf("%s'%s' completed!%s%n", ascii.green, task[0], ascii.reset);
        } else {
            System.out.println("Task is already completed!");
        }
    }

    public static void removeTask(int num) throws IOException {
        System.out.printf("%sRemoved '%s'%s%n", ascii.red, Tasks.get(num - 1)[0], ascii.reset);
        Variable.removeVariable((String) Tasks.get(num - 1)[0]);
        Tasks.remove(num - 1);
    }

    public static void displayTasks() throws IOException {
        loadTasks(); // Load tasks from properties file
        if (!Tasks.isEmpty()) {
            System.out.println("To do list:");
            for (int num = 0; num < Tasks.size(); num++) {
                String status = (String) Tasks.get(num)[1];
                if ("incomplete".equals(status)) {
                    System.out.printf("%s) %s : %sIncomplete%s%n", num + 1, Tasks.get(num)[0].toString(), ascii.red, ascii.reset);
                } else {
                    System.out.printf("%s) %s : %sComplete%s%n", num + 1, Tasks.get(num)[0].toString(), ascii.green, ascii.reset);
                }
            }
        } else {
            System.out.println("There are no tasks");
        }
    }
}
