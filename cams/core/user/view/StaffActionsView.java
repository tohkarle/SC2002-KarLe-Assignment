package cams.core.user.view;

import cams.core.camp.view.AllCampsView;
import cams.core.camp.view.CreateCampView;
import cams.core.camp.view.DeleteCampView;
import cams.core.root.view.RootView;
import cams.util.UIComponents;

public class StaffActionsView {
    // A staff will be able to create, edit and delete camps.
    // A staff can toggle the visibility of the camp to be “on” or “off”. This will be reflected in the camp list that will be visible to students.
    // A staff can view all camps.
    // A staff can see list of camps that his/her created in a separate menu list so they can edit the camps they created.
    // A staff can view and reply to enquiries from students to the camp(s) his/her has created.
    // A staff can view and approve suggestions to changes to camp details from camp committee.
    // A staff can generate a report of the list of students attending each camp that his/her has created. The list will include details of the camp as well as the roles of the participants. There should be filters for how the staff would want to generate the list. (attendee, camp committee, etc.) (generate in either txt or csv format).
    // A staff can also generate a performance report of the camp committee members.
    private RootView rootView;

    public StaffActionsView(RootView rootView) {
        this.rootView = rootView;
    }

    public void show() {
        System.out.println("\nChoose your action:");
        System.out.println("(1) View profile");
        System.out.println("(2) View all camps");
        System.out.println("(3) Create camp");
        System.out.println("(4) View created camps");
        System.out.println("(5) Delete camp");
        System.out.println("(6) Log out");

        int option = UIComponents.navigationInput(1, 6);

        switch (option) {
            case 1:
                ProfileView profileView = new ProfileView(this.rootView);
                profileView.show();
                break;
            case 2:
                AllCampsView allCampsView = new AllCampsView(null, -1);
                allCampsView.show();
                break;
            case 3:
                CreateCampView createCampView = new CreateCampView(this.rootView);
                createCampView.show();
                break;
            case 4:
                AllCampsView createdCampsView = new AllCampsView(null, this.rootView.getCurrentUserID());
                createdCampsView.show();
                break;
            case 5:
                DeleteCampView deleteCampView = new DeleteCampView(this.rootView);
                deleteCampView.show();
                break;
            case 6:
                UIComponents.logOutLoadingIndicator();
                rootView.logUserOut();
                break;
        }
    }
}
