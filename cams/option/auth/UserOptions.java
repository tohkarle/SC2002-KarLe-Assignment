package cams.option.auth;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableSelectableOptions;

public class UserOptions extends DismissableSelectableOptions {

    public UserOptions() {
        super.setOptions( new ArrayList<String>(Arrays.asList(
                "Staff",
                "Student"
            ))
        );
    }
}
