package cams.components.option;

import java.util.ArrayList;
import java.util.Arrays;

public class AuthenticateOptions extends Options {
    public AuthenticateOptions() {
        super.options = new ArrayList<>(Arrays.asList(
            "Register",
            "Log in",
            "Terminate app"
        ));
    }
}
