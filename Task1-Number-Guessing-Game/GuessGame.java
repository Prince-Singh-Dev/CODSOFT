import java.util.Random;
import java.util.Scanner;

public class GuessGame{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int roundsWon = 0;
        String playAgain = "yes";

        System.out.println("===== WELCOME TO THE NUMBER GUESSING GAME =====");

        while (playAgain.equalsIgnoreCase("yes")) {

            int randomNumber = random.nextInt(100) + 1;
            int maxAttempts = 5;
            boolean isGuessed = false;

            System.out.println("\nI have selected a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            for (int attempt = 1; attempt <= maxAttempts; attempt++) {

                System.out.print("\nAttempt " + attempt + " - Enter your guess: ");
                int userGuess = sc.nextInt();

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    System.out.println("You guessed it in " + attempt + " attempt(s).");

                    roundsWon++;
                    isGuessed = true;
                    break;
                }
                else if (userGuess > randomNumber) {
                    System.out.println("Your guess is too high.");
                }
                else {
                    System.out.println("Your guess is too low.");
                }

                System.out.println("Attempts remaining: " + (maxAttempts - attempt));
            }

            if (!isGuessed) {
                System.out.println("\nYou have used all your attempts.");
                System.out.println("The correct number was: " + randomNumber);
            }

            System.out.println("\nCurrent Score (Rounds Won): " + roundsWon);

            System.out.print("\nDo you want to play another round? (yes/no): ");
            playAgain = sc.next();
        }

        System.out.println("\n===== GAME OVER =====");
        System.out.println("Total Rounds Won: " + roundsWon);
        System.out.println("Thank you for playing!");

        sc.close();
    }
}
