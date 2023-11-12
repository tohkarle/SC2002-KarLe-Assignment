package cams.core.user.view;

import cams.Main;
import cams.component.SelectionInput;
import cams.core.root.view.RootView;
import cams.manager.StudentManager;
import cams.manager.UserManager;

public class ProfileView {

    private int studentID;
    private UserManager manager;

    public ProfileView(RootView rootView) {
        this.studentID = rootView.getCurrentUserID();
        this.manager = rootView.getManager();
    }

    public void show() {
        SelectionInput.pageHeader("Profile:");
        System.out.println("Name: " + manager.getName(this.studentID));
        System.out.println("Email: " + manager.getEmail(this.studentID));
        System.out.println("Faculty: " + manager.getFaculty(this.studentID));

        if (manager.isStudent(this.studentID)) {
            studentSpecificProfile();
        } else {
            staffSpecificProfile();
        }

        if (SelectionInput.selectionInputFieldWithBack(-1, -1) == SelectionInput.backOptionInt()) { return; }
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
