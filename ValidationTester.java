import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

public class ValidationTester {

    @Test
    @DisplayName("Attempt to create new BO.")
    void newBOTest(){
        Business house = Business.createNewBO();
        assertTrue(house instanceof Business);
    }

    @Test
    @DisplayName("Attempt to set a name to the business object.")
    void setBOName(){
        Business house = Business.createNewBO();
        //house.setFirstName("John");
        String output = house.getFirstName();
        assertTrue(output.equals("John"));
    }
    
    @Test
    @DisplayName("Check if return value of save file is valid")
    void saveFileBOTest1(){
        Business house = Business.createNewBO();
        int output = house.saveFile();
        assertTrue(output>0);
    }

    @Test
    @DisplayName("Attempt to save a file to BO.")
    void saveFileBOTest2(){
        Business house = Business.createNewBO();
        int output = house.saveFile();
        assertNull(output);
    }



    @Test
    @DisplayName("Attempt to get a null file to BO.")
    void getFileBOTest1(){
        Business house = Business.createNewBO();
        int output = house.getFile(null);
        assertTrue(output == -1);
    }

    @Test
    @DisplayName("Attempt to get a file to BO.")
    void getFileBOTest2(){
        Business house = Business.createNewBO();
        int output = house.getFile("1");
        assertTrue(output>0);
    }

    @Test
    @DisplayName("Attempt to get a invalidly named file to BO.")
    void getFileBOTest3(){
        Business house = Business.createNewBO();
        int output = house.getFile("meow");
        assertTrue(output>0);
    }


    @Test
    @DisplayName("Invalid Reference number to WorkFlow")
    void invalidWorkRef(){
        int status = WorkFlow.addWorkFlow("-1", "Reviewer");
        assertTrue(status == -1);
    }

    @Test
    @DisplayName("Invalid Employee to Workflow")
    void invalidWorkEmployee(){
        int status = WorkFlow.addWorkFlow("123", "Janitorial");
        assertTrue(status == -1);
    }

    @Test
    @DisplayName("Null Reference Number")
    void nullWorkRef(){
        int status = WorkFlow.addWorkFlow(null, "Reviewer");
        assertTrue(status == -1);
    }

    @Test
    @DisplayName("Null Employee")
    void nullWorkEmployee(){
        int status = WorkFlow.addWorkFlow("123", null);
        assertTrue(status == -1);
    }

    @Test
    @DisplayName("Both Null Workflow Input")
    void nullWorkAdd(){
        int status = WorkFlow.addWorkFlow(null, null);
        assertTrue(status == -1);
    }

    


    @Test
    @DisplayName("Attempt to add to the Reviewer's work load.")
    void addReviewWork(){
        int status = WorkFlow.addWorkFlow("123", "Reviewer");
        assertTrue(status == 1);
    }

    @Test
    @DisplayName("Attempt to read back that work.")
    void pullReviewWork(){
        String output = WorkFlow.getNextRef("Reviewer");
        assertTrue(output.equals("123"));
    }

    @Test
    @DisplayName("Attempt to read an empty Reviewer list.")
    void pullEmptyReview(){
        String output = WorkFlow.getNextRef("Reviewer");
        assertTrue(output.equals("Coffee Time"));
    }

    @Test
    @DisplayName("Attempt to add to the Approver's work load")
    void addApproverWork(){
        int status = WorkFlow.addWorkFlow("125", "Approver");
        assertTrue(status == 1);
    }

    @Test
    @DisplayName("Attempt to read back that work")
    void pullApproverWork(){
        String output = WorkFlow.getNextRef("Approver");
        assertTrue(output.equals("125"));
    }

    @Test
    @DisplayName("Attempt to read an empty Approver list.")
    void pullEmptyApprover(){
        String output = WorkFlow.getNextRef("Approver");
        assertTrue(output.equals("Coffee Time"));
    }

/*Testing validate() */
    
