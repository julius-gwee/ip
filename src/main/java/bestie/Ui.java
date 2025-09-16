package bestie;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("____________________________________________________________\n"
                + " heyyy I'm Bestie\n"
                + " whatsup?\n"
                + "____________________________________________________________");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Bestie couldn't load tasks. Starting fresh.");
    }

    public void showError(String message) {
        System.out.println(" " + message);
    }

    public void showBye() {
        System.out.println(" Bye bestie~ Keep slayin and prayin!");
    }

    public void showList(TaskList tasks) {
        System.out.println(" Here is your task list bestie!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }

    public void showMark(Task task) {
        System.out.println(" YAYYY ive marked: \n  " + task);
    }

    public void showUnmark(Task task) {
        System.out.println(" no worries! ive unmarked: \n  " + task);
    }

    public void showDelete(Task task, int size) {
        System.out.println(" Noted. I've removed this task:\n  " + task
                + "\n Now you have " + size + " tasks in the list.");
    }

    public void showAdd(Task task, int size) {
        System.out.println(" aightt ive added this task: \n  " + task
                + "\n now you have " + size + " tasks in your list!!");
    }
}
