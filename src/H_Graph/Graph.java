package H_Graph;
import java.util.*;

public class Graph {

    /////////////////////// LEARNING ////////////////////////////

    // BREADTH FIRST SEARCH (BFS)

    /*
   Traversal Type: Level-wise

   Starting Node = 1

   Graph  Structure:

                           1 (Level 0)
                         /             \
                        /               \
                       /                 \
              2 (Level 1)           6 (Level 1)
                /      \             /       \
               /        \           /         \
              /          \         /           \
     3 (Level 2)   4 (Level 2)  7 (Level 2)  8 (Level 2)
                          \       /
                           \     /
                            \   /
                          5 (Level 3)


   Level-wise Nodes:

   Level 0 -> {1}
   Level 1 -> {2, 6}
   Level 2 -> {3, 4, 7, 8}
   Level 3 -> {5}

   BFS Traversal Order:

   Level 0 : 1
   Level 1 : 2, 6
   Level 2 : 3, 4, 7, 8
   Level 3 : 5

   Complete BFS:
   1 -> 2 -> 6 -> 3 -> 4 -> 7 -> 8 -> 5
*/ // Explanation

    /*
   Queue Operations:

   Each vertex is enqueued exactly once.
   Enqueue Cost = V

   Each vertex is dequeued exactly once.
   Dequeue Cost = V

   Total Queue Cost = 2V

   Adjacency List Traversal:

   In an undirected graph, each edge appears
   in the adjacency list of both endpoints.

   The inner for loop will run for all degrees i.e. ∑ degree(v) = 2E (for undirected graph)

   Therefore, total edge traversals = 2E

   Edge Traversal Cost = 2E

   Total Work:

   = 2V + 2E

   Time Complexity:

   O(2V + 2E)

   Ignoring constants:

   O(V + E)
*/ // Time Complexity

    /*

    ArrayList<Integer> bfsOfGraph(int startNode, ArrayList<ArrayList<Integer>> adjacencyList) {

        // Assuming the graph uses 1-based indexing
        // (i.e., valid nodes are 1, 2, 3, ...)

        // Tracks whether a node has already been visited
        boolean[] visited = new boolean[adjacencyList.size()];

        // Queue used to process nodes in FIFO order
        Queue<Integer> queue = new ArrayDeque<>();

        // Stores the final BFS traversal order
        ArrayList<Integer> bfsTraversal = new ArrayList<>();


        // Mark the starting node as visited
        // and add it to the queue
        visited[startNode] = true;
        queue.add(startNode);


        // Continue until there are no more nodes to process
        while (!queue.isEmpty()) {

            // Remove the front node from the queue
            int currentNode = queue.remove();

            // Add the current node to the BFS traversal
            bfsTraversal.add(currentNode);


            // Visit all adjacent (neighboring) nodes
            for (int neighbourNode : adjacencyList.get(currentNode)) {

                // Process only unvisited neighbors
                if (!visited[neighbourNode]) {

                    // Mark as visited before enqueuing
                    // to prevent duplicate insertions
                    visited[neighbourNode] = true;

                    // Add neighbor to the queue
                    queue.add(neighbourNode);
                }
            }
        }

        // Return the BFS traversal order
        return bfsTraversal;
    }

    */

    // DEPTH FIRST SEARCH (DFS)

    /*
   Traversal Type: Depth-wise (DFS - Preorder)

   Starting Node = 1

   Graph Structure (Assumed Traversal Order):

                           1
                         /   \
                        /     \
                       2       6
                      / \     / \
                     3   4   7   8
                          \
                           5
                            \
                             7   (cross connection as in graph)

   Adjacency (for DFS consistency):

   1 -> {2, 6}
   2 -> {3, 4}
   3 -> {}
   4 -> {5}
   5 -> {7}
   6 -> {7, 8}
   7 -> {}
   8 -> {}

   DFS Traversal (Preorder, Visited Marked):

   Start at 1
   Go deep before visiting siblings

   Traversal Steps:

   1 -> 2 -> 3 (backtrack)
   2 -> 4 -> 5 -> 7 (backtrack to 1)
   1 -> 6 -> 8

   Complete DFS Order:
   1 -> 2 -> 3 -> 4 -> 5 -> 7 -> 6 -> 8
*/ // Explanation

    /*
   DFS (Recursive Approach) - Time & Space Complexity

   Each vertex is visited exactly once
   → Visited array ensures no repeated processing

   Each edge is explored at most twice (undirected graph)
   → Once from each endpoint
   → Total edge checks = 2E

   ---------------------------------------
   TIME COMPLEXITY

   Work done:
   - Visiting all vertices = V
   - Traversing adjacency lists = 2E ( The inner for loop will run for all degrees i.e. ∑ degree(v) = 2E (for undirected graph) )

   Total work = V + 2E

   Ignoring constants:

   Time Complexity = O(V + E)


   ---------------------------------------
   SPACE COMPLEXITY

   1. Visited Array:
      O(V)

   2. Recursion Stack (DFS call stack):
      - Worst case: all nodes in a single chain
      - Maximum depth = V

      Stack Space = O(V)

   3. Adjacency List (given input graph):
      O(V + E)

   ---------------------------------------
   TOTAL SPACE COMPLEXITY

   Auxiliary Space (excluding graph storage):
      = O(V) (visited + recursion stack)

   Including graph representation:
      = O(V + E)


   ---------------------------------------
   FINAL:

   Time Complexity  = O(V + E)
   Space Complexity = O(V) auxiliary
                     = O(V + E) including graph
*/ // Time and Space Complexity

    /*

        void dfsOfGraph(int startNode, boolean visited[], List<List<Integer>> adjacencyList, List<Integer> dfs){

            visited[startNode]=true;
            dfs.add(startNode);

            for(int neighbour:adjacencyList.get(startNode)){
                if(!visited[neighbour]){
                    dfsOfGraph(neighbour, visited, adjacencyList, dfs);
                }
            }
        }

     */

    // DETECT CYCLE IN UNDIRECTED GRAPH (USING BFS)

    /*

    static class Pair {
        int childNode;
        int parentNode;

        Pair(int childNode, int parentNode) {
            this.childNode = childNode;
            this.parentNode = parentNode;
        }
    }

    boolean detectCycle(ArrayList<ArrayList<Integer>> adjacencyList) {

        boolean[] visited = new boolean[adjacencyList.size()];

        // We iterate through all nodes to handle disconnected graphs.
        // If the graph is guaranteed to be connected, a single call like
        // isCyclic(0, visited, adjacencyList) would be sufficient.

        for (int node = 0; node < adjacencyList.size(); node++) {
            if (!visited[node]) {
                if (isCyclic(node, visited, adjacencyList)) {
                    return true;
                }
            }
        }

        return false;
    }

    boolean isCyclic(int startNode, boolean[] visited, ArrayList<ArrayList<Integer>> adjacencyList) {

        visited[startNode] = true;

        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(startNode, -1));

        while (!queue.isEmpty()) {

            Pair currentPair = queue.remove();

            int currentNode = currentPair.childNode;
            int parentNode = currentPair.parentNode;

            for (int neighbourNode : adjacencyList.get(currentNode)) {

                if (!visited[neighbourNode]) {
                    visited[neighbourNode] = true;
                    queue.add(new Pair(neighbourNode, currentNode));
                } else if (neighbourNode != parentNode) {
                    // Neighbour is already visited and is not the parent,
                    // which means we found a cycle in an undirected graph.
                    return true;
                }
            }
        }

        return false;
    }


     */

    // DETECT CYCLE IN UNDIRECTED GRAPH (USING DFS)

    /*

        boolean detectCycle(ArrayList<ArrayList<Integer>> adjacencyList) {

            boolean[] visited = new boolean[adjacencyList.size()];

            // We iterate through all nodes to handle disconnected graphs.
            // If the graph is guaranteed to be connected, a single call like
            // isCyclic(0, -1, visited, adjacencyList) would be sufficient.
            for (int node = 0; node < adjacencyList.size(); node++) {
                if (!visited[node]) {
                    if (isCyclic(node, -1, visited, adjacencyList)) {
                        return true;
                    }
                }
            }

            return false;
        }

        boolean isCyclic(int currentNode, int parentNode,
                         boolean[] visited,
                         ArrayList<ArrayList<Integer>> adjacencyList) {

            visited[currentNode] = true;

            for (int neighbourNode : adjacencyList.get(currentNode)) {

                if (!visited[neighbourNode]) {

                    if (isCyclic(neighbourNode, currentNode,
                            visited, adjacencyList)) {
                        return true;
                    }

                } else if (neighbourNode != parentNode) {

                    // Neighbour is already visited and is not the parent,
                    // which means we found a cycle in an undirected graph.
                    return true;
                }
            }

            return false;
        }


     */


    /////////////////////// PROBLEMS ON BFS/DFS ////////////////////////////

    // Number of Provinces

    /* Canbe solved with both bfs and dfs

            class Solution {
            public int findCircleNum(int[][] isConnected) {

                int n = isConnected.length;

                // convert matrix to adjacency list

                List<List<Integer>> graph = new ArrayList<>();

                for (int city = 0; city < n; city++) {
                    graph.add(new ArrayList<>());
                }

                for (int city = 0; city < n; city++) {
                    for (int neighborCity = 0; neighborCity < n; neighborCity++) {
                        if (isConnected[city][neighborCity] == 1) {
                            graph.get(city).add(neighborCity);
                        }
                    }
                }

                boolean[] visited = new boolean[n];
                int provinceCount = 0;

                for (int city = 0; city < n; city++) {
                    if (!visited[city]) {
                        provinceCount++;
                        bfs(city, visited, graph);
                    }
                }

                return provinceCount;
            }

            private void bfs(int startCity, boolean[] visited, List<List<Integer>> graph) {
                Queue<Integer> queue = new ArrayDeque<>();

                visited[startCity] = true;
                queue.add(startCity);

                while (!queue.isEmpty()) {
                    int currentCity = queue.remove();

                    for (int neighbor : graph.get(currentCity)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.add(neighbor);
                        }
                    }
                }
            }
}

     */

    /* Solution without using adjacency list

            class Solution {
            public int findCircleNum(int[][] isConnected) {
                int n = isConnected.length;
                boolean[] visited = new boolean[n];

                int provinceCount = 0;

                for (int city = 0; city < n; city++) {
                    if (!visited[city]) {
                        provinceCount++;
                        bfs(city, isConnected, visited);
                    }
                }

                return provinceCount;
            }

            private void bfs(int startCity, int[][] isConnected, boolean[] visited) {
                Queue<Integer> queue = new ArrayDeque<>();

                visited[startCity] = true;
                queue.add(startCity);

                while (!queue.isEmpty()) {
                    int currentCity = queue.remove();

                    for (int nextCity = 0; nextCity < isConnected.length; nextCity++) {
                        if (isConnected[currentCity][nextCity] == 1 && !visited[nextCity]) {
                            visited[nextCity] = true;
                            queue.add(nextCity);
                        }
                    }
                }
            }
        }

     */

    // Rotting Oranges

