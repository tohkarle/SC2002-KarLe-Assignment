package cams.ui;

import java.time.LocalDate;

import cams.Main;
import cams.components.LoadingIndicator;
import cams.interfaces.IntInput;
import cams.interfaces.UI;
import cams.models.RegistrationType;
import cams.utils.Dismiss;

public class RegisterForCampUI extends ChooseBetweenTwoOptionsUI implements UI {

    private int studentID;
    private int campID;

    public RegisterForCampUI(int studentID, int campID) {
        super("Do you want to sign up as ATTENDEE or COMMITTEE?", "ATTENDEE", "COMMITTEE");
        this.studentID = studentID;
        this.campID = campID;
    }

    public void body() {

        System.out.printf(String.format("Is a committee member: %s\n", Main.campManager.isACommitteeMember(studentID)));
        
        // Let user choose to sign up as ATTENDEE or COMMITTEE
        int option = super.getValidInt();
        if (option == Dismiss.intOption()) { return; }
        RegistrationType registrationType = (option == 1) ? RegistrationType.ATTENDEE : RegistrationType.COMMITTEE;

        // Confirm register or discard and go back
        IntInput confirmOrDiscard = new ConfirmOrDiscardUI("register");
        if (confirmOrDiscard.getValidInt() != 1) { return; }

        // Check for eligibility
        if (this.notEligible(campID, registrationType)) { return; }

        // Register for camp
        Main.campManager.registerForCamp(studentID, campID, registrationType);
        Main.campManager.save();
        LoadingIndicator.registerLoadingIndicator("camp");
    }

    public boolean notEligible(int campID, RegistrationType registrationType) {
        /*
        * A student is not allowed to register for a camp more than once
        * A student only can register a camp before it is full
        * A student only can register a camp before its registration deadline
        * A student is not allowed to register for multiple camps if there are clashes in the dates
        * A student can only be committee member for one camp
        */

        if (Main.campManager.hasRegisteredForCamp(studentID, campID)) {
            System.out.println("\nRegister unsuccessful. You have already registered for this camp.");
            return true;
        }

        if (Main.campManager.isAfterRegistrationClosingDate(campID, LocalDate.now())) { 
            System.out.println("\nRegister unsuccessful. The dateline for the registration is over.");
            return true;
        }

        if (Main.campManager.hasCampClashes(studentID, campID)) { 
            System.out.println("\nRegister unsuccessful. This camp has clashes with other camps you have registered.");
            return true;
        }

        if (registrationType == RegistrationType.ATTENDEE) {

            if (Main.campManager.participationIsFull(campID)) {
                System.out.println("\nRegister unsuccessful. Camp is full.");
                return true;
            }
        } else {
            if (Main.campManager.committeeIsFull(campID)) {
                System.out.println("\nRegister unsuccessful. Camp's committee slot is full.");
                return true;
            }

            if (Main.campManager.isCommitteeMemberForAnotherCamp(studentID, campID)) {
                System.out.println("\nRegister unsuccessful. You are already a committee member in another camp.");
                return true;
            }
        }

        return false;
    }
}
