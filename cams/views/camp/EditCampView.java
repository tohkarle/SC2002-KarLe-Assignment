package cams.views.camp;

import cams.interfaces.Input;
import cams.interfaces.View;
import cams.ui.camp.EditCampUI;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class EditCampView implements View {

    private Input editCampUI;
    private CampUtil campUtil;

    // Views to navigate to
    private View enquiriesView;
    private View suggestionsView;

    public EditCampView(CampUtil campUtil) {
        this.campUtil = campUtil;
    }

    public void body() {

        // Remove Staff-in-charge, add Update changes, Manage enquiries, Create report and Delete camp options
        campUtil.changeOptionsForEdit();

        while (true) {
            // Display camp info for editing
            // Let user choose the field to edit
            campUtil.display("Select the field you want to edit.");
            int option = campUtil.selectionWithDismiss();
            if (option == Dismiss.intOption()) { return; }

            // Display edit field based on user input
            editCampUI = new EditCampUI(option, campUtil);
            if (!editCampUI.getInput()) { return; };
        }
    }
}
