package cams.service;

import java.util.HashMap;

import cams.model.Enquiry;
import cams.utils.Serialize;

/**
 * EnquiryService is a service class that handles the reading, creation, deletion, and updating of Enquiry object save data
 * It is responsible for maintaining all Enquiry save data
 */
public class EnquiryService {

    private HashMap<Integer, Enquiry> enquiryMap;

    /**
     * Initialize the EnquiryService and load the enquiryMap save data
     */
    public EnquiryService(){
        Serialize.checkAndCreateFile("enquiryMap.sav");
        this.load();
    }

    /**
     * Retrieve a defensive copy of the enquiryMap
     * @return The enquiryMap
     */
    public HashMap<Integer, Enquiry> getEnquiryMap() {
        return new HashMap<>(enquiryMap);
    }

    /**
     * Retrieve a defensive copy of an Enquiry object from the enquiryMap
     * @param enquiryID The ID of the Enquiry object
     * @return The Enquiry object
     */
    public Enquiry getEnquiry(int enquiryID) {
        return new Enquiry(enquiryMap.get(enquiryID));
    }

    /**
     * Update an Enquiry object's save data in the enquiryMap
     * @param enquiry The Enquiry object to be updated
     */
    public void updateEnquiry(Enquiry enquiry) {
        int enquiryID = enquiry.getId();
        if (enquiryMap.containsKey(enquiryID)) {
            enquiryMap.put(enquiryID, enquiry); // Replace the old enquiry with the new enquiry
        } else {
            System.out.println("ERROR: Enquiry with ID " + enquiryID + " does not exist.");
        }
    }

    /**
     * Create a new Enquiry object in the enquiryMap
     * @param newEnquiry The new Enquiry object to be created
     */
    public void createEnquiry(Enquiry newEnquiry){
        enquiryMap.put(newEnquiry.getId(), newEnquiry);
    }

    /**
     * Delete an Enquiry object from the enquiryMap
     * @param enquiryID The ID of the Enquiry object to be deleted
     */
    public void deleteEnquiry(int enquiryID) {
        enquiryMap.remove(enquiryID);
    }

    /**
     * Check if an Enquiry has been resolved
     * @param enquiryID The ID of the Enquiry object to be checked
     * @return boolean of whether the Enquiry has been resolved
     */
    public boolean getIsResolved(int enquiryID) {
        return enquiryMap.get(enquiryID).getIsResolved();
    }

    /**
     * Set the resolved status of an Enquiry
     * @param enquiryID The ID of the Enquiry object to be updated
     * @param isResolved The resolved status of the Enquiry
     */
    public void setIsResolved(int enquiryID, boolean isResolved) {
        enquiryMap.get(enquiryID).setIsResolved(isResolved);
    }

    /**
     * Get the name of the user who resolved an Enquiry
     * @param enquiryID The ID of the Enquiry object to be checked
     * @return The name of the user who resolved the Enquiry
     */
    public String getResolvedBy(int enquiryID) {
        return enquiryMap.get(enquiryID).getResolvedBy();
    }

    /**
     * Set the name of the user who resolved an Enquiry
     * @param enquiryID The ID of the Enquiry object to be updated
     * @param userName The name of the user who resolved the Enquiry
     */
    public void setResolvedBy(int enquiryID, String userName) {
        enquiryMap.get(enquiryID).setResolvedBy(userName);
    }

    /**
     * Get the content of an Enquiry
     * @param enquiryID The ID of the Enquiry object to be checked
     * @return The content of the Enquiry
     */
    public String getContent(int enquiryID) {
        return enquiryMap.get(enquiryID).getContent();
    }

    /**
     * Set the content of an Enquiry
     * @param enquiryID The ID of the Enquiry object to be updated
     * @param content The content of the Enquiry
     */
    public void setContent(int enquiryID, String content) {
        enquiryMap.get(enquiryID).setContent(content);
    }

    /**
     * Get the reply of an Enquiry
     * @param enquiryID The ID of the Enquiry object to be checked
     * @return The reply of the Enquiry
     */
    public String getReply(int enquiryID) {
        return enquiryMap.get(enquiryID).getReply();
    }

    /**
     * Set the reply of an Enquiry
     * @param enquiryID The ID of the Enquiry object to be updated
     * @param reply The reply of the Enquiry
     */
    public void setReply(int enquiryID, String reply) {
        enquiryMap.get(enquiryID).setReply(reply);
    }

    /**
     * Save the enquiryMap to the save file containing all Enquiry save data
     */
    public void save(){
        Serialize.save("enquiryMap.sav", enquiryMap);
    }

    /**
     * Load the enquiryMap from the save file containing all Enquiry save data
     */
    public void load(){
        try{
            @SuppressWarnings("unchecked")
            HashMap<Integer, Enquiry> loadedMap = (HashMap<Integer, Enquiry>)Serialize.load("enquiryMap.sav");
            enquiryMap = loadedMap;
            if (loadedMap != null) {
                enquiryMap = loadedMap;
            } else {
                enquiryMap = new HashMap<Integer, Enquiry>();
            }
        }
        catch(Exception ex){
            enquiryMap = new HashMap<Integer, Enquiry>();
        }
    }
}
