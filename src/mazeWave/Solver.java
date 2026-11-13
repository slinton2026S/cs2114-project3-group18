package mazeWave;

import mazeWave.Queue;
import mazeWave.StoreMaze;

/**
 * Model: solves a StoreMaze with breadth-first search using our linked-list Queue.
 * While running, it marks explored cells VISITED and, once solved, the shortest
 * path as SOLUTION in the maze grid (so the view can show progress).
 */
public class Solver {
    private static final int[][] DIRS = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    private final StoreMaze maze;
    private final Queue<int[]> queue = new Queue<>();
    private final boolean[][] seen;
    private final int[][] parent;      // parent[y][x] = index (y*w+x) of the cell we came from
    private int moveCount;             // steps in the shortest path once solved
    private long elapsedTime;          // nanoseconds
    private boolean solved;
    private boolean finished;          // search ended (solved or exhausted)

    public Solver(StoreMaze maze) {
        this.maze = maze;
        this.seen = new boolean[maze.getHeight()][maze.getWidth()];
        this.parent = new int[maze.getHeight()][maze.getWidth()];
        for (int[] row : parent) {
            java.util.Arrays.fill(row, -1);
        }
        int[] s = maze.getStart();
        seen[s[1]][s[0]] = true;
        enqueue(s);
    }

    /**
     * Runs one BFS step. Returns true while the search is continuing and
     * false once it has ended (maze solved or no solution).
     * @return if it worked
     */
    public boolean move() {
        if (finished) {
            return false;
        }
        if (isEmpty()) {
            finished = true; // exhausted: no solution
            return false;
        }
        int[] cur = dequeue();
        int[] fin = maze.getFinish();
        if (cur[0] == fin[0] && cur[1] == fin[1]) {
            markSolution(cur);
            solved = true;
            finished = true;
            return false;
        }
        for (int[] d : DIRS) {
            int nx = cur[0] + d[0];
            int ny = cur[1] + d[1];
            // bounds check before touching the arrays
            if (!maze.inBounds(nx, ny) || seen[ny][nx] || maze.getCell(nx, ny) == StoreMaze.WALL) {
                continue;
            }
            seen[ny][nx] = true;
            parent[ny][nx] = cur[1] * maze.getWidth() + cur[0];
            if (maze.getCell(nx, ny) == StoreMaze.PATH) {
                maze.setCell(nx, ny, StoreMaze.VISITED);
            }
            enqueue(new int[] {nx, ny});
        }
        return true;
    }

    /** Walks parents back from the finish, marking the path and counting steps. */
    private void markSolution(int[] end) {
        int w = maze.getWidth();
        int idx = end[1] * w + end[0];
        int steps = 0;
        while (parent[idx / w][idx % w] != -1) {
            idx = parent[idx / w][idx % w];
            steps++;
            int x = idx % w;
            int y = idx / w;
            int c = maze.getCell(x, y);
            if (c == StoreMaze.VISITED || c == StoreMaze.PATH) {
                maze.setCell(x, y, StoreMaze.SOLUTION);
            }
        }
        moveCount = steps;
    }

    /** Runs the whole search and records how long it took. */
    public void solve() {
        long begin = System.nanoTime();
        while (move()) {
            // keep stepping
        }
        elapsedTime = System.nanoTime() - begin;
    }

    /** Number of steps in the shortest path (0 if unsolved). */
    public int getMoveCount() { return moveCount; }

    // ----------------------------------------------------------
    /**
     * no javadoc because they speak for themselves
     */
    public boolean solved() { return solved; }

    /** milliseconds. */
    public double getElapsedTime() { return elapsedTime / 1_000_000.0; }

    public boolean isFinished() { return finished; }

    public void enqueue(int[] coord) { queue.enqueue(coord); }

    public int[] dequeue() { return queue.dequeue(); }

    public int[] peek() { return queue.peek(); }

    public boolean isEmpty() { return queue.isEmpty(); }
}
