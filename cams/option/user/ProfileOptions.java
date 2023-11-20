package cams.option.user;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableViewOnlyOptions;
import cams.manager.UserManager;

public class ProfileOptions extends DismissableViewOnlyOptions {

    public ProfileOptions() {
        UserManager userManager = UserManager.getInstance();
        
        super.setOptions( 
            new ArrayList<String>(Arrays.asList(
                String.format("Name: %s", userManager.getCurrentUser().getName()),
                String.format("Email: %s", userManager.getCurrentUser().getEmail()),
                String.format("Faculty: %s", userManager.getCurrentUser().getFaculty())
            ))
        );
    }
}
