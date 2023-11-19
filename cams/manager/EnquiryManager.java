package cams.manager;

import java.util.ArrayList;

import cams.model.Enquiry;
import cams.service.EnquiryService;
import cams.utils.UniqueKey;

public class EnquiryManager {

    private int selectedEnquiryID;
    private boolean viewResolvedEnquiries;
    private EnquiryService enquiryService;
    private Enquiry tempEnquiry;

    public EnquiryManager(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    public void updateEnquiry(Enquiry enquiry) {
        enquiryService.updateEnquiry(enquiry);
        enquiryService.save();
    }

    public void createTempEnquiry() {
        this.tempEnquiry = enquiryService.getEnquiry(selectedEnquiryID);
    }

    public Enquiry getTempEnquiry() {
        return this.tempEnquiry;
    }

    public int getSelectedEnquiryID() {
        return this.selectedEnquiryID;
    }

    public void setSelectedEnquiryID(int option) {
        this.selectedEnquiryID = option;
        this.createTempEnquiry();
    }

    public boolean getViewResolvedEnquiries() {
        return this.viewResolvedEnquiries;
    }

    public void setViewResolvedEnquiries(boolean viewResolvedEnquiries) {
        this.viewResolvedEnquiries = viewResolvedEnquiries;
    }

    public void createEnquiry(String studentName, int campID, String title, String content){
        // Create new enquiry and add to enquiryMap
        int uniqueKey = 0;
        uniqueKey = UniqueKey.generateNewKey(uniqueKey);
        while(enquiryService.getEnquiryMap().get(uniqueKey) != null) uniqueKey = UniqueKey.generateNewKey(uniqueKey);
        Enquiry newEnquiry = new Enquiry(uniqueKey, studentName, campID, title, content);
        enquiryService.createEnquiry(newEnquiry);
        enquiryService.save();
    }

    public void editEnquiry(int enquiryID, String content) {
        // Cannot edit after enquiry is resolved
        if (enquiryService.getIsResolved(enquiryID)) { 
            System.out.println("Enquiry has already been resolved.");
            return; 
        }
        enquiryService.setContent(enquiryID, content);
        enquiryService.save();
    }

    public void deleteEnquiry(int enquiryID) {
        enquiryService.deleteEnquiry(enquiryID);
        enquiryService.save();
    }

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





    public ArrayList<String> getAllStudentCampEnquiryTitles(String studentName, int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getStudentName().equals(studentName)) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }

    public ArrayList<Integer> getAllStudentCampEnquiryIDs(String studentName, int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getStudentName().equals(studentName)) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }

    public ArrayList<String> getResolvedStudentCampEnquiryTitles(String studentName, int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getStudentName().equals(studentName) && enquiry.getIsResolved()) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getResolvedStudentCampEnquiryIDs(String studentName, int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getStudentName().equals(studentName) && enquiry.getIsResolved()) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }
    
    public ArrayList<String> getNotResolvedStudentCampEnquiryTitles(String studentName, int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getStudentName().equals(studentName) && !enquiry.getIsResolved()) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getNotResolvedStudentCampEnquiryIDs(String studentName, int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getStudentName().equals(studentName) && !enquiry.getIsResolved()) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }






    public ArrayList<String> getAllCampEnquiryTitles(int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }

    public ArrayList<Integer> getAllCampEnquiryIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }

    public ArrayList<String> getResolvedCampEnquiryTitles(int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getIsResolved()) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getResolvedCampEnquiryIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && enquiry.getIsResolved()) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }
    
    public ArrayList<String> getNotResolvedCampEnquiryTitles(int campID) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && !enquiry.getIsResolved()) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }
    
    public ArrayList<Integer> getNotResolvedCampEnquiryIDs(int campID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getCampID() == campID && !enquiry.getIsResolved()) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }





    public ArrayList<String> getAllStudentEnquiryTitles(String studentName) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getStudentName().equals(studentName)) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }

    public ArrayList<Integer> getAllStudentEnquiryIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getStudentName().equals(studentName)) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }

    public ArrayList<String> getResolvedStudentEnquiryTitles(String studentName) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getStudentName().equals(studentName) && enquiry.getIsResolved()) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }

    public ArrayList<Integer> getResolvedStudentEnquiryIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getStudentName().equals(studentName) && enquiry.getIsResolved()) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }

    public ArrayList<String> getNotResolvedStudentEnquiryTitles(String studentName) {
        ArrayList<String> titles = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getStudentName().equals(studentName) && !enquiry.getIsResolved()) {
                titles.add(enquiry.getTitle());
            }
        }
        return titles;
    }

    public ArrayList<Integer> getNotResolvedStudentEnquiryIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Enquiry enquiry : enquiryService.getEnquiryMap().values()) {
            if (enquiry.getStudentName().equals(studentName) && !enquiry.getIsResolved()) {
                ids.add(enquiry.getId());
            }
        }
        return ids;
    }
}
