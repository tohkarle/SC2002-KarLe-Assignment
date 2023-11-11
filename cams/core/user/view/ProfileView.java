package cams.core.user.view;

import cams.Main;
import cams.core.root.view.RootView;
import cams.manager.StudentManager;
import cams.manager.UserManager;
import cams.util.UIComponents;

public class ProfileView {

    private boolean running;
    private int studentID;
    private UserManager manager;

    public ProfileView(RootView rootView) {
        this.running = true;
        this.studentID = rootView.getCurrentUserID();
        this.manager = rootView.getManager();
    }

    public void show() {
        do {
            UIComponents.pageHeader("Profile:");
            System.out.println("Name: " + manager.getName(this.studentID));
            System.out.println("Email: " + manager.getEmail(this.studentID));
            System.out.println("Faculty: " + manager.getFaculty(this.studentID));

            if (manager.isStudent(this.studentID)) {
                studentSpecificProfile();
            } else {
                staffSpecificProfile();
            }

            this.running = (UIComponents.navigationInput(1, 1) != UIComponents.backOptionInt());

        } while (this.running);
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
