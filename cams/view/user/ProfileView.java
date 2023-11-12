package cams.view.user;

import cams.Main;
import cams.component.IntInput;
import cams.component.YourSelectionInputWithDismiss;
import cams.manager.StudentManager;
import cams.manager.UserManager;
import cams.util.Page;
import cams.view.root.RootView;

public class ProfileView {

    private int studentID;
    private UserManager manager;

    public ProfileView(RootView rootView) {
        this.studentID = rootView.getCurrentUserID();
        this.manager = rootView.getManager();
    }

    public void show() {
        Page.header("Profile:");
        System.out.println("Name: " + manager.getName(this.studentID));
        System.out.println("Email: " + manager.getEmail(this.studentID));
        System.out.println("Faculty: " + manager.getFaculty(this.studentID));

        if (manager.isStudent(this.studentID)) {
            studentSpecificProfile();
        } else {
            staffSpecificProfile();
        }

        IntInput yourSelectionInputWithDismiss = new YourSelectionInputWithDismiss(-1, -1);
        yourSelectionInputWithDismiss.getValidInput();
    }

    public void studentSpecificProfile() {
        System.out.println("Point: " + ((StudentManager) manager).getPoint(this.studentID));
        int campID = ((StudentManager) manager).isACommitteeMemberFor(this.studentID);
        if (campID != -1) {
            String campName = Main.campManager.getCampName(campID);
            System.out.println("Committee member for: " + campName);
        }
    }

    public void staffSpecificProfile() {

    }
}
