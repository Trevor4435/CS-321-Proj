package org.openjfx;

import java.util.ArrayList;

public class Business{

    private static ArrayList<Business> Database = new ArrayList<Business>();
    private Business openFile;

    private String alienNumber;
    private String refNumber;
    private String firstName;
    private String lastName;
    private String[] middleName;
    private String address;
    private String dob;
    
    private Business(){
    }

    public static Business createNewBO(){
        return new Business();
    }

    protected int validate(){
        return 100;
    }
    
    // If we ge tto the save step, we assume that everyone has run the validation prior. Meaning that we should only get valid data.
    protected int saveFile(){
        // If we are saving to a file we have already opened
        if(openFile != null){
            openFile.setRefNumber(this.refNumber);
            openFile.setAlienNumber(this.alienNumber);
            openFile.setFirstName(this.firstName);
            openFile.setLastName(this.lastName);
            openFile.setMiddleName(this.middleName);
            openFile.setAddress(this.address);
            openFile.setDOB(this.dob);
            return 1;
        }
        else{

            // Catch if at least one value has not been prooperly set for some reason.
            if(alienNumber == null || refNumber == null || firstName == null || lastName == null || middleName == null || address == null || dob == null){
                return -1;
            }

            Database.add(this);
            return 1;
        }
    }

    // Set methods
    protected void setAlienNumber(String newAlienNum){
        this.alienNumber = newAlienNum;
    }

    protected void setRefNumber(String newRefNum){
        this.refNumber = newRefNum;
    }


    protected void setFirstName(String newFirstName){
        this.firstName = newFirstName;
    }

    protected void setLastName(String newLastName){
        this.lastName = newLastName;
    }

    protected void setMiddleName(String[] newMiddleName){
        this.middleName = newMiddleName;
    }

    protected void setAddress(String newAddress){
        this.address = newAddress;
    }

    protected void setDOB(String newDob){
        this.dob = newDob;
    }

    // Get methods
    protected int getFile(String refNumber){
        Boolean found = false;

        // Just a quick null check
        if(refNumber == null){
            return -1;
        }

        // Iterate over the entire databse looking for the desired file
        for(Business data : Database){
            // Just for safety, we shouldnt need this empty check.
            if(Database.isEmpty()){
                return -1;
            }
            // If we find the desired file then make its data ours
            if(data.getRefNum().equals(refNumber)){
                found = true;
                openFile = data;
                this.alienNumber = data.getAlienNumber();
                this.refNumber = data.getRefNum();
                this.firstName = data.getFirstName();
                this.lastName = data.getLastName();
                this.middleName = data.getMiddleName();
                this.address = data.getAddress();
                this.dob = data.getDOB();
            }
        }

        // If we did find the file, return a success. If we didnt, return a failure.
        if(found){
            return 1;
        }
        else{
            return -1;
        }
    }

    protected String getAlienNumber(){
        return this.alienNumber;
    }
    
    protected String getFirstName(){
        return this.firstName;
    }

    protected String getLastName(){
        return this.lastName;
    }

    protected String[] getMiddleName(){
        return this.middleName;
    }

    protected String getAddress(){
        return this.address;
    }

    protected String getRefNum(){
        return this.refNumber;
    }

    protected String getDOB(){
        return this.dob;
    }
 
}
