package cams.views.camp;

import cams.interfaces.Input;
import cams.interfaces.View;
import cams.ui.camp.CreateCampUI;
import cams.utils.Page;
import cams.views.root.RootView;

public class CreateCampView implements View {

    private int staffID;
    private Input createCampUI;

    public CreateCampView(RootView rootView) {
        this.staffID = rootView.getCurrentUserID();
        this.createCampUI = new CreateCampUI(this.staffID);
    }

    public void body() {
        Page.header("Please enter the name, faculty, visibility and dates of the camp.");
        createCampUI.getInput();
    }
}
