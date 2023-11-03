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
    private static int refNumber = 1;
    public int i = -1;
    private TextField alienNumber = new TextField();
    private TextField firstName = new TextField();
    private TextField middleNames = new TextField();
    private TextField lastName = new TextField();
    private TextField DOB = new TextField();
    private TextField mailingAddress = new TextField();
    

    



   

    public void showScreen(){

        
        Stage stage = App.permStage;
        GridPane grid = new GridPane();
 
        Scene data = new Scene(grid);
        
        GridPane sGrid = new GridPane();
        Scene start = new Scene(sGrid);

        ArrayList<Business> boList = BOList();
    
        

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
       // TextField alienNumber = new TextField();    
    
        Label fName = new Label("First Name: ");
       // TextField firstName = new TextField();
      

        Label mNames = new Label("Middle Name: \n(Seperate with a ',' or leave empty)");
       // TextField middleNames = new TextField();
        

        Label lName = new Label("Last Name: ");
       // TextField lastName = new TextField();
       

        Label bDay = new Label("Date of Birth: \n (Please enter in MM/DD/YYYY)");
       // TextField DOB = new TextField();
       

        Label mAddress = new Label("Mailing Address for Delivery: ");
        //TextField mailingAddress = new TextField();
       
        
        Label error = new Label();
        grid.add(error, 0, 8);
        error.setTextFill(Color.RED);
    
        //create and add buttons
        Button submit = new Button("SAVE");
        grid.add(submit, 2, 9);

        
        Button begin = new Button("START");
        sGrid.add(begin, 2, 9);

        Button exit1 = new Button("Exit");
        sGrid.add(exit1, 0, 9);
        
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








    
        
        begin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                stage.setScene(data);
            }
        }); 


        exit1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                stage.setScene(App.homeScene);
                
            }
        });

        exit2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                stage.setScene(App.homeScene);
            }
        });
        

         edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                error.setText("");
                if(i < 0 || i >= boList.size()){
                    
                    error.setText("There is nothing to edit on the screen, get the next item!");
                    return;
                }
                
                alienNumber.setEditable(true);
                firstName.setEditable(true);
                middleNames.setEditable(true);
                lastName.setEditable(true);
                DOB.setEditable(true);
                mailingAddress.setEditable(true);

            }
        });
        
    
       
        
     

        next.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
            //error.setText("button not implemented yet!");
             // Declare the label and field for an alien number to be entered.
            i++;
            if(i >= boList.size()){
                stage.setScene(App.homeScene);
                return;
            }
             
           


            
            alienNumber.setText(boList.get(i).getAlienNumber());
            alienNumber.setEditable(false);
                
    
           
            
            firstName.setText(boList.get(i).getFirstName());
            firstName.setEditable(false);
            

            
            middleNames.setText(printMiddleName(boList.get(i).getMiddleName()));
            middleNames.setEditable(false);
           

            
            lastName.setText(boList.get(i).getLastName());
            lastName.setEditable(false);
            

            
            DOB.setText(boList.get(i).getDOB());
            DOB.setEditable(false);
            
           
            mailingAddress.setText(boList.get(i).getAddress());
            mailingAddress.setEditable(false);
            
            
            stage.setScene(data);
                    return;
        }

        });


        // This is where we are finally going to start checking for input.
        // These are very basic checks just to make sure input exists where it's required.
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                
                if(i<0){
                    error.setText("Get next form to review by clicking Next button!");
                }
                

               if(i >= 0 && i < boList.size()){
                boList.get(i).setAlienNumber(alienNumber.getText());
                boList.get(i).setRefNumber(Integer.toString(refNumber));
                boList.get(i).setFirstName(firstName.getText());
                boList.get(i).setLastName(lastName.getText());

                // Split on ", " to permit middle names with spaces
                boList.get(i).setMiddleName(middleNames.getText().split(", "));
                
                boList.get(i).setAddress(mailingAddress.getText());
                boList.get(i).setDOB(DOB.getText());

               
                

               }
            
            if(i == 1){
            printList(boList);
            }
               

                //reset values for next object in workflow
               /*grid.getChildren().remove(aNumber);
                grid.getChildren().remove(alienNumber);
                
                grid .getChildren().remove(fName);
                grid.getChildren().remove(firstName);
                
                grid.getChildren().remove(mNames);
                grid.getChildren().remove(middleNames);
                
                grid.getChildren().remove(lName);
                grid.getChildren().remove(lastName);

                grid.getChildren().remove(bDay);
                grid.getChildren().remove(DOB);

                grid.getChildren().remove(mAddress);
                grid.getChildren().remove(mailingAddress);
*/

               if(i>= boList.size()){
                stage.setScene(App.homeScene);
               
               }

               else if(i<0){
                error.setText("ERROR: No form is opened for review!");
               }
               else{
                stage.setScene(data);
               }
                
            }
        });

        //stage.setScene(start);
        //stage.show();

    }  
    public void setDefaultScreen(){

    }  
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
    public String printMiddleName(String [] a){
        String s = "";
        for( int i = 0; i< a.length; i++){
            s+=a[i] + " ";


        }
        return s;
    }
}
