package cams.core.camp.view;

import java.util.ArrayList;

import cams.Main;
import cams.util.UIComponents;

public class AllCampsView {

    private ArrayList<String> names;
    private String faculty;
    private int staffID;

    public AllCampsView(String faculty, int staffID) {
        this.faculty = faculty;
        this.staffID = staffID;
    }

    public void show() {

        if (faculty == null && staffID == -1) {
            // Fetch all camps
            this.names = Main.campManager.getAllCampNames();
        } else if (faculty != null && staffID == -1) {
            // Fetch all camps from faculty
            this.names = Main.campManager.getAllFacultyCampNames(this.faculty);
        } else if (faculty == null && staffID != -1) {
            // Fetch all camps created by staff
            this.names = Main.campManager.getAllStaffCampNames(this.staffID);
        }

        this.displayCamps();

        if (UIComponents.navigationInput(-1, -1) == UIComponents.backOptionInt()) { return; };
    }

    public void displayCamps() {
        if (this.names.size() == 0) {
            UIComponents.pageHeader("No camp has been created.");
        } else {
            UIComponents.pageHeader("All camps:");
            for (int i = 0; i < names.size(); i++) {
                if (i == names.size() - 1) {
                    System.out.print(names.get(i));
                } else {
                    System.out.print(names.get(i) + ", ");
                }
            }
            System.out.println("");
        }
    }
}
