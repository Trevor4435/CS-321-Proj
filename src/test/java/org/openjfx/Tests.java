package org.openjfx;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import java.util.Arrays;

public class Tests {
    @Test
    @DisplayName("Attempt to create new BO.")
    void newBOTest(){
        Business house = Business.createNewBO();
        assertTrue(house instanceof Business);
    }
      
    @Test
    @DisplayName("test save file with valid input")
    void saveFileBOTest1(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");
        BO.setAlienNumber("111111111");
        BO.setLastName("Lee");
        BO.setFirstName("Josh");
        String[] midName = {"One", "Two"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("12/13/1970");
        
        int result = BO.saveFile();

        assertTrue(result == 1);
    }

    @Test
    @DisplayName("Check if return value of save file is valid when there should be no files to save")
    void saveFileBOTest2(){
        Business house = Business.createNewBO();
        int output = house.saveFile();
        assertTrue(output == -1);
    }

    @Test
    @DisplayName("test valid input, using getRef method")
    void saveFileBOTest3(){
        
        Business BO = Business.createNewBO();
        BO.setRefNumber("2");
        BO.setAlienNumber("222222222");
        BO.setLastName("Ree");
        BO.setFirstName("Losh");
        String[] midName = {"Three", "Fourth"};
        BO.setMiddleName(midName);
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("12/13/1970");

        int result = BO.saveFile();;

        assertTrue(result == 1);
    }


    @Test
    @DisplayName("Placeholder")
    void getFileBOTest1(){
        Business BO = Business.createNewBO();
        String[] midName = {"One", "Two"};
        int result = BO.getFile("1");
        assertTrue(result == 1);

        assertTrue("1".equals(BO.getRefNum()));
        assertTrue("111111111".equals(BO.getAlienNumber()));
        assertTrue("Lee".equals(BO.getLastName()));
        assertTrue("Josh".equals(BO.getFirstName()));
        assertTrue(Arrays.equals(midName, BO.getMiddleName()));
        assertTrue("123X Green Avenue, Metalbridge, VA 9999".equals(BO.getAddress()));
        assertTrue("12/13/1970".equals(BO.getDOB()));
    }

    @Test
    @DisplayName("Attempt to get a null file to BO.")
    void getFileBOTest2(){
        Business house = Business.createNewBO();
        int output = house.getFile(null);
        assertTrue(output == -1);
    }

    @Test
    @DisplayName("Testing the status of valid input, without using getters,when file may not exist")
    void getFileBOTest3(){
        Business house = Business.createNewBO();
        int output = house.getFile("30");
        assertTrue(output == -1);
    }
    
    @Test
    @DisplayName("Testing the status of invalid output, when unknown if file exists")
    void getFileBOTest4(){
        Business house = Business.createNewBO();
        int output = house.getFile("hihihi");
        assertTrue(output == -1);
    }

    @Test
    @DisplayName("Testing the status of invalid output, should not work cuz file doesn't exist.")
    void getFileBOTest5(){
        Business BO = Business.createNewBO();
        String[] midName = {"Three", "Fourth"};
        int result = BO.getFile("2");

        assertTrue(result == 1);
        assertTrue("2".equals(BO.getRefNum()));
        assertTrue("222222222".equals(BO.getAlienNumber()));
        assertTrue("Ree".equals(BO.getLastName()));
        assertTrue("Losh".equals(BO.getFirstName()));
        assertTrue(Arrays.equals(midName, BO.getMiddleName()));
        assertTrue("123X Green Avenue, Metalbridge, VA 9999".equals(BO.getAddress()));
        assertTrue("12/13/1970".equals(BO.getDOB()));
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
        assertTrue(result == -3);
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
        assertTrue(result == -3);
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
        assertTrue(result == -3);
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
        assertTrue(result == -3);
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
        assertTrue(result == -3);
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
        assertTrue(result == -3);
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
        assertTrue(result == -4);
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
        assertTrue(result == -4);
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
        assertTrue(result == -4);
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
        assertTrue(result == -4);
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
        assertTrue(result == -4);
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
        assertTrue(result == -4);
    }

    // Check middle name

    @Test
    @DisplayName("Attempt to check for invalid middle name, case 1")  
    void invalidMiddleName1(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");               
        BO.setMiddleName(null);             //middleName = null value
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -5);
    }