    /* Recall why dfs will give a wrong ans if used here

            import java.util.*;

        class Solution {

            static class OrangeState {
                int row;
                int col;
                int time;

                OrangeState(int row, int col, int time) {
                    this.row = row;
                    this.col = col;
                    this.time = time;
                }
            }

            public int orangesRotting(int[][] grid) {
                return bfs(grid);
            }

            private int bfs(int[][] grid) {

                int rowCount = grid.length;
                int colCount = grid[0].length;

                boolean[][] visited = new boolean[rowCount][colCount];

                Queue<OrangeState> bfsQueue = new ArrayDeque<>();

                int freshOrangeCount = 0;

                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < colCount; col++) {

                        if (grid[row][col] == 2) {
                            visited[row][col] = true;
                            bfsQueue.add(new OrangeState(row, col, 0));
                        } else if (grid[row][col] == 1) {
                            freshOrangeCount++;
                        }
                    }
                }

                int maxTime = 0;
                int rottedFreshOrangeCount = 0;

                int[] rowDirections = {-1, 0, 0, 1};
                int[] colDirections = {0, -1, 1, 0};

                while (!bfsQueue.isEmpty()) {

                    OrangeState currentOrange = bfsQueue.remove();

                    maxTime = Math.max(maxTime, currentOrange.time);

                    for (int direction = 0; direction < 4; direction++) {

                        int nextRow = currentOrange.row + rowDirections[direction];
                        int nextCol = currentOrange.col + colDirections[direction];

                        if (nextRow >= 0 && nextRow < rowCount &&
                            nextCol >= 0 && nextCol < colCount &&
                            !visited[nextRow][nextCol] &&
                            grid[nextRow][nextCol] == 1) {

                            visited[nextRow][nextCol] = true;

                            bfsQueue.add(
                                new OrangeState(
                                    nextRow,
                                    nextCol,
                                    currentOrange.time + 1
                                )
                            );

                            rottedFreshOrangeCount++;
                        }
                    }
                }

                return rottedFreshOrangeCount == freshOrangeCount? maxTime : -1;
            }
        }

     */

    // Flood Fill

    /*

          class Solution {

            public int[][] floodFill(int[][] image, int sr, int sc, int color) {

                int initialColor = image[sr][sc];

                // No work needed if the pixel already has the target color.
                if (initialColor == color) {
                    return image;
                }

                dfs(sr, sc, image, initialColor, color);

                return image;
            }

            private void dfs(int currentRow, int currentCol, int[][] image, int initialColor,
                int targetColor) {

                image[currentRow][currentCol] = targetColor;

                int[] rowDirections = {-1, 0, 0, 1};
                int[] colDirections = {0, -1, 1, 0};

                for (int direction = 0; direction < 4; direction++) {

                    int nextRow = currentRow + rowDirections[direction];
                    int nextCol = currentCol + colDirections[direction];

                    if (nextRow >= 0 && nextRow < image.length &&
                        nextCol >= 0 && nextCol < image[0].length &&
                        image[nextRow][nextCol] == initialColor) {
                        dfs(nextRow, nextCol, image, initialColor, targetColor);
                    }
                }
            }
        }

     */

    // Detect Cycle in an Undirected Graph (using BFS / DFS) ==> Solved in learning section

    // Distance of nearest cell having zero ( LC 542 )

    /*

    If you start BFS from every cell that contains `1`,
    then for each such cell you may end up exploring a large portion of the matrix until you find a `0`.
    In the worst case, each BFS can take **O(m · n)** time, and since there can be up to **m · n** ones,
    the total time complexity becomes **O((m · n)²)**. This quadratic behavior is too slow for typical constraints in grid problems,
    especially when m and n can be up to a few hundred. That’s why this approach is inefficient compared to multi-source BFS starting from 0 instead of 1,
    which computes all distances in a single traversal.


        class Solution {


                static class CellState {
                int row;
                int col;
                int distanceFromZero;

                CellState(int row, int col, int distanceFromZero) {
                    this.row = row;
                    this.col = col;
                    this.distanceFromZero = distanceFromZero;
                }
            }


            public int[][] updateMatrix(int[][] mat) {

                int rowCount = mat.length;
                int colCount = mat[0].length;

                int[][] distanceMatrix = new int[rowCount][colCount];

                bfs(distanceMatrix, mat);

                return distanceMatrix;
            }

            private void bfs(int[][] distanceMatrix, int[][] matrix) {

                Queue<CellState> queue = new ArrayDeque<>();

                int rowCount = matrix.length;
                int colCount = matrix[0].length;

                boolean[][] visited = new boolean[rowCount][colCount];

                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < colCount; col++) {

                        if (matrix[row][col] == 0) {
                            visited[row][col] = true;
                            queue.add(new CellState(row, col, 0));
                        }
                    }
                }

                int[] rowOffsets = {0, -1, 1, 0};
                int[] colOffsets = {-1, 0, 0, 1};

                while (!queue.isEmpty()) {

                    CellState currentCell = queue.remove();

                    int currentRow = currentCell.row;
                    int currentCol = currentCell.col;
                    int distanceFromZero = currentCell.distanceFromZero;

                    distanceMatrix[currentRow][currentCol] = distanceFromZero;

                    for (int direction = 0; direction < 4; direction++) {

                        int nextRow = currentRow + rowOffsets[direction];
                        int nextCol = currentCol + colOffsets[direction];

                        if (
                            nextRow >= 0 && nextRow < rowCount &&
                            nextCol >= 0 && nextCol < colCount &&
                            !visited[nextRow][nextCol] &&
                            matrix[nextRow][nextCol] == 1
                        ) {
                            visited[nextRow][nextCol] = true;

                            queue.add(new CellState(nextRow, nextCol, distanceFromZero + 1));
                        }
                    }
                }
            }
        }

     */

    /* Without using visited array (more optimal in terms of space)

    If you start BFS from every cell that contains `1`,
    then for each such cell you may end up exploring a large portion of the matrix until you find a `0`.
    In the worst case, each BFS can take **O(m · n)** time, and since there can be up to **m · n** ones,
    the total time complexity becomes **O((m · n)²)**. This quadratic behavior is too slow for typical constraints in grid problems,
    especially when m and n can be up to a few hundred. That’s why this approach is inefficient compared to multi-source BFS starting from 0 instead of 1,
    which computes all distances in a single traversal.

           class Solution {

            class CellState {
                int row;
                int col;
                int distanceFromZero;

                CellState(int row, int col, int distanceFromZero) {
                    this.row = row;
                    this.col = col;
                    this.distanceFromZero = distanceFromZero;
                }
            }

            public int[][] updateMatrix(int[][] mat) {

                int m = mat.length;
                int n = mat[0].length;

                int[][] dist = new int[m][n];

                bfs(mat, dist);

                return dist;
            }

            private void bfs(int[][] mat, int[][] dist) {

                int m = mat.length;
                int n = mat[0].length;

                Queue<CellState> queue = new ArrayDeque<>();

                // Initialize: push all 0s, mark 1s as unvisited (-1)
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {

                        if (mat[i][j] == 0) {
                            dist[i][j] = 0;
                            queue.add(new CellState(i, j, 0));
                        } else {
                            dist[i][j] = -1;
                        }
                    }
                }

                int[] dr = { 0, 0, -1, 1 };
                int[] dc = { -1, 1, 0, 0 };

                while (!queue.isEmpty()) {

                    CellState cur = queue.poll();

                    for (int k = 0; k < 4; k++) {

                        int nr = cur.row + dr[k];
                        int nc = cur.col + dc[k];

                        if (nr >= 0 && nr < m && nc >= 0 && nc < n && dist[nr][nc] == -1) {

                            dist[nr][nc] = cur.distanceFromZero + 1;
                            queue.add(new CellState(nr, nc, cur.distanceFromZero + 1));
                        }
                    }
                }
            }
        }

    */

    //

    /* Both dfs and bfs approach will work here

            class Solution {
            public void solve(char[][] board) {

                int rowCount = board.length;
                int colCount = board[0].length;

                boolean visited[][] = new boolean[rowCount][colCount];

                // traversing top and bottom rows
                for (int col = 0; col < colCount; col++) {

                    if (!visited[0][col] && board[0][col] == 'O') {
                        dfs(0, col, visited, board);
                    }

                    if (!visited[rowCount - 1][col] && board[rowCount - 1][col] == 'O') {
                        dfs(rowCount - 1, col, visited, board);
                    }
                }

                // traversing left and right cols
                for (int row = 0; row < rowCount; row++) {

                    if (!visited[row][0] && board[row][0] == 'O') {
                        dfs(row, 0, visited, board);
                    }

                    if (!visited[row][colCount - 1] && board[row][colCount - 1] == 'O') {
                        dfs(row, colCount - 1, visited, board);
                    }
                }

                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < colCount; col++) {
                        if (board[row][col] == 'O' && !visited[row][col]) {
                            board[row][col] = 'X';
                        }
                    }
                }
            }

            void dfs(int row, int col, boolean visited[][], char[][] board) {

                visited[row][col] = true;

                int rowOffset[] = { -1, 0, 0, 1 };
                int colOffset[] = { 0, 1, -1, 0 };

                for (int i = 0; i < 4; i++) {

                    int nextRow = row + rowOffset[i];
                    int nextCol = col + colOffset[i];

                    if (nextRow >= 0 && nextRow < board.length &&
                            nextCol >= 0 && nextCol < board[0].length &&
                            !visited[nextRow][nextCol] && board[nextRow][nextCol] == 'O') {
                        dfs(nextRow, nextCol, visited, board);
                    }
                }

            }
        }

     */

    // Number of Enclaves

    /*

            class Solution {
            public int numEnclaves(int[][] grid) {

                int rowCount = grid.length;
                int colCount = grid[0].length;

                boolean visited[][] = new boolean[rowCount][colCount];

                // traversing top and bottom rows
                for (int col = 0; col < colCount; col++) {
                    //top row
                    if (grid[0][col] == 1 && !visited[0][col]) {
                        dfs(0, col, visited, grid);
                    }

                    //bottom row
                    if (grid[rowCount - 1][col] == 1 && !visited[rowCount - 1][col]) {
                        dfs(rowCount - 1, col, visited, grid);
                    }
                }

                // traversing first and last columns
                for (int row = 0; row < rowCount; row++) {

                    // traversing first col
                    if (grid[row][0] == 1 && !visited[row][0]) {
                        dfs(row, 0, visited, grid);
                    }

                    // traversing last col
                    if (grid[row][colCount - 1] == 1 && !visited[row][colCount - 1]) {
                        dfs(row, colCount - 1, visited, grid);
                    }

                }

                int landCount = 0;

                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < colCount; col++) {
                        if (grid[row][col] == 1 && !visited[row][col]) {
                            landCount++;
                        }
                    }
                }

                return landCount;

            }

            void dfs(int row, int col, boolean visited[][], int grid[][]) {

                visited[row][col] = true;

                int rowOffset[] = { 1, 0, 0, -1 };
                int colOffset[] = { 0, 1, -1, 0 };

                for (int i = 0; i < 4; i++) {
                    int nextRow = row + rowOffset[i];
                    int nextCol = col + colOffset[i];

                    if (nextRow >= 0 && nextRow < grid.length &&
                            nextCol >= 0 && nextCol < grid[0].length &&
                            !visited[nextRow][nextCol] && grid[nextRow][nextCol] == 1) {
                        dfs(nextRow, nextCol, visited, grid);
                    }
                }
            }
        }

     */

    // Number of Distinct Islands ( LC 694 PAID )

    /* Question

    Given a boolean 2D matrix grid of size n * m.
    You have to find the number of distinct islands where a group of connected 1s (horizontally or vertically) forms an island.
    Two islands are considered to be distinct if and only if one island is equal to another (not rotated or reflected).

    Example 1:

    Input:
    grid[][] = {
        {1, 1, 0, 0, 0},
        {1, 1, 0, 0, 0},
        {0, 0, 0, 1, 1},
        {0, 0, 0, 1, 1}
    }

    Output:
    1

    Explanation:
    Island 1 at the top-left corner is the same as island 2 at the bottom-right corner.

     */

