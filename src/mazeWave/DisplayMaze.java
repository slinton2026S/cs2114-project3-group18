
package mazeWave;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.util.function.BiConsumer;
import javax.swing.*;

/**
 * Displays the maze in a Swing JFrame.
 *
 * @author sjlin
 * @version Nov 18, 2026
 */
public class DisplayMaze {

    private volatile StoreMaze maze;

    private JFrame frame;
    private MazePanel mazePanel;
    private JLabel statusLabel;
    private JTextField widthField;
    private JTextField heightField;
    private JButton generateButton;
    

    /**
     * Initializes the maze window.
     *
     * @param maze the current maze
     */
    public DisplayMaze(StoreMaze maze) {
        this.maze = maze;

        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Maze Wave");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            mazePanel = new MazePanel();
            statusLabel = new JLabel("Maze Wave");

            frame.add(mazePanel, BorderLayout.CENTER);
            frame.add(statusLabel, BorderLayout.SOUTH);

            frame.setSize(800, 800);
            frame.setMinimumSize(new Dimension(400, 400));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    
    
    
    private BiConsumer<Integer, Integer> generateAction;
    private BiConsumer<Integer, Integer> moveAction;
    private int playerX = -1;
    private int playerY = -1;
    
    // ----------------------------------------------------------
    /**
     * set generate action
     * @param action the action
     */
    public void setGenerateAction(
        BiConsumer<Integer, Integer> action) {
        this.generateAction = action;
    }
    
    public void setMoveAction(
        BiConsumer<Integer, Integer> action) {

        this.moveAction = action;
    }
    
    public void setPlayerPosition(int x, int y) {
        this.playerX = x;
        this.playerY = y;
        printMaze();
    }
    
    public void showInputScreen() {
        SwingUtilities.invokeLater(() -> {

            JPanel inputPanel = new JPanel();

            inputPanel.add(new JLabel("Maze Width (5-100):"));

            widthField = new JTextField("20", 5);
            inputPanel.add(widthField);

            inputPanel.add(new JLabel("Maze Height (5-100):"));

            heightField = new JTextField("20", 5);
            inputPanel.add(heightField);

            generateButton = new JButton("Generate Maze");

            generateButton.addActionListener(e -> {
                try {
                    int width = Integer.parseInt(widthField.getText());
                    int height = Integer.parseInt(heightField.getText());

                    if (width < 5 || width > 100 ||
                        height < 5 || height > 100) {

                        JOptionPane.showMessageDialog(
                            frame,
                            "Width and height must be between 5 and 100."
                        );
                        return;
                    }

                    if (generateAction != null) {
                        generateAction.accept(width, height);
                    }

                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Please enter valid integers."
                    );
                }
            });

            inputPanel.add(generateButton);

            frame.getContentPane().removeAll();
            frame.add(inputPanel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }
    
    public void showMazeScreen() {
        SwingUtilities.invokeLater(() -> {
            frame.getContentPane().removeAll();

            frame.add(mazePanel, BorderLayout.CENTER);
            frame.add(statusLabel, BorderLayout.SOUTH);

            frame.revalidate();
            frame.repaint();
        });
    }
    
    public void showPlayAgainButton() {

        SwingUtilities.invokeLater(() -> {

            JButton playAgainButton = new JButton("Play Again");

            playAgainButton.addActionListener(e -> {
                showInputScreen();
            });

            JPanel bottomPanel = new JPanel(new BorderLayout());

            bottomPanel.add(statusLabel, BorderLayout.CENTER);
            bottomPanel.add(playAgainButton, BorderLayout.EAST);

            frame.remove(statusLabel);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            frame.revalidate();
            frame.repaint();
        });
    }

    /**
     * Updates the current maze.
     *
     * @param maze the new maze
     */
    public void setMaze(StoreMaze maze) {
        this.maze = maze;

        if (mazePanel != null) {
            mazePanel.repaint();
        }
    }

    /**
     * Refreshes the maze display.
     */
    public void printMaze() {
        if (mazePanel != null) {
            mazePanel.repaint();
        }
    }

    /**
     * Updates the status text at the bottom of the window.
     *
     * @param text the text to display
     */
    public void printsText(String text) {
        SwingUtilities.invokeLater(() -> {
            if (statusLabel != null) {
                statusLabel.setText(text);
            }
        });
    }

    /**
     * No longer needed for a graphical display.
     */
    public void clearConsole() {
        // Nothing to clear in a JFrame.
    }
    
    

    /**
     * Custom JPanel responsible for drawing the maze.
     */
    private class MazePanel extends JPanel {

        private static final int PADDING = 20;
        
        public MazePanel() {

            setFocusable(true);

            addKeyBinding("UP", 0, -1);
            addKeyBinding("DOWN", 0, 1);
            addKeyBinding("LEFT", -1, 0);
            addKeyBinding("RIGHT", 1, 0);

            addKeyBinding("W", 0, -1);
            addKeyBinding("S", 0, 1);
            addKeyBinding("A", -1, 0);
            addKeyBinding("D", 1, 0);
        }
        
        private void addKeyBinding(
            String key, int dx, int dy) {

            getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(key), key
            );

            getActionMap().put(key, new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if (moveAction != null) {
                        moveAction.accept(dx, dy);
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            StoreMaze currentMaze = maze;

            if (currentMaze == null) {
                return;
            }

            int[][] grid = currentMaze.getGrid();

            int rows = currentMaze.getHeight();
            int cols = currentMaze.getWidth();

            if (rows == 0 || cols == 0) {
                return;
            }

            Graphics2D g2 = (Graphics2D) g.create();

            // Calculate cell size based on available window space.
            int cellWidth = (getWidth() - PADDING * 2) / cols;
            int cellHeight = (getHeight() - PADDING * 2) / rows;

            int cellSize = Math.max(1, Math.min(cellWidth, cellHeight));

            // Center the maze in the window.
            int mazeWidth = cellSize * cols;
            int mazeHeight = cellSize * rows;

            int offsetX = (getWidth() - mazeWidth) / 2;
            int offsetY = (getHeight() - mazeHeight) / 2;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {

                    Color color;

                    switch (grid[row][col]) {

                        case StoreMaze.WALL:
                            color = Color.DARK_GRAY;
                            break;

                        case StoreMaze.PATH:
                            color = Color.WHITE;
                            break;

                        case StoreMaze.START:
                            color = new Color(46, 204, 113);
                            break;

                        case StoreMaze.FINISH:
                            color = new Color(231, 76, 60);
                            break;

                        case StoreMaze.VISITED:
                            color = new Color(174, 214, 241);
                            break;

                        case StoreMaze.SOLUTION:
                            color = new Color(241, 196, 15);
                            break;

                        case StoreMaze.PLAYER:
                            color = new Color(52, 152, 219);
                            break;

                        default:
                            color = Color.MAGENTA;
                            break;
                    }

                    int x = offsetX + col * cellSize;
                    int y = offsetY + row * cellSize;

                    g2.setColor(color);
                    g2.fillRect(x, y, cellSize, cellSize);

                    // Subtle grid lines.
                    if (cellSize >= 5) {
                        g2.setColor(new Color(220, 220, 220));
                        g2.drawRect(x, y, cellSize, cellSize);
                    }
                }
            }
            
            // Draw the player on top of the maze.
            if (playerX >= 0 && playerY >= 0) {

                int x = offsetX + playerX * cellSize;
                int y = offsetY + playerY * cellSize;

                g2.setColor(new Color(52, 152, 219));

                int inset = Math.max(2, cellSize / 5);

                g2.fillOval(
                    x + inset,
                    y + inset,
                    cellSize - inset * 2,
                    cellSize - inset * 2
                );
            }

            g2.dispose();
        }
    }
}
