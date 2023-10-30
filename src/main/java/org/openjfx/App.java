package org.openjfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    protected static Stage permStage;

    @Override
    public void start(Stage stage) {

        permStage = stage;

        // Set the window title.
        stage.setTitle("Window Selects");

        // Declare the stackframe and scene with additional padding.
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));


        // Create the welcome instructions and display them.
        Text welcome = new Text();
        welcome.setText("Please selected your desired service.");
        grid.add(welcome, 0, 0);


        // Create the select buttons and display in a stacked list.
        Button dataEntry = new Button();
        dataEntry.setText("Data Entry");
        grid.add(dataEntry, 0, 1);

        Button review = new Button();
        review.setText("Review");
        grid.add(review, 0, 2);

        Button approve = new Button();
        approve.setText("Approval");
        grid.add(approve, 0, 3);

        dataEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                DataEntry data = new DataEntry();
                grid.getChildren().clear();
                stage.hide();
                data.showScreen();
            }
        });


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}