package cams.ui.camp;

import java.time.LocalDate;
import java.util.ArrayList;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.LoadingIndicator;

/**
 * UI object for editing the start and end dates of a camp
 */
public class CampDatesUI implements UI {

    private Input getInput;
    private Camp camp;
    private LocalDate startDate;
    private LocalDate endDate;
    private String startDateTitle;
    private String endDateTitle;

    /**
     * Initialize the CampDatesUI
     * @param getInput Input object used to get user input
     * @param camp Camp object to be edited
     * @param startDateTitle Title of the UI for start date user input
     * @param endDateTitle Title of the UI for end date user input
     */
    public CampDatesUI(Input getInput, Camp camp, String startDateTitle, String endDateTitle) {
        this.getInput = getInput;
        this.camp = camp;
        this.startDateTitle = startDateTitle;
        this.endDateTitle = endDateTitle;
    }

    /**
     * Executes user interaction logic for setting or modifying the start and end dates of a camp
     */
    @Override
    public void body() {
        startDate = getInput.getValidDate(startDateTitle);
        if (startDate == null) { return; }

        endDate = getValidEndDate();
        if (endDate == null) { return; }

        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(startDate);
        dates.add(endDate);

        camp.setDates(dates);
        return;
    }

    private LocalDate getValidEndDate() {
        while (true) {
            endDate = getInput.getValidDate(endDateTitle);
            if (endDate == null) { return null; }
            if (endDate.isEqual(startDate) || endDate.isAfter(startDate)) { break; }
            LoadingIndicator.customWarningIndicator("End date cannot be before start date.");
        }
        return endDate;
    }
}
