package cams.ui.auth;

import cams.components.input.GetStringInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.AuthManager;
import cams.utils.Dismiss;
import cams.view.user.UserOptionsView;

/**
 * UI object for Get Email Password (login) page
 */
public class GetEmailPasswordUI extends GetStringInput implements UI {

    private Navigation navigation;

    /**
     * Initialize the GetEmailPasswordUI
     * @param navigation Navigation object used to control navigation of the application
     */
    public GetEmailPasswordUI(Navigation navigation) {
        this.navigation = navigation;
    }

    /**
     * Executes user interaction logic for entering login email and password
     */
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
