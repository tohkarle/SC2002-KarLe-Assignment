package cams.ui.camp;

import cams.components.LoadingIndicator;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.ui.ConfirmOrDiscardUI;

public class WithdrawFromCampUI implements UI {

    private Navigation navigation;
    private UserManager userManager;
    private CampManager campManager;
    private int selectedCampID;

    public WithdrawFromCampUI(Navigation navigation, UserManager userManager, CampManager campManager, int selectedCampID) {
        this.navigation = navigation;
        this.userManager = userManager;
        this.campManager = campManager;
        this.selectedCampID = selectedCampID;
    }

    @Override
    public void body() {

        IntInput confirm = new ConfirmOrDiscardUI();

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
