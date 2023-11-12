package cams.view.camp;

import cams.component.CampDetails;
import cams.component.IntInput;
import cams.component.YourSelectionInputWithDismiss;

public class CampDetailsView {

    private CampDetails campDetails;

    public CampDetailsView(int campID) {
        this.campDetails = new CampDetails(campID);
    }

    public void show() {
        // Display camp details
        this.campDetails.displayDetails("Camp details: ", false);

        // Allow user to go back
        IntInput yourSelectionInputWithDismiss = new YourSelectionInputWithDismiss(-1, -1);
        yourSelectionInputWithDismiss.getValidInput();
    }
}
