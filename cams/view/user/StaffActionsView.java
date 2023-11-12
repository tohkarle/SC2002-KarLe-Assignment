package cams.view.user;

import cams.component.IntInput;
import cams.component.LoadingIndicator;
import cams.component.YourSelectionInput;
import cams.view.camp.AllCampsView;
import cams.view.camp.CreateCampView;
import cams.view.camp.DeleteCampView;
import cams.view.camp.EditCampView;
import cams.view.root.RootView;

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

    // Views to navigate to from this page
    private ProfileView profileView;
    private AllCampsView allCampsView;
    private CreateCampView createCampView;
    private AllCampsView createdCampsView;
    private EditCampView editCampView;
    private DeleteCampView deleteCampView;

    public StaffActionsView(RootView rootView) {
        this.rootView = rootView;
        this.profileView = new ProfileView(rootView);
        this.allCampsView = new AllCampsView();
        this.createCampView = new CreateCampView(rootView);
        this.createdCampsView = new AllCampsView(rootView.getCurrentUserID());
        this.editCampView = new EditCampView(createdCampsView.getAllCamps());
        this.deleteCampView = new DeleteCampView(createdCampsView.getAllCamps());
    }

    public void show() {
        System.out.println("\nChoose your action:");
        System.out.println("(1) View profile");
        System.out.println("(2) View all camps");
        System.out.println("(3) Create camp");
        System.out.println("(4) View created camps");
        System.out.println("(5) Edit created camp");
        System.out.println("(6) Delete created camp");
        System.out.println("(7) Log out");

        IntInput yourSelectionInput = new YourSelectionInput(1, 7);
        int option = yourSelectionInput.getValidInput();

        switch (option) {
            case 1:
                profileView.show();
                break;
            case 2:
                allCampsView.show();
                break;
            case 3:
                createCampView.show();
                break;
            case 4:
                createdCampsView.show();
                break;
            case 5:
                editCampView.show();
                break;
            case 6:
                deleteCampView.show();
                break;
            case 7:
                LoadingIndicator.logOutLoadingIndicator();
                rootView.logUserOut();
                break;
        }
    }
}