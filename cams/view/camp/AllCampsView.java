package cams.view.camp;

import cams.component.AllCamps;
import cams.component.View;
import cams.util.Dismiss;

public class AllCampsView implements View {

    private AllCamps allCamps;

    public AllCampsView() {
        this.allCamps = new AllCamps();
    }

    public AllCampsView(String faculty) {
        this.allCamps = new AllCamps(faculty);
    }

    public AllCampsView(int userID) {
        this.allCamps = new AllCamps(userID);
    }

    public void show() {
        while (true) {
            // Display camps
            this.allCamps.displayCamps("Select camp to view details:");

            // Let user select camp to view details
            int option = this.allCamps.selectCamp();
            if (option == Dismiss.intOption()) { return; }

            // Display camp details
            CampDetailsView campDetailsView = new CampDetailsView(option);
            campDetailsView.show();
        }
    }

    public AllCamps getAllCamps() {
        return this.allCamps;
    }

}
