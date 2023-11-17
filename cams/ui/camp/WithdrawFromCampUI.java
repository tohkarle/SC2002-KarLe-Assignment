package cams.ui.camp;

import cams.Main;
import cams.components.LoadingIndicator;
import cams.interfaces.InputField;
import cams.interfaces.IntInput;
import cams.ui.ConfirmOrDiscardUI;
import cams.utils.CampUtil;

public class WithdrawFromCampUI implements InputField {

    private int studentID;
    private CampUtil campUtil;

    public WithdrawFromCampUI(int studentID, CampUtil campUtil) {
        this.campUtil =  campUtil;
    }

    @Override
    public boolean focused() {
        // Confirm withdraw or discard and go back
        IntInput confirmOrDiscard = new ConfirmOrDiscardUI("withdraw");
        if (confirmOrDiscard.getValidInt() != 1) { return false; }

        // Committee member cannot withdraw from camp
        if (Main.campManager.isACommitteeMemberOfThisCamp(studentID, campUtil.getId())) { 
            System.out.println("A camp committee member cannot quit from the camp");
            return false; 
        }

        // Withdraw student from camp
        Main.campManager.removeFromParticipatingStudentIDs(studentID, campUtil.getId());
        Main.campManager.addToWithdrawnStudentIDs(studentID, campUtil.getId());
        Main.campManager.save();
        LoadingIndicator.withdrawLoadingIndicator("camp");
        return false;
    }
}
