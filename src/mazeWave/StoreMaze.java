package mazeWave;

import mazeWave.StoreMaze;

/**
 * Model: stores the maze data (dimensions, grid, start and finish).
 * The grid is indexed as grid[y][x].
 */
public class StoreMaze {
    // Cell types
    public static final int WALL = 0;
    public static final int PATH = 1;
    public static final int START = 2;
    public static final int FINISH = 3;
    public static final int VISITED = 4;   // explored by the solver
    public static final int SOLUTION = 5;  // on the shortest path
    public static final int PLAYER = 6;    // the player's character

    private final int width;
    private final int height;
    private final int[][] grid;
    private int[] start;
    private int[] finish;

    /** Creates a maze of all walls with the given dimensions. */
    public StoreMaze(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                "Maze dimensions must be positive: " + width + "x" + height);
        }
        this.width = width;
        this.height = height;
        this.grid = new int[height][width]; // all WALL (0)
        this.start = new int[] {0, 0};
        this.finish = new int[] {width - 1, height - 1};
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public int[][] getGrid() { return grid; }

    public boolean inBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /** Returns the cell type at (x, y). Throws if out of bounds. */
    public int getCell(int x, int y) {
        if (!inBounds(x, y)) {
            throw new IndexOutOfBoundsException("Cell out of bounds: (" + x + ", " + y + ")");
        }
        return grid[y][x];
    }

    /** Sets the cell type at (x, y). Throws if out of bounds. */
    public void setCell(int x, int y, int type) {
        if (!inBounds(x, y)) {
            throw new IndexOutOfBoundsException("Cell out of bounds: (" + x + ", " + y + ")");
        }
        grid[y][x] = type;
    }

    /** Returns {x, y} of the entrance. */
    public int[] getStart() { return new int[] {start[0], start[1]}; }

    /** Returns {x, y} of the finish. */
    public int[] getFinish() { return new int[] {finish[0], finish[1]}; }

    public void setStart(int x, int y) {
        if (!inBounds(x, y)) {
            throw new IndexOutOfBoundsException("Start out of bounds: (" + x + ", " + y + ")");
        }
        start = new int[] {x, y};
        grid[y][x] = START;
    }

    public void setFinish(int x, int y) {
        if (!inBounds(x, y)) {
            throw new IndexOutOfBoundsException("Finish out of bounds: (" + x + ", " + y + ")");
        }
        finish = new int[] {x, y};
        grid[y][x] = FINISH;
    }

    /** Returns an independent copy (used so solving doesn't alter the player's maze). */
    public StoreMaze copy() {
        StoreMaze c = new StoreMaze(width, height);
        for (int y = 0; y < height; y++) {
            System.arraycopy(grid[y], 0, c.grid[y], 0, width);
        }
        c.start = getStart();
        c.finish = getFinish();
        return c;
    }
}
