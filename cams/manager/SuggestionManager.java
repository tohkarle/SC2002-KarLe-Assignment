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

    public int getSelectedSuggestionCampInfoD() {
        return this.selectedSuggestionCampInfo;
    }

    public void setSelectedSuggestionCampInfo(int option) {
        this.selectedSuggestionCampInfo = option;
    }

    public ArrayList<String> getAllSuggestionTitles() {
        ArrayList<String> contents = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            contents.add(suggestion.getTitle());
        }
        return contents;
    }

    public ArrayList<Integer> getAllSuggestionIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            ids.add(suggestion.getId());
        }
        return ids;
    }


    public ArrayList<String> getAllCampSuggestionTitles(int campID) {
        ArrayList<String> contents = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID) {
                contents.add(suggestion.getTitle());
            }
        }
        return contents;
    }

    public ArrayList<Integer> getAllCampSuggestionIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getCamp().getId() == campID) {
                ids.add(suggestion.getId());
            }
        }
        return ids;
    }

    public ArrayList<Integer> getAllStudentSuggestionTitles(String studentName) {
        ArrayList<Integer> contents = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName)) {
                contents.add(suggestion.getId());
            }
        }
        return contents;
    }

    public ArrayList<Integer> getAllStudentSuggestionIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Suggestion suggestion : suggestionService.getSuggestionMap().values()) {
            if (suggestion.getStudentName().equals(studentName)) {
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

    public void editSuggestion(int suggestionID, String content) {
        suggestionService.setSuggestionContent(suggestionID, content);
    }

    public void deleteSuggestion(int suggestionID){
        suggestionService.deleteSuggestion(suggestionID);
    }

    public void processSuggestion(String staffName, int suggestionID, SuggestionStatus suggestionStatus) {
        suggestionService.setProcessedBy(suggestionID, staffName);
        suggestionService.setSuggestionStatus(suggestionID, suggestionStatus);
    }
}
