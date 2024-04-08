import java.util.Arrays;
import java.util.ArrayList;

public class MineSweeper 
{
    public boolean[][] mineMap = new boolean[10][10];
    public char[][] displayMap = new char[10][10];

    ArrayList<int[]> radius = new ArrayList<int[]>();

    public int totalMines = 30;
    public int minesFound = 0;
    public int flagsPlaced = 0;
    public boolean stillAlive = true;

    public MineSweeper() {
        SetUpDisplay();
    }

    public boolean checkWinStatus()
    {
      for(int r = 0; r < mineMap.length; r++)
      {
        for(int c = 0; c < mineMap[r].length; c++)
        {
          if(mineMap[r][c] != true && displayMap[r][c] == 'F')
          {
            stillAlive = false;

            return false;
          }
        }
      }

      return false;
    }

    public void SetUpDisplay() {
        System.out.println("=======================");

        for (int i = 0; i < displayMap.length; i++) {
            if (i == 0) {
                System.out.print("  ");
            }

            System.out.print(i + " ");
        }

        System.out.println();

        for (int r = 0; r < displayMap.length; r++) {
            System.out.print(r + " ");

            for (int c = 0; c < displayMap[r].length; c++) {
                displayMap[r][c] = '?';
                System.out.print(displayMap[r][c] + " ");
            }
            System.out.println(); // Print new line after each row
        }
        System.out.println("Flags Left: " + (totalMines - flagsPlaced));

        System.out.println("=======================");
    }

    public void PrintMap() {
        for (int i = 0; i < displayMap.length; i++) {
            if (i == 0) {
                System.out.print("  ");
            }

            System.out.print(i + " ");
        }

        System.out.println();

        for (int r = 0; r < displayMap.length; r++) {
            System.out.print(r + " ");

            for (int c = 0; c < displayMap[r].length; c++) {
                System.out.print(displayMap[r][c] + " ");
            }
            System.out.println(); // Print new line after each row
        }
        System.out.println("=======================");
    }

    public void SetUpMines(int row, int col) {
        
        for (int i = 0; i < totalMines; ) {
            int randomRow = (int) (Math.random() * mineMap.length);
            int randomCol = (int) (Math.random() * mineMap.length);

            if (!isOccupied(randomRow, randomCol) && randomRow != row && randomCol != col) {
                i++;
                mineMap[randomRow][randomCol] = true;
            }
        }
    }

    private boolean isOccupied(int row, int col) {
        return mineMap[row][col];
    }

    public void MakeMove(boolean firstTime, int flag, int row, int col) {
        if (firstTime) {
            SetUpMines(row, col);
        }

        if (flag == 0) {
            PlaceFlag(row, col);
        } else {
            if (!isOccupied(row, col)) {
                UpdateDisplays(row, col);
            } else {
                System.out.println("You lost, that was a mine");
                stillAlive = false;
            }
        }

        if(minesFound >= totalMines)
        {
          if(checkWinStatus() == true)
          {
            System.out.println("Congrats you won the game, well done");
          }
        }
    }

    public boolean hasPlayedThere(int row, int col) {
        return displayMap[row][col] != '?';
    }

    public void UpdateDisplays(int row, int col) 
    {
        radius.clear();
    
        int num = GetSpotValue(row, col);
    
        for (int i = 0; i < radius.size(); i++)  //Do radius.size() for all or specific value for only a set amount
        {
            int[] spot = radius.get(i);
            int r = spot[0];
            int c = spot[1];
    
            // Get the value for the empty spot and update the displayMap
            int emptyCellValue = GetSpotValue(r, c);
            char display;

            if(emptyCellValue == 0)
            {
              display = '-';
            } else 
            {
              display = (char) (emptyCellValue + '0');
            }
          
            displayMap[r][c] = display;
        }
    }


    public int GetSpotValue(int row, int col) {
      int[] num = {0, 0, 0};
      int count = 0;

      int[] radiusSpots = new int[2];
  
      for (int r = row - 1; r <= row + 1; r++) 
      {
          for (int c = col - 1; c <= col + 1; c++) 
          {
              if (r != row || c != col) 
              {
                  if (r >= 0 && r < mineMap.length && c >= 0 && c < mineMap[0].length) 
                  {
                      if (mineMap[r][c]) 
                      {
                          count++;
                      } else if(!mineMap[r][c] && containsValueAtIndexZero(radius, r, c) == false && radius.size() < (totalMines * 2))
                      {
                        radiusSpots[0] = r;
                        radiusSpots[1] = c;
                        radius.add(radiusSpots);
                      }
                  }
              }
          }
      }
  
      num[2] = count;

      char display;

      if(count == 0)
      {
          display = '-';
      } else 
      {
         display = (char) (count + '0');
      }
          
      displayMap[row][col] = display;
      
      return count;
  }

  public boolean containsValueAtIndexZero(ArrayList<int[]> intArrayAL, int targetValueOne, int targetValueTwo) 
  {
   for (int[] arr : intArrayAL) 
   {
    if (arr.length > 0 && arr[0] == targetValueOne && arr[1] == targetValueTwo) 
    {
     return true;
    }
   }
   return false;
  }

    public void PlaceFlag(int row, int col) {
        displayMap[row][col] = 'F';
        flagsPlaced++;

        if (mineMap[row][col]) {
            minesFound++;
        }
    }
}
