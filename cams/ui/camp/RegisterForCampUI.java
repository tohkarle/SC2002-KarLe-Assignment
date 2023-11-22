package cams.ui.camp;

import java.time.LocalDate;

import cams.components.input.ChooseBetweenTwoOptions;
import cams.interfaces.Input;
import cams.interfaces.IntInput;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.model.RegistrationType;
import cams.utils.Dismiss;
import cams.utils.LoadingIndicator;

public class RegisterForCampUI implements UI {

    private Input getInput;
    private UserManager userManager;
    private CampManager campManager;
    private int selectedCampID;

    public RegisterForCampUI(Input getInput, int selectedCampID) {
        this.selectedCampID = selectedCampID;
        this.getInput = getInput;
    }

    public void body() {

        userManager = UserManager.getInstance();
        campManager = CampManager.getInstance();

        // Let user choose to sign up as ATTENDEE or COMMITTEE
        IntInput choose = new ChooseBetweenTwoOptions("ATTENDEE", "COMMITTEE");
        int option = choose.getValidInt("Do you want to sign up as ATTENDEE or COMMITTEE?");
        if (option == Dismiss.intOption()) { return; }
        RegistrationType registrationType = (option == 1) ? RegistrationType.ATTENDEE : RegistrationType.COMMITTEE;

        // Confirm register or discard and go back
        if (getInput.confirmOrDiscard("Confirm register?") != 1) { return; }

        // Check for eligibility
        if (this.notEligible(selectedCampID, registrationType)) { return; }

        // Register for camp
        campManager.registerForCamp(userManager.getCurrentUser().getName(), selectedCampID, registrationType);
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
            LoadingIndicator.customLoadingIndicator("Registering...", "Registration is unsuccessful. You have already registered for this camp.");
            return true;
        }

        if (campManager.hasWithdrawnFromCamp(userManager.getCurrentUser().getName(), campID)){
            LoadingIndicator.customLoadingIndicator("Registering...", "Registration is unsuccessful. You have withdrawn from this camp before.");
            return true;
        }

        if (campManager.isAfterRegistrationClosingDate(campID, LocalDate.now())) { 
            LoadingIndicator.customLoadingIndicator("Registering...", "Register unsuccessful. The dateline for the registration is over.");
            return true;
        }

        if (campManager.hasCampClashes(userManager.getCurrentUser().getName(), campID)) { 
            LoadingIndicator.customLoadingIndicator("Registering...", "Register unsuccessful. This camp has clashes in dates with other camps you have registered.");
            return true;
        }

        if (registrationType == RegistrationType.ATTENDEE) {

            if (campManager.participationIsFull(campID)) {
                LoadingIndicator.customLoadingIndicator("Registering...", "Register unsuccessful. Camp is full.");
                return true;
            }
        } else {
            if (campManager.committeeIsFull(campID)) {
                LoadingIndicator.customLoadingIndicator("Registering...", "Register unsuccessful. Camp's committee slot is full.");
                return true;
            }

            if (campManager.isCommitteeMemberForAnotherCamp(userManager.getCurrentUser().getName(), campID)) {
                LoadingIndicator.customLoadingIndicator("Registering...", "Register unsuccessful. You are already a committee member in another camp.");
                return true;
            }
        }

        return false;
    }
}
