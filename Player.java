/*
 * Activity 2.5.2
 * 
 * A Player class the PhraseSolverGame
 */
import java.util.Scanner;

public class Player
{
  /* your code here - attributes */
  public Player(){
    
  }
  
  private String name = "";
  private double points;

  /* your code here - constructor(s) */ 
  public String getName(){
    System.out.print("Enter username: ");
    Scanner thing = new Scanner(System.in);
    String Inputname = thing.nextLine();
    System.out.println(" username " + Inputname);
    return Inputname;

  }
  
  /* your code here - accessor(s) */ 
  public void setName(String x){
    name = x;
  }
  public double getPoints(){
    return points;
  }




}



