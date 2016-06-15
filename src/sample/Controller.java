package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextArea textArea;

    @FXML
    private MenuItem start;

    @FXML
    private MenuItem difficult;

    @FXML
    private MenuItem medium;

    @FXML
    private MenuItem easy;

    @FXML
    private MenuItem save;

    @FXML
    private MenuItem help;

    @FXML
    private MenuItem showScore;

    @FXML
    private TextField textField;

    @FXML
    private Button enterButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button getAnswerButton;

    private int currentLevel = 10;

    private List<Integer> score = new ArrayList<>();

    private GuessGame guessGame;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("DIE");

        textArea.setWrapText(true);

        difficult.setOnAction(e -> setCurrentLevel(100));
        medium.setOnAction(e -> setCurrentLevel(50));
        easy.setOnAction(e -> setCurrentLevel(10));


        start.setOnAction(e -> {
            guessGame = new GuessGame(currentLevel);
            guessGame.start();
            textArea.setText("You must guess a number from 1 to " + currentLevel);
        });

        textField.setOnAction(e -> processInput());
        enterButton.setOnAction(e -> processInput());

        getAnswerButton.setOnAction(e -> {
            if (isGameStarted()) {
                textArea.setText("Start the game first");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Correct result is " + guessGame.getNumber()).append(System.lineSeparator());

            for (int i = 0; i < guessGame.getGuesses().size(); i++) {
                sb.append("Turn: ").append(i + 1).append(" Result: ").append(guessGame.getGuesses().get(i)).append(System.lineSeparator());
            }

            textArea.setText(sb.toString());
        });

        deleteButton.setOnAction(e -> {
            textField.setText("");
            textArea.setText("");
        });

        showScore.setOnAction(e -> {
            if (!score.isEmpty()) {
                textArea.setText("Best Score is " + getBestScore());
            }
        });
    }

    private int getBestScore() {
        return Collections.min(score);
    }

    private void processInput() {
        if (isGameStarted()) {
            textArea.setText("Start the game first");
            return;
        }

        if (!guessGame.isGuessed()) {

            String s = textField.getText();

            try {
                int guess = Integer.parseInt(s);
                textArea.setText(guessGame.getPhrase(guess));
            } catch (NumberFormatException nfe) {
                textArea.setText(guessGame.getErrorPhrase());
            }

            if (guessGame.isGuessed()) {
                score.add(guessGame.getGuesses().size());
            }
        } else {
            textArea.setText("Game over");
        }
    }

    private void setCurrentLevel(int difficulty) {
        guessGame = null;
        currentLevel = difficulty;
    }

    private boolean isGameStarted() {
        return guessGame == null;
    }
}
