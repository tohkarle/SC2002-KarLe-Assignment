package cams.view.auth;

import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.auth.GetRegisterInfoUI;
import cams.utils.Page;

public class RegisterView implements View {

    private boolean isStaff;

    public RegisterView(boolean isStaff) {
        this.isStaff = isStaff;
    }

    public void render() {
        // Enter user info
        Page.header("Please enter your email, name, faculty and password."); 
        UI getRegisterInfoUI = new GetRegisterInfoUI(isStaff);
        getRegisterInfoUI.body();
    }
}
