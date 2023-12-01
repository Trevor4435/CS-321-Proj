package org.openjfx;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Review {
    
    
    private TextField alienNumber = new TextField();
    private TextField firstName = new TextField();
    private TextField middleNames = new TextField();
    private TextField lastName = new TextField();
    private TextField DOB = new TextField();
    private TextField mailingAddress = new TextField();
    private  boolean canEdit = false;
    private boolean nextInAction = false;
    
    

    public void showScreen(){

        
        Stage stage = App.permStage;
        GridPane grid = new GridPane();
 
        Scene data = new Scene(grid);
        
        GridPane sGrid = new GridPane();
        Scene start = new Scene(sGrid);

       
        Business bo = Business.createNewBO();
       
    
        

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        sGrid.setHgap(10);
        sGrid.setVgap(10);
        sGrid.setPadding(new Insets(25,25,25,25));

        Label header = new Label("Reviewer Step");
        grid.add(header, 0,0);
        sGrid.add(header, 0,0);
        
        stage.setScene(start);  //sets up default reviewer screen

             
    
        Label aNumber = new Label("Alien Number: ");
        Label fName = new Label("First Name: ");
        Label mNames = new Label("Middle Name: \n(Seperate with a ',' or leave empty)");
        Label lName = new Label("Last Name: ");
        Label bDay = new Label("Date of Birth: \n (Please enter in MM/DD/YYYY)");
        Label mAddress = new Label("Mailing Address for Delivery: ");
        Label error = new Label();
        Label error2 = new Label();


        grid.add(error, 0, 8);
        error.setTextFill(Color.RED);
    
        //create and add buttons
        Button submit = new Button("SAVE");
        grid.add(submit, 2, 9);

        
        Button begin = new Button("START");
        sGrid.add(begin, 2, 9);

        Button exit1 = new Button("Exit");
        sGrid.add(exit1, 0, 9);
        sGrid.add(error2, 1, 10);
        
        Button exit2 = new Button("EXIT");
        grid.add(exit2, 0, 9);
        

        Button next = new Button("Next");
        grid.add(next,1,9);
        
        
        Button edit = new Button("Edit");
        grid.add(edit,3,9);
        
        
        //add labels and textfeilds
        grid.add(aNumber, 0, 0);
        alienNumber.setEditable(false);
        grid.add(alienNumber, 1, 0);    
       
           
        grid.add(fName, 0, 1);
        firstName.setEditable(false);
        grid.add(firstName, 1, 1);

        grid.add(mNames, 0, 2);
        middleNames.setEditable(false);
        grid.add(middleNames, 1, 2);

        grid.add(lName, 0, 3);
        lastName.setEditable(false);
        grid.add(lastName, 1, 3);

        grid.add(bDay, 0, 4);
        DOB.setEditable(false);
        grid.add(DOB, 1, 4);

        grid.add(mAddress, 0, 5);
        mailingAddress.setEditable(false);
        grid.add(mailingAddress, 1, 5);


    
        //loads the data fields screen
        begin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                stage.setScene(data);
            }
        }); 

        //loads the homescreen
        exit1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                stage.setScene(App.homeScene);
                
            }
        });

        //loads the homescreen
        exit2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                stage.setScene(App.homeScene);
            }
        });
        
        //allows screen text feilds to be editable
         edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                
                //no entries are opened for review
                if(!canEdit){
                    error.setText("Get next entry first!");
                }else{
                    error.setText("");
                
                    alienNumber.setEditable(true);
                    firstName.setEditable(true);
                    middleNames.setEditable(true);
                    lastName.setEditable(true);
                    DOB.setEditable(true);
                    mailingAddress.setEditable(true);
                }

            }
        });
        
    
       //loads the data of next item in review queue
        next.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
            
            error.setText("");
            
            //if data is already loaded to the screen, finish this task
            if(firstName.getText().length() != 0){
            error.setText("Opened form should be submitted to approver first!");
            return;
           } 
           canEdit = true;
           nextInAction = true;
          

           String r =  WorkFlow.getNextRef("Reviewer");
           if(r.charAt(0) == 'N' ){
                error2.setText("Take a water break, the Reviewer's workflow is currently empty!");
                stage.setScene(start);
    
           }

           Integer refN = Integer.parseInt(r);
           

           //checks refNum value
           if(refN < 1 ){
                stage.setScene(start);
           }

           int val = bo.getFile(r);

           if(val < 0){
                stage.setScene(App.homeScene);
           }
             
           


            //loads the textfeilds 
            alienNumber.setText(bo.getAlienNumber());
            alienNumber.setEditable(false);
            
            firstName.setText(bo.getFirstName());
            firstName.setEditable(false);
            
            middleNames.setText(printMiddleName(bo.getMiddleName()));
            middleNames.setEditable(false);
           
            lastName.setText(bo.getLastName());
            lastName.setEditable(false);
        
            DOB.setText(bo.getDOB());
            DOB.setEditable(false);
            
            mailingAddress.setText(bo.getAddress());
            mailingAddress.setEditable(false);
            
            
            stage.setScene(data);
                    
        }

        });


        // This is where we are finally going to start checking for input.
        // These are very basic checks just to make sure input exists where it's required.
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
               
                //saved = false;

               if(!nextInAction){
                    error.setText("No form is currently open for review!");
                    return;
               }
            bo.setAlienNumber(alienNumber.getText());
            bo.setFirstName(firstName.getText());
            bo.setLastName(lastName.getText());
            // Split on ", " to permit middle names with spaces
            bo.setMiddleName(middleNames.getText().split(", "));    
            bo.setAddress(mailingAddress.getText());
            bo.setDOB(DOB.getText());
               
            int validation =  bo.validate();

            if(validation == -1){

              }else if(validation == -1){
                error.setText("Invalid Ref Number format!");
              }else if(validation == -1){
                error.setText("Invalid Alien Number format!");
              }else if(validation == -1){
                error.setText("Invalid Last Name format!");
              }else if(validation == -1){
                error.setText("Invalid First Name format!");
              }else if(validation == -1){
                error.setText("Invalid Middle Name format!");
              }else if(validation == -1){
                error.setText("Invalid Address format!");
              }else if(validation == -1){
                error.setText("Invalid Date of Birth format!");
              }else{ // returns 1;
                bo.saveFile();
                
                
                
                int status = WorkFlow.addWorkFlow(bo.getRefNum(), "Approver");
                if(status == -1){
                    error.setText("There was an issue with saving this form!");
                }

                stage.setScene(start);
              }

                //reset textfeilds
                alienNumber.setText("");
                alienNumber.setEditable(false);
            
                firstName.setText("");
                firstName.setEditable(false);
            
                middleNames.setText("");
                middleNames.setEditable(false);
        
                lastName.setText("");
                lastName.setEditable(false);
            
                DOB.setText("");
                DOB.setEditable(false);
            
                mailingAddress.setText("");
                mailingAddress.setEditable(false);
                canEdit = false;
                nextInAction = false;

            }
        });


    }  
    
    /*
    public ArrayList<Business> BOList(){

         Business b1 = Business.createNewBO();
        b1.setRefNumber("2");
        b1.setAlienNumber("111111111");
        b1.setLastName("Lee");
        b1.setFirstName("Josh");
        String[] midName1 = {"One", "Two"};
        b1.setMiddleName(midName1);
        b1.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        b1.setDOB("12/13/1970");

        Business b2 = Business.createNewBO();
        b2.setRefNumber("3");
        b2.setAlienNumber("222222222");
        b2.setLastName("Bob");
        b2.setFirstName("Jones");
        String[] midName2 = {"Three", "Four"};
        b2.setMiddleName(midName2);
        b2.setAddress("456X Red Avenue, Woodbridge, VA 8888");
        b2.setDOB("10/02/1988");
        ArrayList<Business> boList = new ArrayList<Business>();
        boList.add(b1);
        boList.add(b2);

        return boList;
    }

    */

    /*

    public void printList(ArrayList<Business> obj){
    
        for(int i = 0; i< obj.size(); i++){
            System.out.println("index: " + i);
            System.out.println( " Alien Num: " +  obj.get(i).getAlienNumber());
            System.out.println(" First Name: " + obj.get(i).getFirstName());
            System.out.println(" Last Name: " + obj.get(i).getLastName());
            System.out.println(" Middle Name: " + printMiddleName(obj.get(i).getMiddleName()));
            System.out.println(" Address: " + obj.get(i).getAddress());
            System.out.println(" DOB: " + obj.get(i).getDOB());
        }

    }

    */

    
    public String printMiddleName(String [] a){
        String s = "";
        for( int i = 0; i< a.length; i++){
            s+=a[i] + " ";


        }
        return s;
    }

    
}
