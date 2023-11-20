package cams.ui.auth;

import cams.components.input.GetStringInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.AuthManager;
import cams.utils.Dismiss;
import cams.view.user.UserOptionsView;

public class GetEmailPasswordUI extends GetStringInput implements UI {

    private Navigation navigation;

    public GetEmailPasswordUI(Navigation navigation) {
        this.navigation = navigation;
    }

    @Override
    public void body() {
        AuthManager authManager = AuthManager.getInstance();

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
            navigation.navigateTo(new UserOptionsView(navigation));
            return;
        }
    }
}
