package notifications;

import entities.GroupBO;
import helper.Commands;

public class Information {
    private String message;

    public Information(Commands cmd) {
    }

    public Information(Commands cmd, GroupBO group) {
        this.message = group.getAdmin().getUsername() + " has successfully created the Group " + group.getName();
    }

    public String getMessage() {
        return message;
    }
}
