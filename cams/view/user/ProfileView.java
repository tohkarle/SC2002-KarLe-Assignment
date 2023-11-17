package cams.view.user;

import cams.Main;
import cams.components.option.Options;
import cams.components.option.UserInfoOptions;
import cams.interfaces.IntInput;
import cams.interfaces.View;
import cams.manager.UserManager;
import cams.service.StudentManager;
import cams.ui.GetSelectionWithDismissUI;
import cams.view.root.RootView;

public class ProfileView implements View {

    private int studentID;
    private UserManager manager;
    private Options profileInfo;

    public ProfileView(RootView rootView) {
        this.studentID = rootView.getCurrentUserID();
        this.manager = rootView.getManager();
        this.profileInfo = new UserInfoOptions(rootView.getCurrentUserID(), rootView.getManager());
    }

    public void body() {

        // Display profile
        profileInfo.viewOnlyWithDismiss("Profile: ");

        if (manager.isStudent(this.studentID)) {
            studentSpecificProfile();
        } else {
            staffSpecificProfile();
        }

        // No selection, user can only go back
        IntInput selectionWithDismiss = new GetSelectionWithDismissUI(-1, -1);
        selectionWithDismiss.getValidInt();
    }

    public void studentSpecificProfile() {
        System.out.println("Point: " + ((StudentManager) manager).getPoint(this.studentID));
        String campName = Main.campManager.committeeMemberFor(this.studentID);
        if (campName != null) {
            System.out.println("Committee member for: " + campName);
        }
    }

    public void staffSpecificProfile() {

    }
}
