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
                File taskList = new File("General/src/TodoList/tasks.properties");
                taskList.createNewFile();
                while (!exit) {
                    System.out.print("What would you like to do? ");
                    choice = input.nextLine();
                    if (choice.equalsIgnoreCase("clear")) {ToDo.clearTasks();}
                    else if (choice.equalsIgnoreCase("search")) {ToDo.searchTags();}
                    else if (choice.equalsIgnoreCase("tag")) {ToDo.addTag();}
                    else if (choice.equalsIgnoreCase("exit")) {break;}
                    else if (choice.equalsIgnoreCase("add")) {ToDo.addTask();}
                    else if (choice.equalsIgnoreCase("remove")) {ToDo.removeTask();}
                    else if (choice.equalsIgnoreCase("list")) {ToDo.displayTasks();}
                    else if (choice.equalsIgnoreCase("complete")) {ToDo.completeTask();}
                    else {System.out.println(ascii.red + "Invalid option" + ascii.reset);}
                }
            } else if (choice.equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println(ascii.red + "Invalid program" + ascii.reset);
            }
        }
    }
}
