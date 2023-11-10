package cams.model;
import java.util.ArrayList;

public abstract class User {
    private String name;
    private String userID;
    private String password;
    private String faculty;
    private ArrayList<Integer> campIDs;
    
    public User(String name, String ID, String info){
        this.name = name;
        this.userID = ID;
        this.password = "password";
        this.faculty = info;
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

    public String getUserID() {
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
