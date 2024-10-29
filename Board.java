import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Board
{
    private String solvedPhrase;
    private String phrase;
    private int currentLetterValue;

    // Constructor
    public Board()
    {
        solvedPhrase = "";
        phrase = loadPhrase(); // Load a random phrase from the file
        setLetterValue(); // Set initial letter value
    }

    // Accessor methods
    public String getSolvedPhrase()
    {
        return solvedPhrase;
    }

    public String getPhrase()
    {
        
        return phrase;

    }

    public int getCurrentLetterValue()
    {
        return currentLetterValue;
    }

    // Mutator methods
    public void setPhrase(String newPhrase)
    {
        this.phrase = newPhrase;
    }

    public void setSolvedPhrase(String newSolvedPhrase)
    {
        this.solvedPhrase = newSolvedPhrase;
    }

    // Set letter value method
    public void setLetterValue()
    {
        int randomInt = (int) ((Math.random() * 10) + 1) * 100;
        currentLetterValue = randomInt;
    }

    // Check if the phrase is solved
    public boolean isSolved(String guess)
    {
        return phrase.equalsIgnoreCase(guess);
    }

    // Load a random phrase from a file
    private String loadPhrase()
    {
        ArrayList<String> phrases = new ArrayList<>();
        String fileName = "phrases.txt";
        
        // Read all lines from the file into an ArrayList
        try (Scanner fileScanner = new Scanner(new File(fileName)))
        {
            while (fileScanner.hasNextLine())
            {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) // Avoid empty lines
                {
                    phrases.add(line);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error: File not found. Make sure " + fileName + " exists in the correct directory.");
            return "DEFAULT PHRASE"; // Fallback if file not found
        }

        // Pick a random phrase from the list
        if (phrases.isEmpty())
        {
            System.out.println("Error: No phrases found in the file.");
            return "DEFAULT PHRASE";
        }

        Random random = new Random();
        int index = random.nextInt(phrases.size());
        String selectedPhrase = phrases.get(index).toUpperCase();
        
        // Create the solved phrase with blanks
        solvedPhrase = "";
        for (int i = 0; i < selectedPhrase.length(); i++)
        {
            if (selectedPhrase.charAt(i) == ' ')
            {
                solvedPhrase += "  ";
            }
            else
            {
                solvedPhrase += "_ ";
            }
        }
        return selectedPhrase;
    }

  // 2.5.3
  // the code segment below guesses the letter of the phrase
  // subsequent to looping thru each letter in the phrase, if the letter is equal to the guess, then foundLetter is set to true and the newSolvedletter is the guess 
  // otherwise, the newSolvedPhrase is set to += the substring of the solvdPhrase 
  // the solvedPhrase is intialized 
  // finally, solvedPhrase is returned 
  public boolean guessLetter(String guess)
  {
    boolean foundLetter = false;
    String newSolvedPhrase = "";
    
    for (int i = 0; i < phrase.length(); i++) // looping through the length of phrase 
    {
        
      
      if (phrase.substring(i, i + 1).equals(guess)) // conditional statement checking if the substring is equal to the guess made
      {
        newSolvedPhrase += guess + " "; // the guess concatenated with an empty string (space) is added onto the variable phrase
        foundLetter = true; // since the letter has been found wich was determined by the conditional, we set foundLetter to true
      }
      else // else statement (part of the conditional)
      {
        newSolvedPhrase += solvedPhrase.substring(i * 2, i * 2 + 1) + " "; // the solved phrase's substring is added onto the newSolvedPhrase
      }
    }
    solvedPhrase = newSolvedPhrase; // intialize newSolvedPhrase to solvedPhrase
    return foundLetter; // returning the foundLetter
  } 
}