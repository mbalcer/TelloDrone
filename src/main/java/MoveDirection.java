public enum MoveDirection {
    UP("up"),
    DOWN("down"),
    FORWARD("forward"),
    BACK("back"),
    LEFT("left"),
    RIGHT("right");

    private String command;

    MoveDirection(String direction) {
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
