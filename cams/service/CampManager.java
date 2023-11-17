package cams.service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

import cams.model.Camp;
import cams.utils.Serialize;
import cams.utils.UniqueKey;

public class CampManager {

    private int uniqueKey = 0;
    private HashMap<Integer, Camp> campMap = new HashMap<Integer, Camp>();

    public CampManager(){
        Serialize.checkAndCreateFile("CampManagerKey.sav");
        Serialize.checkAndCreateFile("campMap.sav");
        this.load();
    }

    public int createCamp(int staffID, String campName, ArrayList<LocalDate> dates, String faculty, boolean visibility) {
        // Set registration closing date to be 45 days after date of creation
        LocalDate registrationClosingDate = LocalDate.now().plus(45, ChronoUnit.DAYS);

        this.uniqueKey = UniqueKey.generateNewKey(this.uniqueKey);
        while(campMap.get(this.uniqueKey) != null) this.uniqueKey = UniqueKey.generateNewKey(this.uniqueKey);
        Camp newCamp = new Camp(this.uniqueKey, campName, dates, faculty, visibility, staffID, registrationClosingDate);
        campMap.put(this.uniqueKey, newCamp);
        return this.uniqueKey;
    }

    public void deleteCamp(int campID) {
        campMap.remove(campID);
    }

    // Creating defensive copies of Camp object so it does not affect the actual Camp object in the campMap
    public ArrayList<Camp> getAllCamps() {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            camps.add(new Camp(camp));
        }
        return camps;
    }
    
    public ArrayList<Camp> getStaffCamps(int staffID) {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            if (camp.getStaffInCharge() == staffID) {
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
    
    public ArrayList<Camp> getRegisteredCamps(int studentID) {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            if ((camp.getParticipatingStudentIDs().contains(studentID) || camp.getCommitteeMemberIDs().contains(studentID)) && camp.getVisibility()) {
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
 
    public void save(){
        Serialize.save("CampManagerKey.sav", uniqueKey);
        Serialize.save("campMap.sav", campMap);
    }

    public void load(){
        try{
            uniqueKey = (Integer)Serialize.load("CampManagerKey.sav");
            @SuppressWarnings("unchecked")
            HashMap<Integer, Camp> loadedMap = (HashMap<Integer, Camp>) Serialize.load("campMap.sav");
            campMap = loadedMap;
        }
        catch(Exception ex){
            uniqueKey = 0;
            campMap = new HashMap<Integer, Camp>();
        }
    }
}
