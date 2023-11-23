package cams.ui.camp;

import java.time.LocalDate;
import java.util.ArrayList;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.LoadingIndicator;

public class CampDatesUI implements UI {

    private Input getInput;
    private Camp camp;
    private LocalDate startDate;
    private LocalDate endDate;
    private String startDateTitle;
    private String endDateTitle;

    public CampDatesUI(Input getInput, Camp camp, String startDateTitle, String endDateTitle) {
        this.getInput = getInput;
        this.camp = camp;
        this.startDateTitle = startDateTitle;
        this.endDateTitle = endDateTitle;
    }

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
