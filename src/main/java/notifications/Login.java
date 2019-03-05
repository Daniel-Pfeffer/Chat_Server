package notifications;

import entities.UserBO;

public class Login {
    private UserBO user;

    public Login(UserBO user) {
        this.user = user;
    }

    public String getMessage() {
        if (this.user == null) {
            return "Login failed";
        }
        return "Login Successful\n Welcome back " + user.getUsername() + ".";
    }

    public UserBO getUser() {
        return user;
    }
}
