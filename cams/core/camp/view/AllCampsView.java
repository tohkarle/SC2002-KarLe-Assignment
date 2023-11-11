package cams.core.camp.view;

import java.util.ArrayList;

import cams.Main;
import cams.component.UserInput;

public class AllCampsView {

    private ArrayList<String> names;
    private ArrayList<Integer> ids;

    public AllCampsView(String faculty, int staffID) {
        if (faculty == null && staffID == -1) {
            // Fetch all camps
            this.names = Main.campManager.getAllCampNames();
            this.ids = Main.campManager.getAllCampIDs();

        } else if (faculty != null && staffID == -1) {
            // Fetch all camps from faculty
            this.names = Main.campManager.getAllFacultyCampNames(faculty);
            this.ids = Main.campManager.getAllFacultyCampIDs(faculty);

        } else if (faculty == null && staffID != -1) {
            // Fetch all camps created by staff
            this.names = Main.campManager.getAllStaffCampNames(staffID);
            this.ids = Main.campManager.getAllStaffCampIDs(staffID);

        }
    }

    public void show() {
        this.displayCamps("All camps:");
        if (UserInput.selectionInputField(-1, -1) == UserInput.backOptionInt()) { return; };
    }

    public void displayCamps(String title) {
        if (this.names.size() == 0) {
            UserInput.pageHeader("No camp has been created.");
        } else {
            UserInput.pageHeader(title);
            for (int i = 0; i < names.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + names.get(i));
            }
        }
    }

    public ArrayList<String> getNames() {
        return this.names;
    }

    public ArrayList<Integer> getIds() {
        return this.ids;
    }

}
