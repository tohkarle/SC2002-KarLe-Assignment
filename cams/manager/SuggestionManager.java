package cams.manager;

import java.util.ArrayList;

import cams.model.Camp;
import cams.model.Suggestion;
import cams.model.SuggestionStatus;
import cams.service.SuggestionService;
import cams.utils.UniqueKey;

public class SuggestionManager {

    private static SuggestionManager instance;
    private SuggestionService suggestionService;

    private SuggestionManager() {
        this.suggestionService = new SuggestionService();
    }

    public static SuggestionManager getInstance() {
        if (instance == null) {
            instance = new SuggestionManager();
        }
        return instance;
    }

    public int getNumberOfSuggestionsApproved(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids.size();
    }

    public int getNumberOfSuggestionsGiven(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName)) {
                ids.add(suggestion.getId());
            }
        }
        return ids.size();
    }


    public Suggestion getSuggestion(int suggestionID) {
        return suggestionService.getSuggestion(suggestionID);
    }



    public ArrayList<String> getAllSuggestionTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            titles.add(suggestion.getTitle());
        }
        return titles;
    }

    public ArrayList<Integer> getAllSuggestionIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            ids.add(suggestion.getId());
        }
        return ids;
    }

    public ArrayList<String> getAcceptedSuggestionTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getAcceptedSuggestionIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }

    public ArrayList<String> getRejectedSuggestionTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getRejectedSuggestionIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }
    
    public ArrayList<String> getPendingSuggestionTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getPendingSuggestionIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }






    public ArrayList<String> getAllCampSuggestionTitles(int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility()) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }

    public ArrayList<Integer> getAllCampSuggestionIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility()) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }

    public ArrayList<String> getAcceptedCampSuggestionTitles(int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getAcceptedCampSuggestionIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }
    
    public ArrayList<String> getRejectedCampSuggestionTitles(int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getRejectedCampSuggestionIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }
    
    public ArrayList<String> getPendingCampSuggestionTitles(int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getPendingCampSuggestionIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }





    public ArrayList<String> getAllStudentSuggestionTitles(String studentName) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility()) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }

    public ArrayList<Integer> getAllStudentSuggestionIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility()) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }

    public ArrayList<String> getAcceptedStudentSuggestionTitles(String studentName) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getAcceptedStudentSuggestionIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }

    public ArrayList<String> getRejectedStudentSuggestionTitles(String studentName) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getRejectedStudentSuggestionIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }
    
    public ArrayList<String> getPendingStudentSuggestionTitles(String studentName) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getPendingStudentSuggestionIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }





    public ArrayList<String> getAllStudentCampSuggestionTitles(String studentName, int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility()) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }

    public ArrayList<Integer> getAllStudentCampSuggestionIDs(String studentName, int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility()) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }

    public ArrayList<String> getAcceptedStudentCampSuggestionTitles(String studentName, int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getAcceptedStudentCampSuggestionIDs(String studentName, int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.ACCEPTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }

    public ArrayList<String> getRejectedStudentCampSuggestionTitles(String studentName, int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getRejectedStudentCampSuggestionIDs(String studentName, int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.REJECTED) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }
    
    public ArrayList<String> getPendingStudentCampSuggestionTitles(String studentName, int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                titles.add(suggestion.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getPendingStudentCampSuggestionIDs(String studentName, int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID && suggestion.getStudentName().equals(studentName) && suggestion.getCamp().getVisibility() && suggestion.getSuggestionStatus() == SuggestionStatus.PENDING) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }






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

    public void updateSuggestion(Suggestion suggestion) {
        suggestionService.updateSuggestion(suggestion);
        suggestionService.save();
    }

    public void editSuggestion(int suggestionID, String title) {
        suggestionService.setSuggestionTitle(suggestionID, title);
    }

    public void deleteSuggestion(int suggestionID){
        suggestionService.deleteSuggestion(suggestionID);
        suggestionService.save();
    }

    public void processSuggestion(String staffName, int suggestionID, SuggestionStatus suggestionStatus) {
        suggestionService.setProcessedBy(suggestionID, staffName);
        suggestionService.setSuggestionStatus(suggestionID, suggestionStatus);
        suggestionService.save();
    }
}
