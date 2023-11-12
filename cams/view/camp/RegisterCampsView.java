package cams.view.camp;

import java.time.LocalDate;

import cams.Main;
import cams.component.AllCamps;
import cams.component.ConfirmOrDiscard;
import cams.component.IntInput;
import cams.component.LoadingIndicator;
import cams.component.TwoOptionsInput;
import cams.model.RegistrationType;
import cams.util.Dismiss;

public class RegisterCampsView {

    private int studentID;
    private AllCamps allCamps;

    // Input field
    private IntInput registrationTypeInput;

    public RegisterCampsView(int studentID, AllCamps allCamps) {
        this.studentID = studentID;
        this.allCamps = allCamps;
        this.registrationTypeInput = new TwoOptionsInput("Do you want to sign up as ATTENDEE or COMMITTEE?", "ATTENDEE", "COMMITTEE");
    }

    public void show() {
        while (true) {
            // Show all available camps
            allCamps.displayCamps("Select the camp you want to register:");

            // Let user select the camp to register
            int campID = allCamps.selectCamp();
            if (campID == Dismiss.intOption()) { return; }

            // Let user choose to sign up as ATTENDEE or COMMITTEE
            int option = registrationTypeInput.getValidInput();
            if (option == Dismiss.intOption()) { return; }
            RegistrationType registrationType = (option == 1) ? RegistrationType.ATTENDEE : RegistrationType.COMMITTEE;

            // Confirm register or discard and go back
            IntInput confirmOrDiscard = new ConfirmOrDiscard("register");
            if (confirmOrDiscard.getValidInput() != 1) { continue; }

            // Check for eligibility
            if (this.notEligible(campID, registrationType)) { continue; }

            // Register for camp
            Main.campManager.registerForCamp(studentID, campID, registrationType);
            LoadingIndicator.registerLoadingIndicator("camp");
        }
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

            if (Main.campManager.isACommitteeMemberForAnotherCamp(studentID, campID)) {
                System.out.println("\nRegister unsuccessful. You are already a committee member in another camp.");
                return true;
            }
        }

        return false;
    }
}
