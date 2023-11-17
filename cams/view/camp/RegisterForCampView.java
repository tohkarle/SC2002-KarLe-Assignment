package cams.view.camp;

import cams.components.option.CampOptions;
import cams.components.option.Options;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.RegisterForCampUI;
import cams.utils.Dismiss;

public class RegisterForCampView implements View {

    private int studentID;
    private Options campOptions;

    // UI
    private UI registerForCampUI;

    public RegisterForCampView(int studentID, String faculty) {
        this.studentID = studentID;
        this.campOptions = new CampOptions(faculty);
    }

    public void body() {
        while (true) {
            // Show all available camps
            campOptions.displayWithDismiss("Select the camp you wish to register. Do note that committee members are only allowed to register other camps as attendee");

            // Let user select the camp to register
            int campID = campOptions.selectionWithDismiss();
            if (campID == Dismiss.intOption()) { return; }

            // Register for camp
            registerForCampUI = new RegisterForCampUI(this.studentID, campID);
            registerForCampUI.body();
        }
    }
}
