package cams.component;

import java.time.LocalDate;

public class StartEndDatesInput extends DateInputField {

    private LocalDate startDate;
    private LocalDate endDate;
    private String startDateTitle;
    private String endDateTitle;

    public StartEndDatesInput(String startDateTitle, String endDateTitle) {
        this.startDateTitle = startDateTitle;
        this.endDateTitle = endDateTitle;
    }

    public LocalDate getValidStartDate() {
        this.startDate = super.getValidDate(startDateTitle);
        return this.startDate;
    }

    public LocalDate getValidEndDate() {
        while (true) {
            this.endDate = super.getValidDate(endDateTitle);
            if (this.endDate == null) { return null; }
            if (this.endDate.isEqual(this.startDate) || endDate.isAfter(this.startDate)) { break; }
            System.out.println("End date cannot be before start date.");
        }
        return this.endDate;
    }
}