    /*


        class Solution {


            // DFS to traverse an island and store its shape  relative to the starting cell.

            private void dfs(int currentRow, int currentCol, int[][] visited, int[][] grid, ArrayList<String> islandShape,
                    int baseRow, int baseCol) {

                visited[currentRow][currentCol] = 1;

                // Store coordinates relative to the island's starting cell
                islandShape.add((currentRow - baseRow) + " " + (currentCol - baseCol));

                int[] rowDirection = {-1, 0, 1, 0};
                int[] colDirection = {0, -1, 0, 1};

                int totalRows = grid.length;
                int totalCols = grid[0].length;

                for (int direction = 0; direction < 4; direction++) {

                    int nextRow = currentRow + rowDirection[direction];
                    int nextCol = currentCol + colDirection[direction];

                    if (nextRow >= 0 && nextRow < totalRows &&
                            nextCol >= 0 && nextCol < totalCols &&
                            visited[nextRow][nextCol] == 0 &&
                            grid[nextRow][nextCol] == 1) {

                        dfs(nextRow, nextCol, visited, grid, islandShape, baseRow, baseCol);
                    }
                }
            }


             // Counts the number of distinct island shapes.

            int countDistinctIslands(int[][] grid) {

                int totalRows = grid.length;
                int totalCols = grid[0].length;

                int[][] visited = new int[totalRows][totalCols];

                // Stores unique island shapes
                HashSet<ArrayList<String>> uniqueIslands = new HashSet<>();

                for (int row = 0; row < totalRows; row++) {
                    for (int col = 0; col < totalCols; col++) {

                        // Start DFS from every unvisited land cell
                        if (grid[row][col] == 1 && visited[row][col] == 0) {

                            ArrayList<String> islandShape = new ArrayList<>();

                            dfs(row, col, visited, grid, islandShape,
                                    row,   // base row
                                    col    // base col
                            );

                            uniqueIslands.add(islandShape);
                        }
                    }
                }

                return uniqueIslands.size();
            }
        }

     */

    // Word Ladder I

    /* time complexity : no. of words in wordList * (length of word * 26)

            class Solution {

            static class WordState {
                String word;
                int transformationLength;

                WordState(String word, int transformationLength) {
                    this.word = word;
                    this.transformationLength = transformationLength;
                }
            }

            public int ladderLength(String beginWord, String endWord, List<String> wordList) {

                // Stores all unused dictionary words.
                // Also acts as a visited set by removing words once they are queued.
                Set<String> unvisitedWords = new HashSet<>(wordList);

                // BFS queue storing the current word and the length
                // of the transformation sequence up to that word.
                Queue<WordState> queue = new ArrayDeque<>();

                queue.add(new WordState(beginWord, 1));

                // beginWord does not need to be revisited.
                unvisitedWords.remove(beginWord);

                while (!queue.isEmpty()) {

                    WordState currentState = queue.remove();

                    StringBuilder currentWord = new StringBuilder(currentState.word);
                    int currentLength = currentState.transformationLength;

                    // Since BFS explores level by level,
                    // the first time we reach endWord is the shortest path.
                    if (currentWord.toString().equals(endWord)) {
                        return currentLength;
                    }

                    // Try changing each character position.
                    for (int index = 0; index < currentWord.length(); index++) {

                        char originalCharacter = currentWord.charAt(index);

                        // Replace the current character with every lowercase letter.
                        for (char replacement = 'a'; replacement <= 'z'; replacement++) {

                            currentWord.setCharAt(index, replacement);

                            String transformedWord = currentWord.toString();

                            // If the transformed word exists in the dictionary
                            // and has not been visited yet, enqueue it.
                            if (unvisitedWords.contains(transformedWord)) {

                                queue.add(new WordState(transformedWord,currentLength + 1));

                                // Mark as visited.
                                unvisitedWords.remove(transformedWord);
                            }
                        }

                        // Restore the original character before moving
                        // to the next position.
                        currentWord.setCharAt(index, originalCharacter);
                    }
                }

                // No valid transformation sequence exists.
                return 0;
            }
        }

     */

    // Word Ladder II

    // For both the below solution TLE on LC

    /*  Classical Solution

            class Solution {

            public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

                // Stores all dictionary words that have not yet been visited
                // in previous BFS levels.
                Set<String> unvisitedWords = new HashSet<>(wordList);

                // Each queue entry represents a complete transformation path
                // from beginWord to the current word.
                Queue<List<String>> pathQueue = new ArrayDeque<>();

                List<String> startingPath = new ArrayList<>();
                startingPath.add(beginWord);

                pathQueue.add(startingPath);

                // Stores all words discovered in the current BFS level.
                // These words will be removed from the dictionary only when
                // we move to the next level, allowing multiple shortest paths
                // in the same level to reach the same word.
                List<String> wordsDiscoveredInLevel = new ArrayList<>();
                wordsDiscoveredInLevel.add(beginWord);

                // Tracks the current BFS depth (path length).
                int currentLevel = 0;

                // Stores all shortest transformation sequences.
                List<List<String>> shortestPaths = new ArrayList<>();

                while (!pathQueue.isEmpty()) {

                    List<String> currentPath = pathQueue.remove();

                    // If we have moved to a deeper BFS level,
                    // mark all words discovered in the previous level as visited.
                    if (currentPath.size() > currentLevel) {

                        currentLevel++;

                        for (String discoveredWord : wordsDiscoveredInLevel) {
                            unvisitedWords.remove(discoveredWord);
                        }

                        // Start tracking words for the new level.
                        wordsDiscoveredInLevel.clear();
                    }

                    // Once a shortest path has been found, BFS guarantees that any
                    // longer path cannot be part of the answer.
                    if (!shortestPaths.isEmpty()
                            && currentPath.size() > shortestPaths.get(0).size()) {
                        break;
                    }

                    // The last word in the path is the current word being expanded.
                    StringBuilder currentWord = new StringBuilder(currentPath.get(currentPath.size() - 1));

                    // If the destination word is reached, store the path
                    // and do not expand it further.
                    if (currentWord.toString().equals(endWord)) {
                        shortestPaths.add(new ArrayList<>(currentPath));
                        continue;
                    }

                    // Try changing each character position of the current word.
                    for (int index = 0; index < currentWord.length(); index++) {

                        char originalCharacter = currentWord.charAt(index);

                        // Replace the current character with every lowercase letter.
                        for (char replacement = 'a'; replacement <= 'z'; replacement++) {

                            currentWord.setCharAt(index, replacement);

                            String transformedWord = currentWord.toString();

                            // If the transformed word exists in the dictionary
                            // and has not been visited in a previous level,
                            // extend the current path and enqueue it.
                            if (unvisitedWords.contains(transformedWord)) {

                                currentPath.add(transformedWord);

                                pathQueue.add(new ArrayList<>(currentPath));

                                wordsDiscoveredInLevel.add(transformedWord);

                                // Backtrack so the current path can be reused.
                                currentPath.remove(currentPath.size() - 1);
                            }
                        }

                        // Restore the original character before moving
                        // to the next position.
                        currentWord.setCharAt(index, originalCharacter);
                    }
                }

                return shortestPaths;
            }
        }

     */

    /* My solution, optimizing for space by removing usedOnLvl arraylist ( correct solution )

            Time Complexity:  O(P × (26L + mK)) (if m correct words are generated)
            Space Complexity: O(P × K)
            P = total number of paths generated
            K = average path length

            class Solution {

            public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

                // Stores all dictionary words that have not yet been marked as visited.
                // Words will be removed level by level to ensure that all shortest
                // transformation paths are preserved.
                Set<String> unvisitedWords = new HashSet<>(wordList);

                // Each queue entry represents a complete transformation path
                // from beginWord to the current word.
                Queue<ArrayList<String>> queue = new ArrayDeque<>();

                List<List<String>> result = new ArrayList<>();

                ArrayList<String> initialPath = new ArrayList<>();
                initialPath.add(beginWord);

                queue.add(initialPath);

                while (!queue.isEmpty()) {

                    ArrayList<String> currentPath = queue.remove();

                    // The last word in the path is the current word being expanded.
                    StringBuilder currentWord = new StringBuilder(currentPath.get(currentPath.size() - 1));

                    // This currentWord will be added to arrayList on the previous level,
                    // Mark the current word as visited.
                    // This removal is intended to happen after the previous BFS
                    // level has been fully processed so that multiple shortest
                    // paths can still reach the same word.
                    unvisitedWords.remove(currentWord.toString());

                    if (!result.isEmpty() && currentPath.size() > result.get(0).size()) {
                        break;
                    }

                    // If the destination word is reached, store the path if it
                    // belongs to the shortest transformation length found so far.
                    if (currentWord.toString().equals(endWord)) {
                        result.add(currentPath);
                        continue;
                    }

                    // Generate all possible one-character transformations
                    // of the current word.
                    for (int index = 0; index < currentWord.length(); index++) {

                        char originalCharacter = currentWord.charAt(index);

                        // Replace the current character with every lowercase letter.
                        for (char replacement = 'a'; replacement <= 'z'; replacement++) {

                            currentWord.setCharAt(index, replacement);

                            String transformedWord = currentWord.toString();

                            // If the transformed word exists in the dictionary
                            // and has not been visited yet, extend the current path
                            // and enqueue the new path.
                            if (unvisitedWords.contains(transformedWord)) {

                                currentPath.add(transformedWord);

                                queue.add(new ArrayList<>(currentPath));

                                // Backtrack so that the current path can be reused
                                // for the next transformation.
                                currentPath.remove(currentPath.size() - 1);
                            }
                        }

                        // Restore the original character before moving
                        // to the next position.
                        currentWord.setCharAt(index, originalCharacter);
                    }
                }

                return result;
            }
        }

     */

    // Number of Islands

    /*

            class Solution {
            public int numIslands(char[][] grid) {

                int rowCount = grid.length;
                int colCount = grid[0].length;

                boolean visited[][] = new boolean[rowCount][colCount];

                int islandCount = 0;

                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < colCount; col++) {
                        if (grid[row][col] == '1' && !visited[row][col]) {
                            islandCount++;
                            dfs(row, col, visited, grid);
                        }
                    }
                }

                return islandCount;

            }

            void dfs(int row, int col, boolean visited[][], char[][] grid) {

                visited[row][col] = true;

                int rowOffset[] = { -1, 0, 0, 1 };
                int colOffset[] = { 0, 1, -1, 0 };

                for (int i = 0; i < 4; i++) {

                    int nextRow = row + rowOffset[i];
                    int nextCol = col + colOffset[i];

                    if (nextRow >= 0 && nextRow < grid.length &&
                            nextCol >= 0 && nextCol < grid[0].length &&
                            !visited[nextRow][nextCol] && grid[nextRow][nextCol] == '1') {
                        dfs(nextRow, nextCol, visited, grid);
                    }
                }

            }
        }

     */

    // Bipartite Graph

    // using BFS

    /*

                class Solution {

                public boolean isBipartite(int[][] graph) {

                    int totalNodes = graph.length;

                    // -1 = unvisited
                    //  0 = belongs to partition A
                    //  1 = belongs to partition B
                    int[] color = new int[totalNodes];

                    Arrays.fill(color, -1);

                    // Check every connected component
                    for (int node = 0; node < totalNodes; node++) {
                        if (color[node] == -1) {
                            if (!isComponentBipartite(node, color, graph)) {
                                return false;
                            }
                        }
                    }

                    return true;
                }

                private boolean isComponentBipartite(int startNode, int[] color, int[][] graph) {

                    Queue<Integer> queue = new ArrayDeque<>();

                    color[startNode] = 1;
                    queue.add(startNode);

                    while (!queue.isEmpty()) {

                        int currentNode = queue.remove();

                        for (int neighbor : graph[currentNode]) {

                            // Assign the opposite color to an unvisited neighbor
                            if (color[neighbor] == -1) {
                                color[neighbor] = 1 - color[currentNode];
                                queue.add(neighbor);
                            }
                            // Adjacent nodes cannot belong to the same partition
                            else if (color[neighbor] == color[currentNode]) {
                                return false;
                            }
                        }
                    }

                    return true;
                }
            }

     */

    // using DFS