    @Test
    @DisplayName("Attempt to check the valid inputs")
    void isValidInputs(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");
        BO.setAlienNumber("111111111");
        BO.setLastName("Lee");
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == 1);
    }

    // check for refNum 

    @Test    
    @DisplayName("Attempt to check for invalid reference number")   
    void invalidRefNum1(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("aaa");                 //refNum = characters not number 
        BO.setAlienNumber("111111111");
        BO.setLastName("Lee");
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -1);
    }

    @Test
    @DisplayName("Attempt to check for invalid reference number")  
    void invalidRefNum2(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1a23");                 //refNum = number mixed with character(s)
        BO.setAlienNumber("111111111");
        BO.setLastName("Lee");
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -1);
    }

    @Test    
    @DisplayName("Attempt to check for invalid reference number")   
    void invalidRefNum3(){
        Business BO = Business.createNewBO();
        BO.setRefNumber(null);                 //refNum = null
        BO.setAlienNumber("111111111");
        BO.setLastName("Lee");
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -1);
    }

    // Check for alienNum

    @Test
    @DisplayName("Attempt to check for invalid alien number, case 1")  
    void invalidAlienNum1(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("aaaaaaaaa");        //alienNum = characters not numbers
        BO.setLastName("Lee");
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -2);
    }

    @Test
    @DisplayName("Attempt to check for invalid alien number, case 2")  
    void invalidAlienNum2(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("a1a2a3a4a");        //alienNum = character mixed with numbers
        BO.setLastName("Lee");
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -2);
    }
    @Test
    @DisplayName("Attempt to check for invalid alien number, case 3")  
    void invalidAlienNum3(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("12345");        //alienNum has LESS than 9 digits
        BO.setLastName("Lee");
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -2);
    }

    @Test
    @DisplayName("Attempt to check for invalid alien number, case 4")  
    void invalidAlienNum4(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789000");        //alienNum has MORE than 9 digits
        BO.setLastName("Lee");
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -2);
    }

    @Test
    @DisplayName("Attempt to check for invalid alien number, case 5")  
    void invalidAlienNum5(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("a1a2a3a4a999");        //alienNum = character mixed with numbers and out of range 9 digits
        BO.setLastName("Lee");
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -2);
    }

    @Test
    @DisplayName("Attempt to check for invalid alien number, case 6")  
    void invalidAlienNum6(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber(null);        //alienNum = null value
        BO.setLastName("Lee");
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -2);
    }


    // Check lastName
    @Test
    @DisplayName("Attempt to check for invalid last name, case 1")  
    void invalidLastName1(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName(null);                      //lastName = null value
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -2);
    }

    @Test
    @DisplayName("Attempt to check for invalid last name, case 2")  
    void invalidLastName2(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank007");                      //lastName = letter mixed with numbers
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -2);
    }

    @Test
    @DisplayName("Attempt to check for invalid last name, case 3")  
    void invalidLastName3(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank*##@!");                      //lastName = letters mixed special characters
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -2);
    }

    @Test
    @DisplayName("Attempt to check for invalid last name, case 4")  
    void invalidLastName4(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("123456%!@#$");                      //lastName = mixed special character with numbers
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -2);
    }

    @Test
    @DisplayName("Attempt to check for invalid last name, case 5")  
    void invalidLastName5(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("9999999");                      //lastName = only digits
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -2);
    }

    @Test
    @DisplayName("Attempt to check for invalid last name, case 6")  
    void invalidLastName6(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("!@#$%^&*()");                      //lastName = only characters
        BO.setFirstName("Tom");
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -2);
    }

    // Check firstName
    @Test
    @DisplayName("Attempt to check for invalid first name, case 1")  
    void invalidFirstName1(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                      
        BO.setFirstName(null);                 //firstName = null value
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -3);
    }

    @Test
    @DisplayName("Attempt to check for invalid first name, case 2")  
    void invalidFirstName2(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                    
        BO.setFirstName("Tom007");                    //firstName = letter mixed with numbers
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -3);
    }

    @Test
    @DisplayName("Attempt to check for invalid first name, case 3")  
    void invalidFirstName3(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                     
        BO.setFirstName("Tom*##@!");         //firstName = letters mixed special characters
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -3);
    }

    @Test
    @DisplayName("Attempt to check for invalid first name, case 4")  
    void invalidFirstName4(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                     
        BO.setFirstName("123456%!@#$");              //firstName = mixed special character with numbers
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -3);
    }

    @Test
    @DisplayName("Attempt to check for invalid first name, case 5")  
    void invalidFirstName5(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                     
        BO.setFirstName("9999999");                  //firstName = only digits
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -3);
    }

    @Test
    @DisplayName("Attempt to check for invalid first name, case 6")  
    void invalidFirstName6(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("!@#$%^&*()");               //firstName = only characters
        String[] midName = {"Alpha", "Beta"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -2);
    }


}
