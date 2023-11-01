mvn clean javafx:runpackage org.openjfx;

public class Business{

    private Business(){

    }

    public static Business createNewBO(){
        return new Business();
    }

    protected int validate(){
        return 100;
    }
    
    protected int saveFile(){
        return -100;
    }

    // Set methods
    protected void setAlienNumber(String newAlienNum){
    }

    protected void setRefNumber(String newRefNum){
    }


    protected void setFirstName(String firstName){
    }

    protected void setLastName(String lastName){
    }

    protected void setMiddleName(String[] middleName){
    }

    protected void setAddress(String address){
    }

    protected void setDOB(String dob){
    }

    // Get methods
    protected int getFile(String refNumber){
        return -100;
    }

    protected String getAlienNumber(){
        return "number";
    }
    
    protected String getFirstName(){
        return "supercalifragilisticexpialidocious";
    }

    protected String getLastName(){
        return "";
    }

    protected String[] getMiddleName(){
        return null;
    }

    protected String getAddress(){
        return "space";
    }

    protected String getRefNum(){
        return "-1";
    }

    protected String getDOB(){
        return "-1";
    }
 
}
