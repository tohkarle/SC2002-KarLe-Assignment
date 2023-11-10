package cams.model;
import java.util.ArrayList;

public class User {
    private int userID;
    private String name;
    private String password;
    private String faculty;
    private ArrayList<Integer> campIDs;
    
    public User(int userID, String name,String password, String faculty){
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
        this.campIDs = new ArrayList<Integer>();
    }

    public void addToCampIDsList(int campID){
        this.campIDs.add(campID);
    }

    public void removeCamp(int campID){
        this.campIDs.remove(campID);
    }   

    public ArrayList<Integer> getCampList(){
        return this.campIDs;
    }

    public String getName() {
        return this.name;
    }

    public int getUserID() {
        return this.userID;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public Boolean checkPassword(String password){
        return (this.password == password);
    }

    public void setPassword(String password){
        this.password = password;
    }
}
