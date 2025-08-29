import java.util.Scanner;

public class Bestie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        // Introduction
        System.out.println("____________________________________________________________\n"
                + " heyyy I'm Bestie\n"
                + " whatsup?\n"
                + "____________________________________________________________\n");

        boolean isExit = false;
        while (!isExit) {
            String input = sc.nextLine();
            String[] parts = input.split(" ", 2); // split input command
            String command = parts[0];

            switch (command) {
                case "bye":
                    System.out.println("____________________________________________________________\n"
                            + " Bye bestie~ Keep slayin and prayin!\n"
                            + "____________________________________________________________\n");
                    isExit = true;
                    break;

                case "list":
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here is your to-do list bestie!");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + "." + tasks[i]);
                    }
                    System.out.println("____________________________________________________________");
                    break;

                case "mark":
                    int markIndex = Integer.parseInt(parts[1]) - 1;
                    tasks[markIndex].markAsDone();
                    System.out.println("____________________________________________________________\n"
                            + " YAYYY ive marked: \n" + "  " + tasks[markIndex] + "\n"
                            + "____________________________________________________________\n");
                    break;

                case "unmark":
                    int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                    tasks[unmarkIndex].markAsUndone();
                    System.out.println("____________________________________________________________\n"
                            + " no worries! ive unmarked: \n" + "  " + tasks[unmarkIndex] + "\n"
                            + "____________________________________________________________\n");
                    break;

                default:
                    tasks[taskCount] = new Task(input);
                    taskCount++;
                    System.out.println("____________________________________________________________\n"
                            + " ive added " + input + " to your to-do list :)\n"
                            + "____________________________________________________________\n");
                    break;
            }
        }
    }
}
