
package mazeWave;

/**
 * Tests the Solver class.
 *
 * @author
 * @version Nov 18, 2026
 */
public class SolverTest extends student.TestCase {

    private StoreMaze maze;
    private Solver solver;

    /**
     * Sets up a maze and solver.
     */
    public void setUp()
    {
        maze = new StoreMaze(5, 5);
        solver = new Solver(maze);
    }

    /**
     * Tests the solver constructor.
     */
    public void testConstructor()
    {
        assertNotNull(solver);
        assertFalse(solver.solved());
        assertFalse(solver.isFinished());
    }

    /**
     * Tests solving a simple maze.
     */
    public void testSolve()
    {
        maze.setCell(1, 1, StoreMaze.PATH);
        maze.setCell(1, 2, StoreMaze.PATH);
        maze.setCell(1, 3, StoreMaze.PATH);
        maze.setStart(1, 1);
        maze.setFinish(1, 3);

        // Recreate solver after setting up maze
        solver = new Solver(maze);
        solver.solve();

        assertTrue(solver.solved());
        assertTrue(solver.isFinished());
        assertEquals("Move count", 2, solver.getMoveCount());
        assertTrue(solver.getElapsedTime() >= 0);
    }

    /**
     * Tests that move returns false after completion.
     */
    public void testMoveAfterFinished()
    {
        maze.setStart(1, 1);
        maze.setFinish(1, 1);

        solver = new Solver(maze);

        assertFalse(solver.move());
        assertTrue(solver.solved());
        assertTrue(solver.isFinished());
        assertFalse(solver.move());
    }

    /**
     * Tests the queue methods exposed by Solver.
     */
    public void testQueueMethods()
    {
        int[] coord = {2, 2};

        solver.enqueue(coord);

        assertFalse(solver.isEmpty());
        assertFalse(solver.isEmpty());
    }
}
