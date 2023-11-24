package cams.ui.camp;

import cams.components.input.ConfirmOrDiscard;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.utils.LoadingIndicator;

/**
 * UI object for withdrawing from a camp
 */
public class WithdrawFromCampUI implements UI {

    private Navigation navigation;
    private UserManager userManager;
    private CampManager campManager;
    private int selectedCampID;

    /**
     * Initialize the WithdrawFromCampUI
     * @param navigation Navigation object used to navigate between views
     * @param userManager UserManager object used to get user information
     * @param campManager CampManager object used to manage camp data
     * @param selectedCampID ID of the camp to be withdrawn from
     */
    public WithdrawFromCampUI(Navigation navigation, UserManager userManager, CampManager campManager, int selectedCampID) {
        this.navigation = navigation;
        this.userManager = userManager;
        this.campManager = campManager;
        this.selectedCampID = selectedCampID;
    }

    /**
     * Executes user interaction logic for withdrawing from a camp
     */
    @Override
    public void body() {

        IntInput confirm = new ConfirmOrDiscard();

        System.out.println("Selected camp: " + selectedCampID);
        
        // Confirm withdraw or discard and go back
        if (confirm.getValidInt("Confirm withdraw?") != 1) {
            return;
        }

        if (campManager.hasWithdrawnFromCamp(userManager.getCurrentUser().getName(), selectedCampID)) {
            System.out.println("Student has already withdrawn from camp.");
            return;
        }

        // Committee member cannot withdraw from camp
        if (campManager.isACommitteeMemberOfThisCamp(userManager.getCurrentUser().getName(), selectedCampID)) { 
            System.out.println("A camp committee member cannot quit from the camp");
            return;
        }

        // Withdraw student from camp
        campManager.withdrawFromCamp(userManager.getCurrentUser().getName(), selectedCampID);
        LoadingIndicator.withdrawLoadingIndicator("camp");
        navigation.dismissView();
        return;
    }
}
