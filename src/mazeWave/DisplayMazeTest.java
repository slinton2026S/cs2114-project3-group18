package mazeWave;

import java.awt.event.ActionEvent;
import javax.swing.Action;

// -------------------------------------------------------------------------
/**
 * Tests the DisplayMaze test.
 * 
 * @author sjlin
 * @version Nov 12, 2026
 */
public class DisplayMazeTest
    extends student.TestCase
{

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
     * Tests the setGenerateAction and setMoveAction methods.
     */
    public void testSetActions()
    {
        Exception exception = null;
        try
        {
            displayMaze.setGenerateAction((w, h) -> {
            });
            displayMaze.setMoveAction((dx, dy) -> {
            });
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNull(exception);
    }


    /**
     * Tests the setPlayerPosition method.
     */
    public void testSetPlayerPosition()
    {
        Exception exception = null;
        try
        {
            displayMaze.setPlayerPosition(1, 2);
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNull(exception);
    }


    /**
     * Tests the showInputScreen method.
     */
    public void testShowInputScreen()
    {
        Exception exception = null;
        try
        {
            displayMaze.showInputScreen();
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNull(exception);
    }


    /**
     * Tests the showMazeScreen method.
     */
    public void testShowMazeScreen()
    {
        Exception exception = null;
        try
        {
            displayMaze.showMazeScreen();
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNull(exception);
    }


    /**
     * Tests the showPlayAgainButton method.
     */
    public void testShowPlayAgainButton()
    {
        Exception exception = null;
        try
        {
            displayMaze.showPlayAgainButton();
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNull(exception);
    }


    /**
     * Tests the setMaze method.
     */
    public void testSetMaze()
    {
        Exception exception = null;
        try
        {
            StoreMaze newMaze = new StoreMaze(8, 8);
            displayMaze.setMaze(newMaze);
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNull(exception);
    }


    /**
     * Tests the printMaze method.
     */
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
    }


    /**
     * Tests the printsText method.
     */
    public void testPrintsText()
    {
        Exception exception = null;
        try
        {
            displayMaze.printsText("Testing status text");
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


    /**
     * Tests MazePanel itself.
     */
    public void testMazePanel()
    {
        displayMaze.setMoveAction((dx, dy) -> { //
        });

        displayMaze.setGenerateAction((w, h) -> { //
        });

        displayMaze.showInputScreen();

        // maze with all types
        int[][] grid = storeMaze.getGrid();
        if (grid.length > 2 && grid[0].length > 2)
        {
            grid[0][0] = StoreMaze.WALL;
            grid[0][1] = StoreMaze.PATH;
            grid[0][2] = StoreMaze.START;
            grid[1][0] = StoreMaze.FINISH;
            grid[1][1] = StoreMaze.VISITED;
            grid[1][2] = StoreMaze.SOLUTION;
        }

        // play position
        displayMaze.setPlayerPosition(1, 1);

        // Test key binds
        String[] keys = { "UP", "DOWN", "LEFT", "RIGHT", "W", "S", "A", "D" };
        for (String key : keys)
        {
            Action action = displayMaze.mazePanel.getActionMap().get(key);
            if (action != null)
            {
                action.actionPerformed(
                    new ActionEvent(
                        displayMaze.mazePanel,
                        ActionEvent.ACTION_PERFORMED,
                        key));
            }
        }

        // test paint
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(
            400,
            400,
            java.awt.image.BufferedImage.TYPE_INT_ARGB);
        displayMaze.mazePanel.paintComponent(img.createGraphics());

        // test the screen and buttons
        displayMaze.showInputScreen();
        displayMaze.showMazeScreen();
        displayMaze.showPlayAgainButton();

        displayMaze.printsText("test");
        displayMaze.setMaze(new StoreMaze(20, 20));
        displayMaze.clearConsole();

        assertNotNull(displayMaze.mazePanel);
    }
}
