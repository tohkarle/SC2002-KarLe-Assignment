package cams.manager;
import java.util.HashMap;

import cams.model.Suggestion;
import cams.model.SuggestionStatus;
import cams.util.Serialize;
import cams.util.UniqueKey;

public class SuggestionManager {
    private Integer uniqueKey = 0;
    private HashMap<Integer, Suggestion> suggestionMap;

    public SuggestionManager() {
        Serialize.checkAndCreateFile("SuggestionMangerKey.sav");
        Serialize.checkAndCreateFile("suggestionMap.sav");
    }

    public void createSuggestion(String studentID, int campID, String content) {
        // Create new suggestion and add to suggestionMap
        int key = UniqueKey.generateNewKey(uniqueKey);
        while(suggestionMap.get(key) != null) key = UniqueKey.generateNewKey(uniqueKey);
        suggestionMap.put(key, new Suggestion(key, campID, studentID, content));
    }

    public void editSuggestion(int suggestionID, String content) {
        suggestionMap.get(suggestionID).setContent(content);
    }

    public void deleteSuggestion(int suggestionID){
        suggestionMap.remove(suggestionID);
    }

    public void processSuggestion(String staffID, int suggestionID, SuggestionStatus suggestionStatus) {
        Suggestion suggestion = suggestionMap.get(suggestionID);
        suggestion.setSuggestionStatus(suggestionStatus);
        suggestion.setProcessedBy(staffID);
    }

    public String getSuggestionContent(int suggestionID) {
        return suggestionMap.get(suggestionID).getContent();
    }

    public void save(){
        Serialize.save("SuggestionMangerKey.sav", uniqueKey);
        Serialize.save("suggestionMap.sav", suggestionMap);
    }

    public void load(){
        try{
            uniqueKey = (Integer) Serialize.load("SuggestionMangerKey.sav");
            @SuppressWarnings("unchecked")
            HashMap<Integer, Suggestion> loadedMap = (HashMap<Integer, Suggestion>) Serialize.load("suggestionMap.sav");
            suggestionMap = loadedMap;
        }
        catch(Exception ex){
            uniqueKey = 0;
            suggestionMap = new HashMap<Integer, Suggestion>();
        }
    }
}

