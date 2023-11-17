package cams.view.camp;

import cams.components.option.CampOptions;
import cams.components.option.Options;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.camp.CampInfoUI;
import cams.utils.Dismiss;

public class AllCampsView implements View {

    private Options campOptions;

    // UIs involved
    private UI campInfoView;

    public AllCampsView() {
        this.campOptions = new CampOptions();
    }

    public void body() {
        while (true) {
            // Display camps
            campOptions.displayWithDismiss("Select camp to view details:");

            // Let user select camp to view details
            int option = this.campOptions.selectionWithDismiss();
            if (option == Dismiss.intOption()) { return; }

            // Display camp details
            campInfoView = new CampInfoUI(option);
            campInfoView.body();
        }
    }
}
