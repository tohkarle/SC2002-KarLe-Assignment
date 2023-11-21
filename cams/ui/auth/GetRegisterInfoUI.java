package cams.ui.auth;

import cams.components.input.GetStringInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.AuthManager;
import cams.utils.Dismiss;
import cams.view.user.UserOptionsView;

public class GetRegisterInfoUI extends GetStringInput implements UI {

    private Navigation navigation;
    private boolean isStaff;

    public GetRegisterInfoUI(Navigation navigation, boolean isStaff) {
        this.navigation = navigation;
        this.isStaff = isStaff;
    }

    @Override
    public void body() {
        AuthManager authManager = AuthManager.getInstance();
        
        // Get email
        String email = super.getValidString("Enter email: ");
        if (email.equals(Dismiss.stringOption())) { 
            navigation.dismissView(); 
            return;
        }

        // Get name
        String name = super.getValidString("Enter name: ");
        if (name.equals(Dismiss.stringOption())) { 
            navigation.dismissView();
            return;
        }

        // Get faculty
        String faculty = super.getValidString("Enter faculty: ");
        if (faculty.equals(Dismiss.stringOption())) { 
            navigation.dismissView();
            return;
        }

        // Get password
        String password = super.getValidString("Enter password: ");
        if (password.equals(Dismiss.stringOption())) { 
            navigation.dismissView();
            return;
         }

        // Register user
        if (authManager.registerSuccessful(email, name, password, faculty, isStaff)) { 
            navigation.dismissView();
            navigation.navigateTo(new UserOptionsView(navigation));
            return;
        }
    }
}
