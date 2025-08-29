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
                    System.out.println(" Here is your task list bestie!");
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

                case "todo":
                    Task todo = new Todo(parts[1]);
                    tasks[taskCount++] = todo;
                    printAdd(todo, taskCount);
                    break;

                case "deadline":
                    String[] deadlineParts = parts[1].split("/by", 2);
                    Task deadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                    tasks[taskCount++] = deadline;
                    printAdd(deadline, taskCount);
                    break;

                case "event":
                    String[] eventParts = parts[1].split("/from", 2);
                    String descr = eventParts[0].trim();
                    String[] fromTo = eventParts[1].split("/to", 2);
                    Task event = new Event(descr, fromTo[0].trim(), fromTo[1].trim());
                    tasks[taskCount++] = event;
                    printAdd(event, taskCount);
                    break;

                default:
                    System.out.println("____________________________________________________________\n"
                            + "im sorry bestie but i have no clue what you are sayin \n"
                            + "____________________________________________________________\n");
                    break;
            }
        }
    }

    private static void printAdd(Task task, int count) {
        System.out.println("____________________________________________________________\n"
                + " aightt ive added this task: \n" + "  " + task + "\n"
                + " now you have " + count + " tasks in your list!!" + "\n"
                + "____________________________________________________________\n");
    }
}
