import TodoList.*;

import java.io.*;
import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);
    static boolean exit = false;
    public static void main (String[]args) throws IOException {
        while (!exit) {
            System.out.print("What program would you like to use? ");
            String choice = input.nextLine();
            if (choice.toLowerCase().contains("todo")) {
                while (!exit) {
                    System.out.print("What would you like to do? (add|remove|complete|list) ");
                    choice = input.nextLine();
                    if (choice.equalsIgnoreCase("clear")) {
                        ToDo.clearTasks();
                    } else if (choice.equalsIgnoreCase("exit")) {
                        break;
                    } else if (choice.equalsIgnoreCase("add")) {
                        System.out.print("Enter task name: ");
                        choice = input.nextLine();
                        ToDo.addTask(choice);
                    } else if (choice.equalsIgnoreCase("remove")) {
                        System.out.print("Which task? ");
                        choice = input.nextLine();
                        ToDo.removeTask(choice);
                    } else if (choice.equalsIgnoreCase("list")) {
                        ToDo.displayTasks();
                    } else if (choice.equalsIgnoreCase("complete")) {
                        System.out.print("Which task? ");
                        choice = input.nextLine();
                        ToDo.completeTask(choice);
                    } else {
                        System.out.println(ascii.red + "Invalid option" + ascii.reset);
                    }
                }
            } else if (choice.equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println(ascii.red + "Invalid program" + ascii.reset);
            }
        }
    }
}
