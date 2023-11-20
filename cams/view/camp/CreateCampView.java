package cams.view.camp;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.camp.CreateCampUI;
import cams.utils.Page;

public class CreateCampView implements View {

    private Navigation navigation;

    public CreateCampView(Navigation navigation) {
        this.navigation = navigation;
    }

    public void render() {
        Page.header("Please enter the name, faculty, visibility and dates of the camp.");
        UI getCampInfoUI = new CreateCampUI(navigation);
        getCampInfoUI.body();
    }
}
