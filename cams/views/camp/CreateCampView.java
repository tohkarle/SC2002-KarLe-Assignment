package cams.views.camp;

import cams.interfaces.InputField;
import cams.interfaces.View;
import cams.ui.camp.CreateCampUI;
import cams.utils.Page;
import cams.views.root.RootView;

public class CreateCampView implements View {

    private int staffID;

    // UIs involved
    private InputField createCampUI;

    // Views to navigate to
    private View createdCampsView;

    public CreateCampView(RootView rootView) {
        this.staffID = rootView.getCurrentUserID();

        // Create and initialize UIs for create camp
        this.createCampUI = new CreateCampUI(this.staffID);

        // Create and initialize views to navigate to
        this.createdCampsView = new CreatedCampsView(rootView.getCurrentUserID(), true);
    }

    public void body() {
        Page.header("Please enter the name, faculty, visibility and dates of the camp.");
        createCampUI.focused();
        createdCampsView.body();
    }
}
