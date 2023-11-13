package cams.views.camp;

import cams.components.option.CampOptions;
import cams.components.option.Options;
import cams.interfaces.View;
import cams.utils.Dismiss;

public class FacultyCampsView implements View {

    private Options campOptions;

    // Views to navigate to
    private View campInfoView;

    public FacultyCampsView(String faculty) {
        this.campOptions = new CampOptions(faculty);
    }

    public void body() {
        while (true) {
            // Display camps
            campOptions.display("Select camp to view details:");

            // Let staff select camp to view details
            int option = this.campOptions.selectionWithDismiss();
            if (option == Dismiss.intOption()) { return; }

            // Edit camp details
            campInfoView = new CampInfoView(option);
            campInfoView.body();
        }
    }
}