/*
 * Hai Le, Scott Mitchell
 * 02/27/18
 * Program 3
 * The purpose of this program is to simulate a keypad at apartment entrance.
 */
package program3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Program3 extends Application {

    // stores the list of tenants to be read from the textfile
    public ApartmentRecords list;
    
    // label that acts as a keypad screen
    public Label keypadScreen;
    
    // buttons for keypad
    public Button button0, button1, button2, button3,
            button4, button5, button6,
            button7, button8, button9, buttonStar, buttonPound;
    
    // holds the apartment number and pincode from keypad
    public String input;
    
    // holds a placeholder text for keypad screen when nothing has been inputted yet
    public String placeholder;

    public static void main(String[] args) {
        launch(args);
    } // end main

    @Override
    public void start(Stage stage) {
        // Initial setup for input and placeholder texts
        input = "";
        placeholder = "Wake up, Neo...";
        // initialiazes the list of tenants from the textfile
        list = new ApartmentRecords();
        FileIO.readFile("tenants.txt", list);

        // setups container with a vertical layout and 450 px width
        VBox root = new VBox();
        root.setPrefWidth(450);

        // setups label as keypad screen
        keypadScreen = new Label(placeholder);
        keypadScreen.setStyle("-fx-font: 30 arial; -fx-background-color: Black; -fx-text-fill: 	Lightgreen;");
        keypadScreen.setPrefWidth(root.getPrefWidth());
        keypadScreen.setPrefHeight(100);

        // adds keypad screen to the root container
         root.getChildren().add(keypadScreen);
         
        // setups for keypad buttons
        button0 = new Button("0");
        button0.setPrefSize(150, 150);
        button0.setId("0");
        button0.setOnAction(new SimpleEventHandler());

        button1 = new Button("1");
        button1.setPrefSize(150, 150);
        button1.setId("1");
        button1.setOnAction(new SimpleEventHandler());

        button2 = new Button("2");
        button2.setPrefSize(150, 150);
        button2.setId("2");
        button2.setOnAction(new SimpleEventHandler());

        button3 = new Button("3");
        button3.setPrefSize(150, 150);
        button3.setId("3");
        button3.setOnAction(new SimpleEventHandler());

        button4 = new Button("4");
        button4.setPrefSize(150, 150);
        button4.setId("4");
        button4.setOnAction(new SimpleEventHandler());

        button5 = new Button("5");
        button5.setPrefSize(150, 150);
        button5.setId("5");
        button5.setOnAction(new SimpleEventHandler());

        button6 = new Button("6");
        button6.setPrefSize(150, 150);
        button6.setId("6");
        button6.setOnAction(new SimpleEventHandler());

        button7 = new Button("7");
        button7.setPrefSize(150, 150);
        button7.setId("7");
        button7.setOnAction(new SimpleEventHandler());

        button8 = new Button("8");
        button8.setPrefSize(150, 150);
        button8.setId("8");
        button8.setOnAction(new SimpleEventHandler());

        button9 = new Button("9");
        button9.setPrefSize(150, 150);
        button9.setId("9");
        button9.setOnAction(new SimpleEventHandler());

        buttonStar = new Button("*");
        buttonStar.setPrefSize(150, 150);
        buttonStar.setId("*");
        buttonStar.setOnAction(new SimpleEventHandler());

        buttonPound = new Button("#");
        buttonPound.setPrefSize(150, 150);
        buttonPound.setId("#");
        buttonPound.setOnAction(new SimpleEventHandler());

        // setups a gridpane to hold the buttons, 500px x 450px
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(500, root.getPrefWidth());
        gridPane.add(button1, 0, 0);
        gridPane.add(button2, 1, 0);
        gridPane.add(button3, 2, 0);
        gridPane.add(button4, 0, 1);
        gridPane.add(button5, 1, 1);
        gridPane.add(button6, 2, 1);
        gridPane.add(button7, 0, 2);
        gridPane.add(button8, 1, 2);
        gridPane.add(button9, 2, 2);
        gridPane.add(button0, 1, 3);
        gridPane.add(buttonStar, 0, 3);
        gridPane.add(buttonPound, 2, 3);
        gridPane.setStyle("-fx-text-fill: black; -fx-font: 62 arial;");
        
        // adds the gridpane to the root container 
        root.getChildren().add(gridPane);

        // setups the scene, stage and makes it visible
        stage.setScene(new Scene(root));
        stage.setTitle("Door Entry System");
        stage.show();
    } // end start

    private class SimpleEventHandler implements EventHandler<ActionEvent> {

        // handles the event when any button on keypad is pressed
        @Override
        public void handle(ActionEvent event) {
            // gets the event object and casts it to the button
            Object source = event.getSource();
            Button clickedBtn = (Button) source;

            // clear the keypadScreen if it currently shows placeholder text
            if (keypadScreen.getText().equals(placeholder)) {
                keypadScreen.setText("");
            } // end if

            switch (clickedBtn.getText()) {
                // if 0-9 button is pressed
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    // checks if the keypadScreen does not show welcome/error message now
                    // this forces the user to press '*' to reset the keypad at the start
                    if (!keypadScreen.getText().matches(".*[a-z].*")) {
                        // add the number to the
                        input += clickedBtn.getText();
                        keypadScreen.setText(keypadScreen.getText() + "*");
                    }
                    break;
                    // if * is pressed
                case "*":
                    // clears the input and shows placeholder text
                    input = "";
                    keypadScreen.setText(placeholder);
                    break;
                case "#":
                    // if '#' is pressed
                    // checks if the keypadScreen does not show welcome/error message now
                    // this forces the user to press '*' to reset the keypad at the start 
                    if (!keypadScreen.getText().matches(".*[a-z].*")) {
                        // adds '#' to the input string
                        input += clickedBtn.getText();
                        // show '#' on the keypad Screen
                        keypadScreen.setText(keypadScreen.getText() + "#");
                        // if this is a second #, input string already has apartment number and pincode 
                        if (input.matches("[0-9]*#[0-9]*#")) {
                            // checks the apartment number and pincode
                            // outputs the appropriate message
                            keypadScreen.setText(list.checkTenant(input));
                        } // end if
                    } // end if
            } // end switch
        } // end handle
    } // end SimpleEventHandler
} // end Program 3
