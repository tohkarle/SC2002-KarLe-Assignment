package cams.views.camp;

import cams.components.option.CampInfoOptions;
import cams.interfaces.IntInput;
import cams.interfaces.View;
import cams.ui.GetSelectionWithDismissUI;

public class CampInfoView implements View {

    private CampInfoOptions campInfoOptions;

    public CampInfoView(int campID) {
        this.campInfoOptions = new CampInfoOptions(campID);
    }

    public void body() {
        // Display camp details
        this.campInfoOptions.viewOnly("Camp details: ");

        // Allow user to go back
        IntInput yourSelectionInputWithDismiss = new GetSelectionWithDismissUI(-1, -1);
        yourSelectionInputWithDismiss.getValidInt();
    }
}
