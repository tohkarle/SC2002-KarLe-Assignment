package cams.views.camp;

import cams.interfaces.InputField;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.camp.DeleteCampUI;
import cams.ui.camp.EditCampUI;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class EditCampView implements View {

    private CampUtil campUtil;

    // UIs involved
    private UI editCampUI;
    private InputField deleteCampUI;

    // Views to navigate to
    private View enquiriesView;
    private View suggestionsView;

    public EditCampView(CampUtil campUtil) {
        this.campUtil = campUtil;
    }

    public void body() {

        // Remove Staff-in-charge, add Update changes, Manage enquiries, Create report and Delete camp options
        campUtil.changeOptionsForEdit();

        // KARLE_TODO: If user has made changes, ask them if they want to discard changes if they try to go back
        while (true) {
            // Display camp info for editing
            // Let user choose the field to edit
            campUtil.display("Select the field you want to edit.");
            int option = campUtil.selectionWithDismiss();
            if (option == Dismiss.intOption()) { return; }

            // Display UIs based on user input
            editCampUI = new EditCampUI(option, campUtil);
            if (option == campUtil.updateCampOption()) {
                campUtil.updateCamp();
                return;
            } else if (option == campUtil.manageEnquiriesOption()) {
                // Manage enquiries
            } else if (option == campUtil.manageSuggestionsOption()) {
                // Manage suggestions
            } else if (option == campUtil.createReportOption()) {
                // Create report
            } else if (option == campUtil.deleteCampOption()) {
                deleteCampUI = new DeleteCampUI(campUtil);
                if (!deleteCampUI.focused()) { return; }
            } else {
                editCampUI.body();
            }
        }
    }
}
