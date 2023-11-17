package cams.view.user;

import cams.components.LoadingIndicator;
import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.UserManager;
import cams.model.Staff;
import cams.model.Student;

public class UserOptionsView extends View {

    private UserManager userManager;
    private String[] staffOptionsViews;
    private String[] studentOptionsViews;
    
    // Options for this view:
    private Options staffOptions;
    private Options studentOptions;

    // No UI for this view

    public UserOptionsView(Navigation navigation, UserManager userManager) {
        super(navigation);
        this.userManager = userManager;

        this.staffOptionsViews = new String[] {
            "user.ProfileView",
            "user.ChangePasswordView",
            "camp.AllCampsView",
            "camp.CreateCampView",
            "camp.CreatedCampsView"
        };

        this.studentOptionsViews = new String[] {
            "user.ProfileView",
            "user.ChangePasswordView",
            "camp.FacultyCampsView",
            "camp.RegisteredCampsView",
            "camp.RegisterForCampView"
        };
    }

    @Override
    public void render() {
        
        // Check if user is authenticated
        if (userManager.getCurrentUser() == null) {
            System.out.println("You have to register or log in first");
            return;
        }

        if (userManager.getCurrentUser() instanceof Staff) {

            // Display staff's options and let staff select action
            staffOptions = super.getOptions("user.StaffOptions");
            staffOptions.display("Choose your option: ");
            int option = staffOptions.selection();

            // Display corresponding view
            if (option <= staffOptionsViews.length) {
                super.getNavigation().navigateTo(staffOptionsViews[option - 1]);
            } else {
                logOut();
                return;
            }

        } else if (userManager.getCurrentUser() instanceof Student) {

            // Display student's options and let student select action
            studentOptions = super.getOptions("user.StudentOptions");
            studentOptions.display("Choose your option: ");
            int option = studentOptions.selection();

            // Display corresponding view
            if (option <= studentOptionsViews.length) {
                super.getNavigation().navigateTo(studentOptionsViews[option - 1]);
            } else {
                logOut();
                return;
            }
        }
    }

    private void logOut() {
        LoadingIndicator.logOutLoadingIndicator();
        userManager.setCurrentUser(null);
        super.getNavigation().popToRoot();
    }
}
