package mazeWave;

// -------------------------------------------------------------------------
/**
 * Prints the maze grid in the console and handles clearing the console.
 * 
 * @author sjlin
 * @version Nov 12, 2026
 */
public class DisplayMaze
{
    // ~ Fields ................................................................
    private StoreMaze maze;
    // ~ Constructors ..........................................................

    /**
     * Initializes the display using the current StoreMaze.
     * 
     * @param maze
     *            The current stored maze,.
     */
    public DisplayMaze(StoreMaze maze)
    {
        this.maze = maze;
    }

    // ~Public Methods ........................................................


    /**
     * Formats and prints the maze in the console with symbols.
     * 
     * @param grid
     *            The grid that represents the size of the maze.
     */
    public void printMaze(int[][] grid)
    {
        if (grid == null)
        {
            return;
        }
        for (int row = 0; row < grid.length; row++)
        {
            for (int col = 0; col < grid[row].length; col++)
            {
                String symbol = "";
                switch (grid[row][col])
                {
                    case 1: // Wall
                        symbol = "\u2588\u2588";
                        break;
                    case 0: // Path
                        symbol = "  ";
                        break;
                    case 2: // Start
                        symbol = "S ";
                        break;
                    case 3: // Finish
                        symbol = "F ";
                        break;
                    case 4: // Traveled path
                        symbol = " \u2022";
                        break;
                    default: // Errors
                        symbol = "??";
                        break;
                }
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }


    /**
     * Displays things like final stats, error messages, or welcome/exit message
     * in the console.
     * 
     * @param text
     *            The text that should be output in the console.
     */
    public void printsText(String text)
    {
        System.out.println(text);
    }


    /**
     * Clears the console to reset the display to blank.
     */
    public void clearConsole()
    {
        for (int i = 0; i < 50; i++)
        {
            System.out.println();
        }
    }
}
