package cams.service;
import java.util.ArrayList;
import java.util.HashMap;

import cams.model.Camp;
import cams.utils.Serialize;

public class CampService {

    private HashMap<Integer, Camp> campMap = new HashMap<Integer, Camp>();

    public CampService(){
        Serialize.checkAndCreateFile("campMap.sav");
        this.load();
    }

    public void createCamp(Camp newCamp) {
        campMap.put(newCamp.getId(), newCamp);
    }

    public void deleteCamp(int campID) {
        campMap.remove(campID);
    }

    // Creating defensive copies of Camp object so it does not affect the actual Camp object in the campMap
    public Camp getCamp(int campID) {
        return new Camp(campMap.get(campID));
    }

    public String getCampName(int camID) {
        return campMap.get(camID).getCampName();
    }

    public HashMap<Integer, Camp> getCampMap() {
        return new HashMap<>(campMap);
    }

    public ArrayList<Camp> getAllCamps() {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            camps.add(new Camp(camp));
        }
        return camps;
    }
    
    public ArrayList<Camp> getStaffCamps(String staffName) {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            if (camp.getStaffInCharge().equals(staffName)) {
                camps.add(new Camp(camp));
            }
        }
        return camps;
    }
    
    public ArrayList<Camp> getFacultyCamps(String faculty) {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            if ((camp.getUserGroup().equals(faculty) || camp.getUserGroup().equals("NTU")) && camp.getVisibility()) {
                camps.add(new Camp(camp));
            }
        }
        return camps;
    }
    
    public ArrayList<Camp> getRegisteredCamps(String studentName) {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            if ((camp.getParticipatingStudentNames().contains(studentName) || camp.getCommitteeMemberNames().contains(studentName)) && camp.getVisibility()) {
                camps.add(new Camp(camp));
            }
        }
        return camps;
    }

    public void updateCamp(Camp camp) {
        int campID = camp.getId();
        if (campMap.containsKey(campID)) {
            campMap.put(campID, camp); // Replace the old camp with the new camp
        } else {
            System.out.println("ERROR: Camp with ID " + campID + " does not exist.");
        }
    }

    public boolean campNameAlreadyExists(String name) {
        for (Camp camp : campMap.values()) {
            if (camp.getCampName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getParticipatingStudentNames(int campID) {
        return campMap.get(campID).getParticipatingStudentNames();
    }

    public ArrayList<String> getWithdrawnStudentNames(int campID) {
        return campMap.get(campID).getWithdrawnStudentNames();
    }

    public ArrayList<String> getCommitteeMemberNames(int campID) {
        return campMap.get(campID).getCommitteeMemberNames();
    }

    public ArrayList<Integer> getEnquiryIDs(int campID) {
        return campMap.get(campID).getEnquiryIDs();
    }

    public ArrayList<Integer> getSuggestionIDs(int campID) {
        return campMap.get(campID).getSuggestionIDs();
    }
 
    public void save(){
        Serialize.save("campMap.sav", campMap);
    }

    public void load(){
        try{
            @SuppressWarnings("unchecked")
            HashMap<Integer, Camp> loadedMap = (HashMap<Integer, Camp>) Serialize.load("campMap.sav");
            if (loadedMap != null) {
                campMap = loadedMap;
            } else {
                campMap = new HashMap<Integer, Camp>();
            }
        }
        catch(Exception ex){
            campMap = new HashMap<Integer, Camp>();
        }
    }
}
