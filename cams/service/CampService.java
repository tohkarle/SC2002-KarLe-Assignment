package cams.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import cams.model.Camp;
import cams.utils.Serialize;

/**
 * CampService is a service class that handles the reading, creation, deletion, and updating of Camp object save data
 * It is responsible for maintaining all Camp save data.
 */
public class CampService {

    private HashMap<Integer, Camp> campMap;

    /**
     * Initialize the CampService and load the campMap save data
     */
    public CampService(){
        Serialize.checkAndCreateFile("campMap.sav");
        this.load();
    }

    /**
     * Create a new Camp object in the campMap
     * @param newCamp The new Camp object to be created
     */
    public void createCamp(Camp newCamp) {
        campMap.put(newCamp.getId(), newCamp);
    }

    /**
     * Delete a Camp object from the campMap
     * @param campID The ID of the Camp object to be deleted
     */
    public void deleteCamp(int campID) {
        campMap.remove(campID);
    }

    /**
     * Retrieve a defensive copy of a Camp object from the campMap
     * @param campID The ID of the Camp object
     * @return The Camp object
     */
    // Creating defensive copies of Camp object so it does not affect the actual Camp object in the campMap
    public Camp getCamp(int campID) {
        return new Camp(campMap.get(campID));
    }

    /**
     * Get the name of a Camp object
     * @param camID The ID of the Camp object
     * @return The name of the Camp object
     */
    public String getCampName(int camID) {
        return campMap.get(camID).getCampName();
    }

    /**
     * Retrieve a defensive copy of the campMap
     * @return The campMap
     */
    public HashMap<Integer, Camp> getCampMap() {
        return new HashMap<>(campMap);
    }

    /**
     * Get a sorted list of all camps by campName
     * @return A list of Camp objects sorted by campName
     */
    public ArrayList<Camp> getAllCampsByNameSorted() {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            camps.add(new Camp(camp));
        }
        Collections.sort(camps, Comparator.comparing(Camp::getCampName));
        return camps;
    }

    /**
     * Get a sorted list of all camps a staff is in charge of by campName
     * @param staffName The name of the staff in charge of the camp
     * @return A list of Camp objects a staff is in charge sorted by campName
     */
    public ArrayList<Camp> getStaffCampsByNameSorted(String staffName) {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            if (camp.getStaffInCharge().equals(staffName)) {
                camps.add(new Camp(camp));
            }
        }
        Collections.sort(camps, Comparator.comparing(Camp::getCampName));
        return camps;
    }
    
    /**
     * Get a sorted list of all camps of a faculty by campName
     * @param faculty The name of the faculty
     * @return A list of Camp objects of a faculty sorted by campName
     */
    public ArrayList<Camp> getFacultyCampsByNameSorted(String faculty) {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            if ((camp.getUserGroup().equals(faculty) || camp.getUserGroup().equals("NTU")) && camp.getVisibility()) {
                camps.add(new Camp(camp));
            }
        }
        Collections.sort(camps, Comparator.comparing(Camp::getCampName));
        return camps;
    }
    
    /**
     * Get a sorted list of all camps a student is registered in by campName
     * @param studentName The name of the student
     * @return A list of Camp objects a student is registered in sorted by campName
     */
    public ArrayList<Camp> getRegisteredCampsByNameSorted(String studentName) {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            if ((camp.getParticipatingStudentNames().contains(studentName) || camp.getCommitteeMemberNames().contains(studentName)) && camp.getVisibility()) {
                camps.add(new Camp(camp));
            }
        }
        Collections.sort(camps, Comparator.comparing(Camp::getCampName));
        return camps;
    }

    /**
     * Update a Camp object's save data in the campMap
     * @param camp The Camp object to be updated
     */
    public void updateCamp(Camp camp) {
        int campID = camp.getId();
        if (campMap.containsKey(campID)) {
            campMap.put(campID, camp); // Replace the old camp with the new camp
        } else {
            System.out.println("ERROR: Camp with ID " + campID + " does not exist.");
        }
    }

    /**
     * Get the ID of a Camp object with the campName
     * @param name The name of the Camp object
     * @return The ID of the Camp object or -1 if the Camp object does not exist
     */
    public int getCampIDWithName(String name) {
        for (Camp camp : campMap.values()) {
            if (camp.getCampName().equals(name)) {
                return camp.getId();
            }
        }
        return -1;
    }

    /**
     * Get the list of participating students' names of a camp
     * @param campID The ID of the camp
     * @return The list of participating students' names
     */
    public ArrayList<String> getParticipatingStudentNames(int campID) {
        return campMap.get(campID).getParticipatingStudentNames();
    }

    /**
     * Get the list of withdrawn students' names of a camp
     * @param campID The ID of the camp
     * @return The list of withdrawn students' names
     */
    public ArrayList<String> getWithdrawnStudentNames(int campID) {
        return campMap.get(campID).getWithdrawnStudentNames();
    }

    /**
     * Get the list of committee members' names of a camp
     * @param campID The ID of the camp
     * @return The list of committee members' names
     */
    public ArrayList<String> getCommitteeMemberNames(int campID) {
        return campMap.get(campID).getCommitteeMemberNames();
    }

    /**
     * Get the list of enquiry IDs of a camp
     * @param campID The ID of the camp
     * @return The list of enquiry IDs
     */
    public ArrayList<Integer> getEnquiryIDs(int campID) {
        return campMap.get(campID).getEnquiryIDs();
    }

    /**
     * Get the list of suggestion IDs of a camp
     * @param campID The ID of the camp
     * @return The list of suggestion IDs
     */
    public ArrayList<Integer> getSuggestionIDs(int campID) {
        return campMap.get(campID).getSuggestionIDs();
    }
 
    /**
     * Save the campMap to the save file containing all Camp save data
     */
    public void save(){
        Serialize.save("campMap.sav", campMap);
    }

    /**
     * Load the campMap from the save file containing all Camp save data
     */
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
