package mazeWave;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Model: builds a maze inside a StoreMaze using randomized depth-first search.
 * Rooms live on odd coordinates; walls between them are knocked down.
 * Uses an explicit stack so large mazes can't overflow the call stack.
 */
public class CreateMaze {
    private final StoreMaze maze;
    private final Random random;

    public CreateMaze(StoreMaze maze) {
        this(maze, new Random());
    }

    /** Seeded constructor, handy for repeatable tests. */
    public CreateMaze(StoreMaze maze, Random random) {
        this.maze = maze;
        this.random = random;
    }

    /** Carves a maze. Requires width and height of at least 3. */
    public void generate() {
        int w = maze.getWidth();
        int h = maze.getHeight();
        if (w < 3 || h < 3) {
            throw new IllegalArgumentException("Maze must be at least 3x3 to generate");
        }

        // Reset to all walls
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                maze.setCell(x, y, StoreMaze.WALL);
            }
        }

        int[][] dirs = {{0, -2}, {2, 0}, {0, 2}, {-2, 0}};
        Deque<int[]> stack = new ArrayDeque<>();
        maze.setCell(1, 1, StoreMaze.PATH);
        stack.push(new int[] {1, 1});

        while (!stack.isEmpty()) {
            int[] cur = stack.peek();
            List<int[]> options = new ArrayList<>();
            for (int[] d : dirs) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];
                // stay inside the outer wall border
                if (nx >= 1 && nx <= w - 2 && ny >= 1 && ny <= h - 2
                        && maze.getCell(nx, ny) == StoreMaze.WALL) {
                    options.add(d);
                }
            }
            if (options.isEmpty()) {
                stack.pop(); // dead end, backtrack
            } else {
                Collections.shuffle(options, random);
                int[] d = options.get(0);
                // knock down the wall between, then carve the new room
                maze.setCell(cur[0] + d[0] / 2, cur[1] + d[1] / 2, StoreMaze.PATH);
                maze.setCell(cur[0] + d[0], cur[1] + d[1], StoreMaze.PATH);
                stack.push(new int[] {cur[0] + d[0], cur[1] + d[1]});
            }
        }

        // start top left, finish bottom left
        int fx = (w - 2) % 2 == 1 ? w - 2 : w - 3;
        int fy = (h - 2) % 2 == 1 ? h - 2 : h - 3;
        maze.setStart(1, 1);
        maze.setFinish(fx, fy);
    }
}