    /*

            class Solution {

            public boolean isBipartite(int[][] graph) {

                int totalNodes = graph.length;

                // -1 = unvisited
                //  0 = belongs to partition A
                //  1 = belongs to partition B
                int[] color = new int[totalNodes];

                Arrays.fill(color, -1);

                // Check every connected component
                for (int node = 0; node < totalNodes; node++) {
                    if (color[node] == -1) {

                        if (!isComponentBipartite(node, 0, color, graph)) {
                            return false;
                        }
                    }
                }

                return true;
            }

            private boolean isComponentBipartite(int currentNode, int currColor, int[] color,
                    int[][] graph) {

                color[currentNode] = currColor;

                for (int neighbor : graph[currentNode]) {

                    // Assign the opposite color to an unvisited neighbor
                    if (color[neighbor] == -1) {

                        if (!isComponentBipartite(neighbor, 1 - currColor, color, graph)) {
                            return false;
                        }
                    }
                    // Adjacent nodes cannot belong to the same partition
                    else if (color[neighbor] == currColor) {
                        return false;
                    }
                }

                return true;
            }
        }

     */

    // Cycle Detection in Directed Graph ( DFS )

    /*

            class Solution {

            public boolean isCyclic(int vertices, List<List<Integer>> adj) {

                boolean[] visited = new boolean[vertices];

                // Tracks nodes that are part of the current DFS path
                boolean[] onPath = new boolean[vertices];

                // The graph may be disconnected, so start DFS from every unvisited node
                for (int node = 0; node < vertices; node++) {
                    if (!visited[node]) {
                        if (hasCycle(node, visited, onPath, adj)) {
                            return true;
                        }
                    }
                }

                return false;
            }

            private boolean hasCycle(int currentNode,
                                     boolean[] visited,
                                     boolean[] onPath,
                                     List<List<Integer>> adj) {

                visited[currentNode] = true;
                onPath[currentNode] = true;

                for (int neighbor : adj.get(currentNode)) {

                    // Explore unvisited neighbors
                    if (!visited[neighbor]) {
                        if (hasCycle(neighbor, visited, onPath, adj)) {
                            return true;
                        }
                    }

                    // Reaching a node already on the current DFS path
                    // means we found a back edge, which forms a cycle.
                    else if (onPath[neighbor]) {
                        return true;
                    }
                }

                // Backtrack: remove the current node from the DFS path
                onPath[currentNode] = false;

                return false;
            }
        }

        We can avoid using a separate `onPath` array by storing three states for each node in a single integer array.
        Let `state[node] = -1` represent an unvisited node, `state[node] = 1` represent a node that is currently on the DFS path (i.e., in the recursion stack),
        and `state[node] = 0` represent a node that has been fully processed and is no longer on the current path.
        During DFS, mark a node as `1` when entering it. If we encounter a neighbor whose state is already `1`,
        we have found a back edge and therefore a cycle. After exploring all neighbors, mark the node as `0` before returning.
        This approach combines the functionality of both `visited` and `onPath` into a single array, reducing space usage and simplifying state management.


     */


    /////////////////////// TOPO SORT AND PROBLEMS ////////////////////////////

    /*

        ## Topological Sorting: What is topological sort?

            A **Topological Sort** of a directed graph is a linear ordering of its vertices such that for every directed edge u --> v,
            vertex u comes before vertex v in the ordering.

            > Crucial Condition: Topological sorting is **only valid for Directed Acyclic Graphs (DAGs)**.
            If the graph contains any cycles, a topological sort is mathematically impossible.

            ---

            ### Example 1: A Valid Directed Acyclic Graph (DAG)

            Consider the following graph structure represented in your notes:

            **Vertices:** `0, 1, 2, 3, 4, 5`

            **Directed Edges:**

            * `5 -> 0`
            * `4 -> 0`
            * `5 -> 2`
            * `2 -> 3`
            * `3 -> 1`
            * `4 -> 1`

            Because there are no cycles in this graph, we can find valid linear orderings where every dependency is satisfied.

            #### Valid Topological Orderings:

            * `5  4  2  3  1  0`
            * `4  5  2  3  1  0`

            *(Notice how in both orderings, `5` and `4` always appear before `0` and `1`, and `2` always appears before `3`.)*

            ---

            ### Why Must the Graph Be Acyclic? (The 1-2-3 Example)

            To see why a topological sort fails if a graph has a cycle, let's look at the second example:

            **Edges in the Cycle:**

            * `1 -> 2` (Meaning `1` must appear before `2`)
            * `2 -> 3` (Meaning `2` must appear before `3`)
            * `3 -> 1` (Meaning `3` must appear before `1`)

            #### The Contradiction:

            If we try to arrange these in a straight line:

            1. To satisfy `1 -> 2`, we write: `1, 2`
            2. To satisfy `2 -> 3`, we write: `1, 2, 3`
            3. Now we must satisfy `3 -> 1`. This requires `3` to appear *before* `1`.

            This creates a ✨✨✨ cyclic/circular dependency ✨✨✨ contradiction (1 < 2 < 3 < 1).
            Since a vertex cannot appear both before and after another vertex in a single linear sequence,
            topological sorting cannot exist for graphs with cycles.

     */

    /*

    Topological Sort using DFS

            class Solution {

            public int[] topoSort(int numVertices, List<List<Integer>> adjList) {

                boolean[] isVisited = new boolean[numVertices];

                Stack<Integer> topoOrderStack = new Stack<>();

                // Call DFS for all unvisited component nodes to handle disconnected graphs
                for (int node = 0; node < numVertices; node++) {
                    if (!isVisited[node]) {
                        findTopoSortDFS(node, isVisited, topoOrderStack, adjList);
                    }
                }

                // Pop elements from the stack to build the final topological order array
                int[] topologicalOrder = new int[numVertices];
                int index = 0;

                while (!topoOrderStack.isEmpty()) {

                    topologicalOrder[index++] = topoOrderStack.pop();

                }

                return topologicalOrder;
            }

            private void findTopoSortDFS(int currentNode, boolean[] isVisited, Stack<Integer> topoOrderStack, List<List<Integer>> adjList) {

                // Mark the current node as visited
                isVisited[currentNode] = true;

                // Traverse all the deeper dependencies / neighbors first
                for (int neighbor : adjList.get(currentNode)) {
                    if (!isVisited[neighbor]) {
                        findTopoSortDFS(neighbor, isVisited, topoOrderStack, adjList);
                    }
                }

                // Post-order processing:
                // All tasks/nodes that depend on 'currentNode' have been fully explored and
                // pushed to the stack. Now, it is safe to push 'currentNode' onto the stack.
                topoOrderStack.push(currentNode);
            }
        }

        The above algorithm will fail silently if it is not a DAG and will return incorrect order, optimized algo to fix that

        class Solution {
            public int[] topoSort(int numVertices, List<List<Integer>> adjList) {
                // 0 = Unvisited, 1 = Visiting (in current recursion path), 2 = Visited (fully processed)
                int[] visitedState = new int[numVertices];
                Stack<Integer> topoOrderStack = new Stack<>();

                for (int node = 0; node < numVertices; node++) {
                    if (visitedState[node] == 0) {
                        // If DFS returns false, a cycle was detected!
                        if (!findTopoSortDFS(node, visitedState, topoOrderStack, adjList)) {
                            return new int[0]; // Return an empty array indicating Topo Sort is impossible
                        }
                    }
                }

                int[] topologicalOrder = new int[numVertices];
                int index = 0;
                while (!topoOrderStack.isEmpty()) {
                    topologicalOrder[index++] = topoOrderStack.pop();
                }
                return topologicalOrder;
            }

            private boolean findTopoSortDFS(int currentNode, int[] visitedState, Stack<Integer> topoOrderStack, List<List<Integer>> adjList) {
                // Mark as State 1: Currently visiting (active in the recursion stack)
                visitedState[currentNode] = 1;

                for (int neighbor : adjList.get(currentNode)) {
                    // Case 1: If the neighbor is currently being visited in this path, we found a back-edge (CYCLE!)
                    if (visitedState[neighbor] == 1) {
                        return false;
                    }

                    // Case 2: If the neighbor is unvisited, recursively explore it
                    if (visitedState[neighbor] == 0) {
                        if (!findTopoSortDFS(neighbor, visitedState, topoOrderStack, adjList)) {
                            return false;
                        }
                    }

                    // Case 3: If visitedState[neighbor] == 2, we safely ignore it (it's already in the stack)
                }

                // Post-order processing: Mark as State 2 (fully processed) and push to stack
                visitedState[currentNode] = 2;
                topoOrderStack.push(currentNode);

                return true; // No cycles found along this path
            }
        }

     */

    /*

            Topological Sort using Kahn's Algorithm ( modified bfs )

            class Solution {

            public int[] topoSort(int numVertices, List<List<Integer>> adjList) {

                // Step 1: Calculate the in-degree (number of incoming edges) for every vertex
                int[] inDegree = new int[numVertices];
                for (int node = 0; node < numVertices; node++) {
                    for (int neighbor : adjList.get(node)) {
                        inDegree[neighbor]++;
                    }
                }

                // Step 2: Initialize a queue and enqueue all vertices with an in-degree of 0.
                // An in-degree of 0 means the node has no dependencies and can be processed immediately.
                // A DAG will always have atleast 1 node with indegree 0
                Queue<Integer> bfsQueue = new ArrayDeque<>();
                for (int node = 0; node < numVertices; node++) {
                    if (inDegree[node] == 0) {
                        bfsQueue.add(node);
                    }
                }

                // Initialize variables to track our linear topological ordering
                int[] topologicalOrder = new int[numVertices];
                int index = 0;

                // Step 3: Process the nodes in the queue
                while (!bfsQueue.isEmpty()) {
                    // Remove the node from the front of the queue and add it to our final ordering
                    int currentNode = bfsQueue.remove();
                    topologicalOrder[index++] = currentNode;

                    // Step 4: Iterate through all the neighbors of the processed node
                    for (int neighbor : adjList.get(currentNode)) {
                        // Since 'currentNode' is now processed, we remove its dependency from its neighbors ✨✨✨
                        inDegree[neighbor]--;

                        // If a neighbor's in-degree drops to 0, all its prerequisites are met. ✨✨✨
                        // We can now safely add it to the queue to be processed next. ✨✨✨
                        if (inDegree[neighbor] == 0) {
                            bfsQueue.add(neighbor);
                        }
                    }
                }

                // Note: In an interview setup, if 'index != numVertices', it implies the graph
                // contains a cycle, and a valid topological sort is impossible. ✨✨✨
                return topologicalOrder;
            }
        }

     */

    // Detect a cycle in a directed graph ( using kahn's algo / modified bfs )

    /*

            class Solution {

            public boolean isCyclic(int numVertices, List<List<Integer>> adjList) {

                // Step 1: Calculate the in-degree for every vertex
                int[] inDegree = new int[numVertices];
                for (int node = 0; node < numVertices; node++) {
                    for (int neighbor : adjList.get(node)) {
                        inDegree[neighbor]++;
                    }
                }

                // Step 2: Push all nodes with an in-degree of 0 into the queue
                Queue<Integer> bfsQueue = new ArrayDeque<>();
                for (int node = 0; node < numVertices; node++) {
                    if (inDegree[node] == 0) {
                        bfsQueue.add(node);
                    }
                }

                // Keep a counter to track how many vertices successfully reach an in-degree of 0
                int visitedNodeCount = 0;

                // Step 3: Process the queue
                while (!bfsQueue.isEmpty()) {
                    int currentNode = bfsQueue.remove();
                    visitedNodeCount++; // Increment the counter as this node is safely processed

                    // Since 'currentNode' is now processed, we remove its dependency from its neighbors ✨✨✨
                    for (int neighbor : adjList.get(currentNode)) {
                        inDegree[neighbor]--;

                        // If a neighbor's in-degree becomes 0, add it to the queue
                        if (inDegree[neighbor] == 0) {
                            bfsQueue.add(neighbor);
                        }
                    }
                }

                // Step 4: If we processed all vertices, there is NO cycle.
                // If visitedNodeCount is less than numVertices, a cycle exists.
                return visitedNodeCount != numVertices;
            }
        }

     */

