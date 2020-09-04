class ToDo extends Task {

    private final String LOGO = "[T]";

    ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return LOGO + super.toString();
    }
}
