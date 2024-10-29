import java.util.Scanner;

public class PhraseSolver
{
    // Attributes
    
    private int player1Score;
    private int player2Score;
    Board b = new Board();

    // Constructor
    public PhraseSolver()
    {
        
        // Initialize the Board object, which will load a random phrase from "phrases.txt"
        player1Score = 0;
        player2Score = 0;
    }
    
    // Accessor methods
    public int getPlayer1Score()
    {
        return player1Score;
    }

    public int getPlayer2Score()
    {
        return player2Score;
    }
    
    

    // Mutator methods
    public void setPlayer1Score(int score)
    {
        player1Score = score;
    }

    public void setPlayer2Score(int score)
    {
        player2Score = score;
    }

    // Method to play the game
    public void play()
    {
        boolean solved = false;
        int currentPlayer = 1;
        Scanner input = new Scanner(System.in);

        // Introduce the game
        System.out.println("Welcome to the Phrase Solver Game!");
        System.out.println("Try to guess the hidden phrase by guessing letters or solving the phrase.");
        System.out.println("Let's begin!");

        // Main game loop
        while (!solved)
        {
            System.out.println("Player " + currentPlayer + ", it's your turn.");
            System.out.println("Current solved phrase: " + b.getSolvedPhrase());
            System.out.println("Would you like to guess a letter or solve the phrase? (Enter 'letter' or 'solve')");
            String choice = input.nextLine().toLowerCase();

            if (choice.equals("letter"))
            {
                System.out.print("Enter a letter to guess: ");
                String letter = input.nextLine().toUpperCase();

                if (letter.length() != 1)
                {
                    System.out.println("Please enter a single letter.");
                    continue;
                }

                // Check if the letter is in the phrase
                boolean found = b.guessLetter(letter);
                if (found)
                {
                    System.out.println("Correct! The letter '" + letter + "' is in the phrase.");
                    int letterValue = b.getCurrentLetterValue();
                    if (currentPlayer == 1)
                    {
                        player1Score += letterValue;
                    }
                    else
                    {
                        player2Score += letterValue;
                    }
                    System.out.println("Player " + currentPlayer + " earned $" + letterValue);
                }
                else
                {
                    System.out.println("Sorry, the letter '" + letter + "' is not in the phrase.");
                }
            }
            else if (choice.equals("solve"))
            {
                System.out.print("Enter your guess for the phrase: ");
                String guess = input.nextLine().toUpperCase();
                
                // Check if the guess is correct
                if (b.isSolved(guess))
                {
                    System.out.println("Congratulations, Player " + currentPlayer + "! You solved the phrase!");
                    solved = true;
                }
                else
                {
                    System.out.println("Incorrect guess.");
                }
            }
            else
            {
                System.out.println("Invalid choice. Please enter 'letter' or 'solve'.");
                continue;
            }

            // Display scores
            System.out.println("Player 1 Score: $" + player1Score);
            System.out.println("Player 2 Score: $" + player2Score);
            

            // Switch players
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }

        input.close();
        System.out.println("Game Over! Final Scores:");
        System.out.println("Player 1: $" + player1Score);
        System.out.println("Player 2: $" + player2Score);
    }

    // Main method to run the game
    public static void main(String[] args)
    {
        PhraseSolver game = new PhraseSolver();
        game.play();
    }
}
