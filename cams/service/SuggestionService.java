package cams.service;
import java.util.HashMap;

import cams.model.Suggestion;
import cams.model.SuggestionStatus;
import cams.utils.Serialize;

public class SuggestionService {

    private HashMap<Integer, Suggestion> suggestionMap;

    public SuggestionService() {
        Serialize.checkAndCreateFile("suggestionMap.sav");
        this.load();
    }

    public void createSuggestion(Suggestion newSuggestion) {
        suggestionMap.put(newSuggestion.getId(), newSuggestion);
    }

    public void deleteSuggestion(int suggestionID) {
        suggestionMap.remove(suggestionID);
    }

    public void updateSuggestion(Suggestion suggestion) {
        int suggestionID = suggestion.getId();
        if (suggestionMap.containsKey(suggestionID)) {
            suggestionMap.put(suggestionID, suggestion); // Replace the old suggestion with the new suggestion
        } else {
            System.out.println("ERROR: Suggestion with ID " + suggestionID + " does not exist.");
        }
    }

    public HashMap<Integer, Suggestion> getSuggestionMap() {
        return new HashMap<>(suggestionMap);
    }

    public Suggestion getSuggestion(int suggestionID) {
        return new Suggestion(suggestionMap.get(suggestionID));
    }

    public String getSuggestionTitle(int suggestionID) {
        return suggestionMap.get(suggestionID).getTitle();
    }

    public void setSuggestionTitle(int suggestionID, String title) {
        suggestionMap.get(suggestionID).setTitle(title);
    }

    public String getProcessedBy(int suggestionID) {
        return suggestionMap.get(suggestionID).getProcessedBy();
    }

    public void setProcessedBy(int suggestionID, String staffName) {
        suggestionMap.get(suggestionID).setProcessedBy(staffName);
    }

    public SuggestionStatus getSuggestionStatus(int suggestionID) {
        return suggestionMap.get(suggestionID).getSuggestionStatus();
    }

    public void setSuggestionStatus(int suggestionID, SuggestionStatus suggestionStatus) {
        suggestionMap.get(suggestionID).setSuggestionStatus(suggestionStatus);
    }

    public void save(){
        Serialize.save("suggestionMap.sav", suggestionMap);
    }

    public void load(){
        try{
            @SuppressWarnings("unchecked")
            HashMap<Integer, Suggestion> loadedMap = (HashMap<Integer, Suggestion>) Serialize.load("suggestionMap.sav");
            if (loadedMap != null) {
                suggestionMap = loadedMap;
            } else {
                suggestionMap = new HashMap<Integer, Suggestion>();
            }
        }
        catch(Exception ex){
            suggestionMap = new HashMap<Integer, Suggestion>();
        }
    }
}

