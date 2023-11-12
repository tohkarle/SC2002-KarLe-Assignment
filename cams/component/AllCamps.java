package cams.component;

import java.util.ArrayList;

import cams.Main;
import cams.util.Dismiss;
import cams.util.Page;

public class AllCamps {

    private String noCampTitle;
    private ArrayList<String> names;
    private ArrayList<Integer> ids;

    public AllCamps() {
        // Fetch all camps
        this.noCampTitle = "No camp has been created.";
        this.names = Main.campManager.getAllCampNames();
        this.ids = Main.campManager.getAllCampIDs();
    }

    public AllCamps(String faculty) {
        // Fetch all camps from faculty
        this.noCampTitle = "No camp has been created under this faculty. Please conme back at a later time.";
        this.names = Main.campManager.getAllFacultyCampNames(faculty);
        this.ids = Main.campManager.getAllFacultyCampIDs(faculty);
    }

    public AllCamps(int userID) {
        if (Main.authManager.isStaff(userID)) {
            // Fetch all camps created by staff
            this.noCampTitle = "No camp has been created by you. Go to 'Create camp' to create a new camp.";
            this.names = Main.campManager.getAllStaffCampNames(userID);
            this.ids = Main.campManager.getAllStaffCampIDs(userID);
        } else {
            // Fetch all camps registered by student
            this.noCampTitle = "You have not registered for any camp. Go to 'Register for camps' to register for camps under your faculty.";
            this.names = Main.campManager.getAllRegisteredCampNames(userID);
            this.ids = Main.campManager.getAllRegisteredCampIDs(userID);
        }
    }

    public void displayCamps(String title) {
        if (this.names.size() == 0) {
            Page.header(this.noCampTitle);
        } else {
            Page.header(title);
            for (int i = 0; i < names.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + names.get(i));
            }
        }
    }

    public int selectCamp() {
        IntInput yourSelectionInputWithDismiss = new YourSelectionInputWithDismiss(1, this.ids.size());
        int option = yourSelectionInputWithDismiss.getValidInput();
        if (option == Dismiss.intOption()) { return option; }
        return this.ids.get(option - 1);
    }
}