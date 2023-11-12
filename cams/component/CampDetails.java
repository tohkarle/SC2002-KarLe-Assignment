package cams.component;

import java.time.LocalDate;

import cams.Main;
import cams.util.Page;

public class CampDetails {

    private String campName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String faculty;
    private boolean visibility;

    public CampDetails(int campID) {
        this.campName = Main.campManager.getCampName(campID);
        this.startDate = Main.campManager.getStartDate(campID);
        this.endDate = Main.campManager.getEndDate(campID);
        this.faculty = Main.campManager.getUserGroup(campID);
        this.visibility = Main.campManager.getVisibility(campID);
    }

    public void displayDetails(String title, boolean canEdit) {
        Page.header(title);
        if (canEdit) {
            System.out.println("(1) Name: " + this.campName);
            System.out.println("(2) Dates: " + this.startDate + " to " + this.endDate);
            System.out.println("(3) Faculty: " + this.faculty);
            System.out.print("(4) Visibility: ");
        } else {
            System.out.println("Name: " + this.campName);
            System.out.println("Dates: " + this.startDate + " to " + this.endDate);
            System.out.println("Faculty: " + this.faculty);
            System.out.print("Visibility: ");
        }
        System.out.print(this.visibility ? "On\n" : "Off\n");
    }

    public int selectDetail() {
        IntInput yourSelectionInputWithDismiss = new YourSelectionInputWithDismiss(1, 4);
        return yourSelectionInputWithDismiss.getValidInput();
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
} 

