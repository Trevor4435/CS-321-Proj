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
        

         if(  refNumber == null || !isNumeric(refNumber) || refNumber.length() == 0){
            return -1;
        }
        
        if(alienNumber == null || !isNumeric(alienNumber) || alienNumber.length() != 9){
            return -2;
        }
        
        if(  lastName == null || !isAlpha(lastName) || lastName.length() == 0){
            return -3;
        }


         if(  firstName == null || !isAlpha(firstName) || firstName.length() == 0){
            return -4;
        }
    
       

        if( middleName == null || !checkMiddleName() ){  
            return -5;
        }
        

        if( address == null || address.length() == 0 || !checkAddress()){
            return -6;
        }

        if( dob == null || !checkDOB() || dob.length() == 0){
            return -7;
        }

     
        return 1;
    }
    
    //is date valid?
    private boolean checkDOB(){

        if(dob.length() != 10){
            return false;
        }
        
       
        String month = dob.substring(0,2);
        String day = dob.substring(3,5);
        String year = dob.substring(6);
        
        if(dob.charAt(2) != '/' || dob.charAt(5) !='/'){
            return false;
        }
        if(!isNumeric(month) || !isNumeric(day) || !isNumeric(year) ){
            return false;
        }

        Integer m = Integer.parseInt(month);
        Integer d = Integer.parseInt(day);
        Integer y = Integer.parseInt(year);
        
        //are dates in range?
        if((m < 1 || m > 12) || (y < 0 || y > 2023) ){
            
            return false;
        }
        
        int [] mon = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(d < 1 || d > mon[m-1]){
            return false;
        }

        return true;
    }


    private boolean checkAddress(){

        for( int i = 0; i< address.length() ; i++){
            if(!Character.isLetterOrDigit(address.charAt(i)) && (address.charAt(i) != ' ') && (address.charAt(i) != ',')  ){
                return false;
            }
        }

        return true;
    }

    private boolean isNumeric(String input) {
        if (input == null) {
            return false;
        }
        try {
            Integer i = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
   


    private boolean checkMiddleName(){

        if(this.middleName.length == 0){
            return true;
        }
        for( int i = 0; i< this.middleName.length; i++){
        
        if(!isAlpha(this.middleName[i])){
            return false;
        }

    }
    
        return true;
    }

    private boolean isAlpha(String input){

        for(int i =0; i< input.length(); i++){
            if(!(Character.isAlphabetic(input.charAt(i)))){
            
                return false;
            }
        }
        return true;
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
