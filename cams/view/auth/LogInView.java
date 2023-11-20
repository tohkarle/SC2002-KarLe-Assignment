package cams.view.auth;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.auth.GetEmailPasswordUI;
import cams.utils.Page;

public class LogInView implements View {

    private Navigation navigation;

    public LogInView(Navigation navigation) {
        this.navigation = navigation;
    }

    public void render() {
        Page.header("Log in");

        // Get email and password
        UI getEmailPasswordUI = new GetEmailPasswordUI(navigation);
        getEmailPasswordUI.body();
    }
}