    // Course Schedule I

    /*

    We converted the prerequisites matrix into an adjacency list to avoid a major performance bottleneck.
    In its original form, prerequisites is just an unordered list of edges. To find which courses depend on a completed course,
    the algorithm has to scan the entire list from scratch every single time, resulting in an incredibly slow O(V * E) time complexity in BFS while loop
    that leads to a Time Limit Exceeded (TLE) error on LC . By pre-building an adjacency list,
    we create an explicit lookup structure where each course points directly to its dependent neighbors.
    This optimizes the neighbor lookups from an expensive full-array scan to a near-instant O(1) operation per edge,
    dropping the overall time complexity to an optimal, interview-ready O(V + E).

            class Solution {

            public boolean canFinish(int numCourses, int[][] prerequisites) {

                // Step 1: Initialize the adjacency list for the graph
                List<List<Integer>> adjList = new ArrayList<>();

                for (int i = 0; i < numCourses; i++) {
                    adjList.add(new ArrayList<>());
                }

                // Step 2: Build the graph and populate the in-degree array
                // A prerequisite pair [course, prereq] represents a directed edge: prereq -> course
                int inDegree[] = new int[numCourses];
                for (int edge[] : prerequisites) {
                    int course = edge[0];
                    int prereq = edge[1];

                    adjList.get(prereq).add(course);
                    inDegree[course]++;
                }

                // Step 3: Add all courses with 0 in-degree (no prerequisites) to the queue
                Queue<Integer> bfsQueue = new ArrayDeque<>();

                for (int course = 0; course < numCourses; course++) {
                    if (inDegree[course] == 0) {
                        bfsQueue.add(course);
                    }
                }

                int finishedCourses = 0;

                // Step 4: Process the courses using Kahn's algorithm
                while (!bfsQueue.isEmpty()) {

                    int currCourse = bfsQueue.remove();
                    finishedCourses++;

                    // Instantly look up only the neighbors of the current course (O(1) look up per edge)
                    for (int neighborCourse : adjList.get(currCourse)) {
                        inDegree[neighborCourse]--;

                        if (inDegree[neighborCourse] == 0) {
                            bfsQueue.add(neighborCourse);
                        }
                    }
                }

                // Step 5: If we could finish all courses, it's a valid DAG (no cycles)
                return finishedCourses == numCourses;
            }
        }

     */

    // Course Schedule II

    /*

        class Solution {
            public int[] findOrder(int numCourses, int[][] prerequisites) {

                // Step 1: Initialize the adjacency list for the graph
                List<List<Integer>> adjList = new ArrayList<>();
                for (int i = 0; i < numCourses; i++) {
                    adjList.add(new ArrayList<>());
                }

                // Step 2: Build the graph and populate the in-degree array
                // A prerequisite pair [course, prereq] represents a directed edge: prereq -> course
                int[] inDegree = new int[numCourses];
                for (int[] edge : prerequisites) {
                    int course = edge[0];
                    int prereq = edge[1];

                    adjList.get(prereq).add(course);
                    inDegree[course]++;
                }

                // Step 3: Add all courses with 0 in-degree (no prerequisites) to the queue
                Queue<Integer> bfsQueue = new ArrayDeque<>();
                for (int course = 0; course < numCourses; course++) {
                    if (inDegree[course] == 0) {
                        bfsQueue.add(course);
                    }
                }

                int[] courseOrder = new int[numCourses];
                int idx = 0;

                // Step 4: Process the courses using Kahn's algorithm
                while (!bfsQueue.isEmpty()) {
                    int currCourse = bfsQueue.remove();
                    courseOrder[idx++] = currCourse;

                    // Instantly look up only the neighbors of the current course
                    for (int neighborCourse : adjList.get(currCourse)) {
                        inDegree[neighborCourse]--;

                        if (inDegree[neighborCourse] == 0) {
                            bfsQueue.add(neighborCourse);
                        }
                    }
                }

                // Step 5: If we processed all courses, return the valid order.
                // Otherwise, a cycle exists (it's impossible to complete), so return an empty array.
                if (idx == numCourses) {
                    return courseOrder;
                }

                return new int[0];
            }
        }

     */

    // Find Eventual Safe States

    /*

    Using dfs: here instead of 3 boolean arrays a single int array can be used but this way is more readable and
    interview preferred, in case of using single array code quality and readability goes down

            class Solution {

            // concept : if a node is part of a cycle or leads to a cycle it
            // can never be a safe node

            public List<Integer> eventualSafeNodes(int[][] graph) {

                int vertices = graph.length;

                boolean visited[] = new boolean[vertices];

                boolean inPath[] = new boolean[vertices];

                boolean isSafe[] = new boolean[vertices];

                List<Integer> safeNodes = new ArrayList<>();

                for (int node = 0; node < vertices; node++) {

                    if (!visited[node]) {
                        isComponentCyclic(node, visited, inPath, isSafe, graph);
                    }

                }

                for(int i=0;i<vertices; i++){
                    if(isSafe[i]){
                        safeNodes.add(i);
                    }
                }

                return safeNodes;
            }

            boolean isComponentCyclic(int currNode, boolean visited[], boolean inPath[], boolean isSafe[], int graph[][]){

                visited[currNode]=true;
                inPath[currNode]=true;

                for(int neighbor: graph[currNode]){

                    if(!visited[neighbor]){
                        if(isComponentCyclic(neighbor, visited, inPath, isSafe, graph)){
                            return true;
                        }
                    }else if(inPath[neighbor]){
                    // neighbor is visited and is part of a cycle or leads to a cycle
                        return true;
                    }

                }

                inPath[currNode]=false;
                // if we reached here it means no path from curr node
                // leads to a cycle or is part of a cycle
                isSafe[currNode]=true;

                return false;
            }
        }

     */

    /*

    Using toposort/ kahn's algo (modified bfs)
    // this soln is better than DFS approach because for massive graphs
    // StackOverFlow error will happen using DFS approach but it won't happen with
    // this approach and both algos have same time and space complexity

            class Solution {

            public List<Integer> eventualSafeNodes(int[][] graph) {

                int totalNodes = graph.length;

                // 1. Initialize the adjacency list for the reversed graph
                List<List<Integer>> reversedGraph = new ArrayList<>();
                for (int i = 0; i < totalNodes; i++) {
                    reversedGraph.add(new ArrayList<>());
                }

                // In the reversed graph, 'inDegree' corresponds to the 'out-degree' of the original graph.
                int[] inDegree = new int[totalNodes];

                // 2. Build the reversed graph and calculate in-degrees
                for (int node = 0; node < totalNodes; node++) {
                    for (int neighbor : graph[node]) {
                        // Original edge: node -> neighbor
                        // Reversed edge: neighbor -> node
                        reversedGraph.get(neighbor).add(node);

                        // Track how many outgoing paths 'node' has in the original graph
                        inDegree[node]++;
                    }
                }


                 * CONCEPT EXPLANATION:
                 * * 1. In the original graph, a node is terminal if its out-degree is 0.
                 * After reversing the edges, these terminal nodes will have an in-degree of 0.
                 * * 2. We start our BFS queue with these terminal nodes and iteratively strip away
                 * the paths leading into them in the original graph.
                 * * 3. Whenever a neighbor's in-degree drops to 0, it means that EVERY SINGLE
                 * outgoing path from that node in the original graph successfully terminated
                 * at a safe node. It guarantees the node neither forms a cycle nor leads to one.
                 * * 4. Any nodes locked inside a cycle (or pointing down a dead-end path toward a cycle)
                 * will never have their in-degrees drop to 0, meaning they will never enter the queue
                 * and will be correctly filtered out as unsafe.
                 *

            Queue<Integer> bfsQueue = new ArrayDeque<>();
            boolean isSafeNode[] = new boolean[totalNodes];

            // Push all initial terminal nodes (in-degree 0 in reversed graph) into the queue
                for (int i = 0; i < totalNodes; i++) {
                if (inDegree[i] == 0) {
                    bfsQueue.add(i);
                }
            }

            // 3. Process the graph layer-by-layer
                while (!bfsQueue.isEmpty()) {
                int currNode = bfsQueue.remove();
                isSafeNode[currNode] = true;

                // Since currNode is verified safe, remove its dependency from parent nodes
                for (int parentNode : reversedGraph.get(currNode)) {
                    inDegree[parentNode]--;

                    // If all outgoing paths from parentNode lead to safe nodes, it is now safe
                    if (inDegree[parentNode] == 0) {
                        bfsQueue.add(parentNode);
                    }
                }
            }

            // 4. Collect all verified safe nodes in ascending order
            List<Integer> safeNodesList = new ArrayList<>();
                for (int node = 0; node < totalNodes; node++) {
                if (isSafeNode[node]) {
                    safeNodesList.add(node);
                }
            }

                return safeNodesList;
        }
        }

     */

    // Alien Dictionary

    /*

          class Solution {

            public String findOrder(String[] dictionary, int wordCount, int charLimit) {

                // Initialize graph using Sets to avoid duplicate edges automatically
                List<Set<Integer>> graph = new ArrayList<>();
                for (int i = 0; i < charLimit; i++) {
                    graph.add(new HashSet<>());
                }

                // Build the graph by comparing adjacent words
                for (int i = 0; i < wordCount - 1; i++) {
                    String currentWord = dictionary[i];
                    String nextWord = dictionary[i + 1];

                    // Edge Case Check: If currentWord is a longer prefix of nextWord (e.g., "abc" before "ab")
                    if (currentWord.length() > nextWord.length() && currentWord.startsWith(nextWord)) {
                        return ""; // Invalid dictionary order
                    }

                    int minLength = Math.min(currentWord.length(), nextWord.length());

                    // Find the first mismatching character to establish a dependency rule
                    for (int ptr = 0; ptr < minLength; ptr++) {
                        char u = currentWord.charAt(ptr);
                        char v = nextWord.charAt(ptr);

                        if (u != v) {
                            int sourceNode = u - 'a';
                            int destinationNode = v - 'a';

                            // Adds the rule: sourceNode comes before destinationNode
                            graph.get(sourceNode).add(destinationNode);
                            break; // Stop comparing further characters for this pair
                        }
                    }
                }

                // Return the computed topological sequence
                return performTopologicalSort(charLimit, graph);
            }

            private String performTopologicalSort(int totalLanguages, List<Set<Integer>> graph) {

                int[] inDegree = new int[totalLanguages];

                // Calculate in-degree for each node
                for (int i = 0; i < totalLanguages; i++) {
                    for (int neighbor : graph.get(i)) {
                        inDegree[neighbor]++;
                    }
                }

                // Queue to store nodes with 0 in-degree (ready to be processed)
                Queue<Integer> zeroInDegreeQueue = new LinkedList<>();
                for (int i = 0; i < totalLanguages; i++) {
                    if (inDegree[i] == 0) {
                        zeroInDegreeQueue.add(i);
                    }
                }

                StringBuilder alphabetOrder = new StringBuilder();

                // Process the graph
                while (!zeroInDegreeQueue.isEmpty()) {

                    int currentNode = zeroInDegreeQueue.remove();
                    alphabetOrder.append((char) (currentNode + 'a'));

                    // since current node is processed remove its depencency from its neighbors
                    for (int neighbor : graph.get(currentNode)) {
                        inDegree[neighbor]--;

                        if (inDegree[neighbor] == 0) {
                            zeroInDegreeQueue.add(neighbor);
                        }
                    }
                }

                // Cycle Check: If the order length matches total unique characters, it's valid
                if (alphabetOrder.length() == totalLanguages) {
                    return alphabetOrder.toString();
                }

                // If there's a cycle, a valid topological sort is impossible
                return "";
            }
        }

     */

