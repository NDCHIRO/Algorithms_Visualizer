package PathFindingVisualizer;
import java.awt.Point;
import java.util.*;

public class MazeSolver {

    private static List<Point> path = new ArrayList<>();  // Stores the full path explored
    private static List<Point> solutionPath = new ArrayList<>();  // Stores the final solution path
    private static Set<Point> visited = new HashSet<>();  // Tracks visited nodes
    private static Stack<Point> stack = new Stack<>();  // Stack for DFS
    private static Queue<Point> queue = new LinkedList<>();  // Queue for BFS

    // Initialize the DFS and start solving
    public static void initializeDFS(int[][] maze, Point start) {
        path.clear();
        solutionPath.clear();
        visited.clear();
        stack.clear();
        stack.push(start);  // Start DFS from the start point
    }

    // Initialize BFS for step-by-step solving
    public static void initializeBFS(int[][] maze, Point start) {
        path.clear();
        solutionPath.clear();
        visited.clear();
        queue.clear();
        queue.add(start);  // Start BFS from the start point
        visited.add(start);  // Mark start as visited
    }

    // Step-by-step DFS with backtracking visualization
    public static boolean stepDFS(int[][] maze, Point end) {
        if (stack.isEmpty()) {
            return false;  // DFS is complete
        }

        Point current = stack.peek();
        path.add(current);  // Add the current point to the visualization path

        if (current.equals(end)) {
            solutionPath.addAll(path);  // Add the entire path as the solution if end is reached
            return false;  // Maze is solved
        }

        visited.add(current);
        List<Point> neighbors = getNeighbors(maze, current);

        boolean moved = false;
        for (Point neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                stack.push(neighbor);
                moved = true;
                break;  // Move to the first unvisited neighbor
            }
        }

        if (!moved) {
            stack.pop();  // Backtrack if no unvisited neighbors
        }

        return true;  // Return true to indicate DFS is still in progress
    }

    // Step-by-step BFS with visualization
    public static boolean stepBFS(int[][] maze, Point end) {
        if (queue.isEmpty()) {
            return false;  // BFS is complete
        }

        Point current = queue.poll();  // Dequeue the current point
        path.add(current);  // Add current point to the exploration path

        if (current.equals(end)) {
            solutionPath.addAll(path);  // Add the entire path as the solution if end is reached
            return false;  // Maze is solved
        }

        List<Point> neighbors = getNeighbors(maze, current);
        for (Point neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                queue.add(neighbor);
                visited.add(neighbor);  // Mark neighbor as visited
            }
        }

        return true;  // Return true to indicate BFS is still in progress
    }

    // Utility function to get valid neighbors
    private static List<Point> getNeighbors(int[][] maze, Point p) {
        List<Point> neighbors = new ArrayList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};  // Down, Up, Right, Left

        for (int[] dir : directions) {
            int newX = p.x + dir[0];
            int newY = p.y + dir[1];

            if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length && maze[newX][newY] == 1) {
                neighbors.add(new Point(newX, newY));
            }
        }

        return neighbors;
    }

    public static List<Point> getPath() {
        return path;
    }

    public static List<Point> getSolutionPath() {
        return solutionPath;
    }
}
