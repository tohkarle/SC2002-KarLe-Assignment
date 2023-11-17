package cams.view.user;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.model.Student;
import cams.utils.Dismiss;

public class ProfileView extends View {

    private UserManager userManager;
    private CampManager campManager;

    // Options:
    private Options profileOptions;

    // No UI for this view

    public ProfileView(Navigation navigation, UserManager userManager, CampManager campManager) {
        super(navigation);
        this.userManager = userManager;
        this.campManager = campManager;
    }

    public void render() {

        // Display profile
        profileOptions = super.getOptions("user.ProfileOptions");
        profileOptions.display("Profile: ");

        if (userManager.isStaff()) {
            staffSpecificProfile();
        } else {
            studentSpecificProfile();
        }

        int option = profileOptions.selection();
        if (option == Dismiss.intOption()) { 
            super.getNavigation().dismissView();
            return;
        }
    }

    public void studentSpecificProfile() {
        System.out.println("Point: " + ((Student) userManager.getCurrentUser()).getPoint());
        String campName = campManager.committeeMemberFor(userManager.getCurrentUser().getName());
        if (campName != null) {
            System.out.println("Committee member for: " + campName);
        }
    }

    public void staffSpecificProfile() {

    }
}
