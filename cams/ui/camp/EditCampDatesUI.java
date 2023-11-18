package cams.ui.camp;

import java.time.LocalDate;
import java.util.ArrayList;

import cams.components.input.GetDateInput;
import cams.interfaces.UI;
import cams.model.Camp;

public class EditCampDatesUI extends GetDateInput implements UI {

    private Camp camp;
    private LocalDate startDate;
    private LocalDate endDate;
    private String startDateTitle;
    private String endDateTitle;

    public EditCampDatesUI(Camp camp, String startDateTitle, String endDateTitle) {
        this.camp = camp;
        this.startDateTitle = startDateTitle;
        this.endDateTitle = endDateTitle;
    }

    @Override
    public void body() {
        startDate = super.getValidDate(startDateTitle);
        if (startDate == null) { return; }

        endDate = getValidEndDate();
        if (endDate == null) { return; }

        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(startDate);
        dates.add(endDate);

        camp.setDates(dates);
        return;
    }

    public LocalDate getValidEndDate() {
        while (true) {
            endDate = super.getValidDate(endDateTitle);
            if (endDate == null) { return null; }
            if (endDate.isEqual(startDate) || endDate.isAfter(startDate)) { break; }
            System.out.println("End date cannot be before start date.");
        }
        return endDate;
    }
}