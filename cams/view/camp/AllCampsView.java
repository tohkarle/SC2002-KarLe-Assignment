package cams.view.camp;

import java.util.ArrayList;

import cams.Main;
import cams.component.IntInput;
import cams.component.SelectionInput;
import cams.component.YourSelectionWithBack;

public class AllCampsView {

    private ArrayList<String> names;
    private ArrayList<Integer> ids;
    private int selectedCampID;

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

        this.selectedCampID = SelectionInput.backOptionInt();
    }

    public void show() {
        while (true) {
            // Display camps
            this.displayCamps("Select camp to view details:");

            // Let user select camp to view details
            this.selectCamp();
            if (this.selectedCampID == SelectionInput.backOptionInt()) { return; }

            // Display camp details
            CampDetailsView campDetailsView = new CampDetailsView(this.selectedCampID, "Camp details:");
            campDetailsView.show();
        }
    }

    public void displayCamps(String title) {
        if (this.names.size() == 0) {
            SelectionInput.pageHeader("No camp has been created.");
        } else {
            SelectionInput.pageHeader(title);
            for (int i = 0; i < names.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + names.get(i));
            }
        }
    }

    public void selectCamp() {
        IntInput yourSelectionWithBack = new YourSelectionWithBack(1, this.ids.size());
        int option = yourSelectionWithBack.getValidInput();
        if (option == SelectionInput.backOptionInt()) { 
            this.selectedCampID = option;
            return; 
        }
        this.selectedCampID = this.ids.get(option - 1);
    }

    public ArrayList<String> getNames() {
        return this.names;
    }

    public ArrayList<Integer> getIds() {
        return this.ids;
    }

    public int getSelectedCampID() {
        return this.selectedCampID;
    }

}
