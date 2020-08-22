import java.util.Scanner;

public class Duke {
    private Task[] tasks;
    private int taskCount;

    public Duke() {
        tasks = new Task[100];
        taskCount = 0;
    }

    private void printDottedLine() {
        System.out.println("____________________________________________________________");
    }

    private void greetUser() {
        printDottedLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printDottedLine();
    }

    private void addTask(String taskName) {
        tasks[taskCount] = new Task(taskName);

        printDottedLine();
        System.out.println("added: " + tasks[taskCount++].getName());
        printDottedLine();
    }

    private void echoUser(String line) {
        printDottedLine();
        System.out.println(line);
        printDottedLine();
    }

    private void listTasks() {
        printDottedLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].getName());
        }
        printDottedLine();
    }

    private void markTaskAsDone(int taskNumber) {
        printDottedLine();
        tasks[taskNumber - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + tasks[taskNumber - 1].getStatusIcon() + "] " + tasks[taskNumber - 1].getName());
        printDottedLine();
    }

    private void exit() {
        printDottedLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDottedLine();
        System.exit(0);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Duke duke = new Duke();
        duke.greetUser();

        String line = in.nextLine();
        while (true) {
            String[] instructions = line.split(" ", 2);
            switch (instructions[0]) {
            case "list":
                duke.listTasks();
                break;
            case "done":
                duke.markTaskAsDone(Integer.parseInt(instructions[1]));
                break;
            case "bye":
                duke.exit();
                break;
            default:
                //duke.echoUser(line);
                duke.addTask(line);
                break;
            }
            line = in.nextLine();
        }
    }
}
