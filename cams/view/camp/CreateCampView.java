package cams.view.camp;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.utils.Page;

public class CreateCampView extends View {

    // No options in this view

    // UIs in this view:
    private UI getCampInfoUI;

    public CreateCampView(Navigation navigation) {
        super(navigation);
    }

    public void render() {
        Page.header("Please enter the name, faculty, visibility and dates of the camp.");
        getCampInfoUI = super.getUI("camp.CreateCampUI");
        getCampInfoUI.body();
    }
}
