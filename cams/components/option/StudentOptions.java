package cams.components.option;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentOptions extends Options {
    
    public StudentOptions() {
        super.options = new ArrayList<>(Arrays.asList(
            "View profile",
            "Change password",
            "View all camps",
            "View registered camps",
            "Register for camps",
            "Log out"
        ));
    }
}
