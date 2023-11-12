package cams.component;

import java.util.ArrayList;

import cams.Main;
import cams.util.Dismiss;
import cams.util.Page;

public class AllCamps {

    private ArrayList<String> names;
    private ArrayList<Integer> ids;

    public AllCamps() {
        // Fetch all camps
        this.names = Main.campManager.getAllCampNames();
        this.ids = Main.campManager.getAllCampIDs();
    }

    public AllCamps(String faculty) {
        // Fetch all camps from faculty
        this.names = Main.campManager.getAllFacultyCampNames(faculty);
        this.ids = Main.campManager.getAllFacultyCampIDs(faculty);
    }

    public AllCamps(int staffID) {
        // Fetch all camps created by staff
        this.names = Main.campManager.getAllStaffCampNames(staffID);
        this.ids = Main.campManager.getAllStaffCampIDs(staffID);
    }

    public void displayCamps(String title) {
        if (this.names.size() == 0) {
            Page.header("No camp has been created.");
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
