package notifications;

public class Greeter {
    private final String userToGreet;

    public Greeter(String userToGreet) {
        this.userToGreet = userToGreet;
    }

    public String getMessage() {
        return "Welcome " + userToGreet;
    }
}
