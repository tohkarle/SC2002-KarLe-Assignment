package cams.view.camp;

import cams.components.option.CampOptions;
import cams.components.option.Options;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.camp.CampInfoUI;
import cams.utils.Dismiss;

public class FacultyCampsView implements View {

    private Options campOptions;

    // UIs involved
    private UI campInfoUI;

    public FacultyCampsView(String faculty) {
        this.campOptions = new CampOptions(faculty);
    }

    public void body() {
        while (true) {
            // Display camps
            campOptions.displayWithDismiss("Select camp to view details:");

            // Let staff select camp to view details
            int option = this.campOptions.selectionWithDismiss();
            if (option == Dismiss.intOption()) { return; }

            // Edit camp details
            campInfoUI = new CampInfoUI(option);
            campInfoUI.body();
        }
    }
}