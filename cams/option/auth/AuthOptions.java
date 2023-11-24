package cams.option.auth;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.SelectableOptions;

/**
 * Options object for authentication in the RootView
 * It is not dismissable as it is the first page of the app
 */
public class AuthOptions extends SelectableOptions {

    /**
     * Initialize the authentication options
     */
    public AuthOptions() {
        super.setOptions( new ArrayList<String>(Arrays.asList(
                "Register",
                "Log in",
                "Terminate app"
            ))
        );
    }
}
