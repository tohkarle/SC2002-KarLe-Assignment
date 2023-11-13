package cams.views.camp;

import cams.components.option.CampOptions;
import cams.components.option.Options;
import cams.interfaces.View;
import cams.utils.Dismiss;

public class AllCampsView implements View {

    private Options campOptions;

    // Views to navigate to
    private View campInfoView;

    public AllCampsView() {
        this.campOptions = new CampOptions();
    }

    public void body() {
        while (true) {
            // Display camps
            campOptions.display("Select camp to view details:");

            // Let user select camp to view details
            int option = this.campOptions.selectionWithDismiss();
            if (option == Dismiss.intOption()) { return; }

            // Display camp details
            campInfoView = new CampInfoView(option);
            campInfoView.body();
        }
    }
}
