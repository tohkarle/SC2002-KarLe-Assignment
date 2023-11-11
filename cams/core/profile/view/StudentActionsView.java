package cams.core.profile.view;

import cams.core.camp.view.AllCampsView;
import cams.core.root.view.RootView;
import cams.manager.StudentManager;
import cams.util.UIComponents;

public class StudentActionsView {

    // A student can only view the list of camps that are open to his/her user group (SCSE, whole NTU etc.) and if their visibility has been toggled “on”
    // A student can view the remaining slots of each camp that is open to his/her.
    // A student can submit enquiries regarding a camp and only staff and camp committees in charge of that camp can view it
    // A student can view, edit, and delete their enquiries before it is processed
    private RootView rootView;
    private StudentManager manager;

    public StudentActionsView(RootView rootView) {
        this.rootView = rootView;
        this.manager = (StudentManager) rootView.getManager();
    }

    public void show() {
        do {
            studentOptions(manager.isACommitteeMember(this.rootView.getCurrentUserID()));
        } while (this.rootView.getCurrentUserID() != -1);
    }

    public void studentOptions(boolean isACommitteeMember) {
        System.out.println("Choose your action:");
        System.out.println("(1) View profile");
        System.out.println("(2) View all camps");
        System.out.println("(3) View registered camps");
        System.out.println("(4) View submitted enquiries");
        System.out.println("(5) Log out");

        int option = UIComponents.userInput();

        switch (option) {
            case 1:
                ProfileView profileView = new ProfileView(this.rootView);
                profileView.show();
                break;
            case 2:
                AllCampsView allCampsView = new AllCampsView(this.manager.getFaculty(this.rootView.getCurrentUserID()));
                allCampsView.show();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                UIComponents.logOutLoadingIndicator();
                rootView.logUserOut();
                break;
            default:
                UIComponents.invalidUserInput();
                break;
        }
    }
}
