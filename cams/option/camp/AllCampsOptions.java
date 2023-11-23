package cams.option.camp;

import java.util.ArrayList;

import cams.components.input.GetSelectionWithDismiss;
import cams.components.option.DismissableSelectableOptions;
import cams.interfaces.IntInput;
import cams.manager.CampManager;
import cams.model.Camp;
import cams.utils.Dismiss;
import cams.utils.FilterCamps;
import cams.utils.Page;

public class AllCampsOptions extends DismissableSelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> campIDs;

    public AllCampsOptions(FilterCamps filterCamps) {
        this.fetchCamps(filterCamps);
        super.getOptions().add(0, "Filter");
    }

    @Override
    public void display(String title) {
        if (super.getOptionsSize() == 0) {
            Page.header(this.noCampTitle);
        } else {
            Page.header(title);
            for (int i = 0; i < super.getOptionsSize(); i++) {
                System.out.println("(" + (i) + ") " + super.getOption(i));
            }
        }
    }

    @Override
    public int selection() {
        IntInput selectionWithDismiss = new GetSelectionWithDismiss(0, super.getOptionsSize() - 1);
        int option = selectionWithDismiss.getValidInt("Your selection: ");
        if (option == 0 || option == Dismiss.intOption()) { return option; }
        return this.campIDs.get(option - 1);
    }

    public void fetchCamps(FilterCamps filterCamps) {

        CampManager campManager = CampManager.getInstance();
        this.noCampTitle = "No camp has been created.";
        
        ArrayList<Camp> camps = filterCamps.filteredCamps(campManager.getAllCampsByNameSorted());
        ArrayList<String> campNames = new ArrayList<>();
        ArrayList<Integer> campIds = new ArrayList<>();
        
        for (Camp camp : camps) {
            campNames.add(camp.getCampName());
            campIds.add(camp.getId());
        }
        
        super.setOptions(campNames);
        this.campIDs = campIds;
    }

    public void setNoCampTitle(String title) {
        this.noCampTitle = title;
    }

    public void setCampIDs(ArrayList<Integer> campIDs) {
        this.campIDs = campIDs;
    }
}
