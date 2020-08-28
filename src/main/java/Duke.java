import java.util.Scanner;

public class Duke {

    private final String LOGO = " ____        _        \n"
                            + "|  _ \\ _   _| | _____ \n"
                            + "| | | | | | | |/ / _ \\\n"
                            + "| |_| | |_| |   <  __/\n"
                            + "|____/ \\__,_|_|\\_\\___|\n";

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
        System.out.println(LOGO);
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

    private void addToDo(String toDoName) {
        tasks[taskCount] = new Todo(toDoName);

        printDottedLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount]);
        System.out.println("Now you have " + ++taskCount + " tasks in the list.");
        printDottedLine();
    }

    private void addDeadline(String line) {
        String[] instructions = line.split("/by");
        String name = instructions[0];
        String by = instructions[1];
        tasks[taskCount] = new Deadline(name, by);

        printDottedLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount]);
        System.out.println("Now you have " + ++taskCount + " tasks in the list.");
        printDottedLine();
    }

    private void addEvent(String line) {
        String[] instructions = line.split("/at");
        String name = instructions[0];
        String at = instructions[1];
        tasks[taskCount] = new Event(name, at);

        printDottedLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount]);
        System.out.println("Now you have " + ++taskCount + " tasks in the list.");
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
            System.out.println((i + 1) + "." + tasks[i]);
        }
        printDottedLine();
    }

    private void markTaskAsDone(int taskNumber) {
        printDottedLine();
        tasks[taskNumber - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskNumber - 1]);
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
            case "todo":
                duke.addToDo(instructions[1]);
                break;
            case "deadline":
                duke.addDeadline(instructions[1]);
                break;
            case "event":
                duke.addEvent(instructions[1]);
                break;
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
