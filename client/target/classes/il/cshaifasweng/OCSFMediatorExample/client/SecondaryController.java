package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import javafx.application.Platform;

public class SecondaryController {

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private Button b3;

    @FXML
    private Button b4;

    @FXML
    private Button b5;

    @FXML
    private Button b6;

    @FXML
    private Button b7;

    @FXML
    private Button b8;

    @FXML
    private Button b9;

    @FXML
    private Text winnerText;

    // List of buttons
    private List<Button> buttons;


    @FXML
    void chooseCell(ActionEvent event){
        Platform.runLater(() -> {
            Button clickedButton = (Button) event.getSource();// Get the fx:id of the clicked button to get the button number
            String buttonNum = String.valueOf(clickedButton.getId().charAt(1));// Taking only the second character (the number of the button)
            try {
                SimpleClient.getClient().sendToServer(buttonNum);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Subscribe
    public void startTheGame(Object obj){
        if(!(obj instanceof String) || !obj.equals("Start")){
            return;
        }
        Platform.runLater(this::removeTextFromButtons);
    }

    @Subscribe
    public void displayChoiceFromServer(Object obj){
        if(!(obj instanceof String) || !(((String) obj).startsWith("Chosen"))) {
            return;
        }
        String[] parts = (obj.toString()).split(" ");
        Platform.runLater(() -> {
            // Display the X with blue color and O with red color
            switch (parts[1]){
                case("1"): b1.setText(parts[2]);
                    if(parts[2].equals("X")){
                        b1.setStyle("-fx-text-fill: blue;");
                    }
                    else {
                        b1.setStyle("-fx-text-fill: red;");
                    }
                    break;
                case("2"): b2.setText(parts[2]);
                    if(parts[2].equals("X")){
                        b2.setStyle("-fx-text-fill: blue;");
                    }
                    else {
                        b2.setStyle("-fx-text-fill: red;");
                    }
                    break;
                case("3"): b3.setText(parts[2]);
                    if(parts[2].equals("X")){
                        b3.setStyle("-fx-text-fill: blue;");
                    }
                    else {
                        b3.setStyle("-fx-text-fill: red;");
                    }
                    break;
                case("4"): b4.setText(parts[2]);
                    if(parts[2].equals("X")){
                        b4.setStyle("-fx-text-fill: blue;");
                    }
                    else {
                        b4.setStyle("-fx-text-fill: red;");
                    }
                    break;
                case("5"): b5.setText(parts[2]);
                    if(parts[2].equals("X")){
                        b5.setStyle("-fx-text-fill: blue;");
                    }
                    else {
                        b5.setStyle("-fx-text-fill: red;");
                    }
                    break;
                case("6"): b6.setText(parts[2]);
                    if(parts[2].equals("X")){
                        b6.setStyle("-fx-text-fill: blue;");
                    }
                    else {
                        b6.setStyle("-fx-text-fill: red;");
                    }
                    break;
                case("7"): b7.setText(parts[2]);
                    if(parts[2].equals("X")){
                        b7.setStyle("-fx-text-fill: blue;");
                    }
                    else {
                        b7.setStyle("-fx-text-fill: red;");
                    }
                    break;
                case("8"): b8.setText(parts[2]);
                    if(parts[2].equals("X")){
                        b8.setStyle("-fx-text-fill: blue;");
                    }
                    else {
                        b8.setStyle("-fx-text-fill: red;");
                    }
                    break;
                case("9"): b9.setText(parts[2]);
                    if(parts[2].equals("X")){
                        b9.setStyle("-fx-text-fill: blue;");
                    }
                    else {
                        b9.setStyle("-fx-text-fill: red;");
                    }
                    break;
            }
        });
    }

    @Subscribe
    public void HandleTurns(Object obj){
        if(!(obj instanceof String) || ( !obj.equals("Play") && !obj.equals("Wait") )){
            return;
        }
        String msg = obj.toString();
        Platform.runLater(() -> {
            if(msg.equals("Play")){
                for (Button button : buttons) {
                    // Enable the empty buttons that haven't chosen yet
                    if(button.getText().isEmpty()){
                        button.setDisable(false);
                    }
                }
                winnerText.setFont(new Font(30));
                winnerText.setText("Your Turn");
            }
            else if(msg.equals("Wait")) {
                // Disable all the buttons for who waits
                for (Button button : buttons) {
                    button.setDisable(true);
                }
                winnerText.setFont(new Font(30));
                winnerText.setText("Opponent Turn");
            }
        });
    }

    @Subscribe
    public void theWinner(Object obj){
        if(!(obj instanceof String) || ( !obj.equals("You Won!") && !obj.equals("You Lose!") && !obj.equals("Draw") )){
            return;
        }
        String msg = obj.toString();
        Platform.runLater(() -> {
            // Post who is he winner
            winnerText.setFont(new Font(50));
            winnerText.setText(msg);
            // Disable all the nuttons
            for (Button button : buttons) {
                button.setDisable(true);
            }
            highlightWin();
        });
    }

    @Subscribe
    public void terminate(Object obj){
        if(!(obj instanceof String) || !obj.equals("Terminate")){
            return;
        }
        Platform.runLater(() -> {
            for (Button button : buttons) {
                button.setDisable(true);
            }
            String textField = winnerText.getText();
            if(!textField.startsWith("You ") && !textField.equals("Draw")){
                winnerText.setText("Your Opponent Left The Match");
                winnerText.setFont(new Font(30));
            }
            EventBus.getDefault().unregister(this);
        });
    }

    // Method to remove text from all buttons
    void removeTextFromButtons() {
        Platform.runLater(() -> {
            // Removes text from buttons
            b1.setText("");
            b2.setText("");
            b3.setText("");
            b4.setText("");
            b5.setText("");
            b6.setText("");
            b7.setText("");
            b8.setText("");
            b9.setText("");
        });
    }


    @FXML
    void initialize() throws IOException {

        EventBus.getDefault().register(this);
        SimpleClient.getClient().sendToServer("#join");
        buttons = List.of(b1, b2, b3, b4, b5, b6, b7, b8, b9);
        // Display the wait screen
        b1.setText("W");
        b2.setText("A");
        b3.setText("I");
        b4.setText("");
        b5.setText("T");
        b6.setText("");
        b7.setText("...");
        b8.setText("...");
        b9.setText("...");
    }

    @FXML
    void highlightWin(){
        Platform.runLater(() -> {
            // Check rows
            if (b1.getText() != null && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText()) && !b1.getText().isEmpty()) {
                highlightWinningButtons(b1, b2, b3);
            }
            if (b4.getText() != null && b4.getText().equals(b5.getText()) && b5.getText().equals(b6.getText()) && !b4.getText().isEmpty()) {
                highlightWinningButtons(b4, b5, b6);
            }
            if (b7.getText() != null && b7.getText().equals(b8.getText()) && b8.getText().equals(b9.getText()) && !b7.getText().isEmpty()) {
                highlightWinningButtons(b7, b8, b9);
            }

            // Check columns
            if (b1.getText() != null && b1.getText().equals(b4.getText()) && b4.getText().equals(b7.getText()) && !b1.getText().isEmpty()) {
                highlightWinningButtons(b1, b4, b7);
            }
            if (b2.getText() != null && b2.getText().equals(b5.getText()) && b5.getText().equals(b8.getText()) && !b2.getText().isEmpty()) {
                highlightWinningButtons(b2, b5, b8);
            }
            if (b3.getText() != null && b3.getText().equals(b6.getText()) && b6.getText().equals(b9.getText()) && !b3.getText().isEmpty()) {
                highlightWinningButtons(b3, b6, b9);
            }

            // Check diagonal (top-left to bottom-right)
            if (b1.getText() != null && b1.getText().equals(b5.getText()) && b5.getText().equals(b9.getText()) && !b1.getText().isEmpty()) {
                highlightWinningButtons(b1, b5, b9);
            }

            // Check diagonal (top-right to bottom-left)
            if (b3.getText() != null && b3.getText().equals(b5.getText()) && b5.getText().equals(b7.getText()) && !b3.getText().isEmpty()) {
                highlightWinningButtons(b3, b5, b7);
            }
        });
    }

    // Helper function to highlight winning buttons
    private void highlightWinningButtons(Button b1, Button b2, Button b3) {
        Platform.runLater(() -> {
            b1.setStyle("-fx-background-color: green; -fx-opacity: 1.0;");
            b2.setStyle("-fx-background-color: green; -fx-opacity: 1.0;");
            b3.setStyle("-fx-background-color: green; -fx-opacity: 1.0;");
        });
    }
}