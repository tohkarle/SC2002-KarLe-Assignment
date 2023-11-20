package cams.view.auth;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.auth.GetRegisterInfoUI;
import cams.utils.Page;

public class RegisterView implements View {

    private Navigation navigation;
    private boolean isStaff;

    public RegisterView(Navigation navigation, boolean isStaff) {
        this.navigation = navigation;
        this.isStaff = isStaff;
    }

    public void render() {
        // Enter user info
        Page.header("Please enter your email, name, faculty and password."); 
        UI getRegisterInfoUI = new GetRegisterInfoUI(navigation, isStaff);
        getRegisterInfoUI.body();
    }
}
