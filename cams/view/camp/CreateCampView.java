package cams.view.camp;

import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.camp.CreateCampUI;
import cams.utils.Page;

public class CreateCampView implements View {

    private Navigation navigation;
    private Input getInput;

    public CreateCampView(Navigation navigation, Input getInput) {
        this.navigation = navigation;
        this.getInput = getInput;
    }

    public void render() {
        Page.header("Please enter the name, faculty, visibility and dates of the camp.");
        UI getCampInfoUI = new CreateCampUI(navigation, getInput);
        getCampInfoUI.body();
    }
}
