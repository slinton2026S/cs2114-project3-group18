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


    /**
     * Updates the maze reference when a new maze is generated.
     *
     * @param maze
     *            The new stored maze.
     */
    public void setMaze(StoreMaze maze)
    {
        this.maze = maze;
    }

    /**
     * Formats and prints the maze in the console with symbols.
     * 
     * @param grid
     *            The grid that represents the size of the maze.
     */
    public void printMaze() {
        if (maze == null)
        {
            return;
        }

        int[][] grid = maze.getGrid();

        for (int row = 0; row < maze.getHeight(); row++)
        {
            for (int col = 0; col < maze.getWidth(); col++)
            {
                String symbol;

                switch (grid[row][col])
                {
                    case StoreMaze.WALL:
                        symbol = "██";
                        break;

                    case StoreMaze.PATH:
                        symbol = "  ";
                        break;

                    case StoreMaze.START:
                        symbol = "S ";
                        break;

                    case StoreMaze.FINISH:
                        symbol = "F ";
                        break;

                    case StoreMaze.VISITED:
                        symbol = "\u2022 ";
                        break;

                    case StoreMaze.SOLUTION:
                        symbol = "░░";
                        break;

                    case StoreMaze.PLAYER:
                        symbol = "P ";
                        break;

                    default:
                        symbol = "??";
                        break;
                }

                System.out.print(symbol);
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
