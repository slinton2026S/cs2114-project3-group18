package mazeWave;

import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * The main class that prompts the user and runs the main maze loop.
 * 
 * @author sjlin
 * @version Nov 12, 2026
 */
public class RunMaze
{
    // ~ Fields ................................................................
    private StoreMaze maze;
    private CreateMaze create;
    private Solver solve;
    private DisplayMaze display;

    // ~ Constructors ..........................................................

    /**
     * Initializes RunMaze application and current session.
     */
    public RunMaze()
    {
        this.display = new DisplayMaze(maze);
    }
    // ~Public Methods ........................................................


    /**
     * Starts the main program.
     * 
     * @param args
     *            The argument to start the application loop.
     */
    public static void main(String[] args)
    {
        RunMaze game = new RunMaze();
        game.promptUser();
    }


    /**
     * Prompts the user for input to make the game interactive. (Dimensions,
     * making sure it’s a valid input, restarting the game)
     */
    public void promptUser()
    {
        Scanner scanner = new Scanner(System.in);
        display.printsText("====| Maze Wave |====");
        int width = 0;
        int height = 0;
        while (height <= 3 || width <= 3)
        {
            display.printsText("Pick maze width (> 3) :");
            if (scanner.hasNextInt())
            {
                width = scanner.nextInt();
            }
            else
            {
                scanner.next();
            }
            display.printsText("Pick maze height (> 3) :");
            if (scanner.hasNextInt())
            {
                height = scanner.nextInt();
            }
            else
            {
                scanner.next();
            }
            if (width <= 3 || height <= 3)
            {
                display.printsText("Invalid height/width. Try again.");
            }
        }
        this.maze = new StoreMaze(width, height);
        this.create = new CreateMaze(maze);
        this.solve = new Solver(maze);

        display.printsText("Making the maze...");
        create.generate();
        updateDisplay();
        scanner.close();
    }


    /**
     * Loops through moving through the maze and calls printMaze after each move
     * to update the console.
     */
    public void updateDisplay()
    {
        while (!solve.solved())
        {
            solve.move();
            display.clearConsole();
            display.printMaze(maze.getGrid());
            try
            {
                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                break;
            }
        }
        display.printsText("Steps taken: " + solve.getMoveCount());
        display.printsText("Solve time: " + solve.getElapsedTime() + " ns");
    }
}
