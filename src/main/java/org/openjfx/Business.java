package org.openjfx;

public class Business{

    
    public String alienNumber;
    public String refNumber;
    public String firstName;
    public String lastName;
    public String[] middleName;
    public String address;
    public String dob;
    
    private Business(){
    }

    public static Business createNewBO(){
        return new Business();
    }

    protected int validate(){ //Nicole?
        return 100;
    }
    
    protected int saveFile(){
        return -100;
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
        return -100;
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
