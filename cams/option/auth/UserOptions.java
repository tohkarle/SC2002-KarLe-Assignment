package cams.option.auth;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableSelectableOptions;

/**
 * Options object for user type selection in the RegisterTypeView
 */
public class UserOptions extends DismissableSelectableOptions {

    /**
     * Initialize the user selection options
     */
    public UserOptions() {
        super.setOptions( new ArrayList<String>(Arrays.asList(
                "Staff",
                "Student"
            ))
        );
    }
}
