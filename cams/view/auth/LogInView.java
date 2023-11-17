package cams.view.auth;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.utils.Page;

public class LogInView extends View {

    // No options for this view

    // UIs for this view:
    private UI getEmailPasswordUI;

    public LogInView(Navigation navigation) {
        super(navigation);
    }

    public void render() {
        Page.header("Log in");

        // Get email and password
        getEmailPasswordUI = super.getUI("auth.GetEmailPasswordUI");
        getEmailPasswordUI.body();
    }
}
