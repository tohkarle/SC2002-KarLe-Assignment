package cams.option.camp;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.CampManager;
import cams.model.Camp;
import cams.utils.Dismiss;
import cams.utils.Page;

public class AllCampsOptions extends DismissableSelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> campIDs;

    public AllCampsOptions() {
        this.fetchCamps();
    }

    @Override
    public void display(String title) {
        fetchCamps();
        if (super.getOptionsSize() == 0) {
            Page.header(this.noCampTitle);
        } else {
            super.display(title);
        }
    }

    @Override
    public int selection() {
        int option = super.selection();
        if (option == Dismiss.intOption()) { return option; }
        return this.campIDs.get(option - 1);
    }

    public void fetchCamps() {
        CampManager campManager = CampManager.getInstance();
        this.noCampTitle = "No camp has been created.";
        
        ArrayList<Camp> camps = campManager.getAllCampsByNameSorted();
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
