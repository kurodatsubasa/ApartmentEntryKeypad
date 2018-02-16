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

    //list holds all apartment records from the source file
    public ApartmentRecords list;
    //hold text for the keypad's display
    public Label keypadScreen;
    //buttons for keypad
    public Button button0, button1, button2, button3,
            button4, button5, button6,
            button7, button8, button9, buttonStar, buttonPound;
    //keeps track of what't been input via keypad
    public String input;
    //default text for display
    public String placeholder;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        //input starts empty
        input = "";
        //we take the red pill
        placeholder = "Wake up, Neo...";
        //instantiate apartment records
        list = new ApartmentRecords();
        //read in source file and fill list
        FileIO.readFile("tenants.txt", list);
        
        //vbox to hold controls
        VBox root = new VBox();
        //set default width to the optimal 450
        root.setPrefWidth(450);
        
        //instantiate and setup keypad screen
        keypadScreen = new Label(placeholder);
        keypadScreen.setStyle("-fx-font: 30 arial; -fx-background-color: Black; -fx-text-fill: 	Lightgreen;");
        keypadScreen.setPrefWidth(root.getPrefWidth());
        keypadScreen.setPrefHeight(100);

        //instantiate and setup keypad buttons for input
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

        // buttons for the second row of the game
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

        // buttons for the third row of the game
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

        //add the keypad screen to our vbox
        root.getChildren().add(keypadScreen);
        //new gridpane to hold buttons
        GridPane gridPane = new GridPane();
        //default size
        gridPane.setPrefSize(500, root.getPrefWidth());
        //add buttons to gridpane
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

        //set style for buttons in gridpane
        gridPane.setStyle("-fx-text-fill: black; -fx-font: 62 arial;");
        //add gridpane to our vbox
        root.getChildren().add(gridPane);

        //setup scene and stage
        stage.setScene(new Scene(root));
        stage.setTitle("Door Entry System");
        stage.show();
    }

    //event handler for buttons
    private class SimpleEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Object source = event.getSource();
            // Only buttons have event handlers, so cast the Object 
            // to a Button.
            Button clickedBtn = (Button) source;

            //once a buttons is clicked we have to get rid of the placeholder text
            if (keypadScreen.getText().equals(placeholder)) {
                keypadScreen.setText("");
            }

            //switch decides what to do with each button click
            switch (clickedBtn.getText()) {
                //if a number button is clicked
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
                    //if there are no letters on the screen (the placeholder isn't still there)
                    if (!keypadScreen.getText().matches(".*[a-z].*")) {
                        //add the number clicked to input string
                        input += clickedBtn.getText();
                        //add a * to the screen text
                        keypadScreen.setText(keypadScreen.getText() + "*");
                    }
                    break;
                //if star is clicked clear out input and set screen to display placeholder text
                case "*":
                    input = "";
                    keypadScreen.setText(placeholder);
                    break;
                //if pound is clicked
                case "#":
                    //if there are no letters on the screen (the placeholder isn't still there)
                    if (!keypadScreen.getText().matches(".*[a-z].*")) {
                        //add # to input string
                        input += clickedBtn.getId();
                        //add # to screen text indicating that the apartment number is entered
                        //or that we are done entering the PIN
                        keypadScreen.setText(keypadScreen.getText() + "#");
                        //if we have "numbers#numbers#" (the right format for input)
                        if (input.matches("[0-9]*#[0-9]*#")) {
                            //check if the apartment number and pin match and are found in our db
                            keypadScreen.setText(list.findTenant(input));
                        } // end if
                    }
            } // end switch
        } // end handle
    } // end SimpleEventHandler
} // end Program 3
