import java.util.Scanner;

public class MyConsole {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.println("=======================");
    System.out.println("Welcome to MineSweeper the console game");
    System.out.println("How to Play:");
    System.out.println("Select the action you are wanting to perform (0 for place flag, and 1 for uncover area)");
    System.out.println("Then input the row and the column to indicated the tile you are wanting the action to be done upon.");
    System.out.println("Happy Hunting!");

    MineSweeper mineSweeper = new MineSweeper();
    boolean firstTime = true;
    
    while(mineSweeper.minesFound < mineSweeper.totalMines && mineSweeper.stillAlive == true)
    {
      System.out.println("Enter Action (0 for Flag, 1 for Uncover)");
      int action = scanner.nextInt();
      
      System.out.println("Enter Row");
      int row = scanner.nextInt();
      
      System.out.println("Enter Column");
      int col = scanner.nextInt();

      if(mineSweeper.hasPlayedThere(row,col) != true)
      {
        mineSweeper.MakeMove(firstTime, action, row, col);
        firstTime = false;
       
        if(mineSweeper.stillAlive == true)
        {
          mineSweeper.PrintMap();
        }
        
      } else 
      {
        System.out.println("You have already played there, try again");
      }
    }
  }
}
