package mazeWave;

// -------------------------------------------------------------------------
/**
 * Tests the DisplayMaze test.
 * 
 * @author sjlin
 * @version Nov 12, 2026
 */
public class DisplayMazeTest extends student.TestCase {

    private StoreMaze storeMaze;
    private DisplayMaze displayMaze;

    /**
     * Sets up StoreMaze and DisplayMaze before each test.
     */
    public void setUp()
    {
        storeMaze = new StoreMaze(5, 5);
        displayMaze = new DisplayMaze(storeMaze);
    }


    /**
     * Tests the DisplayMaze is created correctly.
     */
    public void testDisplayMaze()
    {
        assertNotNull(storeMaze);
        assertNotNull(displayMaze);
    }


    /**
     * Tests the printMaze method.
     */
    public void testPrintMaze()
    {
        int[][] sampleGrid = { { 1, 1, 1, 1, 1 }, { 1, 2, 0, 3, 1 },
            { 1, 4, 1, 0, 1 }, { 1, 1, 1, 1, 1 } };

        Exception exception = null;
        try
        {
            displayMaze.printMaze();
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNull(exception);

    }


    /**
     * Tests the printText method.
     */
    public void testPrintText()
    {
        Exception exception = null;
        try
        {
            displayMaze.printsText("Test");
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNull(exception);
    }


    /**
     * Tests the clearConsole method.
     */
    public void testClearConsole()
    {
        Exception exception = null;
        try
        {
            displayMaze.clearConsole();
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNull(exception);
    }
}
