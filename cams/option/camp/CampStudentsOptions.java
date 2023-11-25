package cams.option.camp;

import java.util.ArrayList;

import cams.components.option.DismissableViewOnlyOptions;
import cams.manager.CampManager;
import cams.utils.Page;


/**
 * This class extends DismissableViewOnlyOptions and provides a view of the students registered for a specific camp.
 * The students are categorized as committee members or attendees.
 */
public class CampStudentsOptions extends DismissableViewOnlyOptions {

    /**
     * Initialize the camp students options.
     * @param campID The ID of the camp to fetch students from.
     */
    public CampStudentsOptions(int campID) {
        fetchStudents(campID);
    }

    /**
     * Display the list of students registered for the camp.
     * If no students are registered, a message is displayed instead.
     * @param title The title to display at the top of the page.
     */
    @Override
    public void display(String title) {
        if (super.getOptionsSize() == 0) {
            Page.header("No student has registered for this camp. Please come back at a later time.");
        } else {
            Page.header(title);
            for (int i = 0; i < super.getOptionsSize(); i++) {
                System.out.println(super.getOption(i));
            }
        }
    }

    /**
     * Fetch the list of students registered for the camp and categorize them as committee members or attendees.
     * @param campID The ID of the camp to fetch students from.
     */
    public void fetchStudents(int campID) {
        CampManager campManager = CampManager.getInstance();
        
        ArrayList<String> committeeNames = campManager.getCommitteeMemberNames(campID);
        ArrayList<String> attendeeNames = campManager.getParticipatingStudentNames(campID);

        for (int i = 0; i < committeeNames.size(); i++) {
            if (!committeeNames.get(i).contains(" (committee)")) {
                committeeNames.set(i, committeeNames.get(i) + " (committee)");
            }
        }

        ArrayList<String> allNames = new ArrayList<>();
        allNames.addAll(committeeNames);
        allNames.addAll(attendeeNames);
        
        super.setOptions(allNames);
    }
}
