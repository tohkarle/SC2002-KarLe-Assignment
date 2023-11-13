package cams.ui.camp;

import java.time.LocalDate;

import cams.components.input.GetDate;
import cams.interfaces.DateInput;
import cams.interfaces.Input;
import cams.utils.CampUtil;

public class CampDatesUI extends GetDate implements Input {

    private CampUtil campUtil;
    private LocalDate startDate;
    private LocalDate endDate;
    private DateInput endDateInput;

    public CampDatesUI(CampUtil campUtil, String startDateTitle, String endDateTitle) {
        super(startDateTitle);
        this.campUtil = campUtil;
        this.endDateInput = new GetDate(endDateTitle);
    }

    public boolean getInput() {
        startDate = getValidDate();
        if (startDate == null) { return false; }

        endDate = getValidEndDate();
        if (endDate == null) { return false; }

        campUtil.setStartDate(startDate);
        campUtil.setEndDate(endDate);
        return true;
    }

    public LocalDate getValidEndDate() {
        while (true) {
            endDate = endDateInput.getValidDate();
            if (endDate == null) { return null; }
            if (endDate.isEqual(startDate) || endDate.isAfter(startDate)) { break; }
            System.out.println("End date cannot be before start date.");
        }
        return endDate;
    }
}
