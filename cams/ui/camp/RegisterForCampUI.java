package cams.ui.camp;

import java.time.LocalDate;

import cams.components.LoadingIndicator;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.model.RegistrationType;
import cams.ui.ChooseBetweenTwoOptionsUI;
import cams.utils.Dismiss;

public class RegisterForCampUI extends ChooseBetweenTwoOptionsUI implements UI {

    // private Navigation navigation;
    private UserManager userManager;
    private CampManager campManager;
    private IntInput confirm;

    public RegisterForCampUI(Navigation navigation, UserManager userManager, CampManager campManager, IntInput confirm) {
        super("ATTENDEE", "COMMITTEE");
        // this.navigation = navigation;
        this.userManager = userManager;
        this.campManager = campManager;
        this.confirm = confirm;
    }

    public void body() {

        System.out.printf(String.format("Is a committee member: %s\n", campManager.isACommitteeMember(userManager.getCurrentUser().getName())));
        
        // Let user choose to sign up as ATTENDEE or COMMITTEE
        int option = super.getValidInt("Do you want to sign up as ATTENDEE or COMMITTEE?");
        if (option == Dismiss.intOption()) { return; }
        RegistrationType registrationType = (option == 1) ? RegistrationType.ATTENDEE : RegistrationType.COMMITTEE;

        // Confirm register or discard and go back
        if (confirm.getValidInt("Confirm register?") != 1) { return; }

        // Check for eligibility
        if (this.notEligible(campManager.getSelectedID(), registrationType)) { return; }

        // Register for camp
        campManager.registerForCamp(userManager.getCurrentUser().getName(), campManager.getSelectedID(), registrationType);
        LoadingIndicator.registerLoadingIndicator("camp");
    }

    public boolean notEligible(int campID, RegistrationType registrationType) {
        /*
        * A student is not allowed to register for a camp more than once
        * A student is not allowed to register again after withdrawing from the camp
        * A student only can register a camp before it is full
        * A student only can register a camp before its registration deadline
        * A student is not allowed to register for multiple camps if there are clashes in the dates
        * A student can only be committee member for one camp
        */

        if (campManager.hasRegisteredForCamp(userManager.getCurrentUser().getName(), campID)) {
            System.out.println("\nRegistration is unsuccessful .You have registered for this camp");
            return true;
        }

        if (campManager.hasWithdrawnFromCamp(userManager.getCurrentUser().getName(), campID)){
            System.out.println("\nRegistration is unsuccessful. You have withdrawn from this camp before");
            return true;
        }

        if (campManager.isAfterRegistrationClosingDate(campID, LocalDate.now())) { 
            System.out.println("\nRegister unsuccessful. The dateline for the registration is over.");
            return true;
        }

        if (campManager.hasCampClashes(userManager.getCurrentUser().getName(), campID)) { 
            System.out.println("\nRegister unsuccessful. This camp has clashes with other camps you have registered.");
            return true;
        }

        if (registrationType == RegistrationType.ATTENDEE) {

            if (campManager.participationIsFull(campID)) {
                System.out.println("\nRegister unsuccessful. Camp is full.");
                return true;
            }
        } else {
            if (campManager.committeeIsFull(campID)) {
                System.out.println("\nRegister unsuccessful. Camp's committee slot is full.");
                return true;
            }

            if (campManager.isCommitteeMemberForAnotherCamp(userManager.getCurrentUser().getName(), campID)) {
                System.out.println("\nRegister unsuccessful. You are already a committee member in another camp.");
                return true;
            }
        }

        return false;
    }
}
