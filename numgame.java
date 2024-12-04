import java.util.Random;
import java.util.Scanner;

public class numgame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;
        int totalScore = 0;
        int roundsPlayed = 0;

        System.out.println("Welcome to the number guessing game!");

        do {
            roundsPlayed++;
            totalScore += playGame(scanner);
            System.out.println("Do you want to play again? (yes/no):");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        } while (playAgain);

        scanner.close();
        System.out.println("Thank you for playing!");
        System.out.println("Total rounds played: " + roundsPlayed);
        System.out.println("Your total score: " + totalScore);
    }

    private static int playGame(Scanner scanner) {
        Random rand = new Random();
        int numberToGuess = rand.nextInt(100) + 1;
        int guess;
        int numberOfAttempts = 10;
        int attempts = 0;
        int score = 0;

        System.out.println("Guess a number between 1 and 100. You have " + numberOfAttempts + " attempts:");

        while (attempts < numberOfAttempts) {
            guess = scanner.nextInt();
            attempts++;

            if (guess == numberToGuess) {
                score = numberOfAttempts - attempts + 1; // Higher score for fewer attempts
                System.out.println("Congratulations, you guessed the number in " + attempts + " attempts!");
                System.out.println("Your score for this round: " + score);
                return score;
            } else if (guess < numberToGuess) {
                System.out.println("Your guess is too low. Try again:");
            } else {
                System.out.println("Your guess is too high. Try again:");
            }
        }

        System.out.println("You've used all your attempts. The number was " + numberToGuess + ".");
        System.out.println("Your score for this round: 0");
        return 0;
    }
}
