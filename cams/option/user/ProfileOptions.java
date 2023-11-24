package cams.option.user;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableViewOnlyOptions;
import cams.manager.UserManager;

/**
 * Options object for user profile options in the ProfileView
 */
public class ProfileOptions extends DismissableViewOnlyOptions {

    /**
     * Initialize the user profile options
     */
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
