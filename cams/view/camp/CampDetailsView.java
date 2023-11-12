package cams.view.camp;

import java.time.LocalDate;

import cams.Main;
import cams.component.IntInput;
import cams.component.SelectionInput;
import cams.component.YourSelectionWithBack;

public class CampDetailsView {

    private String title;
    private String campName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String faculty;
    private boolean visibility;

    public CampDetailsView(int campID, String title) {
        this.title = title;
        this.campName = Main.campManager.getCampName(campID);
        this.startDate = Main.campManager.getStartDate(campID);
        this.endDate = Main.campManager.getEndDate(campID);
        this.faculty = Main.campManager.getUserGroup(campID);
        this.visibility = Main.campManager.getVisibility(campID);
    }

    public void show() {
        // Display camp details
        this.displayDetails(title, false);

        // Allow user to go back
        IntInput yourSelectionWithBack = new YourSelectionWithBack(-1, -1);
        yourSelectionWithBack.getValidInput();
    }

    public void displayDetails(String title, boolean canEdit) {
        SelectionInput.pageHeader(title);
        if (canEdit) {
            System.out.println("(1) Name: " + this.campName);
            System.out.println("(2) Start date: " + this.startDate);
            System.out.println("(3) End date: " + this.endDate);
            System.out.println("(4) Faculty: " + this.faculty);
            System.out.print("(5) Visibility: ");
        } else {
            System.out.println("Name: " + this.campName);
            System.out.println("Start date: " + this.startDate);
            System.out.println("End date: " + this.endDate);
            System.out.println("Faculty: " + this.faculty);
            System.out.print("Visibility: ");
        }
        System.out.print(this.visibility ? "On\n" : "Off\n");
    }

    public int selectDetail() {
        IntInput yourSelectionWithBack = new YourSelectionWithBack(1, 5);
        return yourSelectionWithBack.getValidInput();
    }
}
