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
    private RunMaze game;

    // ~Public Methods ........................................................

    /**
     * Sets up a maze and solver for each test.
     */
    @Before
    public void setUp()
    {
        game = new RunMaze();
    }


    /**
     * Checks that RunMaze creates objects properly.
     */
    @Test
    public void testRunMaze()
    {
        assertNotNull(game);
    }


    /**
     * Tests the main method.
     */
    @Test
    public void testMain()
    {
        Exception exception = null;
        try
        {
            RunMaze.main(new String[0]);
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNull(exception);
    }


    /**
     * Tests the startMaze method.
     */
    @Test
    public void testStartMaze()
    {
        Exception exception = null;
        try
        {
            game.startMaze(10, 10);
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNull(exception);
    }


    /**
     * Tests the movePlayer method.
     */
    public void testMovePlayer()
    {
        game.startMaze(50, 50);

        Exception exception = null;
        try
        {
            game.movePlayer(0, 5);
            game.movePlayer(5, 0);
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNull(exception);
    }
}
