
package mazeWave;

// -------------------------------------------------------------------------

/**
 * Tests the CreateMaze class.
 *
 * @author
 * @version Nov 18, 2026
 */
public class CreateMazeTest extends student.TestCase {

    private StoreMaze storeMaze;
    private CreateMaze createMaze;

    /**
     * Sets up StoreMaze and CreateMaze before each test.
     */
    public void setUp()
    {
        storeMaze = new StoreMaze(5, 5);
        createMaze = new CreateMaze(storeMaze);
    }

    /**
     * Tests the CreateMaze constructor.
     */
    public void testCreateMaze()
    {
        assertNotNull(storeMaze);
        assertNotNull(createMaze);
    }

    /**
     * Tests that generate creates a maze successfully.
     */
    public void testGenerate()
    {
        Exception exception = null;

        try
        {
            createMaze.generate();
        }
        catch (Exception e)
        {
            exception = e;
        }

        assertNull(exception);
        assertEquals(StoreMaze.START, storeMaze.getCell(1, 1)); // start is 2
    }

    /**
     * Tests that generate rejects mazes that are too small.
     */
    public void testGenerateTooSmall()
    {
        StoreMaze smallMaze = new StoreMaze(2, 5);
        CreateMaze smallCreateMaze = new CreateMaze(smallMaze);

        Exception exception = null;

        try
        {
            smallCreateMaze.generate();
        }
        catch (Exception e)
        {
            exception = e;
        }

        assertNotNull(exception);
        assertTrue(exception instanceof IllegalArgumentException);
    }
}
