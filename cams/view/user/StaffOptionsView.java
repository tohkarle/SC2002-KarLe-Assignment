package cams.view.user;

import cams.components.LoadingIndicator;
import cams.components.option.Options;
import cams.interfaces.View;
import cams.option.user.StaffOptions;
import cams.view.camp.AllCampsView;
import cams.view.camp.CreateCampView;
import cams.view.camp.CreatedCampsView;
import cams.view.root.RootView;

public class StaffOptionsView implements View {

    // A staff will be able to create, edit and delete camps.
    // A staff can toggle the visibility of the camp to be “on” or “off”. This will be reflected in the camp list that will be visible to students.
    // A staff can view all camps.
    // A staff can see list of camps that his/her created in a separate menu list so they can edit the camps they created.
    // A staff can view and reply to enquiries from students to the camp(s) his/her has created.
    // A staff can view and approve suggestions to changes to camp details from camp committee.
    // A staff can generate a report of the list of students attending each camp that his/her has created. The list will include details of the camp as well as the roles of the participants. There should be filters for how the staff would want to generate the list. (attendee, camp committee, etc.) (generate in either txt or csv format).
    // A staff can also generate a performance report of the camp committee members.

    private RootView rootView;
    private Options staffOptions;
    private View[] views;

    // Views to navigate to
    private View profileView;
    private View changePasswordView;
    private View allCampsView;
    private View createCampView;
    private View createdCampsView;

    public StaffOptionsView(RootView rootView) {
        this.rootView = rootView;
        this.staffOptions = new StaffOptions();
        this.profileView = new ProfileView(rootView);
        this.changePasswordView = new ChangePasswordView(rootView);
        this.allCampsView = new AllCampsView();
        this.createCampView = new CreateCampView(rootView);
        this.createdCampsView = new CreatedCampsView(rootView.getCurrentUserID());

        this.views = new View[] {
            this.profileView,
            this.changePasswordView,
            this.allCampsView,
            this.createCampView,
            this.createdCampsView,
        };
    }

    public void body() {
        // Display staff's options
        this.staffOptions.display("Choose your option: ");

        // Let staff select action
        int option = staffOptions.selection();

        // Display corresponding view
        if (option <= views.length) {
            this.views[option - 1].render();
        } else {
            LoadingIndicator.logOutLoadingIndicator();
            rootView.logUserOut();
        }
    }
}
