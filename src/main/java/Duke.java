import java.util.Scanner;

public class Duke {
    private Task[] tasks;
    private int taskCount;

    public Duke() {
        tasks = new Task[100];
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
        tasks[taskCount++] = new Task(task);

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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].getName());
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
            //duke.echo(line);
            switch (line) {
            case "list":
                duke.list();
                break;
            default:
                if (line.startsWith("done")) {
                    int taskNumber = Integer.parseInt(line.split(" ")[1]);
                    System.out.println("____________________________________________________________");
                    duke.tasks[taskNumber - 1].markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + duke.tasks[taskNumber - 1].getStatusIcon() + "] " + duke.tasks[taskNumber - 1].getName());
                    System.out.println("____________________________________________________________");
                }
                else {
                    duke.add(line);
                }
                break;
            }
            line = in.nextLine();
        }

        duke.exit();
    }
}
