package notifications;

import entities.UserBO;

public class Register {
    private UserBO user;

    public Register(UserBO user) {
        this.user = user;
    }

    public String getMessage() {
        if (this.user == null) {
            return "Register failed";
        }
        return "Register successful\nWelcome " + user.getUsername() + ".";
    }

    public UserBO getUser() {
        return user;
    }
}