    //////////////////// SHORTEST PATH ALGORITHMS AND PROBLEMS ////////////////////////////

    // Shortest path in undirected graph with unit weights

    /*

            Time Complexity : O ( V + E )

            class Solution {

            public int[] findShortestPaths(int totalNodes, List<List<Integer>> adjacencyList, int sourceNode) {

                // Step 1: Initialize the distances array.
                // We set all distances to infinity initially to represent unvisited/unreachable states.
                int[] distances = new int[totalNodes];
                Arrays.fill(distances, Integer.MAX_VALUE);

                // Step 2: Set up the BFS queue.
                // Standard Queue handles the level-by-level traversal essential for unweighted graphs.
                Queue<Integer> bfsQueue = new ArrayDeque<>();

                // Step 3: Base case setup.
                // Distance from a node to itself is always 0. Then, kick off the BFS by queuing the source.
                distances[sourceNode] = 0;
                bfsQueue.add(sourceNode);

                // Step 4: Process the graph layer-by-layer (BFS execution).
                while (!bfsQueue.isEmpty()) {

                    int currentNode = bfsQueue.remove();

                    // Because every edge has a unit weight of 1, any unvisited neighbor
                    // will naturally be 1 step further away than the currentNode.
                    int tentativeDistance = distances[currentNode] + 1;

                    // Explore all immediate neighbors of the current node.
                    for (int neighbor : adjacencyList.get(currentNode)) {

                        // If the newly calculated distance is shorter than the known distance,
                        // update it. (In BFS, the first time we see a node is always its shortest path).
                        if (tentativeDistance < distances[neighbor]) {
                            distances[neighbor] = tentativeDistance;

                            // Add neighbor to queue so we can later explore its neighbors in turn.
                            bfsQueue.add(neighbor);
                        }
                    }
                }

                // Step 5: Post-processing for unreachable nodes.
                // If a node's distance is still Infinity, it means there is no path connecting
                // the source to that node. Per convention, we convert these to -1.
                for (int i = 0; i < totalNodes; i++) {
                    if (distances[i] == Integer.MAX_VALUE) {
                        distances[i] = -1;
                    }
                }

                return distances;
            }
        }

     */

    // Shortest path in DAG

    /*

        Topological sort works because it creates a linear ordering based entirely on dependencies.
        In a directed acyclic graph, if there is a directed edge pointing from node A to node B, it means node B depends on node A.
        Therefore, node A must appear before node B in the topological sequence.

        When your algorithm processes the nodes by popping them one by one from the topological stack, it follows this exact order.
        By the time the algorithm pops a particular node from the stack to relax its edges,
        every single ancestor node that could possibly reach it has already been popped and processed.

        Because all incoming paths to that particular node have already been completely explored,
        the shortest distance to that node is guaranteed to be locked in and accurate.
        The algorithm will never encounter a scenario where a later node updates an earlier node,
        ensuring that a single pass through the graph is enough to find all shortest paths.

        class Solution {

            public int[] findShortestPathInDAG(int totalNodes, int sourceNode, int[][] edges) {

                // edges = [[sourceNode, destinationNode, edgeWeight]]

                // Step 1: Build the Adjacency List
                // Each entry in the list holds an array of size 2: [neighborNode, edgeWeight]
                List<List<int[]>> adjacencyList = new ArrayList<>();
                for (int i = 0; i < totalNodes; i++) {
                    adjacencyList.add(new ArrayList<>());
                }

                for (int edge[] : edges) {
                    int u = edge[0];
                    int v = edge[1];
                    int weight = edge[2];
                    adjacencyList.get(u).add(new int[]{v, weight});
                }

                // Step 2: Generate Topological Ordering using DFS
                // A Topological Sort ensures that for any directed edge u -> v, u comes before v.
                Stack<Integer> topologicalOrderStack = new Stack<>();

                boolean[] isVisited = new boolean[totalNodes];

                for (int node = 0; node < totalNodes; node++) {
                    if (!isVisited[node]) {
                        generateTopologicalSortDFS(node, topologicalOrderStack, isVisited, adjacencyList);
                    }
                }

                // Step 3: Initialize the Distances Array
                // We set all distances to infinity initially to represent unvisited/unreachable states.
                int[] distances = new int[totalNodes];
                Arrays.fill(distances, Integer.MAX_VALUE);

                // Distance from the source to itself is 0
                distances[sourceNode] = 0;

                // Step 4: Process vertices in Topological Order (Relaxing Edges)
                while (!topologicalOrderStack.isEmpty()) {
                    int currentNode = topologicalOrderStack.pop();

                    // Optimization/Safety Check: If the current node has a distance of Infinity,
                    // it means it cannot be reached from the specified sourceNode.
                    // We skip it to avoid processing unreachable paths and to prevent integer overflow.
                    if (distances[currentNode] == Integer.MAX_VALUE) {
                        continue;
                    }

                    int currentDistance = distances[currentNode];

                    // Relax all outgoing edges from the current node
                    for (int neighborPair[] : adjacencyList.get(currentNode)) {
                        int neighborNode = neighborPair[0];
                        int edgeWeight = neighborPair[1];

                        int calculatedDistance = currentDistance + edgeWeight;

                        // If the new calculated path is shorter than what we currently know, update it
                        if (calculatedDistance < distances[neighborNode]) {
                            distances[neighborNode] = calculatedDistance;
                        }
                    }
                }

                // Step 5: Post-processing for Unreachable Nodes
                // Any node that still retains its initialization value of Integer.MAX_VALUE
                // is genuinely unreachable from the source. Convert these values to -1 per constraints.
                for (int i = 0; i < totalNodes; i++) {
                    if (distances[i] == Integer.MAX_VALUE) {
                        distances[i] = -1;
                    }
                }

                return distances;
            }

            private void generateTopologicalSortDFS(int currentNode, Stack<Integer> topologicalOrderStack,
                                                    boolean[] isVisited, List<List<int[]>> adjacencyList) {
                isVisited[currentNode] = true;

                for (int neighborPair[] : adjacencyList.get(currentNode)) {
                    int neighbor = neighborPair[0];
                    if (!isVisited[neighbor]) {
                        generateTopologicalSortDFS(neighbor, topologicalOrderStack, isVisited, adjacencyList);
                    }
                }

                // Push to stack only after all deeper dependencies are fully explored
                topologicalOrderStack.push(currentNode);
            }
        }

        Time Complexity : Here is the time complexity calculation:

        Step 1: Building the adjacency list takes O(V + E) time because we initialize V lists and then iterate through E edges.

        Step 2: The topological sort via DFS takes O(V + E) time because every node is visited exactly once and every edge is explored once.

        Step 3: Initializing the distances array using Arrays.fill takes O(V) time.

        Step 4: Processing the stack and relaxing the edges takes O(V + E) time because we pop V nodes in total, and across the entire loop,
        we traverse all E edges exactly once.

        Step 5: The final loop to convert unreachable infinity values to -1 takes O(V) time.

        Adding these up: O(V + E) + O(V + E) + O(V) + O(V + E) + O(V) = O(3V + 3E).
        Dropping the constants leaves a final time complexity of O(V + E).

     */

    // Dijkstra's algorithm

    // Using Priority Queue

    /*

        Using Priority Queue:

        class Solution {

            static class NodeDistancePair {
                int nodeVertex;
                int distanceFromSource;

                NodeDistancePair(int nodeVertex, int distanceFromSource) {
                    this.nodeVertex = nodeVertex;
                    this.distanceFromSource = distanceFromSource;
                }
            }

            public int[] dijkstra(int totalNodes, ArrayList<ArrayList<Integer>> edgeList, int sourceNode) {

                // Step 1: Initialize the adjacency list representing the graph
                // Structure: adjList.get(u) -> returns a list of [neighborNode, edgeWeight] pairs
                List<List<int[]>> adjacencyList = new ArrayList<>();
                for (int i = 0; i < totalNodes; i++) {
                    adjacencyList.add(new ArrayList<>());
                }

                // Step 2: Convert the raw edge list into an adjacency list graph
                for (ArrayList<Integer> edge : edgeList) {
                    int source = edge.get(0);
                    int destination = edge.get(1);
                    int weight = edge.get(2);

                    // Build bi-directional paths (if Undirected Graph)
                    adjacencyList.get(source).add(new int[]{destination, weight});
                    adjacencyList.get(destination).add(new int[]{source, weight});
                }

                // Step 3: Track min-distances from the source node to all other nodes
                int shortestDistances[] = new int[totalNodes];
                Arrays.fill(shortestDistances, Integer.MAX_VALUE); // Default all distances to infinity

                // Step 4: Initialize Min-Heap Priority Queue sorted by distance
                PriorityQueue<NodeDistancePair> minHeap = new PriorityQueue<>(
                    (pairA, pairB) -> Integer.compare(pairA.distanceFromSource, pairB.distanceFromSource)
                );
                // Uses Integer.compare() instead of subtraction (pairA.distanceFromSource - pairB.distanceFromSource)
                // to prevent integer overflow/underflow bugs with large distances.

                // Step 5: Seed the algorithm with the starting source node
                shortestDistances[sourceNode] = 0;
                minHeap.add(new NodeDistancePair(sourceNode, 0));

                // Step 6: Process the graph dynamically
                while (!minHeap.isEmpty()) {

                    NodeDistancePair currentPair = minHeap.remove();
                    int currentNode = currentPair.nodeVertex;
                    int currentDistance = currentPair.distanceFromSource;

                    // Optimization: If we found a shorter path to this node already,
                    // skip processing its stale neighbors
                    if (currentDistance > shortestDistances[currentNode]) {
                        continue;
                    }

                    // Step 7: Relax edges for all neighbors of the current node
                    for (int neighborData[] : adjacencyList.get(currentNode)) {
                        int neighborNode = neighborData[0];
                        int edgeWeight = neighborData[1];
                        int calculatedDistance = currentDistance + edgeWeight;

                        // If a shorter path to the neighbor is discovered, update and queue it
                        if (calculatedDistance < shortestDistances[neighborNode]) {
                            shortestDistances[neighborNode] = calculatedDistance;
                            minHeap.add(new NodeDistancePair(neighborNode, calculatedDistance));
                        }
                    }
                }

                return shortestDistances;
            }
        }

        ### Time Complexity: O((V + E) log V)

        Where:

        * V = Total number of nodes (totalNodes)
        * E = Total number of edges (edgeList.size())

        ---

        ### Step-by-Step Breakdown

        * Graph Construction (Steps 1 & 2): O(V + E)
        * Priority Queue Operations (Steps 6 & 7): O(E log V)
        * Each vertex can be pushed and popped from the minHeap. In the worst-case scenario (dense graph),
        we might insert up to E elements into the heap.
        * Every insertion (minHeap.add()) and deletion (minHeap.remove()) in a heap of size V takes O(log V) time.
        * Therefore, processing the edges takes O(E log V) time.

        Combining these gives O(V + E + E log V), which simplifies to O((V + E) log V).

        To simplify the expression using Big O notation, we look at which terms grow the fastest as V and E become very large.

        Here is the step-by-step mathematical breakdown:

        Step 1: Compare E and E log V
        This means: E + E log V simplifies to just E log V.

        Now our expression is: V + E log V.

        Step 2: Compare V and V log V
        To group the V and E terms together, we can look at the upper bound.
        Since log V is greater than 1 for any graph of a reasonable size, the term V is strictly less than or equal to V log V.
        Therefore, we can safely upper-bound V to V log V to find a clean, factored expression.

        This changes the expression to: V log V + E log V.

        This gives us the final simplified complexity of O((V + E) log V).

        (For a connected graph where E >= V, this is often written simply as O(E log V)).

        ---

        ### Space Complexity: O(V + E)

        * O(V + E) to store the graph inside the adjacencyList.
        * O(V) for the shortestDistances tracking array.
        * O(V) for the elements held inside the minHeap.

     */

