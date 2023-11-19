package cams.ui.camp;

import cams.components.LoadingIndicator;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.UserManager;

public class WithdrawFromCampUI implements UI {

    private Navigation navigation;
    private UserManager userManager;
    private CampManager campManager;
    private IntInput confirm;

    public WithdrawFromCampUI(Navigation navigation, UserManager userManager, CampManager campManager, IntInput confirm) {
        this.navigation = navigation;
        this.userManager = userManager;
        this.campManager = campManager;
        this.confirm = confirm;
    }

    @Override
    public void body() {

        System.out.println("Selected camp: " + campManager.getSelectedCampID());
        
        // Confirm withdraw or discard and go back
        if (confirm.getValidInt("Confirm withdraw?") != 1) {
            return;
        }

        if (campManager.hasWithdrawnFromCamp(userManager.getCurrentUser().getName(), campManager.getSelectedCampID())) {
            System.out.println("Student has already withdrawn from camp.");
            return;
        }

        // Committee member cannot withdraw from camp
        if (campManager.isACommitteeMemberOfThisCamp(userManager.getCurrentUser().getName(), campManager.getSelectedCampID())) { 
            System.out.println("A camp committee member cannot quit from the camp");
            return;
        }

        // Withdraw student from camp
        campManager.withdrawFromCamp(userManager.getCurrentUser().getName(), campManager.getSelectedCampID());
        LoadingIndicator.withdrawLoadingIndicator("camp");
        navigation.dismissView();
        return;
    }
}
