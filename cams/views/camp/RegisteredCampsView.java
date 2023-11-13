package cams.views.camp;

import cams.components.option.CampOptions;
import cams.components.option.Options;
import cams.interfaces.View;
import cams.utils.Dismiss;

public class RegisteredCampsView implements View {

    private Options campOptions;

    // Views to navigate to
    private View campInfoView;

    public RegisteredCampsView(int studentID) {
        this.campOptions = new CampOptions(studentID);
    }

    public void body() {
        while (true) {
            // Display camps
            campOptions.display("Select camp to view details:");

            // Let student select camp to view details
            int option = this.campOptions.selectionWithDismiss();
            if (option == Dismiss.intOption()) { return; }

            // View camp details
            campInfoView = new CampInfoView(option);
            campInfoView.body();
        }
    }
}
