package cams.components.option;

import java.util.ArrayList;
import java.util.Arrays;

import cams.manager.UserManager;

public class UserInfoOptions extends Options {

    public UserInfoOptions(int userID, UserManager manager) {
        super.setOptions( new ArrayList<String>(Arrays.asList(
                String.format("Name: %s", manager.getName(userID)),
                String.format("Email: %s", manager.getName(userID)),
                String.format("Faculty: %s", manager.getName(userID))
            ))
        );
    }
}
