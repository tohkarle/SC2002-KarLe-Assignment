package cams.manager;

import java.util.ArrayList;

import cams.model.Enquiry;
import cams.service.EnquiryService;
import cams.utils.UniqueKey;


/**
 * A high level controller for the enquiry objects,
 * is a singleton object
 */
public class EnquiryManager {

    /**
     * A singleton reference to this object
     */
    private static EnquiryManager instance;

    /**
     * A sigleton reference to the enquiry service object
     */
    private EnquiryService enquiryService;


    /**
     * Initialize this object
     */
    private EnquiryManager() {
        this.enquiryService = new EnquiryService();
    }


    /**
     * A public static method to get this object
     * @return EnquiryManager object, this object
     */
    public static EnquiryManager getInstance() {
        if (instance == null) {
            instance = new EnquiryManager();
        }
        return instance;
    }


    /**
     * A method to update an enquiry object
     * @param enquiry The new enquiry object
     */
    public void updateEnquiry(Enquiry enquiry) {
        enquiryService.updateEnquiry(enquiry);
        enquiryService.save();
    }


    /**
     * A method to get a copy of the enquiry object stored in memory
     * @param enquiryID The ID of the enquiry object
     * @return Enquiry object, only a copy
     */
    public Enquiry getEnquiry(int enquiryID) {
        return enquiryService.getEnquiry(enquiryID);
    }


    /**
     * A method to create a new enquiry
     * @param studentName The name of the user that wrote the enquiry
     * @param campID The ID of the camp
     * @param title The title of the enquiry
     * @param content The enquiry content
     */
    public void createEnquiry(String studentName, int campID, String title, String content){
        // Create new enquiry and add to enquiryMap
        int uniqueKey = 0;
        uniqueKey = UniqueKey.generateNewKey(uniqueKey);
        while(enquiryService.getEnquiryMap().get(uniqueKey) != null) uniqueKey = UniqueKey.generateNewKey(uniqueKey);
        Enquiry newEnquiry = new Enquiry(uniqueKey, studentName, campID, title, content);
        enquiryService.createEnquiry(newEnquiry);
        enquiryService.save();
    }


    /**
     * A method to edit an enquiry object via ID
     * @param enquiryID The ID of the enquiry
     * @param content The new content of the enquiry
     */
    public void editEnquiry(int enquiryID, String content) {
        // Cannot edit after enquiry is resolved
        if (enquiryService.getIsResolved(enquiryID)) { 
            System.out.println("Enquiry has already been resolved.");
            return; 
        }
        enquiryService.setContent(enquiryID, content);
        enquiryService.save();
    }


    /**
     * A method to delete the spedified enquiry via ID
     * @param enquiryID The ID of the enquiry
     */
    public void deleteEnquiry(int enquiryID) {
        enquiryService.deleteEnquiry(enquiryID);
        enquiryService.save();
    }


    /**
     * A method to mark an enquiry as resolved
     * @param userName The userID of the resolver
     * @param enquiryID The ID of the enquiry
     * @param reply The reply string to the enquiry
     */
    public void resolveEnquiry(String userName, int enquiryID, String reply) {
        // Cannot resolve again after enquiry is resolved
        if (enquiryService.getIsResolved(enquiryID)) { 
            System.out.println("Enquiry has already been resolved.");
            return; 
        }
        enquiryService.setReply(enquiryID, reply);
        enquiryService.setIsResolved(enquiryID, true);
        enquiryService.setResolvedBy(enquiryID, userName);
    }


