import TodoList.*;

import java.io.*;
import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);
    static boolean exit = false;
    public static void main (String[]args) throws IOException {
        while (!exit) {
            System.out.print("What program would you like to use? "); String choice = input.nextLine();
            if (choice.toLowerCase().contains("todo")) {
                File taskList = new File("General/src/TodoList/tasks.properties");
                taskList.createNewFile();
                while (true) {
                    System.out.println("Options:");
                    System.out.println("1. Add task");
                    System.out.println("2. Add tag");
                    System.out.println("3. Complete task");
                    System.out.println("4. Remove task");
                    System.out.println("5. Show tasks");
                    System.out.println("6. Search tags");
                    System.out.println("7. Sort tasks");
                    System.out.println("8. Clear tasks");
                    System.out.println("9. Exit");
                    System.out.print("Choose an option: "); choice = input.nextLine();
                    switch (choice) {
                        case "1":
                            ToDo.addTask();
                            break;
                        case "2":
                            ToDo.addTag();
                            break;
                        case "3":
                            ToDo.completeTask();
                            break;
                        case "4":
                            ToDo.removeTask();
                            break;
                        case "5":
                            ToDo.displayTasks();
                            break;
                        case "6":
                            ToDo.searchTags();
                            break;
                        case "7":
                            System.out.println("Sort by:");
                            System.out.println("1. Priority");
                            System.out.println("2. Alphabetical");
                            System.out.println("3. Completion status");
                            System.out.print("Choose an option: "); String sortOption = input.nextLine();
                            switch (sortOption) {
                                case "1" -> ToDo.sortTasks("priority");
                                case "2" -> ToDo.sortTasks("alphabetical");
                                case "3" -> ToDo.sortTasks("completion");
                            }
                            break;
                        case "8":
                            ToDo.clearTasks();
                            break;
                        case "9":
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                }
            }
        }
    }
}
