

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;

public class HangmanController implements Initializable {

    @FXML
    private ImageView backgroundImg;

    @FXML
    private Label endGameLbl;

    @FXML
    private Label hiddenWord;

    @FXML
    private Label labelToShow;

    @FXML
    private AnchorPane keyboardPane;

    @FXML
    private AnchorPane gameOverPane;

    @FXML
    private Label winLbl;

    private char chosenChar;
    private Hangman hangman;
    private final String FILE_NAME = "words.txt";
    private final Image[] HANGMAN_PHOTOS = {new Image("file:../img/1.png"), new Image("file:../img/2.png"), new Image("file:../img/3.png"), new Image("file:../img/4.png"), new Image("file:../img/5.png"), new Image("file:../img/6.png"), new Image("file:../img/7.png"), new Image("file:../img/8.png"), new Image("file:../img/9.png")};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startGame();
    }

    //  start a new game
    private void startGame() {
        newGameBoard();
        // create a new game
        try {
            hangman = new Hangman(FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // set the hidden word
        hiddenWord.setText(hangman.showHiddenWord());
        labelToShow.setGraphic(new ImageView(HANGMAN_PHOTOS[hangman.getNumOfGuesses()]));
    }

    // reset the game board
    private void newGameBoard() {
        backgroundImg.setImage(new Image("file:../img/background.jpg"));
        labelToShow.setVisible(true);
        winLbl.setVisible(false);
        gameOverPane.setVisible(false);
        setKeyboard(false);
    }


    @FXML
    void chooseCharOnAction(ActionEvent event) {
        chosenChar = Character.toLowerCase(((Button) (event.getSource())).getText().charAt(0));
        ((Button) (event.getSource())).setDisable(true); // disable the button
        hangman.updateLetterGuessed(chosenChar); // update the letter guessed

        // if the char appears in the secret word
        if ((hangman.getSecretWord().contains(String.valueOf(chosenChar)))) {
            hiddenWord.setText(hangman.showHiddenWord());
            // check win
            if (hangman.checkWin()) {
                setKeyboard(true);
                // show win label
                winTheGameBoard();
            }
        }

        // if the char is not appear in the secret word
        else {
            hangman.setNumOfGuesses(hangman.getNumOfGuesses() + 1);
            labelToShow.setGraphic(new ImageView(HANGMAN_PHOTOS[hangman.getNumOfGuesses()]));
            // check lose
            if (hangman.getNumOfGuesses() == hangman.MAX_TRIES) {
                setKeyboard(true);
                hangman.setNumOfGuesses(hangman.getNumOfGuesses() + 1);
                gameOverBoard();
            }
        }
    }

    // the game board when the player wins
    private void winTheGameBoard() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(newEvent -> {
            winLbl.setVisible(true);
            winLbl.setGraphic(new ImageView(new Image("file:../img/victory.gif")));
            hiddenWord.setVisible(false);
            keyboardPane.setVisible(false);
            gameOverPane.setVisible(true);
            labelToShow.setVisible(false);
            endGameLbl.setText("WINNER! \n The secret word is: " + hangman.getSecretWord());
        });
        pause.play();

    }

    // the game board when the player loses
    private void gameOverBoard() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(newEvent -> {
            labelToShow.setGraphic(new ImageView(HANGMAN_PHOTOS[hangman.getNumOfGuesses()]));
            hiddenWord.setVisible(false);
            keyboardPane.setVisible(false);
            gameOverPane.setVisible(true);
            endGameLbl.setText("GAME OVER");
        });
        pause.play();
    }


    // enable or disable the keyboard
    private void setKeyboard(boolean b) {
        for( Node btn: keyboardPane.getChildren()){
            btn.setDisable(b);
        }
    }


    @FXML
    void exitOnAction(ActionEvent event) {
        exit();
    }

    @FXML
    void playAgainOnAction(ActionEvent event) {
        gameOverPane.setVisible(false);
        hiddenWord.setVisible(true);
        keyboardPane.setVisible(true);
        startGame();
    }

}







