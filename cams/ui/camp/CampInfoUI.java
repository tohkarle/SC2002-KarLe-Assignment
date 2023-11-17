package cams.ui.camp;

import cams.interfaces.IntInput;
import cams.interfaces.UI;
import cams.ui.GetSelectionWithDismissUI;
import cams.utils.CampUtil;
import cams.utils.Dismiss;

public class CampInfoUI implements UI {

    private CampUtil campUtil;
    private int studentID;

    // UIs involved
    private UI withdrawFromCampUI;

    public CampInfoUI(int campID) {
        this.campUtil = new CampUtil(campID);
        this.studentID = -1;
    }

    public CampInfoUI(int campID, int studentID) {
        this.campUtil = new CampUtil(campID);
        this.studentID = studentID;
    }

    public void body() {
        // If student is viewing registered camps, they will have an option to withdraw from the camp
        if (viewingRegisteredCampInfo()) { campUtil.addWithdrawOption(); }

        // Display camp info
        campUtil.viewOnly("Camp details: ");

        IntInput selectionWithDismiss;

        if (!viewingRegisteredCampInfo()) {
            // Allow user to go back only, since there is no withdraw option
            selectionWithDismiss = new GetSelectionWithDismissUI(-1, -1);
            selectionWithDismiss.getValidInt();
            return;
        }

        // Allow student to go back or withdraw from camp
        selectionWithDismiss = new GetSelectionWithDismissUI(-1, 1);
        if (selectionWithDismiss.getValidInt() == Dismiss.intOption() ) { return; }

        withdrawFromCampUI = new WithdrawFromCampUI(studentID, campUtil);
        withdrawFromCampUI.body();
    }

    public boolean viewingRegisteredCampInfo () {
        return (studentID != -1);
    }
}
