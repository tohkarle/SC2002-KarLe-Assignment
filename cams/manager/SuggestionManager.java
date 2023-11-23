package cams.manager;

import java.util.ArrayList;

import cams.model.Camp;
import cams.model.Suggestion;
import cams.model.SuggestionStatus;
import cams.service.SuggestionService;
import cams.utils.UniqueKey;


/**
 * A high level controller to manage suggestions,
 * is a singleton object
 */
public class SuggestionManager {

    /**
     * A singleton reference to this object
     */
    private static SuggestionManager instance;

    /**
     * A singleton reference to the suggetion service object
     */
    private SuggestionService suggestionService;


    /**
     * Initialize this object
     */
    private SuggestionManager() {
        this.suggestionService = new SuggestionService();
    }


    /**
     * A public static method for other objects to get this object
     * @return SuggestionManager object, this object
     */
    public static SuggestionManager getInstance() {
        if (instance == null) {
            instance = new SuggestionManager();
        }
        return instance;
    }


    /**
     * A method to get the number of suggestions specified user has made that are approved
     * @param studentName The name of the student
     * @return int of the number of suggestions approved
     */
    public int getNumberOfSuggestionsApproved(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids.size();
    }


    /**
     * A method to get the number of suggestions a specified user has made
     * @param studentName The name of the student
     * @return int of the the number of suggestion made
     */
    public int getNumberOfSuggestionsGiven(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName)) {
                ids.add(suggestion.getId());
            }
        }
        return ids.size();
    }


    /**
     * A method to get a suggestion object via ID
     * @param suggestionID The ID of the suggestion
     * @return Suggestion object, only a copy
     */
    public Suggestion getSuggestion(int suggestionID) {
        return suggestionService.getSuggestion(suggestionID);
    }


    /**
     * A method to get all the suggestion titles
     * @return ArrayList<String> of all suggestion titles
     */
    public ArrayList<String> getAllSuggestionTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            titles.add(suggestion.getTitle());
        }
        return titles;
    }


    /**
     * A method to get all the suggestion IDs
     * @return ArrayList<Integer> of all suggestion IDs
     */
    public ArrayList<Integer> getAllSuggestionIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            ids.add(suggestion.getId());
        }
        return ids;
    }


    /**
     * A method to get all the suggestion titles that have been accepted
     * @return ArrayList<String> of the suggestion titles
     */
    public ArrayList<String> getAcceptedSuggestionTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    

    /**
     * A method to get all the suggestion IDs that have been accepted
     * @return ArrayList<Integer> of the suggestion titles
     */
    public ArrayList<Integer> getAcceptedSuggestionIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }


    /**
     * A method to get all the suggestion titles that have been rejected
     * @return ArrayList<String> of the suggestion titles
     */
    public ArrayList<String> getRejectedSuggestionTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }


    /**
     * A method to get all the suggestion IDs that have been rejected
     * @return ArrayList<Integer> of the suggestion titles
     */
    public ArrayList<Integer> getRejectedSuggestionIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }
    

    /**
     * A method to get all the suggestion titles that are still pending
     * @return ArrayList<String> of the suggestion titles
     */
    public ArrayList<String> getPendingSuggestionTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }


    /**
     * A method to get all the suggestion IDs that are still pending
     * @return ArrayList<Integer> of the suggestion titles
     */
    public ArrayList<Integer> getPendingSuggestionIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }


    /**
     * A method to get all suggestion titles for specified camp
     * @param campID The ID of the camp
     * @return ArrayList<String> of suggestion titles
     */
    public ArrayList<String> getAllCampSuggestionTitles(int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility()) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }


    /**
     * A method to get all suggestion IDs for specified camp
     * @param campID The ID of the camp
     * @return ArrayList<Integer> of suggestion IDs
     */
    public ArrayList<Integer> getAllCampSuggestionIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility()) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }


    /**
     * A method to get all suggestion titles for specified camp that have been accepted
     * @param campID The ID of the camp
     * @return ArrayList<String> of suggestion titles
     */
    public ArrayList<String> getAcceptedCampSuggestionTitles(int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    

    /**
     * A method to get all suggestion IDs for specified camp that have been accepted
     * @param campID The ID of the camp
     * @return ArrayList<Integer> of suggestion IDs
     */
    public ArrayList<Integer> getAcceptedCampSuggestionIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }
    

    /**
     * A method to get all suggestion titles for specified camp that have been rejected
     * @param campID The ID of the camp
     * @return ArrayList<String> of suggestion titles
     */
    public ArrayList<String> getRejectedCampSuggestionTitles(int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    

    /**
     * A method to get all suggestion IDs for specified camp that have been rejected
     * @param campID The ID of the camp
     * @return ArrayList<Integer> of suggestion IDs
     */
    public ArrayList<Integer> getRejectedCampSuggestionIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }
    

    /**
     * A method to get all suggestion titles for specified camp that are still pending
     * @param campID The ID of the camp
     * @return ArrayList<String> of suggestion titles
     */
    public ArrayList<String> getPendingCampSuggestionTitles(int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    

    /**
     * A method to get all suggestion IDs for specified camp that are still pending
     * @param campID The ID of the camp
     * @return ArrayList<Integer> of suggestion IDs
     */
    public ArrayList<Integer> getPendingCampSuggestionIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }




    /**
     * A method to get all suggestion titles for specified student
     * @param studentName The name of the student
     * @return ArrayList<String> of suggestion titles
     */
    public ArrayList<String> getAllStudentSuggestionTitles(String studentName) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility()) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }


    /**
     * A method to get all suggestion IDs for specified student
     * @param studentName The name of the student
     * @return ArrayList<integer> of suggestion titles
     */
    public ArrayList<Integer> getAllStudentSuggestionIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility()) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }


    /**
     * A method to get all suggestion titles for specified student that have been accepted
     * @param studentName The name of the student
     * @return ArrayList<String> of suggestion titles
     */
    public ArrayList<String> getAcceptedStudentSuggestionTitles(String studentName) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    

    /**
     * A method to get all suggestion IDs for specified student that have been accepted
     * @param studentName The name of the student
     * @return ArrayList<Integer> of suggestion IDs
     */
    public ArrayList<Integer> getAcceptedStudentSuggestionIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }


    /**
     * A method to get all suggestion titles for specified student that have been rejected
     * @param studentName The name of the student
     * @return ArrayList<String> of suggestion titles
     */
    public ArrayList<String> getRejectedStudentSuggestionTitles(String studentName) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    

    /**
     * A method to get all suggestion IDs for specified student that have been rejected
     * @param studentName The name of the student
     * @return ArrayList<Integer> of suggestion IDs
     */
    public ArrayList<Integer> getRejectedStudentSuggestionIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }
    

    /**
     * A method to get all suggestion titles for specified student that are still pending
     * @param studentName The name of the student
     * @return ArrayList<String> of suggestion titles
     */
    public ArrayList<String> getPendingStudentSuggestionTitles(String studentName) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    

    /**
     * A method to get all suggestion IDs for specified student that are still pending
     * @param studentName The name of the student
     * @return ArrayList<Integer> of suggestion IDs
     */
    public ArrayList<Integer> getPendingStudentSuggestionIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }




    /**
     * A method to get all suggestion titles for specified student, for specified camp
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @return ArrayList<String> of suggestion titles
     */
    public ArrayList<String> getAllStudentCampSuggestionTitles(String studentName, int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility()) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }


    /**
     * A method to get all suggestion IDs for specified student, for specified camp
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @return ArrayList<Integer> of suggestion IDs
     */
    public ArrayList<Integer> getAllStudentCampSuggestionIDs(String studentName, int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility()) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }


    /**
     * A method to get all suggestion titles for specified student, for specified camp, that have been accepted
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @return ArrayList<String> of suggestion titles
     */
    public ArrayList<String> getAcceptedStudentCampSuggestionTitles(String studentName, int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    

    /**
     * A method to get all suggestion IDs for specified student, for specified camp, that have been accepted
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @return ArrayList<Integer> of suggestion IDs
     */
    public ArrayList<Integer> getAcceptedStudentCampSuggestionIDs(String studentName, int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }


    /**
     * A method to get all suggestion titles for specified student, for specified camp, that have been rejected
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @return ArrayList<String> of suggestion titles
     */
    public ArrayList<String> getRejectedStudentCampSuggestionTitles(String studentName, int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    

    /**
     * A method to get all suggestion IDs for specified student, for specified camp, that have been rejected
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @return ArrayList<Integer> of suggestion IDs
     */
    public ArrayList<Integer> getRejectedStudentCampSuggestionIDs(String studentName, int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }
    

    /**
     * A method to get all suggestion titles for specified student, for specified camp, that are still pending
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @return ArrayList<String> of suggestion titles
     */
    public ArrayList<String> getPendingStudentCampSuggestionTitles(String studentName, int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    

    /**
     * A method to get all suggestion IDs for specified student, for specified camp, that are still pending
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @return ArrayList<Integer> of suggestion IDs
     */
    public ArrayList<Integer> getPendingStudentCampSuggestionIDs(String studentName, int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }



    /**
     * A method to create a new suggestion
     * @param studentName The name of the user creating the suggestion
     * @param title The title of the suggestion
     * @param camp The camp object the suggestion is for
     */
    public void createSuggestion(String studentName, String title, Camp camp) {
        // Create new suggestion and add to 'database'
        int uniqueKey = 0;
        uniqueKey = UniqueKey.generateNewKey(uniqueKey);
        while(suggestionService.getSuggestionMap().get(uniqueKey) != null) uniqueKey = UniqueKey.generateNewKey(uniqueKey);
        Suggestion newSuggestion = new Suggestion(uniqueKey, studentName, title, camp);
        suggestionService.createSuggestion(newSuggestion);
        suggestionService.save();
        return;
    }


    /**
     * A method to update a suggestion
     * @param suggestion The new suggestion object
     */
    public void updateSuggestion(Suggestion suggestion) {
        suggestionService.updateSuggestion(suggestion);
        suggestionService.save();
    }


    /**
     * A method to edit a suggestion title
     * @param suggestionID The ID of the suggestion
     * @param title The new title of the suggestion
     */
    public void editSuggestion(int suggestionID, String title) {
        suggestionService.setSuggestionTitle(suggestionID, title);
    }


    /**
     * A method to delete a suggestion via its ID
     * @param suggestionID The ID of the suggestion
     */
    public void deleteSuggestion(int suggestionID){
        suggestionService.deleteSuggestion(suggestionID);
        suggestionService.save();
    }


    /**
     * A methof to process the specified suggestion
     * @param staffName The name of the user processing the suggestion
     * @param suggestionID The ID of the suggestion
     * @param suggestionStatus The new status of the suggestion
     */
    public void processSuggestion(String staffName, int suggestionID, SuggestionStatus suggestionStatus) {
        suggestionService.setProcessedBy(suggestionID, staffName);
        suggestionService.setSuggestionStatus(suggestionID, suggestionStatus);
        suggestionService.save();
    }
}
