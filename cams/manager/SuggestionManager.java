package cams.manager;

import java.util.ArrayList;

import cams.model.Camp;
import cams.model.Suggestion;
import cams.model.SuggestionStatus;
import cams.service.SuggestionService;
import cams.utils.UniqueKey;

public class SuggestionManager {

    private SuggestionService suggestionService;
    private int selectedSuggestionID;
    private int selectedSuggestionCampInfo;
    private Suggestion tempSuggestion;

    public SuggestionManager(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
        this.selectedSuggestionID = -1;
        this.selectedSuggestionCampInfo = -1;
    }

    public Camp getSuggestionCamp() {
        return suggestionService.getSuggestion(selectedSuggestionID).getCamp();
    }

    public void createTempSuggestion() {
        this.tempSuggestion = suggestionService.getSuggestion(selectedSuggestionID);
    }

    public Suggestion getTempSuggestion() {
        return this.tempSuggestion;
    }

    public void clearTempSuggestion() {
        this.tempSuggestion = null;
    }

    public int getSelectedSuggestionID() {
        return this.selectedSuggestionID;
    }

    public void setSelectedSuggestionID(int option) {
        this.selectedSuggestionID = option;
    }

    public int getSelectedSuggestionCampInfo() {
        return this.selectedSuggestionCampInfo;
    }

    public void setSelectedSuggestionCampInfo(int option) {
        this.selectedSuggestionCampInfo = option;
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
    }
}
