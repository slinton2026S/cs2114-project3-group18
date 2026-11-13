package mazeWave;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Tests the DisplayMaze test.
 * 
 * @author sjlin
 * @version Nov 12, 2026
 */
public class DisplayMazeTest
{

    private StoreMaze storeMaze;
    private DisplayMaze displayMaze;

    /**
     * Sets up StoreMaze and DisplayMaze before each test.
     */
    @Before
    public void setUp()
    {
        storeMaze = new StoreMaze(5, 5);
        displayMaze = new DisplayMaze(storeMaze);
    }


    /**
     * Tests the DisplayMaze is created correctly.
     */
    @Test
    public void testDisplayMaze()
    {
        assertNotNull(storeMaze);
        assertNotNull(displayMaze);
    }


    /**
     * Tests the printMaze method.
     */
    @Test
    public void testPrintMaze()
    {
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

        DisplayMaze nullDisplay = new DisplayMaze(null);
        Exception exception2 = null;
        try
        {
            nullDisplay.printMaze();
        }
        catch (Exception e)
        {
            exception2 = e;
        }
        assertNull(exception2);
    }


    /**
     * Tests the printText method.
     */
    @Test
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
    @Test
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
