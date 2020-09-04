package duke.exception;

public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        System.out.println("\t" + "____________________________________________________________");
        System.out.println("\t " + errorMessage);
        System.out.println("\t" + "____________________________________________________________");
    }
}
