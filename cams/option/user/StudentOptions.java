package cams.option.user;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.SelectableOptions;

public class StudentOptions extends SelectableOptions {
    
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
