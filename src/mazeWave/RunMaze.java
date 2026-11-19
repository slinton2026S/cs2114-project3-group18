
package mazeWave;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * Main class that runs Maze Wave.
 *
 * @author sjlin
 * @version Nov 19, 2026
 */
public class RunMaze {

    private StoreMaze maze;
    private StoreMaze solverMaze;

    private CreateMaze create;
    private Solver solve;
    private DisplayMaze display;

    private int playerX;
    private int playerY;
    private int playerMoves;

    private boolean playerFinished;

    public RunMaze() {

        display = new DisplayMaze(maze);

        display.setGenerateAction(this::startMaze);
        display.setMoveAction(this::movePlayer);

        display.showInputScreen();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new RunMaze();
        });
    }

    /**
     * Generates a new maze and starts the player phase.
     */
    public void startMaze(int width, int height) {

        maze = new StoreMaze(width, height);

        create = new CreateMaze(maze);

        display.setMaze(maze);

        display.printsText("Generating maze...");

        create.generate();

        // Initialize player at the entrance.
        int[] start = maze.getStart();

        playerX = start[0];
        playerY = start[1];

        playerMoves = 0;
        playerFinished = false;

        display.setPlayerPosition(playerX, playerY);
        display.showMazeScreen();

        display.printsText(
            "Your turn! Use WASD or arrow keys. Moves: 0"
        );
    }

    /**
     * Attempts to move the player.
     *
     * @param dx horizontal movement
     * @param dy vertical movement
     */
    public void movePlayer(int dx, int dy) {

        if (playerFinished || maze == null) {
            return;
        }

        int nextX = playerX + dx;
        int nextY = playerY + dy;

        // Check boundaries.
        if (!maze.inBounds(nextX, nextY)) {
            return;
        }

        // Prevent walking through walls.
        if (maze.getCell(nextX, nextY) == StoreMaze.WALL) {
            return;
        }

        // Update player position.
        playerX = nextX;
        playerY = nextY;

        playerMoves++;

        display.setPlayerPosition(playerX, playerY);

        display.printsText(
            "Your turn! Moves: " + playerMoves
        );

        // Check whether the player reached the finish.
        int[] finish = maze.getFinish();

        if (playerX == finish[0] && playerY == finish[1]) {

            playerFinished = true;

            display.printsText(
                "Maze completed! Your moves: " + playerMoves
                + " | Now watching BFS..."
            );

            startSolver();
        }
    }

    /**
     * Starts the BFS solver on a separate copy of the maze.
     */
    private void startSolver() {

        // Preserve the original maze and create a clean copy.
        solverMaze = maze.copy();

        display.setPlayerPosition(-1, -1);
        display.setMaze(solverMaze);

        solve = new Solver(solverMaze);

        Timer timer = new Timer(8, e -> {

            boolean continuing = solve.move();

            display.printMaze();

            if (!continuing) {

                ((Timer) e.getSource()).stop();

                if (solve.solved()) {
                    showResults();
                }
                else {
                    display.printsText(
                        "BFS could not find a solution."
                    );
                }
            }
        });

        timer.start();
    }

    /**
     * Compares the player's result with the optimal BFS result.
     */
    private void showResults() {

        int optimalMoves = solve.getMoveCount();

        double efficiency = playerMoves == 0
            ? 0.0
            : (double) optimalMoves / playerMoves * 100.0;

        int extraMoves = playerMoves - optimalMoves;

        String rating;

        if (extraMoves == 0) {
            rating = "Perfect! You found the optimal path!";
        }
        else if (efficiency >= 90) {
            rating = "Excellent!";
        }
        else if (efficiency >= 75) {
            rating = "Great job!";
        }
        else if (efficiency >= 50) {
            rating = "Not bad!";
        }
        else {
            rating = "There's room for improvement!";
        }

        display.printsText(
            String.format(
                "%s Player: %d | Optimal: %d | Extra: %d | Efficiency: %.1f%%",
                rating,
                playerMoves,
                optimalMoves,
                extraMoves,
                efficiency
            )
        );
        display.showPlayAgainButton();
    }
}
