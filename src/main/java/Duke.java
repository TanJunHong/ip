import java.util.Scanner;

public class Duke {

    private final String LOGO = " ____        _        \n"
                            + "|  _ \\ _   _| | _____ \n"
                            + "| | | | | | | |/ / _ \\\n"
                            + "| |_| | |_| |   <  __/\n"
                            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String dottedLine = "____________________________________________________________";

    private Task[] tasks;
    private int taskCount;

    public Duke() {
        tasks = new Task[100];
        taskCount = 0;
    }

    private void printWithIndent(String string) {

        System.out.println("\t" + string.replaceAll("\n", "\n\t"));
    }

    private void greetUser() {
        printWithIndent(dottedLine);
        printWithIndent(LOGO);
        printWithIndent(" Hello! I'm Duke");
        printWithIndent(" What can I do for you?");
        printWithIndent(dottedLine);
    }

    private void addTask(String taskName) {
        tasks[taskCount] = new Task(taskName);

        printWithIndent(dottedLine);
        printWithIndent(" added: " + tasks[taskCount++].getName());
        printWithIndent(dottedLine);
    }

    private void addToDo(String toDoName) {
        tasks[taskCount] = new Todo(toDoName);

        printWithIndent(dottedLine);
        printWithIndent(" Got it. I've added this task:");
        printWithIndent("   " + tasks[taskCount].toString());
        printWithIndent(" Now you have " + ++taskCount + " tasks in the list.");
        printWithIndent(dottedLine);
    }

    private void addDeadline(String line) {
        String[] instructions = line.split("/by");
        String name = instructions[0];
        String by = instructions[1];
        tasks[taskCount] = new Deadline(name, by);

        printWithIndent(dottedLine);
        printWithIndent(" Got it. I've added this task:");
        printWithIndent("   " + tasks[taskCount].toString());
        printWithIndent(" Now you have " + ++taskCount + " tasks in the list.");
        printWithIndent(dottedLine);
    }

    private void addEvent(String line) {
        String[] instructions = line.split("/at");
        String name = instructions[0];
        String at = instructions[1];
        tasks[taskCount] = new Event(name, at);

        printWithIndent(dottedLine);
        printWithIndent(" Got it. I've added this task:");
        printWithIndent("   " + tasks[taskCount].toString());
        printWithIndent(" Now you have " + ++taskCount + " tasks in the list.");
        printWithIndent(dottedLine);
    }

    private void echoUser(String line) {
        printWithIndent(dottedLine);
        printWithIndent(line);
        printWithIndent(dottedLine);
    }

    private void listTasks() {
        printWithIndent(dottedLine);
        printWithIndent(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            printWithIndent(" " + (i + 1) + "." + tasks[i]);
        }
        printWithIndent(dottedLine);
    }

    private void markTaskAsDone(int taskNumber) {
        printWithIndent(dottedLine);
        tasks[taskNumber - 1].markAsDone();
        printWithIndent(" Nice! I've marked this task as done:");
        printWithIndent("   " + tasks[taskNumber - 1].toString());
        printWithIndent(dottedLine);
    }

    private void exit() {
        printWithIndent(dottedLine);
        printWithIndent(" Bye. Hope to see you again soon!");
        printWithIndent(dottedLine);
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
