import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Hangman {

    private String secretWord;
    private int numOfGuesses = 0;
    private ArrayList<Character> oldLetterGuessed = new ArrayList<Character>();
    public static final int MAX_TRIES = 7;

    // constructor
    public Hangman(String fileName) throws FileNotFoundException {
        ArrayList<String> words = FileHandler.readFile(fileName);
        secretWord = chooseRandomWord(words);
    }

    // read the file and return an array list of words
    private String chooseRandomWord(ArrayList<String> words) {
        Random r = new Random();
        return words.get(r.nextInt(words.size()));
    }

    // getters and setters
    public String getSecretWord() {
        return secretWord;
    }

    public void setNumOfGuesses(int numOfGuesses) {
        this.numOfGuesses = numOfGuesses;
    }

    public int getNumOfGuesses() {
        return numOfGuesses;
    }

    // show the hidden word by underscores
    public String showHiddenWord() {
        String showHidden = "";
        for (char ch : secretWord.toCharArray())
            if (oldLetterGuessed.contains(ch)) {
                showHidden += Character.toUpperCase(ch) + " ";
            } else {
                showHidden += "_ ";
            }
        return showHidden;
    }

    public void updateLetterGuessed(char letterGuessed) {
        oldLetterGuessed.add(letterGuessed);
    }

    // check if the user guessed all the letters
    public boolean checkWin() {
        for (char ch : secretWord.toCharArray())
            if (!oldLetterGuessed.contains(ch)) {
                return false;
            }
        return true;
    }
}
