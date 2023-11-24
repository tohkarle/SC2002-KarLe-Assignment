package cams.option.user;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.SelectableOptions;

/**
 * Options object for Student options in the StudentView
 */
public class StudentOptions extends SelectableOptions {
    
    /**
     * Initialize the student options
     */
    public StudentOptions() {
        super.setOptions( 
            new ArrayList<String>(Arrays.asList(
                "View profile",
                "Change password",
                "View all camps",
                "View registered camps",
                "Register for camps",
                "Log out"
            ))
        );
    }
}
