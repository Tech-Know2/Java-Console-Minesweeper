public class MineSweeper
{
  public boolean[][] mineMap = new boolean [5][5];
  public char[][] displayMap = new char [5][5];

  public int totalMines = 10;
  public int minesFound = 0;
  public int flagsPlaced = 0;
  public boolean stillAlive = true;

  //True means a mine is there, false means no mine there
  
  public MineSweeper()
  {
    SetUpDisplay();
    SetUpMines();
  }

  public void SetUpDisplay() 
  {
    System.out.println("=======================");
    
    for(int i = 0; i < displayMap.length; i++)
    {
      if(i == 0)
      {
        System.out.print("  ");
      }
      
      System.out.print(i + " ");
    }

    System.out.println();
    
    for (int r = 0; r < displayMap.length; r++) 
    {
        System.out.print(r + " ");
          
        for (int c = 0; c < displayMap[r].length; c++) 
        {
            displayMap[r][c] = '?';
            System.out.print(displayMap[r][c] + " ");
        }
        System.out.println(); // Print new line after each row
      }
    System.out.println("Flags Left: " + (totalMines - flagsPlaced));
    
    System.out.println("=======================");
  }

  public void PrintMap()
  {
    for(int i = 0; i < displayMap.length; i++)
    {
      if(i == 0)
      {
        System.out.print("  ");
      }
      
      System.out.print(i + " ");
    }

    System.out.println();
    
    for (int r = 0; r < displayMap.length; r++) 
    {
        System.out.print(r + " ");
          
        for (int c = 0; c < displayMap[r].length; c++) 
        {
            System.out.print(displayMap[r][c] + " ");
        }
        System.out.println(); // Print new line after each row
      }
    System.out.println("=======================");
  }

  public void SetUpMines()
  {
    for(int i = 0; i < totalMines;)
    {
      int randomRow = (int) (Math.random() * mineMap.length);
      int randomCol = (int) (Math.random() * mineMap.length);

      if(isOccupied(randomRow, randomCol) != true)
      {
        i++;
        mineMap[randomRow][randomCol] = true;
      }
    }
  }

  private boolean isOccupied(int row, int col)
  {
    if(mineMap[row][col] == true)
    {
      return true;
    } else 
    {
      return false;
    }
  }

  public void MakeMove(int flag, int row, int col)
  {
    if(flag == 0)
    {
      PlaceFlag(row,col);
    } else 
    {
      if(isOccupied(row,col) == false)
      {
        UpdateDisplays(row,col);
      } else 
      {
        System.out.println("You lost, that was a mine");
        stillAlive = false;
        //System to uncover and show all the mines and safe spots
      }
    }
  }

  public boolean hasPlayedThere(int row, int col)
  {
    if(displayMap[row][col] != '?')
    {
      return true;
    } else 
    {
      return false;
    }
  }

  public void UpdateDisplays(int row, int col)
  {
    int num = GetSpotValue(row, col);
    displayMap[row][col] = (char) (num + '0');
  }

  public int GetSpotValue(int row, int col) 
  {
      int num = 0;
      
      for(int r = row - 1; r <= row + 1; r++) 
      {
          for(int c = col - 1; c <= col + 1; c++) 
          {
              if(r >= 0 && r < mineMap.length && c >= 0 && c < mineMap[0].length) 
              {
                  if(mineMap[r][c]) 
                  {
                      num++;
                  }
              }
          }
      }
      System.out.println(num);
    
      return num;
  }

  public void PlaceFlag(int row, int col)
  {
    displayMap[row][col] = 'F';
    flagsPlaced++;

    if(mineMap[row][col] == true)
    {
      minesFound++;
    }
  }
}