    @Test
    @DisplayName("Attempt to check for invalid middle name, case 2")  
    void invalidMiddleName2(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"123"};               
        BO.setMiddleName(middleN);             //middleName contain digits 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -5);
    }

    @Test
    @DisplayName("Attempt to check for invalid middle name, case 3")  
    void invalidMiddleName3(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"!@#$%^"};               
        BO.setMiddleName(middleN);             //middleName contain only special characters 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -5);
    }

    @Test
    @DisplayName("Attempt to check for invalid middle name, case 4")  
    void invalidMiddleName4(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"1234@%^&"};               
        BO.setMiddleName(middleN);             //middleName contain mixed special characters and digits 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -5);
    }

    @Test
    @DisplayName("Attempt to check for invalid middle name, case 5")  
    void invalidMiddleName5(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"Aaaa124", "BBB@#$%"};               
        BO.setMiddleName(middleN);             //middleName contain mixed letter, special characters, and digits 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -5);
    }

    @Test
    @DisplayName("Attempt to check for invalid middle name, case 6")  
    void invalidMiddleName6(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {""};               
        BO.setMiddleName(middleN);             //Empty middle name means a person only has last name and first name 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -5);
    }

    // Check Address
    @Test
    @DisplayName("Attempt to check for invalid address, case 1")  
    void invalidAddress1(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"Alpha", "Beta"};               
        BO.setMiddleName(middleN); 
        BO.setAddress(null);                        // Address = null value
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -6);
    }

    @Test
    @DisplayName("Attempt to check for invalid address, case 2")  
    void invalidAddress2(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"Alpha", "Beta"};               
        BO.setMiddleName(middleN); 
        BO.setAddress("123X @Green Avenue, *Metalbridge, VA 9999");       // Address contain special characters
        BO.setDOB("01/01/1999");
        int result = BO.validate();
        assertTrue(result == -6);
    }

    // Check DOB
    @Test
    @DisplayName("Attempt to check for invalid DOB, case 1")  
    void invalidDOB1(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"Alpha", "Beta"};               
        BO.setMiddleName(middleN); 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");       
        BO.setDOB(null);                                //DOB is null value
        int result = BO.validate();
        assertTrue(result == -7);
    }

    @Test
    @DisplayName("Attempt to check for invalid DOB, case 2")  
    void invalidDOB2(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"Alpha", "Beta"};               
        BO.setMiddleName(middleN); 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");       
        BO.setDOB("Mar 20 1999");                                //DOB input follow incorrect format
        int result = BO.validate();
        assertTrue(result == -7);
    }

    @Test
    @DisplayName("Attempt to check for invalid DOB, case 3")  
    void invalidDOB3(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"Alpha", "Beta"};               
        BO.setMiddleName(middleN); 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");       
        BO.setDOB("aaaaa");                                //DOB contain letters
        int result = BO.validate();
        assertTrue(result == -7);
    }

    @Test
    @DisplayName("Attempt to check for invalid DOB, case 4")  
    void invalidDOB4(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"Alpha", "Beta"};               
        BO.setMiddleName(middleN); 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");       
        BO.setDOB("");                                //DOB is empty or missing
        int result = BO.validate();
        assertTrue(result == -7);
    }

    @Test
    @DisplayName("Attempt to check for invalid DOB, case 5")  
    void invalidDOB5(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"Alpha", "Beta"};               
        BO.setMiddleName(middleN); 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");       
        BO.setDOB("13/01/1999");                                // invalid month, must be in range [1,12]
        int result = BO.validate();
        assertTrue(result == -7);
    }

    @Test
    @DisplayName("Attempt to check for invalid DOB, case 6")  
    void invalidDOB6(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"Alpha", "Beta"};               
        BO.setMiddleName(middleN); 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");       
        BO.setDOB("12/99/1999");                                // invalid date, must be in range [1,31]
        int result = BO.validate();
        assertTrue(result == -7);
    }

    @Test
    @DisplayName("Attempt to check for invalid DOB, case 7")  
    void invalidDOB7(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"Alpha", "Beta"};               
        BO.setMiddleName(middleN); 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");       
        BO.setDOB("01/01/2500");                                // invalid year, must be in range [1900, 2050]
        int result = BO.validate();
        assertTrue(result == -7);
    }

    @Test
    @DisplayName("Attempt to check for invalid DOB, case 8")  
    void invalidDOB8(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"Alpha", "Beta"};               
        BO.setMiddleName(middleN); 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");       
        BO.setDOB("01-01-1999");                                //Incorrect format for separators, must be "/"
        int result = BO.validate();
        assertTrue(result == -7);
    }

    @Test
    @DisplayName("Attempt to check for invalid DOB, case 9")  
    void invalidDOB9(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"Alpha", "Beta"};               
        BO.setMiddleName(middleN); 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");       
        BO.setDOB("01/01/02");                                //Incorrect format for year, must be 4 digits YYYY
        int result = BO.validate();
        assertTrue(result == -7);
    }

    @Test
    @DisplayName("Attempt to check for invalid DOB, case 10")  
    void invalidDOB10(){
        Business BO = Business.createNewBO();
        BO.setRefNumber("1");                 
        BO.setAlienNumber("123456789");        
        BO.setLastName("Hank");                         
        BO.setFirstName("Tom");
        String[] middleN = {"Alpha", "Beta"};               
        BO.setMiddleName(middleN); 
        BO.setAddress("123X Green Avenue, Metalbridge, VA 9999");       
        BO.setDOB("1/1/1999");                     //Incorrect format for date and month, must be 2 digits DD or MM
        int result = BO.validate();
        assertTrue(result == -7);
    }
}
