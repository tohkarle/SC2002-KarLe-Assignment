package cams.option.camp;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.Options;
import cams.interfaces.IntInput;
import cams.manager.CampManager;
import cams.model.Camp;
import cams.ui.GetSelectionUI;
import cams.ui.GetSelectionWithDismissUI;
import cams.utils.Page;

public class CampInfoOptions extends Options {

    private Camp camp;
    private CampManager campManager;

    public CampInfoOptions(CampManager campManager) {
        this.campManager = campManager;
    }

    public void updateCampInfo() {
        this.camp = campManager.getSelectedCamp();
        this.setCampInfo();
    }

    public void setCampInfo() {
        super.setOptions(
            new ArrayList<String>(Arrays.asList(
                String.format("Name: %s", this.camp.getCampName()),
                String.format("Faculty: %s", this.camp.getUserGroup()),
                String.format("Location: %s", this.camp.getLocation()),
                String.format("Description: %s", this.camp.getDescription()),
                String.format("Visibility: %s", this.camp.getVisibility() ? "On" : "Off"),
                String.format("Dates: %s to %s", this.camp.getStartDate(), this.camp.getEndDate()),
                String.format("Registration closing date: %s", this.camp.getRegistrationClosingDate()),
                String.format("Remaining slots: %d / %d", (this.camp.getTotalSlots() - this.camp.getParticipatingStudentNames().size()), this.camp.getTotalSlots()),
                String.format("Remaining committee slots: %d / %d", (this.camp.getCommitteeSlots() - this.camp.getCommitteeMemberNames().size()), this.camp.getCommitteeSlots()),
                String.format("Staff-in-charge: %s", this.camp.getStaffInCharge())
            ))
        );
    }

    @Override
    public void display(String title) {
        Page.header(title);
        super.printOptions();
    }

    @Override
    public int selection() {
        IntInput selectionWithDismiss = new GetSelectionWithDismissUI(1, super.getOptionsSize());
        return selectionWithDismiss.getValidInt("Your selection: ");
    }

    public void viewOnly(String title) {
        Page.header(title);
        for (int i = 0; i < super.getOptionsSize(); i++) {
            System.out.println(super.getOption(i));
        }
    }

    public int dismiss() {
        IntInput selection = new GetSelectionUI(-1, -1);
        return selection.getValidInt("Your selection: ");
    }

    public Camp getCamp() {
        return this.camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public CampManager getCampManager() {
        return this.campManager;
    }
}