    // Why do we use a PQ even when using a queue will also give us the correct result

    /*

Why We Use a Priority Queue Instead of a Regular Queue in Dijkstra

While it is true that using a regular Queue can eventually give you the correct answer
(provided you allow nodes to re-enter the queue when a shorter path is found),
it completely destroys the efficiency of the algorithm.

Here is the exact reason why a Priority Queue is mandatory for performance.

1. The Greediness and Finalization
A Priority Queue always pulls out the node with the absolute smallest tentative distance.
Because it chooses the minimum distance first, it guarantees that for graphs with positive weights,
the distance to that popped node is finalized and cannot get any shorter. Each node is effectively processed and finalized exactly once.
2. The Regular Queue Problem (The Re-processing Avalanche)
A regular Queue operates on a First-In, First-Out (FIFO) basis.
It does not care about the size of the distance; it only cares about which node arrived first.

If you use a regular Queue, you might find a long,
inefficient path to Node X early on (for example, a distance of 100).
The algorithm will pop Node X and waste computational time updating all of Node X's neighbors based on that bad distance of 100.

Much later, the regular Queue will discover a much shorter path to Node X (for example, a distance of 5).
Because 5 is less than 100, the algorithm updates the distance. But now,
it must push Node X back into the Queue to re-update all of Node X's neighbors all over again.

3. Time Complexity Explosion
This constant rewriting and re-pushing of nodes means the same node can enter and leave a regular queue multiple times.

With a Priority Queue, the time complexity is a highly efficient O(E log V)
because nodes are finalized in order of their true shortest distance.

With a regular Queue, the algorithm degrades into a variation of the Shortest Path Faster Algorithm (SPFA).
In the worst-case scenario, this repeated re-processing causes the time complexity to skyrocket to O(V * E).
On large graphs, this difference means the regular Queue version will run incredibly slow or hit a time-limit exceeded error.

Summary
A Priority Queue ensures you explore the most promising paths first,
guaranteeing you only process each node's neighbors efficiently.
A regular Queue blindly processes paths as they appear, leading to a massive avalanche of redundant recalculations.

     */

    // Best time complexity derivation of Dijkstra using PQ

    /*

        Dijkstra's Time Complexity Derivation for Dense Graphs

        Legend:
        V = Total Vertices (Nodes)
        E = Total Edges
        ne = Number of edges connected to a single active vertex

        Step 1: The Core Operations per Vertex
        At its foundational level, Dijkstra processes every vertex (V) and handles its respective edges (ne).
        For each vertex, the algorithm performs two heavy collection operations:

        1. Pop from Min-Heap: Extracts the minimum element from the priority queue.
        2. Push into PQ (Relaxation): For each edge connected to that vertex (ne),
        it might push an updated distance into the queue.

        Mathematical Representation:
        O(V * (pop vertex from min-heap + (no. of edges on each vertex * push into PQ)))

        Substituting the time complexities of binary heap operations O(log(heap size)):
        O(V * (log(heap size) + ne * log(heap size)))

        Step 2: Factoring and the Dense Graph Assumption
        By factoring out the common log(heap size) term, the formula simplifies to:
        O(V * log(heap size) * (ne + 1))

        Worst-Case Boundary: One vertex can have up to (V - 1) edges. In a worst-case dense graph scenario,
        the number of edges per vertex (ne) is roughly equal to V.

        Substituting (ne + 1) with V yields:
        O(V * log(heap size) * V) -> O(V^2 * log(heap size))

        Step 3: Determining Maximum Heap Size
        Next, evaluate how large the priority queue can actually grow.
        Because standard Dijkstra allows duplicate/stale nodes to pile up in the heap rather than updating them in place,
        the heap size can grow to match the total number of edges (E) in the graph.

        In a dense graph, the total number of edges E is roughly equal to V^2 (E = V^2).
        Therefore, the maximum heap size can be substituted with V^2:
        O(V^2 * log(V^2))

        Step 4: Final Algebraic Simplification
        Using logarithmic properties, the exponent inside a logarithm can be moved out front as a constant multiplier: log(x^a) = a * log(x).

        Thus, log(V^2) transforms into 2 * log(V):
        O(V^2 * 2 * log(V))

        Finally, since V^2 represents the maximum capacity of total edges (E = V^2), we substitute V^2 back with E:
        O(E * 2 * log(V))

        Dropping the constant multiplier 2 brings us to the finalized, textbook asymptotic time complexity:
        FINAL TIME COMPLEXITY: O(E log V)

        Undirected Edge Logic:
        In an undirected graph, a unique edge is formed by picking any 2 distinct vertices.
        The total number of ways to choose 2 vertices out of V total nodes is defined by combinations as VC2.
        This formula expands to (V * (V - 1)) / 2, which mathematically eliminates double-counting common edges.
        Asymptotically, this simplifies to O(V^2), validating why E is substituted with V^2 in dense graph analysis.

     */

    // Using TreeSet

    /*

                class Solution {

                // Helper data carrier class to pair a graph vertex with its computed distance from the source
                static class NodeDistancePair {
                    int nodeVertex;
                    int distanceFromSource;

                    NodeDistancePair(int nodeVertex, int distanceFromSource) {
                        this.nodeVertex = nodeVertex;
                        this.distanceFromSource = distanceFromSource;
                    }
                }

                public int[] dijkstraWithSet(int totalNodes, ArrayList<ArrayList<Integer>> edgeList, int sourceNode) {

                    // Step 1: Initialize the adjacency list structure to represent the graph.
                    // Each index 'u' will hold a nested list of arrays in the format: [neighborNode, edgeWeight]
                    List<List<int[]>> adjacencyList = new ArrayList<>();
                    for (int i = 0; i < totalNodes; i++) {
                        adjacencyList.add(new ArrayList<>());
                    }

                    // Step 2: Populate the adjacency list by parsing the raw input edge data.
                    // This configuration models an undirected graph, processing two-way pathways.
                    for (ArrayList<Integer> edge : edgeList) {
                        int source = edge.get(0);
                        int destination = edge.get(1);
                        int weight = edge.get(2);

                        adjacencyList.get(source).add(new int[]{destination, weight});
                        adjacencyList.get(destination).add(new int[]{source, weight});
                    }

                    // Step 3: Create an array to maintain the minimum calculated distance to each node.
                    // Initialize all values to Integer.MAX_VALUE to simulate an unvisited state of infinity.
                    int[] shortestDistances = new int[totalNodes];
                    Arrays.fill(shortestDistances, Integer.MAX_VALUE);

                    // Step 4: Configure a TreeSet to act as our self-sorting tracking structure.
                    // CRITICAL DESIGN RULE: A TreeSet identifies duplicate objects when its comparator returns 0.
                    // If evaluated strictly by distance, different nodes with identical distances would overwrite
                    // each other. We use a mandatory two-tiered sorting strategy to preserve distinct elements.

                    TreeSet<NodeDistancePair> set = new TreeSet<>((pairA, pairB) -> {
                        // Tier 1: Primary sort dynamically structures elements by path distance in ascending order.
                        if (pairA.distanceFromSource != pairB.distanceFromSource) {
                            return Integer.compare(pairA.distanceFromSource, pairB.distanceFromSource);
                        }
                        // Tier 2: If distances are a tie, unique vertex IDs break the tie to prevent data erasure.
                        return Integer.compare(pairA.nodeVertex, pairB.nodeVertex);
                    });

                    // Step 5: Seed the processing cycle with the initial root source node.
                    shortestDistances[sourceNode] = 0;
                    set.add(new NodeDistancePair(sourceNode, 0));

                    // Step 6: Process the graph dynamically until the set runs completely out of elements
                    while (!set.isEmpty()) {

                        // pollFirst() returns and removes the element containing the lowest distance metric

                        NodeDistancePair currentPair = set.pollFirst();
                        int currentNode = currentPair.nodeVertex;
                        int currentDistance = currentPair.distanceFromSource;

                         *  THE OMISSION OF THE "CONTINUE" OPTIMIZATION:
                         * In alternative priority-queue-based implementations of Dijkstra, old, stale, and duplicate versions
                         * of a vertex are allowed to remain trapped inside the collection. This requires a defensive
                         * 'if (currentDistance > shortestDistances[currentNode]) continue;' statement to prune them out later.
                         *
                         * When employing a TreeSet, this optimization check is entirely useless and redundant. Because
                         * the logic explicitly cleanses and eliminates an old node configuration from the tree hierarchy
                         * *prior* to inserting its newly discovered optimal distance, the TreeSet is guaranteed to maintain
                         * exactly one unique, absolute best-case reference pair per discovered node. Stale entries are
                         * completely prevented from accumulating.
                         *

                // Step 7: Step through each immediate neighboring node connected to the active vertex
                        for (int neighborData[] : adjacencyList.get(currentNode)) {
                    int neighborNode = neighborData[0];
                    int edgeWeight = neighborData[1];
                    int calculatedDistance = currentDistance + edgeWeight;

                    // Evaluate if the newly uncovered route provides a cheaper path than previously tracked
                    if (calculatedDistance < shortestDistances[neighborNode]) {

                         * Step 8: The Erase-and-Replace Sequence
                         * If the target neighbor node was reached on a prior loop iteration (meaning its value
                         * is no longer set to infinity), its old, less efficient distance pair is still actively
                         * tracking inside the TreeSet. We must search out and remove that old pairing using its
                         * exact current distance profile so the tree structure does not break or desynchronize.

                        if (shortestDistances[neighborNode] != Integer.MAX_VALUE) {
                            set.remove(new NodeDistancePair(neighborNode, shortestDistances[neighborNode]));
                        }

                        // Step 9: Re-record the updated minimum path and commit the fresh element into the sorted set
                        shortestDistances[neighborNode] = calculatedDistance;
                        set.add(new NodeDistancePair(neighborNode, calculatedDistance));
                    }
                }
            }

            // Return the finalized tracking array containing absolute shortest distances across all vertices
                    return shortestDistances;
                }
            }

            Time Complexity: When you combine all the graph processing steps, the total time complexity adds up to:
            O(V + E) + O(V log V) + O(E log V)

            For any standard connected graph where the number of edges is greater than or equal to the number of nodes (E >= V),
            the expression simplifies directly to: O((V + E) log V) — which is commonly written as O(E log V).

            Even though both approaches share the exact same theoretical complexity of O(E log V),
            a TreeSet is slower in practice compared to PQ, PriorityQueue wins on real-world speed
            because it avoids the heavy memory management and pointer-shuffling required to maintain a balanced tree.

     */

    // Why Dijkstra doesn't work with negative edge weights