    /**
     * A method to get all the enquiry titles a specified student has written for the specified camp
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @return ArrayList<String> of enquiry titles
     */
    public ArrayList<String> getAllStudentCampEnquiryTitles(String studentName, int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getStudentName().equals(studentName)) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }


    /**
     * A method to get the ID of all the enquiry IDs the specified student has written for a specified camp
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @return ArrayList<Integer> of enquiry IDs
     */
    public ArrayList<Integer> getAllStudentCampEnquiryIDs(String studentName, int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getStudentName().equals(studentName)) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }


    /**
     * A method to get all the enquiry titles a user has resolved for specified camp
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @return ArrayList<String> of the titles of enquirires
     */
    public ArrayList<String> getResolvedStudentCampEnquiryTitles(String studentName, int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getStudentName().equals(studentName) && enquiry.getIsResolved()) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }
    

    /**
     * A method to get the enquiry IDs resolved by specified user, for specified camp
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @return ArrayList<Integer> of the enquiry IDs
     */
    public ArrayList<Integer> getResolvedStudentCampEnquiryIDs(String studentName, int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getStudentName().equals(studentName) && enquiry.getIsResolved()) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }
    

    /**
     * A method to get the titles of the enquiries not yet resolved for specified user, for specified camp
     * @param studentName The name of the user
     * @param campID The ID of the camp
     * @return ArrayList<String> of enquiry titles
     */
    public ArrayList<String> getNotResolvedStudentCampEnquiryTitles(String studentName, int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getStudentName().equals(studentName) && !enquiry.getIsResolved()) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }
    

    /**
     * A method to get the IDs of the enquiries not yet resolved for specified user, for specified camp
     * @param studentName The name of the user
     * @param campID The ID of the camp
     * @return ArrayList<Integer> of enquiry IDs
     */
    public ArrayList<Integer> getNotResolvedStudentCampEnquiryIDs(String studentName, int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getStudentName().equals(studentName) && !enquiry.getIsResolved()) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }


    /**
     * A method to get all enquiry titles for specified camp
     * @param campID The ID of the camp
     * @return ArrayList<String> of all the enquiry titles for the camp
     */
    public ArrayList<String> getAllCampEnquiryTitles(int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }


    /**
     * A method to get all enquiry IDs for specified camp
     * @param campID The ID of the camp
     * @return ArrayList<Integer> of all the enquiry IDs for the camp
     */
    public ArrayList<Integer> getAllCampEnquiryIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }


    /**
     * A method to get all the resolved enquiry titles for a specified camp
     * @param campID The ID of the camp
     * @return ArrayList<String> of the resolved enquiry titles
     */
    public ArrayList<String> getResolvedCampEnquiryTitles(int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getIsResolved()) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }
    

    /**
     * A method to get all the resolved enquiry IDs for a specified camp
     * @param campID The ID of the camp
     * @return ArrayList<Integer> of the resolved enquiry IDs
     */
    public ArrayList<Integer> getResolvedCampEnquiryIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getIsResolved()) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }
    

    /**
     * A method to get all the unresolved enquiry titles for a specified camp
     * @param campID The ID of the camp
     * @return ArrayList<String> of the unresolved enquiry titles
     */
    public ArrayList<String> getNotResolvedCampEnquiryTitles(int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && !enquiry.getIsResolved()) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }


    /**
     * A method to get all the unresolved enquiry IDs for a specified camp
     * @param campID The ID of the camp
     * @return ArrayList<Integer> of the unresolved enquiry IDs
     */
    public ArrayList<Integer> getNotResolvedCampEnquiryIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && !enquiry.getIsResolved()) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }


    /**
     * A method to get all enquiry titles for specified user
     * @param studentName The name of the student
     * @return ArrayList<String> of the enquiry titles
     */
    public ArrayList<String> getAllStudentEnquiryTitles(String studentName) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getStudentName().equals(studentName)) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }


    /**
     * A method to get all enquiry IDs for specified user
     * @param studentName The name of the student
     * @return ArrayList<Integer> of the enquiry IDs
     */
    public ArrayList<Integer> getAllStudentEnquiryIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getStudentName().equals(studentName)) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }


    /**
     * A method to get all the resolved enquiry titles for specified user
     * @param studentName The name of the student
     * @return ArrayList<String> of the resolved enquiry titles
     */
    public ArrayList<String> getResolvedStudentEnquiryTitles(String studentName) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getStudentName().equals(studentName) && enquiry.getIsResolved()) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }


    /**
     * A method to get all the resolved enquiry IDs for specified user
     * @param studentName The name of the student
     * @return ArrayList<Integer> of the resolved enquiry IDs
     */
    public ArrayList<Integer> getResolvedStudentEnquiryIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getStudentName().equals(studentName) && enquiry.getIsResolved()) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }


    /**
     * A method to get all the unresolved enquiry titles for specified user
     * @param studentName The name of the student
     * @return ArrayList<String> of the unresolved enquiry titles
     */
    public ArrayList<String> getNotResolvedStudentEnquiryTitles(String studentName) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getStudentName().equals(studentName) && !enquiry.getIsResolved()) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }


    /**
     * A method to get all the unresolved enquiry IDs for specified user
     * @param studentName The name of the student
     * @return ArrayList<Integer> of the unresolved enquiry ID's
     */
    public ArrayList<Integer> getNotResolvedStudentEnquiryIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getStudentName().equals(studentName) && !enquiry.getIsResolved()) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }


    /**
     * A method to get the number of replies a student has made
     * @param The name of the student
     * @return int of the number of replies a student has made
     */
    public int getNumberOfEnquiriesReplied(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            String resolvedBy = enquiry.getResolvedBy();
            if (resolvedBy != null && resolvedBy.equals(studentName) && enquiry.getIsResolved() && enquiry.getReply() != null) {
                ids.add(enquiry.getId());
            }
        }
        return ids.size();
    }
}
