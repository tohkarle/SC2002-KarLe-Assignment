package cams.components.option;

import java.util.ArrayList;
import java.util.Arrays;

import cams.managers.UserManager;

public class UserInfoOptions extends Options {

    public UserInfoOptions(int userID, UserManager manager) {
        super.options = new ArrayList<String>(Arrays.asList(
            String.format("Name: %s", manager.getName(userID)),
            String.format("Email: %s", manager.getName(userID)),
            String.format("Faculty: %s", manager.getName(userID))
        ));
    }
}
