package cams.views.camp;

import cams.components.option.CampOptions;
import cams.components.option.Options;
import cams.interfaces.View;
import cams.utils.CampUtil;
import cams.utils.Dismiss;

public class CreatedCampsView implements View {

    private int staffID;
    private Options campOptions;
    private CampUtil campUtil;

    // Views to navigate to
    private View editCampView;

    public CreatedCampsView(int staffID) {
        this.staffID = staffID;
    }

    public void body() {
        while (true) {
            // Display camps created by staff
            campOptions = new CampOptions(staffID);
            campOptions.display("Select camp to edit:");

            // Let staff select camp to view details
            int option = this.campOptions.selectionWithDismiss();
            if (option == Dismiss.intOption()) { return; }

            // Edit camp details
            campUtil = new CampUtil(option);
            editCampView = new EditCampView(campUtil);
            editCampView.body();
        }
    }
}
