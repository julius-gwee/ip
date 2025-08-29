import java.util.ArrayList;
import java.util.Scanner;

public class Bestie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        // Introduction
        System.out.println("____________________________________________________________\n"
                + " heyyy I'm Bestie\n"
                + " whatsup?\n"
                + "____________________________________________________________\n");

        boolean isExit = false;
        while (!isExit) {
            String input = sc.nextLine();

            try {
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
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(" " + (i + 1) + "." + tasks.get(i));
                        }
                        System.out.println("____________________________________________________________");
                        break;

                    case "mark":
                        if (parts.length < 2) {
                            throw new BestieException("lmk which you want to mark!");
                        }
                        int markIndex = Integer.parseInt(parts[1]) - 1;
                        tasks.get(markIndex).markAsDone();
                        System.out.println("____________________________________________________________\n"
                                + " YAYYY ive marked: \n" + "  " + tasks.get(markIndex) + "\n"
                                + "____________________________________________________________\n");
                        break;

                    case "unmark":
                        if (parts.length < 2) {
                            throw new BestieException("lmk which you want to UNmark!");
                        }
                        int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                        tasks.get(unmarkIndex).markAsUndone();
                        System.out.println("____________________________________________________________\n"
                                + " no worries! ive unmarked: \n" + "  " + tasks.get(unmarkIndex) + "\n"
                                + "____________________________________________________________\n");
                        break;

                    case "delete":
                        if (parts.length < 2) {
                            throw new BestieException("lmk which you want to delete!");
                        }
                        int delIndex = Integer.parseInt(parts[1]) - 1;
                        Task removed = tasks.remove(delIndex);
                        System.out.println("____________________________________________________________\n"
                                + " okay g ive removed this task: \n" + "  " + removed + "\n"
                                + " now you have " + tasks.size() + " tasks in your list!!" + "\n"
                                + "____________________________________________________________\n");
                        break;

                    case "todo":
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new BestieException("why is your todo empty >:(");
                        }
                        Task todo = new Todo(parts[1].trim());
                        tasks.add(todo);
                        printAdd(todo, tasks.size());
                        break;

                    case "deadline":
                        if (parts.length < 2 || !parts[1].contains("/by")) {
                            throw new BestieException("let me know when you want to do this by!");
                        }
                        String[] deadlineParts = parts[1].split("/by", 2);
                        if (deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                            throw new BestieException("give me the task you want to do as well as a date and time!");
                        }
                        Task deadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                        tasks.add(deadline);
                        printAdd(deadline, tasks.size());
                        break;

                    case "event":
                        if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
                            throw new BestieException("Event must have /from and /to times!");
                        }
                        String[] eventParts = parts[1].split("/from", 2);
                        String descr = eventParts[0].trim();
                        String[] fromTo = eventParts[1].split("/to", 2);
                        if (descr.isEmpty() || fromTo[0].trim().isEmpty() || fromTo[1].trim().isEmpty()) {
                            throw new BestieException("PLEASEEE give me and event description and time");
                        }
                        Task event = new Event(descr, fromTo[0].trim(), fromTo[1].trim());
                        tasks.add(event);
                        printAdd(event, tasks.size());
                        break;

                    default:
                        throw new BestieException("OOPS!!! I'm sorry, but I don't know what you are sayin :(");
                }
            } catch (BestieException e) {
                System.out.println("____________________________________________________________\n"
                        + " " + e.getMessage() + "\n"
                        + "____________________________________________________________\n");
            } catch (Exception e) {
                System.out.println("____________________________________________________________\n"
                        + " Something feels off! Check your input again dear!\n"
                        + "____________________________________________________________\n");
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
