package cams.view.user;

import cams.component.IntInput;
import cams.component.LoadingIndicator;
import cams.component.Options;
import cams.component.StudentOptions;
import cams.component.View;
import cams.component.YourSelectionInput;
import cams.view.authentication.ChangePasswordView;
import cams.view.camp.AllCampsView;
import cams.view.camp.RegisterCampsView;
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
    private ProfileView profileView;
    private ChangePasswordView changePasswordView;
    private AllCampsView allCampsInFacultyView;
    private AllCampsView allRegisteredCamps;
    private RegisterCampsView registerCampsView;


    public StudentOptionsView(RootView rootView) {
        this.rootView = rootView;
        this.studentOptions = new StudentOptions();

        this.profileView = new ProfileView(rootView);
        this.changePasswordView = new ChangePasswordView(rootView);
        this.allCampsInFacultyView = new AllCampsView(rootView.getManager().getFaculty(rootView.getCurrentUserID()));
        this.allRegisteredCamps = new AllCampsView(rootView.getCurrentUserID());
        this.registerCampsView = new RegisterCampsView(rootView.getCurrentUserID(), allCampsInFacultyView.getAllCamps());

        this.views = new View[] {
            this.profileView,
            this.changePasswordView,
            this.allCampsInFacultyView,
            this.allRegisteredCamps,
            this.registerCampsView,
        };
    }

    public void show() {
        studentOptions();
    }

    public void studentOptions() {
        // Display student's options
        this.studentOptions.display();

        // Let student select action
        int option = studentOptions.selection();

        // Display corresponding view
        if (option <= views.length) {
            this.views[option - 1].show();
        } else {
            LoadingIndicator.logOutLoadingIndicator();
            rootView.logUserOut();
        }
    }

    public void committeeMemberOptions() {
        System.out.println("\nChoose your action:");
        System.out.println("(1) View profile");
        System.out.println("(2) View all camps");
        System.out.println("(3) View registered camps");
        System.out.println("(4) View submitted enquiries");
        System.out.println("(5) Log out");

        IntInput yourSelectionInput = new YourSelectionInput(1, 5);
        int option = yourSelectionInput.getValidInput();

        switch (option) {
            case 1:
                profileView.show();
                break;
            case 2:
                allCampsInFacultyView.show();
                break;
            case 3:
                allRegisteredCamps.show();
                break;
            case 4:
                break;
            case 5:
                LoadingIndicator.logOutLoadingIndicator();
                rootView.logUserOut();
                break;
        }
    }
}
