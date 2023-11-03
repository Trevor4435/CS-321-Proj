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
       // GridPane g = new GridPane();
        //Scene start = new Scene(g);
        Scene data = new Scene(grid);
        ArrayList<Business> boList = BOList();
    
        

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        Label header = new Label("Reviewer Step");
        grid.add(header, 0,0);
       

        /*grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(40,40,40,40));
         stage.setScene(data);
        stage.show();*/
             
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
        grid.add(error, 0, 6);
        error.setTextFill(Color.RED);
    

        Button submit = new Button("Submit");
        grid.add(submit, 2, 9);

        Button exit = new Button("Exit");
        grid.add(exit, 0, 9);

        Button next = new Button("Next");
        grid.add(next,1,9);

        Button edit = new Button("Edit");
        grid.add(next,3,9);
    
        
        


        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                stage.setScene(App.homeScene);
            }
        });
        

         edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
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
             
           


            grid.add(aNumber, 0, 0);
            alienNumber = new TextField(boList.get(i).getAlienNumber());
            alienNumber.setEditable(false);
            grid.add(alienNumber, 1, 0);    
    
           
            grid.add(fName, 0, 1);
            firstName = new TextField(boList.get(i).getFirstName());
            firstName.setEditable(false);
            grid.add(firstName, 1, 1);

            grid.add(mNames, 0, 2);
            middleNames = new TextField(boList.get(i).getMiddleName().toString());
            middleNames.setEditable(false);
            grid.add(middleNames, 1, 2);

            grid.add(lName, 0, 3);
            lastName = new TextField(boList.get(i).getLastName());
            lastName.setEditable(false);
            grid.add(lastName, 1, 3);

            grid.add(bDay, 0, 4);
            DOB = new TextField(boList.get(i).getDOB());
            DOB.setEditable(false);
            grid.add(DOB, 1, 4);

            grid.add(mAddress, 0, 5);
            mailingAddress = new TextField(boList.get(i).getAddress());
            mailingAddress.setEditable(false);
            grid.add(mailingAddress, 1, 5);
            
            stage.setScene(data);
                    return;
        }

        });


        // This is where we are finally going to start checking for input.
        // These are very basic checks just to make sure input exists where it's required.
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                
                error.setText("");

               
                if(alienNumber.getText().isEmpty()){
                    error.setText("ERROR: Empty Alien Number!");
                    return;
                }
                else if(firstName.getText().isEmpty()){
                    error.setText("ERROR: Empty First Name!");
                    return;
                }
                else if(lastName.getText().isEmpty()){
                    error.setText("ERROR: Empty Last Name!");
                    return;
                }
                else if(DOB.getText().isEmpty()){
                    error.setText("ERROR: Empty Date of Birth!");
                    return;
                }
                else if(mailingAddress.getText().isEmpty()){
                    error.setText("ERROR: Empty Mailing Address!");
                    return;
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



                //reset values for next object in workflow
                grid.getChildren().remove(aNumber);
                grid.getChildren().remove(alienNumber);
                
                grid.getChildren().remove(fName);
                grid.getChildren().remove(firstName);
                
                grid.getChildren().remove(mNames);
                grid.getChildren().remove(middleNames);
                
                grid.getChildren().remove(lName);
                grid.getChildren().remove(lastName);

                grid.getChildren().remove(bDay);
                grid.getChildren().remove(DOB);

                grid.getChildren().remove(mAddress);
                grid.getChildren().remove(mailingAddress);


               if(i>= boList.size()){
                stage.setScene(App.homeScene);
               
               }
               if(i<0){
                error.setText("ERROR: No form is opened for review!");
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
        b2.setRefNumber("2");
        b2.setAlienNumber("111111111");
        b2.setLastName("Lee");
        b2.setFirstName("Josh");
        String[] midName2 = {"Three", "Four"};
        b2.setMiddleName(midName2);
        b2.setAddress("123X Green Avenue, Metalbridge, VA 9999");
        b2.setDOB("12/13/1970");
        ArrayList<Business> boList = new ArrayList<Business>();
        boList.add(b1);
        boList.add(b2);

        return boList;
    }
}
