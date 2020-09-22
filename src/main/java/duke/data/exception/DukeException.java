package duke.data.exception;

/**
 * Throws exceptions relating to Duke.
 */
public class DukeException extends Exception {

    private static final String OOPS = "☹ OOPS!!! ";

    /**
     * Initializes message to throw.
     *
     * @param message Exception message to show.
     */
    public DukeException(String message) {
        super(OOPS + message);
    }
}
