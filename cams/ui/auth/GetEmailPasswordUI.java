package cams.ui.auth;

import cams.components.input.GetStringInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.AuthManager;
import cams.utils.Dismiss;

public class GetEmailPasswordUI extends GetStringInput implements UI {

    private Navigation navigation;
    private AuthManager authManager;

    public GetEmailPasswordUI(Navigation navigation, AuthManager authManager) {
        this.navigation = navigation;
        this.authManager = authManager;
    }

    @Override
    public void body() {
        String email = super.getValidString("Enter email: ");
        if (email.equals(Dismiss.stringOption())) { 
            navigation.dismissView();
            return; 
        }

        String password = super.getValidString("Enter password: ");
        if (password.equals(Dismiss.stringOption())) { 
            navigation.dismissView();
            return; 
        }

        if (authManager.logInSuccessful(email, password)) { 
            navigation.dismissView();
            navigation.navigateTo("user.UserOptionsView");
            return; 
        }
    }
}
