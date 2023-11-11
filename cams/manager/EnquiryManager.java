package cams.manager;

import java.util.HashMap;

import cams.model.Enquiry;
import cams.util.Serialize;
import cams.util.UniqueKey;

public class EnquiryManager {

    private Integer uniqueKey = 0;
    private HashMap<Integer, Enquiry> enquiryMap; // the Enquiry objects stored in a map

    public EnquiryManager(){
        Serialize.checkAndCreateFile("EnquiryMangerKey.sav");
        Serialize.checkAndCreateFile("enquiryMap.sav");
    }

    public void createEnquiry(String studentID, int campID, String content){
        // Create new enquiry and add to enquiryMap
        this.uniqueKey = UniqueKey.generateNewKey(this.uniqueKey);
        while(enquiryMap.get(this.uniqueKey) != null) this.uniqueKey = UniqueKey.generateNewKey(this.uniqueKey);
        enquiryMap.put(this.uniqueKey, new Enquiry(this.uniqueKey, studentID, campID, content));
    }

    public void editEnquiry(int enquiryID, String content) {

        Enquiry enquiry = enquiryMap.get(enquiryID);

        // Cannot edit after enquiry is resolved
        if (enquiry.isResolved()) { return; }

        enquiry.setContent(content);
    }

    public void deleteEnquiry(int enquiryID) {
        enquiryMap.remove(enquiryID);
    }

    public void resolveEnquiry(String userID, int enquiryID, String reply) {
        Enquiry enquiry = enquiryMap.get(enquiryID);

        // Cannot resolve again after enquiry is resolved
        if (enquiry.isResolved()) { return; }

        enquiry.setReply(reply);
        enquiry.setResolvedBy(userID);
        enquiry.setResolved();
    }

    public String getEnquiryContent(int enquiryID) {
        return enquiryMap.get(enquiryID).getContent();
    }

    public String getEnquiryReply(int enquiryID) {
        return enquiryMap.get(enquiryID).getReply();
    }

    public String getStudentID(int enquiryID){
        return enquiryMap.get(enquiryID).getStudentID();
    }

    public void save(){
        Serialize.save("EnquiryMangerKey.sav", uniqueKey);
        Serialize.save("enquiryMap.sav", enquiryMap);
    }

    public void load(){
        try{
            uniqueKey = (Integer)Serialize.load("EnquiryMangerKey.sav");
            @SuppressWarnings("unchecked")
            HashMap<Integer, Enquiry> loadedMap = (HashMap<Integer, Enquiry>)Serialize.load("enquiryMap.sav");
            enquiryMap = loadedMap;
        }
        catch(Exception ex){
            uniqueKey = 0;
            enquiryMap = new HashMap<Integer, Enquiry>();
        }
    }
}
