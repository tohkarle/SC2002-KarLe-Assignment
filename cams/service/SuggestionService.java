package cams.service;
import java.util.HashMap;

import cams.model.Suggestion;
import cams.model.SuggestionStatus;
import cams.utils.Serialize;

/**
 * SuggestionService is a service class that handles the reading, creation, deletion, and updating of Suggestion object save data
 * It is responsible for maintaining all Suggestion save data
 */
public class SuggestionService {

    private HashMap<Integer, Suggestion> suggestionMap;

    /**
     * Initialize the SuggestionService and load the suggestionMap save data
     */
    public SuggestionService() {
        Serialize.checkAndCreateFile("suggestionMap.sav");
        this.load();
    }

    /**
     * Create a new Suggestion object in the suggestionMap
     * @param newSuggestion The new Suggestion object to be created
     */
    public void createSuggestion(Suggestion newSuggestion) {
        suggestionMap.put(newSuggestion.getId(), newSuggestion);
    }

    /**
     * Delete a Suggestion object from the suggestionMap
     * @param suggestionID The ID of the Suggestion object to be deleted
     */
    public void deleteSuggestion(int suggestionID) {
        suggestionMap.remove(suggestionID);
    }

    /**
     * Update a Suggestion object's save data in the suggestionMap
     * @param suggestion The Suggestion object to be updated
     */
    public void updateSuggestion(Suggestion suggestion) {
        int suggestionID = suggestion.getId();
        if (suggestionMap.containsKey(suggestionID)) {
            suggestionMap.put(suggestionID, suggestion); // Replace the old suggestion with the new suggestion
        } else {
            System.out.println("ERROR: Suggestion with ID " + suggestionID + " does not exist.");
        }
    }

    /**
     * Retrieve a defensive copy of the suggestionMap
     * @return The suggestionMap
     */
    public HashMap<Integer, Suggestion> getSuggestionMap() {
        return new HashMap<>(suggestionMap);
    }

    /**
     * Retrieve a defensive copy of a Suggestion object from the suggestionMap
     * @param suggestionID The ID of the Suggestion object
     * @return The Suggestion object
     */
    public Suggestion getSuggestion(int suggestionID) {
        return new Suggestion(suggestionMap.get(suggestionID));
    }

    /**
     * Get the title of a Suggestion object
     * @param suggestionID The ID of the Suggestion object
     * @return The title of the Suggestion object
     */
    public String getSuggestionTitle(int suggestionID) {
        return suggestionMap.get(suggestionID).getTitle();
    }

    /**
     * Set the title of a Suggestion object
     * @param suggestionID The ID of the Suggestion object
     * @param title The title of the Suggestion object
     */
    public void setSuggestionTitle(int suggestionID, String title) {
        suggestionMap.get(suggestionID).setTitle(title);
    }

    /**
     * Get the description of a Suggestion object
     * @param suggestionID The ID of the Suggestion object
     * @return The description of the Suggestion object
     */
    public String getProcessedBy(int suggestionID) {
        return suggestionMap.get(suggestionID).getProcessedBy();
    }

    /**
     * Set the description of a Suggestion object
     * @param suggestionID The ID of the Suggestion object
     * @param description The description of the Suggestion object
     */
    public void setProcessedBy(int suggestionID, String staffName) {
        suggestionMap.get(suggestionID).setProcessedBy(staffName);
    }

    /**
     * Get the status of a Suggestion object, whether it is pending, accepted or rejected
     * @param suggestionID The ID of the Suggestion object
     * @return The status of the Suggestion object
     */
    public SuggestionStatus getSuggestionStatus(int suggestionID) {
        return suggestionMap.get(suggestionID).getSuggestionStatus();
    }

    /**
     * Set the status of a Suggestion object, whether it is pending, accepted or rejected
     * @param suggestionID The ID of the Suggestion object
     * @param suggestionStatus The status of the Suggestion object
     */
    public void setSuggestionStatus(int suggestionID, SuggestionStatus suggestionStatus) {
        suggestionMap.get(suggestionID).setSuggestionStatus(suggestionStatus);
    }

    /**
     * Save the suggestionMap to the save file containing all Suggestion save data
     */
    public void save(){
        Serialize.save("suggestionMap.sav", suggestionMap);
    }

    /**
     * Load the suggestionMap from the save file containing all Suggestion save data
     */
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

