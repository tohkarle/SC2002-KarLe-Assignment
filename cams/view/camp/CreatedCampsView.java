package cams.view.camp;

import cams.components.option.CampOptions;
import cams.components.option.Options;
import cams.interfaces.View;
import cams.utils.CampUtil;
import cams.utils.Dismiss;

public class CreatedCampsView implements View {

    private int staffID;
    private boolean justCreated;
    private Options campOptions;
    private CampUtil campUtil;

    // Views to navigate to
    private View editCampView;

    public CreatedCampsView(int staffID) {
        this.staffID = staffID;
        this.justCreated = false;
    }

    public CreatedCampsView(int staffID, boolean justCreated) {
        this.staffID = staffID;
        this.justCreated = justCreated;
    }

    public void body() {

        while (true) {
            campOptions = new CampOptions(staffID);

            // Label new if camp is just created
            labelCampNew();

            // Display camps created by staff
            campOptions.displayWithDismiss("Select camp to edit:");

            // Let staff select camp to view details
            int option = campOptions.selectionWithDismiss();
            if (option == Dismiss.intOption()) { return; }

            // Edit camp details
            campUtil = new CampUtil(option);
            editCampView = new EditCampView(campUtil);
            editCampView.render();
        }
    }

    public void labelCampNew() {
        if (justCreated) {
            String name = campOptions.getOption(campOptions.getOptionsSize() - 1);
            campOptions.replaceOption(name, name + " (New)");
        }
    }
}
