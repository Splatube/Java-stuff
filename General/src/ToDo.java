import java.util.*;
import java.io.*;

public class ToDo {
    static List<Object[]> Tasks = new ArrayList<>();

    public static void addTask(String name) {
        Tasks.addFirst(new Object[] {name, false});
    }

    public  static void completeTask(int num) {
        if (!((boolean) Tasks.get(num - 1)[1])) {
            Object[] task = Tasks.get(num - 1);
            task[1] = true;
            Tasks.set(num - 1, task);
            System.out.printf("%s'%s' completed!%s%n", ascii.green, Tasks.get(num-1)[0], ascii.reset);
        } else {
            System.out.println("Task is already completed!");
        }
    }

    public static void removeTask(int num) {
        System.out.printf("%sRemoved '%s'%s%n", ascii.red, Tasks.get(num - 1)[0], ascii.reset);
        Tasks.remove(num - 1);
    }

    public static void displayTasks() {
        if (!Tasks.isEmpty()) {
            System.out.println("To do list:");
            for (int num = 0; num < Tasks.size(); num++) {
                if (!((Boolean) Tasks.get(num)[1])) {
                    System.out.printf("%s) %s : %sIncomplete%s%n", num+1, Tasks.get(num)[0].toString(), ascii.red, ascii.reset);
                } else {
                    System.out.printf("%s) %s : %sComplete%s%n", num+1, Tasks.get(num)[0].toString(), ascii.green, ascii.reset);
                }
            }
        } else {
            System.out.println("There are no tasks");
        }
    }
}
