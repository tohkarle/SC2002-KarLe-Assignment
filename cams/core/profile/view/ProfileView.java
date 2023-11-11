package cams.core.profile.view;

import cams.core.root.view.RootView;
import cams.manager.StudentManager;
import cams.util.UIComponents;

public class ProfileView {

    private boolean running;
    private RootView rootView;
    private StudentManager manager;

    public ProfileView(RootView rootView) {
        this.running = true;
        this.rootView = rootView;
        this.manager = (StudentManager) rootView.getManager();
    }

    public void show() {
        do {
            System.out.println("Name: " + manager.getName(this.rootView.getCurrentUserID()));
            System.out.println("Email: " + manager.getEmail(this.rootView.getCurrentUserID()));
            System.out.println("Faculty: " + manager.getFaculty(this.rootView.getCurrentUserID()));
            System.out.println("Point: " + manager.getPoint(this.rootView.getCurrentUserID()));

            this.running = UIComponents.backOption(1);

        } while (this.running);
    }
}