    /*

        Dijkstra's Algorithm and Negative Cycles

        Your trace perfectly captures how Dijkstra's algorithm breaks down on an undirected graph with negative edge weights.
        It hits a true infinite loop condition.

        1. The Setup

        * There is an undirected edge between Node 0 and Node 1 with a weight of -2.
        * Because the graph is undirected, the adjacency list builds two separate paths:
        * Node 0 to Node 1 (weight: -2)
        * Node 1 to Node 0 (weight: -2)


        * This creates a negative cycle right between these two nodes (0 -> 1 -> 0) with a total round-trip cost of -4.

        2. Tracing the Steps

        * Start at Source (Node 0):
        * The shortest distances array is initialized to [0, INF].
        * The Priority Queue (PQ) holds {node: 0, distance: 0}.


        * Process Node 0:
        * The algorithm checks neighbor Node 1.
        * The calculated distance to Node 1 is 0 + (-2) = -2.
        * Since -2 is less than INF, the distances array updates to [0, -2] and {1, -2} is added to the PQ.


        * Process Node 1:
        * The algorithm checks neighbor Node 0.
        * The calculated distance to Node 0 is -2 + (-2) = -4.
        * Since -4 is less than 0, the distances array updates to [-4, -2] and {0, -4} is added to the PQ.


        * Process Node 0 again:
        * The algorithm checks neighbor Node 1.
        * The calculated distance to Node 1 is -4 + (-2) = -6.
        * Since -6 is less than -2, the distances array updates to [-4, -6] and {1, -6} is added to the PQ.



        3. Why the Code Gets Trapped
        Because the values keep dropping infinitely (0 -> -2 -> -4 -> -6 -> -8...),
        the core relaxation condition in the code will always evaluate to true:
        if (calculatedDistance < shortestDistances[neighborNode])

        Furthermore, because the newly pulled distance is always equal to the current minimum recorded value,
        the stale-check optimization will never trigger to stop it:
        if (currentDistance > shortestDistances[currentNode]) continue;

        As a result, the while loop never empties, the Priority Queue keeps filling up with smaller negative numbers,
        and the program gets stuck in an infinite loop until it runs out of memory.

     */

    // Print Shortest Path

    /* Question

    You are given a weighted undirected graph with n vertices numbered from 1 to n and m edges along with their weights.
    Find the shortest path between vertex 1 and vertex n. Each edge is given as {a, b, w}, denoting an edge between vertices a and b with weight w.
    If a path exists, return a list of integers where the first element is the total weight of the shortest path,
    and the remaining elements are the nodes along that path (from 1 to n). If no path exists, return a list containing only {-1}.

     */

    /*

        class Solution {
            public List<Integer> shortestPath(int n, int m, int edges[][]) {

                // 1. Initialize adjacency list for 1-based indexing (0 to n)
                List<List<int[]>> adjList = new ArrayList<>(); // Stores [neighborNode, edgeWeight]
                for (int i = 0; i <= n; i++) {
                    adjList.add(new ArrayList<>());
                }

                // 2. Build the undirected graph
                for (int[] edge : edges) {
                    int u = edge[0];
                    int v = edge[1];
                    int weight = edge[2];

                    adjList.get(u).add(new int[] {v, weight});
                    adjList.get(v).add(new int[] {u, weight});
                }

                // 3. Setup tracking arrays for Dijkstra
                int[] distances = new int[n + 1];
                int[] parent = new int[n + 1];
                Arrays.fill(distances, Integer.MAX_VALUE);

                for (int i = 1; i <= n; i++) {
                    parent[i] = i;
                }

                // 4. Initialize source node
                distances[1] = 0;
                PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1])); // [node, distance]
                minHeap.add(new int[]{1, 0});

                // 5. Run Dijkstra's Algorithm
                while (!minHeap.isEmpty()) {
                    int[] current = minHeap.remove();
                    int currNode = current[0];
                    int currDist = current[1];

                    // Skip stale node processing if a shorter path to this node was already found
                    if (distances[currNode] < currDist) {
                        continue;
                    }

                    // Relax adjacent edges
                    for (int[] neighbor : adjList.get(currNode)) {
                        int neighborNode = neighbor[0];
                        int edgeWeight = neighbor[1];
                        int newDist = currDist + edgeWeight;

                        if (distances[neighborNode] > newDist) {
                            distances[neighborNode] = newDist;
                            parent[neighborNode] = currNode;
                            minHeap.add(new int[]{neighborNode, newDist});
                        }
                    }
                }

                // 6. Path Reconstruction (Moved safely outside the while loop)
                // If the destination node 'n' was never reached, return [-1]
                if (distances[n] == Integer.MAX_VALUE) {
                    ArrayList<Integer> noPathResult = new ArrayList<>();
                    noPathResult.add(-1);
                    return noPathResult;
                }

                ArrayList<Integer> shortestPath = new ArrayList<>();
                int currentNode = n;

                // Trace back from destination to source using the parent tracker
                while (currentNode != parent[currentNode]) {
                    shortestPath.add(currentNode);
                    currentNode = parent[currentNode];
                }
                shortestPath.add(1); // Add the source node

                // Reverse to orient path from source -> destination
                Collections.reverse(shortestPath);

                return shortestPath;
            }
        }

     */

    // Shortest Distance in a Binary Maze

    /*

    class Solution {

	public int shortestPath(int[][] mat, int[] src, int[] dest) {

		// Edge Case: If source or destination is blocked, or they are the same
		if (mat[src[0]][src[1]] == 0 || mat[dest[0]][dest[1]] == 0)
			return - 1;
		if (src[0] == dest[0] && src[1] == dest[1])
			return 0;

		int numRows = mat.length;
		int numCols = mat[0].length;

		// Initialize distances matrix with infinity
		int minDistance[][] = new int[numRows][numCols];
		for (int row[] : minDistance) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}

		// BFS Queue storing coordinates: {row, col}
		// (We don't need to store distance inside the queue since minDistance tracks it)

		* WHY A STANDARD QUEUE INSTEAD OF A PRIORITY QUEUE (Dijkstra's)?
		* 1. Uniform Edge Weights: Every step from one cell to an adjacent cell has a constant weight of exactly 1.
		* 2. Monotonicity of BFS: In an unweighted graph, a standard First - In - First - Out (FIFO) queue naturally processes
		* nodes level by level. It first processes all nodes at distance 0, then all nodes at distance 1, then distance 2, etc.
		* 3. Efficiency: Since the distance values of elements added to the queue are monotonically increasing (e.g., 0, 1, 1, 2, 2, 2...),
		* a standard Queue gives us O(1) insertion and deletion. A Priority Queue would incur an unnecessary O(log N) overhead
		* just to sort elements that are already inherently sorted by the order of insertion.

    Queue<int[]> cellQueue = new ArrayDeque<>();

    // Initialize source
    minDistance[src[0]][src[1]] = 0;
		cellQueue.add(new int[] {src[0], src[1]});

    // Direction vectors for moving Up, Down, Left, Right
    int[] rowOffsets = {-1, 1, 0, 0};
    int[] colOffsets = {0, 0, -1, 1};

		while (!cellQueue.isEmpty()) {

        int[] currentCell = cellQueue.remove();
        int currRow = currentCell[0];
        int currCol = currentCell[1];
        int currDist = minDistance[currRow][currCol];

        // Explore all 4 adjacent directions
        for (int i = 0; i < 4; i++) {
            int nextRow = currRow + rowOffsets[i];
            int nextCol = currCol + colOffsets[i];
            int nextDist = currDist + 1;

            // Validate boundaries, traversability, and if a shorter path is found
            if (nextRow >= 0 && nextRow < numRows &&
                    nextCol >= 0 && nextCol < numCols &&
                    mat[nextRow][nextCol] == 1 &&
                    nextDist < minDistance[nextRow][nextCol]) {

                 * WHY THE EARLY RETURN ON FIRST HIT IS CORRECT:
                 * Because BFS expands outward uniformly like a ripple in water, it explores paths in order of length.
                 * The very first time we touch the destination cell, it is guaranteed to be via the shortest possible
                 * sequence of steps. Any subsequent discovery of the destination from a later queue element would
                 * represent a path that took more or equal steps. Returning instantly saves us from traversing
                 * the rest of the grid pointlessly.
                 *
                if (nextRow == dest[0] && nextCol == dest[1]) {
                    return nextDist;
                }

                minDistance[nextRow][nextCol] = nextDist;
                cellQueue.add(new int[] {nextRow, nextCol});
            }
        }
    }

    // Return -1 if the destination is unreachable after exhausting the queue
		return - 1;
}
}


        */

    // Shortest Path in Binary Matrix ( LC 1091 )

    /*

            class Solution {

            public int shortestPathBinaryMatrix(int[][] grid) {
                int n = grid.length;

                // Edge case: If starting or ending cell is blocked
                if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
                    return -1;
                }

                // Edge case: Single cell matrix that is clear
                if (n == 1) {
                    return 1;
                }

                // Stores the shortest path length to reach each cell
                int pathLength[][] = new int[n][n];
                for (int[] row : pathLength) {
                    Arrays.fill(row, Integer.MAX_VALUE);
                }

                // Queue stores coordinates: {row, col}
                Queue<int[]> cellQueue = new ArrayDeque<>();

                // Initialize the starting cell (0, 0)
                cellQueue.add(new int[]{0, 0});
                pathLength[0][0] = 1;

                while (!cellQueue.isEmpty()) {

                    int[] currentCell = cellQueue.remove();
                    int currRow = currentCell[0];
                    int currCol = currentCell[1];
                    int currLen = pathLength[currRow][currCol];

                    // Explore all 8 directions using nested offsets (-1, 0, 1)
                    for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
                        for (int colOffset = -1; colOffset <= 1; colOffset++) {

                            int nextRow = currRow + rowOffset;
                            int nextCol = currCol + colOffset;
                            int nextLen = currLen + 1;

                            if (nextRow >= 0 && nextRow < n &&
                                nextCol >= 0 && nextCol < n &&
                                grid[nextRow][nextCol] == 0 &&
                                nextLen < pathLength[nextRow][nextCol]) {

                                // Early return: First time hitting the bottom-right corner is the shortest path
                                if (nextRow == n - 1 && nextCol == n - 1) {
                                    return nextLen;
                                }

                                pathLength[nextRow][nextCol] = nextLen;
                                cellQueue.add(new int[]{nextRow, nextCol});
                            }
                        }
                    }
                }

                return -1;
            }
        }

     */

    // Path With Minimum Effort

    /*

            class Solution {

            public int minimumEffortPath(int[][] heights) {

                int totalRows = heights.length;
                int totalCols = heights[0].length;

                // Track the minimum effort required to reach each cell
                int minEffortToCell[][] = new int[totalRows][totalCols];
                for (int[] row : minEffortToCell) {
                    Arrays.fill(row, Integer.MAX_VALUE);
                }

                // Min-Heap stores: {row, col, effortSoFar} sorted by effortSoFar
                PriorityQueue<int[]> minHeap = new PriorityQueue<>((pairA, pairB) -> Integer.compare(pairA[2], pairB[2]));

                // Initialize the starting cell
                minHeap.add(new int[]{0, 0, 0});
                minEffortToCell[0][0] = 0;

                // Direction arrays for moving Right, Left, Down, Up
                int[] rowOffsets = {0, 0, 1, -1};
                int[] colOffsets = {1, -1, 0, 0};

                while (!minHeap.isEmpty()) {

                    int currentCell[] = minHeap.remove();
                    int currRow = currentCell[0];
                    int currCol = currentCell[1];
                    int currEffort = currentCell[2];

                    // Since we use a Min-Heap, the first time we reach the destination is guaranteed to be the minimum effort
                    if (currRow == totalRows - 1 && currCol == totalCols - 1) {
                        return currEffort;
                    }

                    // Explore all 4 neighboring directions
                    for (int i = 0; i < 4; i++) {
                        int nextRow = currRow + rowOffsets[i];
                        int nextCol = currCol + colOffsets[i];

                        // Check boundary conditions
                        if (nextRow >= 0 && nextRow < totalRows && nextCol >= 0 && nextCol < totalCols) {
                            // Effort to move to the next cell is the max of our current path's effort
                            // and the absolute difference between the current and next cell heights
                            int absoluteDifference = Math.abs(heights[currRow][currCol] - heights[nextRow][nextCol]);
                            int nextEffort = Math.max(currEffort, absoluteDifference);

                            // If we found a path with less effort to the next cell, update it and push to heap
                            if (nextEffort < minEffortToCell[nextRow][nextCol]) {
                                minEffortToCell[nextRow][nextCol] = nextEffort;
                                minHeap.add(new int[]{nextRow, nextCol, nextEffort});
                            }
                        }
                    }
                }

                return 0; // Fallback return to satisfy Java compiler
            }
        }

     */


}
