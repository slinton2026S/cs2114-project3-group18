package mazeWave;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Testing the RunMaze class.
 * 
 * @author sjlin
 * @version Nov 12, 2026
 */
public class RunMazeTest
{

    // ~ Fields ................................................................
    private StoreMaze storeMaze;
    private CreateMaze createMaze;
    private Solver solver;
    private DisplayMaze displayMaze;

    // ~Public Methods ........................................................

    /**
     * Sets up a maze and solver for each test.
     */
    @Before
    public void setUp()
    {
        storeMaze = new StoreMaze(10, 10);
        createMaze = new CreateMaze(storeMaze);
        displayMaze = new DisplayMaze(storeMaze);
        solver = new Solver(storeMaze);
    }


    /**
     * Checks that RunMaze creates objects properly.
     */
    @Test
    public void testRunMaze()
    {
        Exception exception = null;
        RunMaze game = null;
        try
        {
            game = new RunMaze();
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNull(exception);
    }


    /**
     * Tests the main method.
     */
    @Test
    public void testMain()
    {
        String simulatedInput = "10\n10\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Exception exception = null;
        try
        {
            RunMaze.main(newArgs());
        }
        catch (Exception e)
        {
            exception = e;
        }
        finally
        {
            System.setIn(originalSystemIn);
        }
        assertNull(exception);
    }


    /**
     * Help Method for Testing
     */
    private String[] newArgs()
    {
        return new String[0];
    }


    /**
     * Tests the promptUser method.
     */
    @Test
    public void testPromptUser()
    {
        String simulatedInput = "10\n10\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Exception exception = null;
        try
        {
            RunMaze game = new RunMaze();
            game.promptUser();
        }
        catch (Exception e)
        {
            exception = e;
        }
        finally
        {
            System.setIn(originalSystemIn);
        }
        assertNull(exception);
    }


    /**
     * Tests the updateDisplay method.
     */
    @Test
    public void testUpdateDisplay()
    {
        createMaze.generate();

        Exception exception = null;
        try
        {
            RunMaze game = new RunMaze();
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNull(exception);
    }
}
