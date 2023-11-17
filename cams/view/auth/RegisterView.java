package cams.view.auth;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.utils.Page;

public class RegisterView extends View {

    // No options for this view

    // UIs for this view:
    private UI getRegisterInfoUI;

    public RegisterView(Navigation navigation) {
        super(navigation);
    }

    public void render() {
        // Enter user info
        Page.header("Please enter your email, name, faculty and password."); 
        getRegisterInfoUI = super.getUI("auth.GetRegisterInfoUI");
        getRegisterInfoUI.body();
    }
}
