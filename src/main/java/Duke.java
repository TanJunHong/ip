import java.util.Scanner;

public class Duke {
    private String[] tasks;
    private int taskCount;

    public Duke() {
        tasks = new String[100];
        taskCount = 0;
    }

    private void greet() {
        System.out.println("____________________________________________________________");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private void add(String task) {
        tasks[taskCount++] = task;

        System.out.println("____________________________________________________________");
        System.out.println("added: " + task);
        System.out.println("____________________________________________________________");
    }

    private void echo(String line) {
        System.out.println("____________________________________________________________");
        System.out.println(line);
        System.out.println("____________________________________________________________");
    }

    private void list() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    private void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Duke duke = new Duke();

        duke.greet();

        String line = in.nextLine();
        while (!line.equals("bye")) {
            duke.echo(line);
            line = in.nextLine();
        }

        duke.exit();
    }
}
