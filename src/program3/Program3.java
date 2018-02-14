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

    public ApartmentRecords list;
    public Label keypadScreen;
    public Button button0, button1, button2, button3,
            button4, button5, button6,
            button7, button8, button9, buttonStar, buttonPound;
    public String input;
    public String placeholder;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        input = "";
        placeholder = "Wake up, Neo...";
        list = new ApartmentRecords();
        FileIO.readFile("tenants.txt", list);

        VBox root = new VBox();
        root.setPrefWidth(450);

        keypadScreen = new Label(placeholder);
        keypadScreen.setStyle("-fx-font: 30 arial; -fx-background-color: Black; -fx-text-fill: 	Lightgreen;");
        keypadScreen.setPrefWidth(root.getPrefWidth());
        keypadScreen.setPrefHeight(100);

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

        root.getChildren().add(keypadScreen);
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(500, root.getPrefWidth()); // default size
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
        root.getChildren().add(gridPane);

        stage.setScene(new Scene(root));
        stage.setTitle("Door Entry System");
        stage.show();
    }

    private class SimpleEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Object source = event.getSource();
            // Only buttons have event handlers, so cast the Object 
            // to a Button.
            Button clickedBtn = (Button) source;

            if (keypadScreen.getText().equals(placeholder)) {
                keypadScreen.setText("");
            }

            switch (clickedBtn.getText()) {
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
                    if (!keypadScreen.getText().matches(".*[a-z].*")) {
                        input += clickedBtn.getText();
                        keypadScreen.setText(keypadScreen.getText() + "*");
                    }
                    break;
                case "*":
                    input = "";
                    keypadScreen.setText(placeholder);
                    break;
                case "#":
                    if (!keypadScreen.getText().matches(".*[a-z].*")) {
                        input += clickedBtn.getId();
                        keypadScreen.setText(keypadScreen.getText() + "#");
                        if (input.matches("[0-9]*#[0-9]*#")) {
                            keypadScreen.setText(list.findTenant(input));
                        } // end if
                    }
            } // end switch
        } // end handle
    } // end SimpleEventHandler
} // end Program 3
