package cams.ui.auth;

import cams.components.input.GetStringInput;
import cams.interfaces.UI;
import cams.manager.AuthManager;
import cams.utils.Dismiss;
import cams.view.auth.RegisterTypeView;

public class GetRegisterInfoUI extends GetStringInput implements UI {

    private RegisterTypeView registerTypeView;
    private AuthManager authManager;

    public GetRegisterInfoUI(RegisterTypeView registerTypeView, AuthManager authManager) {
        this.registerTypeView = registerTypeView;
        this.authManager = authManager;
    }

    @Override
    public void body() {
        // Get email
        String email = super.getValidString("Enter email: ");
        if (email.equals(Dismiss.stringOption())) { 
            registerTypeView.getNavigation().dismissView(); 
            return;
        }

        // Get name
        String name = super.getValidString("Enter name: ");
        if (name.equals(Dismiss.stringOption())) { 
            registerTypeView.getNavigation().dismissView();
            return;
        }

        // Get faculty
        String faculty = super.getValidString("Enter faculty: ");
        if (faculty.equals(Dismiss.stringOption())) { 
            registerTypeView.getNavigation().dismissView();
            return;
        }

        // Get password
        String password = super.getValidString("Enter password: ");
        if (password.equals(Dismiss.stringOption())) { 
            registerTypeView.getNavigation().dismissView();
            return;
         }

        // Register user
        if (authManager.registerSuccessful(email, name, password, faculty, registerTypeView.getIsStaff())) { 
            registerTypeView.getNavigation().dismissView();
            registerTypeView.getNavigation().navigateTo("user.UserOptionsView");
            return; 
        }
    }
}
