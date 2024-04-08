import java.util.Arrays;

public class MineSweeper 
{
    public boolean[][] mineMap = new boolean[5][5];
    public char[][] displayMap = new char[5][5];

    public int totalMines = 10;
    public int minesFound = 0;
    public int flagsPlaced = 0;
    public boolean stillAlive = true;

    public MineSweeper() {
        SetUpDisplay();
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
    }

    public boolean hasPlayedThere(int row, int col) {
        return displayMap[row][col] != '?';
    }

    public void UpdateDisplays(int row, int col) {
        revealCell(row, col);
    }

    private void revealCell(int row, int col) {
        if (row < 0 || row >= mineMap.length || col < 0 || col >= mineMap[0].length)
            return; // Out of bounds
    
        if (displayMap[row][col] != '?')
            return; // Cell already revealed
    
        int[] spotValue = GetSpotValue(row, col);
        displayMap[row][col] = (char) (spotValue[2] + '0');
    
        if (spotValue[2] == 0) {
            // Recursively reveal neighboring cells
            for (int r = row - 1; r <= row + 1; r++) {
                for (int c = col - 1; c <= col + 1; c++) {
                    if (!(r == row && c == col)) { // Exclude the current cell
                        revealCell(r, c);
                    }
                }
            }
        }
    }

    public int[] GetSpotValue(int row, int col) {
      int[] num = {0, 0, 0};
      int count = 0;
  
      for (int r = row - 1; r <= row + 1; r++) {
          for (int c = col - 1; c <= col + 1; c++) {
              if (r != row || c != col) { // Exclude the current cell
                  if (r >= 0 && r < mineMap.length && c >= 0 && c < mineMap[0].length) {
                      if (mineMap[r][c]) {
                          count++;
                      }
                  }
              }
          }
      }
  
      num[2] = count;
      return num;
  }


    public void PlaceFlag(int row, int col) {
        displayMap[row][col] = 'F';
        flagsPlaced++;

        if (mineMap[row][col]) {
            minesFound++;
        }
    }
}
