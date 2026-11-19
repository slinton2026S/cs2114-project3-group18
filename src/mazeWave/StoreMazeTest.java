
package mazeWave;

/**
 * Tests the StoreMaze class.
 *
 * @author
 * @version Nov 18, 2026
 */
public class StoreMazeTest extends student.TestCase {

    private StoreMaze maze;

    /**
     * Sets up a maze before each test.
     */
    public void setUp()
    {
        maze = new StoreMaze(5, 5);
    }

    /**
     * Tests the constructor and dimensions.
     */
    public void testConstructor()
    {
        assertEquals("Width", 5, maze.getWidth());
        assertEquals("Height", 5, maze.getHeight());
        assertEquals(StoreMaze.WALL, maze.getCell(0, 0));
    }

    /**
     * Tests invalid dimensions.
     */
    public void testInvalidConstructor()
    {
        Exception exception = null;

        try
        {
            new StoreMaze(0, 5);
        }
        catch (Exception e)
        {
            exception = e;
        }

        assertTrue(exception instanceof IllegalArgumentException);
    }

    /**
     * Tests inBounds.
     */
    public void testInBounds()
    {
        assertTrue(maze.inBounds(0, 0));
        assertTrue(maze.inBounds(4, 4));
        assertFalse(maze.inBounds(-1, 0));
        assertFalse(maze.inBounds(5, 0));
    }

    /**
     * Tests setting and getting cells.
     */
    public void testSetGetCell()
    {
        maze.setCell(2, 3, StoreMaze.PATH);

        assertEquals(StoreMaze.PATH, maze.getCell(2, 3));
    }

    /**
     * Tests accessing cells out of bounds.
     */
    public void testOutOfBounds()
    {
        Exception exception = null;

        try
        {
            maze.getCell(5, 5);
        }
        catch (Exception e)
        {
            exception = e;
        }

        assertTrue(exception instanceof IndexOutOfBoundsException);

        exception = null;

        try
        {
            maze.setCell(-1, 0, StoreMaze.PATH);
        }
        catch (Exception e)
        {
            exception = e;
        }

        assertTrue(exception instanceof IndexOutOfBoundsException);
    }

    /**
     * Tests setting and getting the start and finish.
     */
    public void testStartFinish()
    {
        maze.setStart(1, 1);
        maze.setFinish(3, 3);

        assertEquals(1, maze.getStart()[0]);
        assertEquals(1, maze.getStart()[1]);
        assertEquals(3, maze.getFinish()[0]);
        assertEquals(3, maze.getFinish()[1]);

        assertEquals(StoreMaze.START, maze.getCell(1, 1));
        assertEquals(StoreMaze.FINISH, maze.getCell(3, 3));
    }

    /**
     * Tests copying a maze.
     */
    public void testCopy()
    {
        maze.setCell(2, 2, StoreMaze.PATH);
        maze.setStart(1, 1);
        maze.setFinish(3, 3);

        StoreMaze copy = maze.copy();

        assertEquals(maze.getWidth(), copy.getWidth());
        assertEquals(maze.getHeight(), copy.getHeight());
        assertEquals(StoreMaze.PATH, copy.getCell(2, 2));
        assertEquals(1, copy.getStart()[0]);
        assertEquals(3, copy.getFinish()[0]);

        copy.setCell(2, 2, StoreMaze.WALL);

        assertEquals(StoreMaze.PATH, maze.getCell(2, 2));
    }
}
