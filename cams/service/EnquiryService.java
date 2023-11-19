package cams.service;

import java.util.HashMap;

import cams.model.Enquiry;
import cams.utils.Serialize;

public class EnquiryService {

    private HashMap<Integer, Enquiry> enquiryMap; // the Enquiry objects stored in a map

    public EnquiryService(){
        Serialize.checkAndCreateFile("enquiryMap.sav");
        this.load();
    }

    public HashMap<Integer, Enquiry> getEnquiryMap() {
        return new HashMap<>(enquiryMap);
    }

    public Enquiry getEnquiry(int enquiryID) {
        return new Enquiry(enquiryMap.get(enquiryID));
    }

    public void updateEnquiry(Enquiry enquiry) {
        int enquiryID = enquiry.getId();
        if (enquiryMap.containsKey(enquiryID)) {
            enquiryMap.put(enquiryID, enquiry); // Replace the old enquiry with the new enquiry
        } else {
            System.out.println("ERROR: Enquiry with ID " + enquiryID + " does not exist.");
        }
    }

    public void createEnquiry(Enquiry newEnquiry){
        enquiryMap.put(newEnquiry.getId(), newEnquiry);
    }

    public void deleteEnquiry(int enquiryID) {
        enquiryMap.remove(enquiryID);
    }


    public boolean getIsResolved(int enquiryID) {
        return enquiryMap.get(enquiryID).getIsResolved();
    }

    public void setIsResolved(int enquiryID, boolean isResolved) {
        enquiryMap.get(enquiryID).setIsResolved(isResolved);
    }

    public String getResolvedBy(int enquiryID) {
        return enquiryMap.get(enquiryID).getResolvedBy();
    }

    public void setResolvedBy(int enquiryID, String userName) {
        enquiryMap.get(enquiryID).setResolvedBy(userName);
    }

    public String getContent(int enquiryID) {
        return enquiryMap.get(enquiryID).getContent();
    }

    public void setContent(int enquiryID, String content) {
        enquiryMap.get(enquiryID).setContent(content);
    }

    public String getReply(int enquiryID) {
        return enquiryMap.get(enquiryID).getReply();
    }

    public void setReply(int enquiryID, String reply) {
        enquiryMap.get(enquiryID).setReply(reply);
    }


    public void save(){
        Serialize.save("enquiryMap.sav", enquiryMap);
    }

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
