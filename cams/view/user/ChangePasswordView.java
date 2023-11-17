package cams.view.user;

import cams.interfaces.UI;
import cams.interfaces.View;

public class ChangePasswordView extends View {

    private UI changPasswordUI;

    public void render() {
        changPasswordUI = super.getUI("auth.ChangePasswordUI");
        changPasswordUI.body();
    }
}
