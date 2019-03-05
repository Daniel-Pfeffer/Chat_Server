package helper;

public enum Commands {

    REGISTER("[register]"),
    LOGIN("[login]"),
    KICK("[kick]"),
    CREATE("[login]"),
    DISBAND("[disband]"),
    INVITE("[invite]");

    private final String text;

    Commands(String command) {
        this.text = command;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
