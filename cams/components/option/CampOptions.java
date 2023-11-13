package cams.components.option;

import java.util.ArrayList;

import cams.Main;
import cams.utils.Dismiss;
import cams.utils.Page;

public class CampOptions extends Options {

    private String noCampTitle;
    private ArrayList<Integer> campIDs;

    public CampOptions() {
        // Fetch all camps
        this.noCampTitle = "No camp has been created.";
        super.options = Main.campManager.getAllCampNames();
        this.campIDs = Main.campManager.getAllCampIDs();
    }

    public CampOptions(String faculty) {
        // Fetch all camps from faculty
        this.noCampTitle = "No camp has been created under this faculty. Please conme back at a later time.";
        super.options = Main.campManager.getAllFacultyCampNames(faculty);
        this.campIDs = Main.campManager.getAllFacultyCampIDs(faculty);
    }

    public CampOptions(int userID) {
        if (Main.authManager.isStaff(userID)) {
            // Fetch all camps created by staff
            this.noCampTitle = "No camp has been created by you. Go to 'Create camp' to create a new camp.";
            super.options = Main.campManager.getAllStaffCampNames(userID);
            this.campIDs = Main.campManager.getAllStaffCampIDs(userID);
        } else {
            // Fetch all camps registered by student
            this.noCampTitle = "You have not registered for any camp. Go to 'Register for camps' to register for camps under your faculty.";
            super.options = Main.campManager.getAllRegisteredCampNames(userID);
            this.campIDs = Main.campManager.getAllRegisteredCampIDs(userID);
        }
    }

    @Override
    public void display(String title) {
        if (super.options.size() == 0) {
            Page.header(this.noCampTitle);
        } else {
            super.display(title);
        }
    }

    @Override
    public int selectionWithDismiss() {
        int option = super.selectionWithDismiss();
        if (option == Dismiss.intOption()) { return option; }
        return this.campIDs.get(option - 1);
    }
}
