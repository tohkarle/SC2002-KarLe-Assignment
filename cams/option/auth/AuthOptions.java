package cams.option.auth;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.SelectableOptions;

public class AuthOptions extends SelectableOptions {

    public AuthOptions() {
        super.setOptions( new ArrayList<String>(Arrays.asList(
                "Register",
                "Log in",
                "Terminate app"
            ))
        );
    }
}
