package duke;

class DukeException extends Exception {

    DukeException(String errorMessage) {
        System.out.println("\t" + "____________________________________________________________");
        System.out.println("\t " + errorMessage);
        System.out.println("\t" + "____________________________________________________________");
    }
}
