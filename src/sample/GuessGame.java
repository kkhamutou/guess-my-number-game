package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GuessGame {

    private int difficulty; // 10, 50, 100
    private int number;
    private Random r = new Random();
    private boolean isGuessed = false;
    private List<Integer> guesses = new ArrayList<>();

    private static final List<String> lowPhrases;
    private static final List<String> correctPhrases;
    private static final List<String> highPhrases;
    private static final List<String> errorPhrases;

    static {
        lowPhrases = new ArrayList<>();
        correctPhrases = new ArrayList<>();
        highPhrases = new ArrayList<>();
        errorPhrases = new ArrayList<>();

        lowPhrases.add("Too Low. Try again");
        lowPhrases.add("The number is too low. Think again!");
        lowPhrases.add("My buddy, you should try one more time. The number is too low");
        lowPhrases.add("Try to think higher. The number is too low");
        lowPhrases.add("You should want more. The number is too small");
        lowPhrases.add("You have no idea what the number is. Try more!");

        correctPhrases.add("You have such a nice penis");

        errorPhrases.add("Such a retard");

        highPhrases.add("Too High. Try again");
        highPhrases.add("This number is too high");
        highPhrases.add("Well, my dear, try one more time. The number is too high");
        highPhrases.add("You must be kidding me! This number is too large");
        highPhrases.add("No, you are wrong! This number is higher then I think");
        highPhrases.add("Jack, think again. The number is too high");
        highPhrases.add("You guessed the number too large. Try better!");
    }


    public GuessGame(int difficulty) {
        this.difficulty = difficulty;
    }

    public void start() {
        number = r.nextInt(difficulty) + 1;
    }

    public int getNumber() {
        return number;
    }

    public String getPhrase(int guess) {
        guesses.add(guess);
        if (guess < number) {
            return lowPhrases.get(r.nextInt(lowPhrases.size()));
        } else if (guess > number) {
            return highPhrases.get(r.nextInt(highPhrases.size()));
        } else {
            isGuessed = true;
            return correctPhrases.get(r.nextInt(correctPhrases.size()));
        }
    }

    public String getErrorPhrase() {
        return errorPhrases.get(r.nextInt(errorPhrases.size()));
    }

    public List<Integer> getGuesses() {
        return guesses;
    }

    public boolean isGuessed() {
        return isGuessed;
    }
}