package cams.components.option;

import java.util.ArrayList;
import java.util.Arrays;

public class StaffOptions extends Options {

    public StaffOptions() {
        super.options = new ArrayList<>(Arrays.asList(
            "View profile",
            "Change password",
            "View all camps",
            "Create camp",
            "View created camps",
            "Log out"
        ));
    }
}
