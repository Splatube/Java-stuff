import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);
    static boolean exit = false;
    public static void main(String[] args) {
        /*System.out.print("What program would you like to use? " + ascii.cyan);
        String choice = input.nextLine();
        System.out.print(ascii.reset);
        if (choice.equalsIgnoreCase("todo list")) {
            while (!exit) {
                System.out.print("What would you like to do? (add|remove|complete|list) " + ascii.cyan);
                choice = input.nextLine();
                System.out.print(ascii.reset);
                if (choice.equalsIgnoreCase("exit")) {
                    break;
                } else if (choice.equalsIgnoreCase("add")) {
                    System.out.print("Enter task name: " + ascii.cyan);
                    choice = input.nextLine();
                    System.out.print(ascii.reset);
                    ToDo.addTask(choice);
                } else if (choice.equalsIgnoreCase("remove")) {
                    System.out.print("Which task? " + ascii.cyan);
                    choice = input.nextLine();
                    System.out.print(ascii.reset);
                    ToDo.removeTask(Integer.parseInt(choice));
                } else if (choice.equalsIgnoreCase("list")) {
                    ToDo.displayTasks();
                } else if (choice.equalsIgnoreCase("complete")) {
                    System.out.print("Which task? " + ascii.cyan);
                    choice = input.nextLine();
                    System.out.print(ascii.reset);
                    ToDo.completeTask(Integer.parseInt(choice));
                }
            }
        }*/
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
    }
}
