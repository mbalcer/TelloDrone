public enum RotateDirection {
    CLOCKWISE("cw"),
    COUNTER_CLOCKWISE("ccw");

    private String command;

    RotateDirection(String direction) {
        this.command = direction;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return command;
    }
}
