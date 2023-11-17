package cams.option.user;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.SelectableOptions;

public class StaffOptions extends SelectableOptions {

    public StaffOptions() {
        super.setOptions( 
            new ArrayList<String>(Arrays.asList(
                "View profile",
                "Change password",
                "View all camps",
                "Create camp",
                "View created camps",
                "Log out"
            ))
        );
    }
}
