package cams.view.user;

import cams.components.LoadingIndicator;
import cams.components.option.Options;
import cams.interfaces.View;
import cams.option.user.StudentOptions;
import cams.view.camp.FacultyCampsView;
import cams.view.camp.RegisterForCampView;
import cams.view.camp.RegisteredCampsView;
import cams.view.root.RootView;

public class StudentOptionsView implements View {

    // A student can only view the list of camps that are open to his/her user group (SCSE, whole NTU etc.) and if their visibility has been toggled “on”
    // A student can view the remaining slots of each camp that is open to his/her.
    // A student can submit enquiries regarding a camp and only staff and camp committees in charge of that camp can view it
    // A student can view, edit, and delete their enquiries before it is processed

    private RootView rootView;
    private Options studentOptions;
    private View[] views;

    // Views to navigate to
    private View profileView;
    private View changePasswordView;
    private View facultyCampsView;
    private View registeredCampsView;
    private View registerForCampView;


    public StudentOptionsView(RootView rootView) {
        this.rootView = rootView;
        this.studentOptions = new StudentOptions();

        this.profileView = new ProfileView(rootView);
        this.changePasswordView = new ChangePasswordView(rootView);
        this.facultyCampsView = new FacultyCampsView(rootView.getManager().getFaculty(rootView.getCurrentUserID()));
        this.registeredCampsView = new RegisteredCampsView(rootView.getCurrentUserID());
        this.registerForCampView = new RegisterForCampView(rootView.getCurrentUserID(), rootView.getManager().getFaculty(rootView.getCurrentUserID()));

        this.views = new View[] {
            this.profileView,
            this.changePasswordView,
            this.facultyCampsView,
            this.registeredCampsView,
            this.registerForCampView,
        };
    }

    public void body() {
        studentOptions();
    }

    public void studentOptions() {
        // Display student's options
        this.studentOptions.display("Choose your option: ");

        // Let student select action
        int option = studentOptions.selection();

        // Display corresponding view
        if (option <= views.length) {
            this.views[option - 1].render();
        } else {
            LoadingIndicator.logOutLoadingIndicator();
            rootView.logUserOut();
        }
    }
}
