import java.util.Scanner;

public class Bestie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Introduction
        System.out.println("____________________________________________________________\n"
                + " Hello! I'm Bestie\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n");

        boolean isExit = false;
        while (!isExit) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("____________________________________________________________\n"
                        + " Bye. Hope to see you again soon!\n"
                        + "____________________________________________________________\n");
                isExit = true;
            } else {
                System.out.println("____________________________________________________________\n"
                        + " " + input + "\n"
                        + "____________________________________________________________\n");
            }
        }
    }
}
