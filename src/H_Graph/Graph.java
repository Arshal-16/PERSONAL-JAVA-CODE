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

    // Cheapest Flights Within K Stops

    /* Using Dijkstra


         * ============================================================================
         * WHY STANDARD DIJKSTRA FAILS HERE:
         * ============================================================================
         * 1. Standard Dijkstra relies on a 1D 'dist[]' array to track the absolute
         * cheapest cost to reach each node. Once a node is popped from the min-heap,
         * it assumes that path is permanently optimal and discards any subsequent
         * paths to that same node that have a higher cost.
         * * 2. However, this problem introduces a second constraint: at most 'K' stops.
         * A path that is cheaper might consume too many stops, blocking a later,
         * slightly more expensive path that has plenty of stops left to reach the
         * actual destination.
         * * ============================================================================
         * THE SOLUTION (2D DIJKSTRA):
         * ============================================================================
         * Instead of tracking the absolute minimum cost globally per city, we track the
         * minimum cost *relative to the number of stops taken* using a 2D array:
         * 'minCost[city][stops]'.
         * * This isolates paths based on their length. A more expensive path is no longer
         * blindly discarded by a cheaper path unless that cheaper path *also* used the
         * exact same number of flights. This allows the Min-Heap to naturally find the
         * cheapest overall valid route without cutting off viable paths prematurely.
         * ============================================================================


            class Solution {

                private static class FlightEdge {
                    int targetCity;
                    int price;

                    FlightEdge(int targetCity, int price) {
                        this.targetCity = targetCity;
                        this.price = price;
                    }
                }

                private static class TravelState implements Comparable<TravelState> {
                    int currentCity;
                    int totalCost;
                    int stopsCount;

                    TravelState(int currentCity, int totalCost, int stopsCount) {
                        this.currentCity = currentCity;
                        this.totalCost = totalCost;
                        this.stopsCount = stopsCount;
                    }

                    @Override
                    public int compareTo(TravelState other) {
                        return Integer.compare(this.totalCost, other.totalCost);
                    }
                }

                public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
                    // 1. Build the Adjacency List
                    List<List<FlightEdge>> graph = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        graph.add(new ArrayList<>());
                    }

                    for (int[] flight : flights) {
                        graph.get(flight[0]).add(new FlightEdge(flight[1], flight[2]));
                    }

                    // 2. Initialize 2D tracking array: minCost[city][stops]
                    // stops can range from 0 to k + 1 (where k + 1 means k intermediate stops + 1 final destination flight)
                    int[][] minCost = new int[n][k + 2];
                    for (int[] row : minCost) {
                        Arrays.fill(row, Integer.MAX_VALUE);
                    }

                    PriorityQueue<TravelState> minHeap = new PriorityQueue<>();

                    // Base case: 0 cost and 0 stops to be at the source
                    minHeap.add(new TravelState(src, 0, 0));
                    minCost[src][0] = 0;

                    // 3. Process routes
                    while (!minHeap.isEmpty()) {
                        TravelState current = minHeap.poll();

                        int currCity = current.currentCity;
                        int currCost = current.totalCost;
                        int currStops = current.stopsCount;

                        // Since it's a Min-Heap sorted by cost, the first time we pop
                        // the destination, it is guaranteed to be the cheapest valid route.
                        if (currCity == dst) {
                            return currCost;
                        }

                        // If we have already taken k + 1 flights (edges), we cannot go further.
                        if (currStops > k) {
                            continue;
                        }

                        // Optimize: If we found a cheaper way to reach this city with the exact same
                        // number of stops before this state popped out of the heap, skip it.
                        if (currCost > minCost[currCity][currStops]) {
                            continue;
                        }

                        // Explore neighbors
                        for (FlightEdge edge : graph.get(currCity)) {
                            int nextCity = edge.targetCity;
                            int flightPrice = edge.price;
                            int nextCost = currCost + flightPrice;
                            int nextStops = currStops + 1;

                            // CRUCIAL: Only push to heap if this cost is strictly better than
                            // any previously recorded cost for reaching 'nextCity' with this EXACT number of stops.
                            if (nextCost < minCost[nextCity][nextStops]) {
                                minCost[nextCity][nextStops] = nextCost;
                                minHeap.add(new TravelState(nextCity, nextCost, nextStops));
                            }
                        }
                    }

                    return -1;
                }
            }

         * ============================================================================
         * TIME COMPLEXITY DERIVATION (DEEP ANALYSIS):
         * ============================================================================
         * Let V = Number of cities (n), E = Number of flights (flights.length),
         * and K = Maximum allowed intermediate stops.
         * * 1. STATE SPACE RESTRUCTURING:
         * Because we must respect the stop constraint, a "node" in our search is no
         * longer just a city. It is now a unique tuple: (City, Stops Used).
         * Since 'Stops Used' ranges from 0 to K + 1, the total number of unique
         * explorable states is bounded by: O(K * V).
         * * 2. PRIORITY QUEUE (HEAP) OPERATION COST:
         * The maximum number of items residing in our min-heap at any given time
         * is bounded by the total number of unique states: O(K * V).
         * Therefore, the time complexity for any single push (add) or pop (poll)
         * operation is: O(log(K * V)).
         * * 3. TOTAL WORK BREAKDOWN:
         * Total Time = Graph Construction + Heap Extraction + Edge Relaxation
         * * A. Graph Construction:
         * Iterating over all flights to build the adjacency list takes: O(V + E).
         * * B. Heap Extractions (Pops):
         * Each unique (City, Stops Used) state is processed and popped at most
         * once due to our 'currCost > minCost[currCity][currStops]' pruning.
         * Work done: O(K * V * log(K * V)).
         * * C. Edge Relaxations (Pushes):
         * In the worst-case dense graph, every flight connection (E) can potentially
         * be evaluated across each of the valid stop layers.
         * Work done: O(E * log(K * V)).
         * * 4. FINAL MATHEMATICAL SUM & SIMPLIFICATION:
         * Total Time = O(V + E) + O(K * V * log(K * V)) + O(E * log(K * V))
         * * Grouping terms and simplifying to the tight upper bound yields:
         * 👉 TIME COMPLEXITY: O(K * V + E * log(K * V))
         * * Note: If K is very small, this converges to standard Dijkstra O(E * log V).
         * If K approaches V, the runtime scales up heavily as the search
         * space multiplies across all available stop layers.
         * ============================================================================


     */

    /* BFS / BELLMAN FORD ALGO ( OPTIMAL SOLN FOR THIS QSN )

            class Solution {

            // Represents a flight destination and its ticket price
            private static class FlightEdge {
                int targetCity;
                int price;

                FlightEdge(int targetCity, int price) {
                    this.targetCity = targetCity;
                    this.price = price;
                }
            }

            // Represents the structural state of our path tracking inside the BFS queue
            private static class TravelState {
                int currentCity;
                int accumulatedCost;
                int stopsCount; // Tracking intermediate stops used so far

                TravelState(int currentCity, int accumulatedCost, int stopsCount) {
                    this.currentCity = currentCity;
                    this.accumulatedCost = accumulatedCost;
                    this.stopsCount = stopsCount;
                }
            }

            public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

                // 1. Build the Graph Adjacency List
                List<List<FlightEdge>> graph = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    graph.add(new ArrayList<>());
                }

                for (int[] flight : flights) {
                    int fromCity = flight[0];
                    int toCity = flight[1];
                    int price = flight[2];
                    graph.get(fromCity).add(new FlightEdge(toCity, price));
                }

                // 2. Track globally optimal costs per city
                int[] minCostToCity = new int[n];
                Arrays.fill(minCostToCity, Integer.MAX_VALUE);
                minCostToCity[src] = 0;

                // 3. Setup standard FIFO Queue for Level-Order BFS
                Queue<TravelState> queue = new LinkedList<>();

                // Base state: Standing at 'src' with 0 cost and 0 stops used
                queue.add(new TravelState(src, 0, 0));

                // 4. Execute layer-by-layer BFS traversal

                 * Note on standard FIFO Queue Ordering:
                 * A standard Queue processes elements strictly by the order they are inserted (First-In, First-Out).
                 * Because taking a flight to a neighbor transitions us to the next layer of stops, the queue
                 * naturally groups and processes paths level-by-level based on the number of intermediate stops:
                 * * Queue Sequence: [0 stops paths] -> [1 stop paths] -> [2 stops paths] -> ...
                 * Example: [src (0 stops)] -> [cityA (1 stop), cityB (1 stop)] -> [cityC (2 stops), cityD (2 stops)] -> ...
                 * * This structural guarantee ensures that a path with fewer intermediate stops is always evaluated
                 * before a path with more stops, eliminating the complex pruning bugs found in standard Dijkstra.

                while (!queue.isEmpty()) {
                TravelState current = queue.remove();

                int currCity = current.currentCity;
                int currCost = current.accumulatedCost;
                int currStops = current.stopsCount;

                // If this city is already past our intermediate stop limit,
                // it cannot be used to branch out to any more neighbors.
                if (currStops > k) {
                    continue;
                }

                // Explore all routes expanding from the current city
                for (FlightEdge flight : graph.get(currCity)) {
                    int nextCity = flight.targetCity;
                    int ticketPrice = flight.price;
                    int nextTotalCost = currCost + ticketPrice;

                    // CRUCIAL CONDITION:
                    // Because standard BFS processes paths level-by-level (fewer stops processed first),
                    // a deeper path with MORE stops is only valid if it yields a strictly CHEAPER cost
                    // than what we've previously recorded.
                    if (nextTotalCost < minCostToCity[nextCity]) {

                        minCostToCity[nextCity] = nextTotalCost;
                        queue.add(new TravelState(nextCity, nextTotalCost, currStops + 1));

                    }
                }
            }

            // 5. Final validation and extraction
                return minCostToCity[dst] == Integer.MAX_VALUE ? -1 : minCostToCity[dst];
        }
        }


         * ============================================================================
         * COMPLEXITY ANALYSIS DERIVATION (BFS APPROACH):
         * ============================================================================
         * Let V = Number of cities (n), E = Number of flights (flights.length),
         * and K = Maximum allowed intermediate stops.
         * * 1. TIME COMPLEXITY: O(V + K * E)
         * - Graph Construction: Iterating over all flights to build the adjacency list
         * takes linear time: O(V + E).
         * - BFS Traversal: The algorithm processes the graph layer-by-layer (by number
         * of stops) using a standard FIFO Queue.
         * - Because of the pruning condition 'if (currStops > k) continue;', the search
         * tree has a strict maximum depth of K + 1 levels. The algorithm completely
         * halts any path deeper than this.
         * - In the worst-case scenario (such as a highly dense or fully-connected graph),
         * every single flight edge 'E' might be traversed, checked, and relaxed at
         * every single level of stops because paths can keep getting cheaper.
         * - Multiplying the maximum number of layers by the maximum work done per layer
         * gives: Total Operations = (K + 1) levels * E edges per level.
         * - Combining Graph Construction and BFS yields: O(V + E) + O(K * E), which
         * simplifies to the final asymptotic tight upper bound: 👉 O(V + K * E)
         * * 2. SPACE COMPLEXITY: O(V + E)
         * - Adjacency List: Requires O(V + E) space to store all 'V' cities and 'E'
         * flight connections.
         * - Distance Array (minCostToCity): Requires a simple 1D array of O(V) space to
         * store the global minimum cost for each city (unlike Dijkstra which requires a
         * 2D O(K * V) matrix).
         * - BFS Queue: In the worst case, the queue stores paths layer-by-layer. A single
         * layer will never hold more than O(V) unique city states at any given time.
         * - Combining these terms: O(V + E) + O(V) + O(V) = 👉 O(V + E)
         * ============================================================================


     */

    // Number of Ways to Arrive at Destination

    /*

            class Solution {

            // Static nested class to store node information for the PriorityQueue
            static class Node implements Comparable<Node> {
                int id;
                long time;

                Node(int id, long time) {
                    this.id = id;
                    this.time = time;
                }

                @Override
                public int compareTo(Node other) {
                    return Long.compare(this.time, other.time);
                }
            }

            public int countPaths(int n, int[][] roads) {
                final int MOD = 1_000_000_007;

                // 1. Build Adjacency List
                List<List<int[]>> adj = new ArrayList<>();
                for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
                for (int[] road : roads) {
                    adj.get(road[0]).add(new int[]{road[1], road[2]});
                    adj.get(road[1]).add(new int[]{road[0], road[2]});
                }

                // 2. Initialize tracking arrays
                // minTime stores the shortest time found to each intersection
                long minTime[] = new long[n];
                Arrays.fill(minTime, Long.MAX_VALUE);

                // numWays stores the number of ways to reach the intersection in minTime
                long numWays[] = new long[n];

                minTime[0] = 0;
                numWays[0] = 1;

                // 3. Dijkstra's Algorithm using Min-Heap
                PriorityQueue<Node> pq = new PriorityQueue<>();
                pq.add(new Node(0, 0));

                while (!pq.isEmpty()) {

                    Node current = pq.poll();
                    int u = current.id;
                    long timeU = current.time;

                    // Optimization: Skip if we already processed a shorter path to this node
                    if (timeU > minTime[u]) continue;

                    for (int edge[] : adj.get(u)) {
                        int v = edge[0];
                        int weight = edge[1];

                        // Case 1: Found a strictly shorter path
                        if (minTime[u] + weight < minTime[v]) {
                            minTime[v] = minTime[u] + weight;
                            numWays[v] = numWays[u];
                            pq.add(new Node(v, minTime[v]));
                        }
                        // Case 2: Found another path with the same shortest time
                        else if (minTime[u] + weight == minTime[v]) {
                            numWays[v] = (numWays[v] + numWays[u]) % MOD;
                        }
                    }
                }

                return (int) numWays[n - 1];
            }
        }

     */

    // Minimum Multiplications to reach End

    /*

            class Solution {

            public int minSteps(int[] arr, int start, int end) {

                // Edge case: If we are already at the destination
                if (start == end) {
                    return 0;
                }

                final int MOD = 1000;

                // Array to store the minimum steps to reach each number (0 to 999)
                // Initialized to -1 to represent unvisited numbers
                int minSteps[] = new int[MOD];
                Arrays.fill(minSteps, -1);
                minSteps[start] = 0;

                Queue<Integer> queue = new LinkedList<>();
                queue.add(start);

                // BFS guarantees we process nodes level by level.
                // The queue will naturally order the processing as:
                // [0 steps, 0 steps...] -> [1 step, 1 step...] -> [2 steps...]
                // Because of this strict order, the very first time we calculate
                // a 'nextNum', it is guaranteed to be via the minimum steps required.

                while (!queue.isEmpty()) {

                    int currentNum = queue.remove();
                    int currentSteps = minSteps[currentNum];

                    // Try multiplying the current number by every element in the array
                    for (int multiplier : arr) {

                        int nextNum = (currentNum * multiplier) % MOD;

                        // If this number hasn't been visited yet, we found the shortest path to it
                        if (minSteps[nextNum] == -1) {
                            minSteps[nextNum] = currentSteps + 1;

                            // If we reached our target, we can return immediately
                            if (nextNum == end) {
                                return minSteps[nextNum];
                            }

                            queue.add(nextNum);
                        }
                    }
                }

                // Target cannot be reached
                return -1;
            }
        }

     */

    // Bellman Ford Algorithm

    // Concept:

    /*

The Bellman-Ford Algorithm: Complete Overview

1. Core Purpose & Advantages
Single-Source Shortest Path (SSSP): It finds the shortest distance from one source node to all other nodes in the graph.
Beats Dijkstra on Negative Weights: Dijkstra's algorithm acts greedily and fails when negative edge weights or negative cycles are present.
Bellman-Ford handles negative weights flawlessly.
Negative Cycle Detection: It doesn't just fail silently; it explicitly detects if a graph contains a negative weight cycle.
2. Graph Prerequisites & Representation
Directed Graphs Only: Bellman-Ford strictly requires a directed graph.
Handling Undirected Graphs: If given an undirected graph with negative weights,
you must convert it into a directed graph by duplicating every edge in both directions (e.g., an edge A - B becomes A → B and B → A).
Best Data Structure: Instead of an adjacency list, an Edge List is highly recommended.
Representing the graph as a simple array/list of [src, dest, wt] makes iterating through all edges much cleaner.
3. Initialization Strategy
Create a dist array of size $N$ (number of nodes) and fill it with infinity (∞), representing that they are initially unreachable.
Set the distance of your starting node to zero: dist[src] = 0.
4. The Core Mechanism: Edge Relaxation
"Relaxing" an edge means checking if you can find a shorter path to a destination node by going through a specific source node.
The Formula: if (dist[u] + wt < dist[v]) { dist[v] = dist[u] + wt }
You must sequentially relax all edges in the graph. The edges can be processed in absolutely any order.
5. The N-1 Rule
You must run the edge relaxation process on all edges exactly N-1 times.
The "Why": In a graph of N nodes, the absolute longest simple path (a path with no cycles) will contain exactly N-1 edges.
Relaxing all edges N-1 times guarantees that the shortest path information has fully cascaded through every possible valid route in the graph.
6. Detecting Negative Weight Cycles
After completing your N-1 iterations, the shortest paths should be mathematically locked in.
The Trap: Run the relaxation loop for one final, N-th iteration.
If dist[v] gets updated again during this N-th run, it proves that a shorter path was found by infinitely looping through a cycle.
You can instantly flag the graph as containing a negative weight cycle.

Bonus DSA Addition: Complexity Analysis
Time Complexity: O(V x E) — You iterate through all edges (E) exactly Vertices minus one times (V-1).
This makes it slower than Dijkstra's O(E log V), which is why we only use Bellman-Ford when negative weights are involved.
Space Complexity: O(V) — You only need a 1D array of size V to store the shortest distances.

     */

    /*

            class Solution {

            public int[] bellmanFord(int V, int[][] edges, int src) {

                // Step 1: Initialization
                // Create the distance array and fill it with 10^8 (infinity proxy)
                int distances[] = new int[V];
                int infinity = (int) 1e8;

                Arrays.fill(distances, infinity);

                // Distance to the source node is always 0
                distances[src] = 0;

                // Step 2: The N-1 Rule (Edge Relaxation)
                // Relax all edges V - 1 times to guarantee shortest paths
                for (int i = 0; i < V - 1; i++) {
                    for (int edge[] : edges) {
                        int u = edge[0];
                        int v = edge[1];
                        int weight = edge[2];

                        // If node 'u' has been reached, check if the path through 'u' is shorter
                        if (distances[u] != infinity && distances[u] + weight < distances[v]) {
                            distances[v] = distances[u] + weight;
                        }
                    }
                }

                // Step 3: Detecting Negative Weight Cycles
                // Run the relaxation logic one final (N-th) time
                for (int edge[] : edges) {
                    int u = edge[0];
                    int v = edge[1];
                    int weight = edge[2];

                    // If an edge can STILL be relaxed, we are trapped in a negative cycle
                    if (distances[u] != infinity && distances[u] + weight < distances[v]) {
                        // Return an array with -1 to indicate failure to compute reliable paths
                        return new int[]{-1};
                    }
                }

                // Step 4: Return the finalized shortest distances
                return distances;
            }
        }

     */

    // Floyd Warshall Algorithm

    // Concept

    /*

        The Floyd-Warshall Algorithm: Complete Overview

        1. Core Purpose and Advantages
        Multi-Source Shortest Path: Unlike Dijkstra or Bellman-Ford which work from a single source,
        Floyd-Warshall is an All-Pairs Shortest Path algorithm. It finds the shortest distance from every single node to every other node in the graph.
        Negative Cycle Detection: It can successfully help detect negative weight cycles in a graph.
        If the distance from any node to itself becomes negative, a negative cycle exists.
        2. Graph Prerequisites and Representation
        Adjacency Matrix Representation: The graph must be stored in a 2D grid format of size N x N, where N is the number of nodes.
        Handling Undirected Graphs: If given an undirected graph,
        you must convert it into a directed graph by duplicating the edge in both opposite directions.
        Handling No Edges: If there is no direct edge between two nodes i and j,
        fill that position in the matrix with infinity to signify that they are initially unreachable.
        3. Algorithm Intuition and Dynamic Programming Mechanics
        Brute Force on All Combinations: The algorithm is essentially a structured brute force that tries all possible combinations of paths.
        It relies on three nested loops to systematically compute paths.
        Dynamic Programming Nature: It utilizes dynamic programming because it updates distances incrementally by building upon previously computed optimal sub-paths.
        The Core State Transition: To find the shortest path from a node i to a node j,
        the algorithm calculates the minimum of its current known distance and the distance obtained by traveling from i to j through an intermediate node k.
        The Formula: matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j])
        4. Step-by-Step Execution Strategy
        Initialize the DP matrix of size N x N with infinity.
        Set the base condition where the distance from a node to itself is zero: matrix[i][i] = 0.
        Populate the matrix with the direct edge weights given in the input graph.
        Run three nested loops to find the minimum distance from i to j via every possible node k.
        The loop structure must strictly place the intermediate node k as the outermost loop,
        followed by the source node i and destination node j.
        As a result of this comprehensive evaluation, the matrix will hold the true shortest paths between any two nodes in the graph.
        5. Optimization and Row-Column Insight
        When evaluating paths via an intermediate node k,
        the entire k-th row (matrix[k][j]) and the entire k-th column (matrix[i][k]) remain completely unchanged from the previous iteration.
        This serves as a useful conceptual shortcut when tracing the algorithm manually.
        6. Complexity Analysis
        Time Complexity: O(V^3) - Driven by the three nested loops running from 0 to V-1.
        While slow for sparse graphs, it is highly efficient and straightforward for dense graphs when all-pairs distances are needed.
        Space Complexity: O(V^2) - Required to maintain the 2D adjacency matrix to track distances dynamically.

        The Floyd-Warshall Algorithm: Negative Cycle Detection

        How to detect a negative cycle
        If a graph contains a cycle whose total edge weight sum is negative, it is a negative weight cycle.
        The Floyd-Warshall algorithm can easily identify this condition
        by checking the main diagonal of the distance matrix after the main loops finish executing.

        The Logic
        In any valid graph without negative cycles, the shortest distance from any node i to itself should always be zero (matrix[i][i] = 0).
        However, if there is a negative cycle, a path can travel through the cycle and return to node i with a total cost that is less than zero.

        Detection Step
        After running the three nested loops for all intermediate nodes, iterate through all vertices from 0 to N-1 and check the diagonal elements.

        for (int i = 0; i < N; i++) {
        if (matrix[i][i] < 0) {
        System.out.println("Negative cycle exists");
        }
        }

        Example Scenario
        Consider a cycle with three nodes: 0, 1, and 2.
        The edge weights are:
        From 0 to 1 with a weight of -2
        From 1 to 2 with a weight of -3
        From 2 to 0 with a weight of +2

        Calculating the path cost to complete the cycle yields:
        -2 + -3 + 2 = -3

        Because the overall sum of the cycle path is -3,
        the algorithm will dynamically update the self-distance matrix[0][0] to a value below zero during its execution.
        Identifying any negative value along the main diagonal accurately flags the presence of a negative cycle.

        Alternative for Graphs with Non-Negative Weights
        If a graph contains absolutely no negative edge weights, using the Floyd-Warshall algorithm is usually inefficient.
        Instead, you can run Dijkstra's algorithm independently from every single vertex as the source.

        Since a single run of Dijkstra's algorithm using a min-heap priority queue takes O(E log V) time,
        running it for all V vertices results in a total time complexity of O(V x E log V).
        In a sparse graph where the number of edges E is much smaller than V squared,
        this approach is significantly faster than Floyd-Warshall's rigid O(V cubed) runtime.

     */

    /*

            class Solution {

            public void floydWarshall(int[][] matrix) {

                int n = matrix.length;
                int infinity = (int) 1e9; // Standard high threshold to prevent overflow on additions

                // Step 1: Pre-processing and Initialization
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        // If the input indicates unreachable paths via -1, change to infinity proxy
                        if (matrix[i][j] == -1) {
                            matrix[i][j] = infinity;
                        }
                        // Distance from a node to itself is always 0
                        if (i == j) {
                            matrix[i][j] = 0;
                        }
                    }
                }

                // Step 2: The Core 3-Loop Brute Force
                // Outer loop 'via' represents the intermediate node we try to route through
                for (int via = 0; via < n; via++) {
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            // Check if paths through the intermediate node are valid and shorter
                            if (matrix[i][via] != infinity && matrix[via][j] != infinity) {
                                matrix[i][j] = Math.min(matrix[i][j], matrix[i][via] + matrix[via][j]);
                            }
                        }
                    }
                }

                // Step 3: Check for Negative Weight Cycles (Optional/Safety Step)
                // If any diagonal element drops below zero, a negative cycle exists
                for (int i = 0; i < n; i++) {
                    if (matrix[i][i] < 0) {
                        // Handle negative cycle scenario based on specific problem requirements
                        // e.g., flag it, or clear data. For now, we note its presence.
                    }
                }

                // Step 4: Post-processing back to standard format
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (matrix[i][j] == infinity) {
                            matrix[i][j] = -1;
                        }
                    }
                }
            }
        }

     */

    // Minimum Spanning Tree

    // Theory

    /*

        Minimum Spanning Trees: Complete Overview

        1. Core Definition of a Spanning Tree
        Given an undirected, weighted graph with N nodes and M edges, a spanning tree is a subset of the graph that connects all N nodes together using exactly N-1 edges.
        Every single node in the graph must be reachable from every other node within this tree structure.
        2. Defining a Tree in Graph Theory
        By mathematical definition, a tree is an acyclic connected graph. This means that a valid spanning tree must be fully connected,
        and it cannot contain any cycles. If a cycle forms, it is no longer a tree.
        3. The Minimum Spanning Tree (MST)
        A single graph can have multiple valid spanning trees depending on which edges you choose to include.
        The spanning tree that has the minimum possible total edge weight sum among all available spanning trees is called the Minimum Spanning Tree.
        4. Algorithmic Approaches
        There are two primary greedy algorithms used to construct a Minimum Spanning Tree:
        Prims Algorithm: This approach grows the MST outwardly from a single starting node.
        It maintains a pool of available edges connected to the current tree and greedily picks the minimum weight edge that connects an unvisited node to the tree.
        Kruskals Algorithm: This approach sorts all available edges of the graph by weight in ascending order.
        It greedily picks the absolute smallest edge and adds it to the MST,
        using a Disjoint Set Union (DSU) data structure to ensure that adding the edge does not accidentally close a cycle.
        5. Critical Missing Points to Remember
        If the graph is disconnected (made of separate components), it is impossible to form a single Spanning Tree.
        In that scenario, the algorithm will find a Minimum Spanning Forest instead.
        If all edge weights in the graph are completely unique, the graph is guaranteed to have exactly one unique Minimum Spanning Tree.
        If there are duplicate edge weights, multiple distinct MSTs can exist with the same total minimum cost.

     */

    // Prim's Algorithm

    /*


        class Solution {

            // Represents a basic neighbor node connection within the adjacency list
            static class NodeConnection {
                int targetNode;
                int edgeWeight;

                NodeConnection(int targetNode, int edgeWeight) {
                    this.targetNode = targetNode;
                    this.edgeWeight = edgeWeight;
                }
            }

            // Represents the dynamic state of an edge inside our Min-Heap structure
            static class HeapState implements Comparable<HeapState> {
                int weightToNode;
                int currentNode;
                int parentNode;

                HeapState(int weightToNode, int currentNode, int parentNode) {
                    this.weightToNode = weightToNode;
                    this.currentNode = currentNode;
                    this.parentNode = parentNode;
                }

                // Ensure the PriorityQueue always pops the smallest edge weight first
                @Override
                public int compareTo(HeapState other) {
                    return Integer.compare(this.weightToNode, other.weightToNode);
                }
            }

            public int spanningTree(int V, int[][] edges) {

                // Step 1: Build the Adjacency List
                List<List<NodeConnection>> adjacencyList = new ArrayList<>();
                for (int i = 0; i < V; i++) {
                    adjacencyList.add(new ArrayList<>());
                }

                for (int[] edge : edges) {
                    int source = edge[0];
                    int destination = edge[1];
                    int weight = edge[2];

                    adjacencyList.get(source).add(new NodeConnection(destination, weight));
                    adjacencyList.get(destination).add(new NodeConnection(source, weight));
                }

                // Tracking structures for our Minimum Spanning Tree
                List<int[]> mstEdges = new ArrayList<>(); // To track actual edges [node, parent] if needed
                int totalMstWeight = 0;
                boolean inMinimumSpanningTree[] = new boolean[V];

                // Priority Queue to always fetch the minimum weight cut edge
                PriorityQueue<HeapState> minHeap = new PriorityQueue<>();

                // Seed the heap with an initial arbitrary starting node (Wt 0, Current 0, Parent -1)
                minHeap.add(new HeapState(0, 0, -1));

                // Step 2: The Core Prim's Loop
                while (!minHeap.isEmpty()) {

                    HeapState current = minHeap.remove();

                    int currentWeight = current.weightToNode;
                    int currentNode = current.currentNode;
                    int parentNode = current.parentNode;

                    // If the node is already absorbed into our growing MST structure, skip it
                    if (inMinimumSpanningTree[currentNode]) {
                        continue;
                    }

                    // Absorb the node into the MST structure
                    inMinimumSpanningTree[currentNode] = true;
                    totalMstWeight += currentWeight;

                    // If it isn't the root seed node, record the edge
                    if (parentNode != -1) {
                        mstEdges.add(new int[]{currentNode, parentNode});
                    }

                    // Step 3: Explore neighbors and add valid cut edges to the heap
                    for (NodeConnection connection : adjacencyList.get(currentNode)) {

                        int neighborNode = connection.targetNode;
                        int neighborEdgeWeight = connection.edgeWeight;

                        // Only push edges leading to nodes not yet part of the MST
                        if (!inMinimumSpanningTree[neighborNode]) {

                        The Importance of Delayed Visited Marking :
                        In Prim's algorithm, a node must only be marked as visited (or added to the MST) when it is extracted out of the priority queue,
                        not when it is pushed into the queue.

                        Because the graph can have multiple paths to the same node, a larger weight edge might get pushed into the priority queue first.
                        If you mark the node as visited right when it is pushed,
                        you would block the algorithm from ever accepting a smaller weight edge to that same node that gets discovered later.
                        By waiting until the node actually pops out of the min-heap,
                        you guarantee that the edge being processed is the absolute smallest possible connection to that node.

                            minHeap.add(new HeapState(neighborEdgeWeight, neighborNode, currentNode));
                        }
                    }
                }

                return totalMstWeight;
            }
        }

        Optimizing Prim's Algorithm for Total Weight Only
        If a coding problem only asks you to find the total minimum weight of the Spanning Tree (the sum) and does not
        require you to track or print the actual parent-child edges that form the tree, you can optimize your code and save memory.

        You can entirely remove the parent tracking variable from your data structures.
        This means your Heap State class only needs to track two variables instead of three: the node index and the minimum edge weight to reach it.
        You can also completely remove the conditional parent check loop inside the algorithm's main iteration.


        Time Complexity: E*logE

        Heap Operations Complexity in Prim's Algorithm

        1. Insertion Performance
        In the absolute worst-case scenario, such as a complete graph where every node connects to every other node,
        every single edge in the graph can be pushed into the priority queue.
        Inserting an individual edge into a min-heap takes logarithmic time relative to the current size of the heap, resulting in a runtime of O(log E).
        Because there are E edges total to evaluate, the total time spent pushing all elements into the heap accumulates to O(E log E).
        2. Extraction Performance
        The maximum number of extraction operations performed on the min-heap is directly equal to the total number of edges that were inserted.
        Removing the minimum-weight edge from the heap also requires logarithmic time per operation.
        Therefore, processing and extracting these elements across the entire execution of the algorithm takes O(E log E) time.



     */

    // Disjoint Set

    // Concept

    /*

        ================================================================================
         DISJOINT SET UNION (DSU) / UNION-FIND: ARCHITECTURAL & THEORETICAL BLUEPRINT
        ================================================================================

        1. CORE OBJECTIVE & ADVANTAGE OVER TRADITIONAL GRAPH TRAVERSALS
        --------------------------------------------------------------------------------
        - Purpose: Efficiently manages and queries partition states of a graph to check
          if two nodes reside in the same connected component.
        - Performance Disparity:
          * Traversal (BFS/DFS): Determining dynamic connectivity requires traversing the
            graph structure, incurring a time complexity of O(V + E) per operational query.
          * Disjoint Set: By managing direct parent pointers, connectivity questions are
            resolved in near-constant time, O(1), bypassing full path discovery.

        2. OPERATIONAL PILLARS: FIND & UNION
        --------------------------------------------------------------------------------
        - findUltimateParent(node): Traverses parent chains upward to locate the absolute
          representative (root) of a component. If two distinct nodes yield identical
          ultimate parents, they belong to the same connected component.
        - union(u, v): Merges two disjoint components if they are disconnected. To protect
          the tree structures from degrading into linear chains, tracking matrices are
          introduced via two methodologies: Rank or Size.

        3. UNION BY RANK MECHANICS
        --------------------------------------------------------------------------------
        - State Arrays: `componentRank[]` and `parentNodes[]`.
        - Initialization: Ranks default to 0 (approximate depth level). Every node points
          to itself as its own parent, establishing independent, single-node components.
        - Execution Logic:
          1. Resolve the ultimate parents of nodes 'u' and 'v' (`ult_u`, `ult_v`).
          2. Evaluate and compare the depths (ranks) of `ult_u` and `ult_v`.
          3. Graft the tree with the smaller rank beneath the root of the tree with the
             larger rank.
        - Rank Mutability Policies:
          * Asymmetrical Ranks: Grafting a smaller tree under a larger tree creates no
            change in the maximum depth of the parent component. No rank modification occurs.
          * Identical Ranks: Grafting either tree under the other increases the total
            depth of the combined component by exactly 1 level. The new root's rank increments.

        4. MATHEMÁTICAL INTUITION: THE BIAS TO CONNECT SMALLER TO LARGER
        --------------------------------------------------------------------------------
        - Lowering Traversal Costs: When merging a smaller tree under a larger tree, the
          distance to the root increases by 1 level *only* for the nodes residing in the
          smaller component.
        - The Counterfactual: Merging a larger tree under a smaller tree would shift the
          distance to the root by +1 for the absolute majority of nodes in the system,
          rapidly escalating future `find` lookup paths.

        5. THE TRANSITION FROM RANK TO SIZE
        --------------------------------------------------------------------------------
        - Structural Change: Path compression actively flattens the tree structure during
          every `find` query. Consequently, the literal depth values stored in `rank`
          lose accurate geometric meaning.
        - Size Alternative: Rather than tracking depth approximations, tracking the
          absolute element count (`componentSize[]`) achieves the same optimization goal:
          attaching the smaller entity to the larger entity.
        - Initialization: Every standalone node starts with an initial component size of 1.
        - Size Mutability Policies:
          * Grafting: The absolute parent of the smaller component is linked directly to
            the root of the larger component.
          * Aggregation: The size representation of the chosen root must absorb the size of
            the child component (`size[larger] += size[smaller]`). If component sizes
            are equal, the selection is arbitrary, and aggregation behaves identically.

        6. OPTIMIZATION STRATEGIES & TIME COMPLEXITY JUSTIFICATION
        --------------------------------------------------------------------------------
        - Path Compression: During execution of `findUltimateParent`, the recursive call
          dynamically rewires every visited node's parent pointer to point directly to the
          ultimate root parent. This flattens the depth profile of the tree.
        - Algorithmic Bounds: Combining Path Compression with either Union by Rank or
          Union by Size suppresses lookup degradation. The resulting amortized time
          complexity per operation is capped at O(4α), where α represents the Inverse
          Ackermann function. Given that α grows so exceptionally slowly that it never
          exceeds 4 for any practical universe size ($N < 2^{2^{2^{65536}}}$), operations
          execute in effective constant time, O(1).

        ================================================================================
         CODE IMPLEMENTATION JUSTIFICATIONS & SANITIZATION POLICIES
        ================================================================================
        - Encapsulation Strategy (`private final`): Declaring array variables as `final`
          guarantees pointer-reference immutability. It forces compile-time security
          against unintended runtime structural reinstantiation, while allowing internal state
          mutability of individual elements.
        - Index Neutrality Strategy (`n + 1`): Internal buffer allocations scale to `n + 1`.
          This acts as a universal buffer layer that accepts 0-based graphs (indices 0 to N-1)
          and 1-based graphs (indices 1 to N) interchangeably without requiring client-side
          index manipulations.
        - Short-Circuit Validation (`if (ult_u == ult_v) return;`): Guard conditions check
          for shared absolute parents prior to executing merging protocols. This prevents
          redundant mutations, cycle generation, and erroneous self-aggregation behaviors.
        ================================================================================

*/

    /*

        // The Fine Distinction:
        // - Disjoint Set (The Data Structure): A mathematical concept and data structure
        //   that models a collection of distinct, non-overlapping (disjoint) sets.
        // - Disjoint Set Union / DSU (The Algorithm/Action): Focuses on the primary
        //   operations you perform on that data structure—specifically finding sets
        //   and uniting them (Union).
        // Also widely known as the "Union-Find" algorithm.

        class DisjointSet {

            private final int[] componentRank;
            private final int[] componentSize;
            private final int[] parentNodes;

            public DisjointSet(int n) {

                // Allocate N + 1 to safely accommodate both 0 to N-1 and 1 to N indexing systems
                int allocationSize = n + 1;

                componentRank = new int[allocationSize];
                componentSize = new int[allocationSize];
                parentNodes = new int[allocationSize];

                // Initially, every node is its own independent component parent
                for (int node = 0; node < allocationSize; node++) {
                    parentNodes[node] = node;
                }

                // Every standalone node initially forms a component of size 1
                Arrays.fill(componentSize, 1);
                // Note: componentRank array defaults to 0 automatically in Java
            }

            public int findUltimateParent(int node) {
                // Base case: If the node points to itself, it is the root of its tree
                if (node == parentNodes[node]) {
                    return node;
                }

                // Path Compression: Re-link the current node directly to its ultimate parent
                // by recursively backtracking up the tree.
                return parentNodes[node] = findUltimateParent(parentNodes[node]);
            }

            public void unionByRank(int u, int v) {

                int ultimateParentU = findUltimateParent(u);
                int ultimateParentV = findUltimateParent(v);

                // If they already share the same ultimate parent, they are already part of the same component
                if (ultimateParentU == ultimateParentV) {
                    return;
                }

                // Attach the tree with the smaller depth (rank) underneath the root of the deeper tree
                if (componentRank[ultimateParentU] < componentRank[ultimateParentV]) {
                    parentNodes[ultimateParentU] = ultimateParentV;
                } else if (componentRank[ultimateParentV] < componentRank[ultimateParentU]) {
                    parentNodes[ultimateParentV] = ultimateParentU;
                } else {
                    // Ranks are identical: Attach either one under the other arbitrarily,
                    // and increment the rank of the new ultimate parent by 1.
                    parentNodes[ultimateParentV] = ultimateParentU;
                    componentRank[ultimateParentU]++;
                }
            }

            public void unionBySize(int u, int v) {

                int ultimateParentU = findUltimateParent(u);
                int ultimateParentV = findUltimateParent(v);

                // If they already share the same ultimate parent, they are already part of the same component
                if (ultimateParentU == ultimateParentV) {
                    return;
                }

                // Attach the smaller component under the larger component.
                // Update the size of the larger component by absorbing the smaller component's size.
                if (componentSize[ultimateParentU] < componentSize[ultimateParentV]) {
                    parentNodes[ultimateParentU] = ultimateParentV;
                    componentSize[ultimateParentV] += componentSize[ultimateParentU];
                } else {
                    // Handles both ultimateParentV < ultimateParentU AND equal sizes perfectly.
                    // When sizes match, order doesn't matter; adding sizes works exactly the same.
                    parentNodes[ultimateParentV] = ultimateParentU;
                    componentSize[ultimateParentU] += componentSize[ultimateParentV];
                }
            }
        }

     */

    // Kruskal's Algorithm

    // Concept

    /*

    ================================================================================
     KRUSKAL'S ALGORITHM FOR MINIMUM SPANNING TREE (MST) — OVERVIEW
    ================================================================================

    1. Core Strategy: The Greedy Edge Approach
    --------------------------------------------------------------------------------
    - Kruskal's algorithm is a greedy graph algorithm used to find the Minimum Spanning
      Tree (MST) of a connected, weighted, undirected graph.
    - Unlike Prim's algorithm, which grows a tree outward from a starting vertex,
      Kruskal's algorithm works purely edge-by-edge. It focuses on globally picking the
      cheapest valid paths available across the entire graph.

    2. Algorithmic Steps
    --------------------------------------------------------------------------------
    - Step 1 (Sort): Flatten and sort all edges in the graph in non-decreasing order
      of their weights. This positions the minimum weight edges at the front line.
    - Step 2 (Iterate & Filter): Step through the sorted edge stream one by one.
    - Step 3 (Cycle Check via DSU): For each edge connecting node 'u' and node 'v',
      query the Disjoint Set to find if they are already in the same connected component:
      * If findUltimateParent(u) == findUltimateParent(v): It means that they are part of the same component so skip the edge.
        Adding it would close a loop, violating the structural constraint of a tree.
      * If findUltimateParent(u) != findUltimateParent(v): Accept the edge. Merge
        the components using union, add the weight to our running tally, and record
        the edge details.

    3. Complete Output Tracking
    --------------------------------------------------------------------------------
    - Total MST Weight: A cumulative integer counting the sum of chosen edge weights.
    - MST Graph Visual: An edge collection tracking the precise node pairs (u, v) and
      weights that compose the final minimal layout.

    4. Computational Complexities
    --------------------------------------------------------------------------------
    - Time Complexity: O(E log E) or O(E log V). Sorting the collection takes O(E log E)
      time. The subsequent loop runs E times, calling DSU operations that take nearly
      constant time O(4α). Hence, the sorting step dominates the total runtime.
    - Space Complexity: O(V + E). Requires space to hold parent/size tracking structures
      within the DSU layout (O(V)) and storage to save the output MST edge lists (O(V)).
    ================================================================================

     */

    /*


        class Solution {

            static class DisjointSet {

                private final int[] componentRank;
                private final int[] componentSize;
                private final int[] parentNodes;

                public DisjointSet(int n) {
                    // Allocate N + 1 to safely accommodate both 0 to N-1 and 1 to N indexing systems
                    int allocationSize = n + 1;

                    componentRank = new int[allocationSize];
                    componentSize = new int[allocationSize];
                    parentNodes = new int[allocationSize];

                    // Initially, every node is its own independent component parent
                    for (int node = 0; node < allocationSize; node++) {
                        parentNodes[node] = node;
                    }

                    // Every standalone node initially forms a component of size 1
                    Arrays.fill(componentSize, 1);
                }

                public int findUltimateParent(int node) {
                    if (node == parentNodes[node]) {
                        return node;
                    }
                    // Path Compression: Point current node directly to ultimate parent
                    return parentNodes[node] = findUltimateParent(parentNodes[node]);
                }

                public void unionByRank(int u, int v) {
                    int ultimateParentU = findUltimateParent(u);
                    int ultimateParentV = findUltimateParent(v);

                    if (ultimateParentU == ultimateParentV) {
                        return;
                    }

                    // Attach tree with smaller depth (rank) under tree with larger depth
                    if (componentRank[ultimateParentU] < componentRank[ultimateParentV]) {
                        parentNodes[ultimateParentU] = ultimateParentV;
                    } else if (componentRank[ultimateParentV] < componentRank[ultimateParentU]) {
                        parentNodes[ultimateParentV] = ultimateParentU;
                    } else {
                        parentNodes[ultimateParentV] = ultimateParentU;
                        componentRank[ultimateParentU]++;
                    }
                }

                public void unionBySize(int u, int v) {
                    int ultimateParentU = findUltimateParent(u);
                    int ultimateParentV = findUltimateParent(v);

                    if (ultimateParentU == ultimateParentV) {
                        return;
                    }

                    // Attach smaller component under larger component and merge size counters
                    if (componentSize[ultimateParentU] < componentSize[ultimateParentV]) {
                        parentNodes[ultimateParentU] = ultimateParentV;
                        componentSize[ultimateParentV] += componentSize[ultimateParentU];
                    } else {
                        parentNodes[ultimateParentV] = ultimateParentU;
                        componentSize[ultimateParentU] += componentSize[ultimateParentV];
                    }
                }
            }

            public int spanningTree(int numberOfVertices, int[][] edgeList) {

                // 1. Sort all available edges globally in non-decreasing order based on their weights (index 2)
                Arrays.sort(edgeList, (edgeA, edgeB) -> Integer.compare(edgeA[2], edgeB[2]));

                // 2. Initialize our Union-Find structure and structural storage collections
                DisjointSet disjointSet = new DisjointSet(numberOfVertices);
                int totalMstWeight = 0;
                ArrayList<int[]> minimumSpanningTreeEdges = new ArrayList<>();

                // 3. Process the sorted edge stream greedily
                for (int[] currentEdge : edgeList) {
                    int sourceNode = currentEdge[0];
                    int destinationNode = currentEdge[1];
                    int edgeWeight = currentEdge[2];

                    int ultimateParentSource = disjointSet.findUltimateParent(sourceNode);
                    int ultimateParentDestination = disjointSet.findUltimateParent(destinationNode);

                    // Cycle Check Intuition:
                    // If both nodes return the exact same ultimate root parent, it means they
                    // are already connected and belong to the same component via a different path.
                    // Introducing this edge would create an alternative path between them, forming a loop (cycle).
                    // Therefore, if the ultimate parents match, we silently skip the edge to protect the tree structure.
                    if (ultimateParentSource != ultimateParentDestination) {

                        // If they are in separate components, merge them together safely using size tracking
                        disjointSet.unionBySize(sourceNode, destinationNode);

                        // Accumulate the current valid path weight into our aggregate tally
                        totalMstWeight += edgeWeight;

                        // Simultaneously capture the complete structural edge data for graph layout tracking
                        minimumSpanningTreeEdges.add(new int[]{sourceNode, destinationNode, edgeWeight});
                    }
                }

                return totalMstWeight;
            }
        }

     */

    // Number of Provinces using Disjoint Set Data Structure

    /*

            class Solution {

            static class DisjointSet {
                private final int[] componentSize;
                private final int[] ultimateParent;

                public DisjointSet(int numberOfVertices) {
                    // Allocate V + 1 to easily handle both 0-indexed and 1-indexed problems
                    int allocationSize = numberOfVertices + 1;
                    componentSize = new int[allocationSize];
                    ultimateParent = new int[allocationSize];

                    // Initially, every node is its own independent component of size 1
                    for (int node = 0; node < allocationSize; node++) {
                        ultimateParent[node] = node;
                        componentSize[node] = 1;
                    }
                }

                public int findUltimateParent(int node) {
                    // Base case: If a node points to itself, it is the root parent
                    if (ultimateParent[node] == node) {
                        return node;
                    }

                    // Path Compression: Dynamically flatten the tree structures
                    return ultimateParent[node] = findUltimateParent(ultimateParent[node]);
                }

                public void unionBySize(int u, int v) {
                    int rootU = findUltimateParent(u);
                    int rootV = findUltimateParent(v);

                    // Guard check: If they share the same root, they are already connected
                    if (rootU == rootV) {
                        return;
                    }

                    // Attach the smaller component under the larger component
                    if (componentSize[rootU] < componentSize[rootV]) {
                        ultimateParent[rootU] = rootV;
                        componentSize[rootV] += componentSize[rootU];
                    } else {
                        ultimateParent[rootV] = rootU;
                        componentSize[rootU] += componentSize[rootV];
                    }
                }
            }

            public int findCircleNum(int[][] isConnected) {

                int totalVertices = isConnected.length;
                DisjointSet disjointSet = new DisjointSet(totalVertices);

                // 1. Process the adjacency matrix to merge connected cities
                // Optimization: Loop v starts at (u + 1) because the graph is undirected/symmetrical (isConnected[u][v] == isConnected[v][u])
                for (int u = 0; u < totalVertices; u++) {
                    for (int v = u + 1; v < totalVertices; v++) {
                        if (isConnected[u][v] == 1) {
                            disjointSet.unionBySize(u, v);
                        }
                    }
                }

                int provinceCount = 0;

                // 2. Count independent provinces
                // Intuition: The number of distinct components equals the number of nodes
                // that still act as their own ultimate parent.
                for (int vertex = 0; vertex < totalVertices; vertex++) {
                    if (vertex == disjointSet.findUltimateParent(vertex)) {
                        provinceCount++;
                    }
                }

                return provinceCount;
            }
        }

     */

    // Number of operations to make network connected

    /*

            class Solution {

            static class DisjointSet {
                private final int[] size;
                private final int[] parent;

                public DisjointSet(int V) {
                    int allocationSize = V + 1;
                    size = new int[allocationSize];
                    parent = new int[allocationSize];

                    for (int idx = 0; idx < allocationSize; idx++) {
                        parent[idx] = idx;
                        size[idx] = 1;
                    }
                }

                public int findUltimateParent(int node) {
                    if (node == parent[node]) {
                        return node;
                    }
                    // Path compression
                    return parent[node] = findUltimateParent(parent[node]);
                }

                public boolean unionBySize(int u, int v) {
                    int rootU = findUltimateParent(u);
                    int rootV = findUltimateParent(v);

                    if (rootU == rootV) {
                        return false;
                    }

                    if (size[rootU] < size[rootV]) {
                        parent[rootU] = rootV;
                        size[rootV] += size[rootU];
                    } else {
                        parent[rootV] = rootU;
                        size[rootU] += size[rootV];
                    }
                    return true;
                }
            }

            public int makeConnected(int n, int[][] connections) {

                // Fast-fail constraint check:
                // To fully connect N nodes in a graph, we absolutely need at least (N - 1) total edges.
                // If we have fewer cables than that, it is mathematically impossible to connect them.
                if (connections.length < n - 1) {
                    return -1;
                }

                DisjointSet disjointSet = new DisjointSet(n);
                int extraCables = 0;

                // 1. Process connections to build network groups and find redundant cables
                for (int connection[] : connections) {
                    int u = connection[0];
                    int v = connection[1];

                    // If unionBySize returns false, the two computers are already connected.
                    // This cable is redundant (can be unplugged/reused).
                    if (!disjointSet.unionBySize(u, v)) {
                        extraCables++;
                    }
                }

                // 2. Count independent, disconnected computer networks (components)
                int components = 0;
                for (int idx = 0; idx < n; idx++) {
                    if (idx == disjointSet.parent[idx]) {
                        components++;
                    }
                }

                // 3. Determine if we have enough spare cables
                // To join C isolated components into a single unified network, we need exactly (C - 1) connections.
                int requiredCables = components - 1;
                if (extraCables >= requiredCables) {
                    return requiredCables;
                } else {
                    return -1;
                }
            }
        }

     */

    // Most Stones Removed with Same Row or Column

    // Brute force approach ( still a good soln )

    /*

            class Solution {

            public int removeStones(int[][] stones) {
                int totalStones = stones.length;
                boolean visited[] = new boolean[totalStones];
                int componentCount = 0;

                // Traverse through each stone node
                for (int i = 0; i < totalStones; i++) {
                    // If the stone hasn't been visited, it belongs to a new component
                    if (!visited[i]) {
                        dfs(i, stones, visited);
                        componentCount++; // Finished exploring one complete component
                    }
                }

                // Formula remains: Total Stones - Number of Connected Components
                return totalStones - componentCount;
            }

            private void dfs(int currentStoneIdx, int stones[][], boolean visited[]) {
                visited[currentStoneIdx] = true;

                // O(N) scan to find all neighbors sharing the same row or column
                for (int nextStoneIdx = 0; nextStoneIdx < stones.length; nextStoneIdx++) {
                    if (!visited[nextStoneIdx]) {
                        // Check if the next stone shares the same row OR column with the current stone
                        if (stones[currentStoneIdx][0] == stones[nextStoneIdx][0] ||
                            stones[currentStoneIdx][1] == stones[nextStoneIdx][1]) {

                            // Recursive call to traverse deeper into the component
                            dfs(nextStoneIdx, stones, visited);
                        }
                    }
                }
            }
        }

     */

    // Optimal approach ( next level thought process )

    /*

        class Solution {

            static class DisjointSet {
                private final int[] size;
                private final int[] parent;

                DisjointSet(int V) {
                    int allocationSize = V + 1;
                    size = new int[allocationSize];
                    parent = new int[allocationSize];

                    for (int idx = 0; idx < allocationSize; idx++) {
                        parent[idx] = idx;
                        size[idx] = 1;
                    }
                }

                int findUltimateParent(int u) {
                    if (u == parent[u]) {
                        return u;
                    }
                    return parent[u] = findUltimateParent(parent[u]);
                }

                void unionBySize(int u, int v) {
                    int rootU = findUltimateParent(u);
                    int rootV = findUltimateParent(v);

                    if (rootU == rootV) {
                        return;
                    }

                    if (size[rootU] < size[rootV]) {
                        parent[rootU] = rootV;
                        size[rootV] += size[rootU];
                    } else {
                        parent[rootV] = rootU;
                        size[rootU] += size[rootV];
                    }
                }
            }


             * Calculates the maximum number of stones that can be removed.
             * * =========================================================================
             * WHY WE TREAT ROWS AND COLUMNS AS NODES (THE INTUITION):
             * =========================================================================
             * 1. THE TRADITIONAL APPROACH (Stones as Nodes):
             * Normally, you might think of each stone as a node. If Stone A and Stone B
             * share the same row/col, you draw an edge between them. While this works,
             * building this graph requires checking every pair of stones, resulting in
             * an O(N^2) time complexity to find which stones are connected.
             * * 2. THE OPTIMIZED APPROACH (Rows and Columns as Nodes):
             * Instead of connecting stones to stones, we treat the grid's gridlines
             * (Row indices and Column indices) as the actual vertices of our graph.
             * * - When we see a stone at coordinate (r, c), it acts as a "bridge".
             * It tells us: "Row 'r' and Column 'c' are now connected."
             * - By calling union(r, c), we merge Row 'r' and Column 'c' into the
             * same component.
             * * 3. WHY THIS MATHEMATICALLY WORKS:
             * - If Stone 1 is at (row 1, col 2), we connect:  Row 1 <---> Col 2
             * - If Stone 2 is at (row 3, col 2), we connect:  Row 3 <---> Col 2
             * * Because both are now connected to Col 2, Row 1 and Row 3 are transitively
             * merged into the exact same connected component: Row 1 <---> Col 2 <---> Row 3.
             * * Every stone in this component shares a row or col pathway. In any connected
             * component of size 'S' (representing 'S' connected rows/columns containing stones),
             * we can recursively delete stones one-by-one until only 1 representative "anchor"
             * stone remains to hold the component together.
             * * Therefore: Max Stones Removed = Total Stones - Number of Connected Components.
             * =========================================================================


             *
             * Calculates the maximum number of stones that can be removed.
             * Core Concept:
             * 1. Stones sharing a row or column belong to the same connected component.
             * 2. From any component of size 'S', we can safely remove 'S - 1' stones.
             * 3. Total stones removed = Total Stones - Number of Connected Components.
             *
            public int removeStones(int[][] stones) {
                int totalStones = stones.length;

                // Step 1: Scan stones to find the maximum row and col index present.
                // We need this to determine the structural size boundaries of our Disjoint Set.
                int maxRow = 0;
                int maxCol = 0;
                for (int[] stone : stones) {
                    maxRow = Math.max(maxRow, stone[0]);
                    maxCol = Math.max(maxCol, stone[1]);
                }

                // Column indices are offset by (maxRow + 1) to completely separate
                // row nodes from column nodes in our single Disjoint Set array space.
                int colOffset = maxRow + 1;
                int totalGraphNodes = maxRow + maxCol + 1;

                DisjointSet disjointSet = new DisjointSet(totalGraphNodes);

                // Track only the unique row and column IDs that actually contain a stone.
                // This helps us filter out completely empty rows/columns during the component count.
                Set<Integer> activeNodes = new HashSet<>();

                // Step 2: Connect coordinates and populate active nodes
                for (int[] stone : stones) {
                    int rowNode = stone[0];
                    int colNode = stone[1] + colOffset;

                    // Connect the row index with the offset column index
                    disjointSet.unionBySize(rowNode, colNode);

                    // Mark these nodes as containing stones
                    activeNodes.add(rowNode);
                    activeNodes.add(colNode);
                }

                // Step 3: Count independent components (Optimized Approach)
                // Instead of searching through every slot, we check only our active nodes.
                // An active component root is found whenever an active node points to itself.
                int componentCount = 0;
                for (int node : activeNodes) {
                    if (disjointSet.findUltimateParent(node) == node) {
                        componentCount++;
                    }
                }

                // Return the maximum removable stones formula
                return totalStones - componentCount;
            }
        }

     */

    // Accounts Merge

    /*

        class Solution {

            static class DisjointSet {
                private final int[] size;
                private final int[] parent;

                DisjointSet(int V) {
                    int allocationSize = V + 1;
                    size = new int[allocationSize];
                    parent = new int[allocationSize];

                    for (int idx = 0; idx < allocationSize; idx++) {
                        parent[idx] = idx;
                        size[idx] = 1;
                    }
                }

                int findUltimateParent(int u) {
                    if (u == parent[u]) {
                        return u;
                    }
                    return parent[u] = findUltimateParent(parent[u]);
                }

                void unionBySize(int u, int v) {
                    int rootU = findUltimateParent(u);
                    int rootV = findUltimateParent(v);

                    if (rootU == rootV) {
                        return;
                    }

                    if (size[rootU] < size[rootV]) {
                        parent[rootU] = rootV;
                        size[rootV] += size[rootU];
                    } else {
                        parent[rootV] = rootU;
                        size[rootU] += size[rootV];
                    }
                }
            }

            public List<List<String>> accountsMerge(List<List<String>> accounts) {
                int totalAccounts = accounts.size();
                DisjointSet disjointSet = new DisjointSet(totalAccounts);

                // Step 1: Map each unique email to its corresponding Account ID (index).
                Map<String, Integer> emailToAccountMap = new HashMap<>();

                for (int accountIndex = 0; accountIndex < totalAccounts; accountIndex++) {
                    List<String> accountDetails = accounts.get(accountIndex);

                    for (int emailIdx = 1; emailIdx < accountDetails.size(); emailIdx++) {
                        String currentEmail = accountDetails.get(emailIdx);

                        if (emailToAccountMap.containsKey(currentEmail)) {
                            int originalAccountIndex = emailToAccountMap.get(currentEmail);
                            disjointSet.unionBySize(accountIndex, originalAccountIndex);
                        } else {
                            emailToAccountMap.put(currentEmail, accountIndex);
                        }
                    }
                }

                 * ---------------------------------------------------------------------
                 * Map.Entry EXPLANATION REFERENCE:
                 * ---------------------------------------------------------------------
                 * 1. .entrySet(): Returns a collection framework Set containing objects
                 * of type Set<Map.Entry<K, V>>. Instead of executing isolated lookups
                 * for keys or values, it pulls both dimensions simultaneously.
                 * * 2. Map.Entry<K, V>: A nested interface in Java representing a single,
                 * unified key-value container pair object stored within that Set.
                 * * 3. entry: The temporary loop iterator variable holding the active
                 * Map.Entry<K, V> object instance for the current iteration step.
                 * * 4. .getKey(): A method called on the 'entry' object to cleanly fetch
                 * the Key component (in this case, the email String or Root ID).
                 * * 5. .getValue(): A method called on the 'entry' object to cleanly fetch
                 * the mapped Value component (the original account Index or List<String>).
                 * ---------------------------------------------------------------------

            // Step 2: Group all unique emails under their respective component's Root ID.
            // We use .entrySet() to pull both the email (Key) and the raw account index (Value) at once.
            Map<Integer, List<String>> mergedEmailGroups = new HashMap<>();

                for (Map.Entry<String, Integer> entry : emailToAccountMap.entrySet()) {
                String email = entry.getKey(); // Extract the email string
                int mappedAccountIndex = entry.getValue(); // Extract the original account index

                // Find the ultimate representative (root) index for this email's component group
                int ultimateParentIndex = disjointSet.findUltimateParent(mappedAccountIndex);

                // Fetch or create the list of emails associated with this root parent group
                if (mergedEmailGroups.containsKey(ultimateParentIndex)) {
                    mergedEmailGroups.get(ultimateParentIndex).add(email);
                } else {
                    List<String> temp = new ArrayList<>();
                    temp.add(email);
                    mergedEmailGroups.put(ultimateParentIndex, temp);
                }
            }

            // Step 3: Format the grouped results into the final output list.
            List<List<String>> finalMergedAccounts = new ArrayList<>();

                for (Map.Entry<Integer, List<String>> entry : mergedEmailGroups.entrySet()) {
                int rootAccountIndex = entry.getKey();
                List<String> sortedEmails = entry.getValue();

                // Sort the emails lexicographically
                Collections.sort(sortedEmails);

                String ownerName = accounts.get(rootAccountIndex).get(0);

                List<String> combinedAccountRecord = new ArrayList<>();
                combinedAccountRecord.add(ownerName);
                combinedAccountRecord.addAll(sortedEmails);

                finalMergedAccounts.add(combinedAccountRecord);
            }

                return finalMergedAccounts;
        }
        }

     */

    // Number of islands II

    /*

        class Solution {

            static class DisjointSet {
                private final int[] size;
                private final int[] parent;

                DisjointSet(int V) {
                    int allocationSize = V + 1;
                    size = new int[allocationSize];
                    parent = new int[allocationSize];

                    for (int idx = 0; idx < allocationSize; idx++) {
                        parent[idx] = idx;
                        size[idx] = 1;
                    }
                }

                int findUltimateParent(int u) {
                    if (u == parent[u]) {
                        return u;
                    }
                    return parent[u] = findUltimateParent(parent[u]);
                }

                void unionBySize(int u, int v) {
                    int rootU = findUltimateParent(u);
                    int rootV = findUltimateParent(v);

                    if (rootU == rootV) {
                        return;
                    }

                    if (size[rootU] < size[rootV]) {
                        parent[rootU] = rootV;
                        size[rootV] += size[rootU];
                    } else {
                        parent[rootV] = rootU;
                        size[rootU] += size[rootV];
                    }
                }
            }

            public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {

                // Compute total linear matrix size to accurately size our data structures
                int totalNodes = rows * cols;
                DisjointSet disjointSet = new DisjointSet(totalNodes);

                int currentIslandCount = 0;
                List<Integer> islandCountHistory = new ArrayList<>();


                boolean isVisitedLand[] = new boolean[totalNodes];

                // Direction vectors to inspect Up, Down, Left, and Right neighbors cleanly
                int[] rowOffset = {0, 1, -1, 0};
                int[] colOffset = {1, 0, 0, -1};

                for (int operator[] : operators) {
                    int row = operator[0];
                    int col = operator[1];

                    // Map the 2D matrix coordinates onto a distinct 1D array index slot
                    int nodeIdx = row * cols + col;

                    // EDGE CASE: If this exact coordinate was already flipped to land previously,
                    // we skip redundant recalculations and record the ongoing active island count.
                    if (isVisitedLand[nodeIdx]) {
                        islandCountHistory.add(currentIslandCount);
                        continue;
                    }

                    // Step 1: Mark this unique 1D node cell as active land, provisionally adding to total counts
                    isVisitedLand[nodeIdx] = true;
                    currentIslandCount++;

                    // Step 2: Traverse all 4 cardinal directions to discover neighboring components
                    for (int i = 0; i < 4; i++) {
                        int neighborRow = row + rowOffset[i];
                        int neighborCol = col + colOffset[i];

                        // CRITICAL BOUNDARY CHECK: Eliminates Row Wrapping mathematical anomalies
                        // where the end boundary of one line bridges into the start boundary of the next line.
                        if (neighborRow >= 0 && neighborRow < rows && neighborCol >= 0 && neighborCol < cols) {
                            int neighborNodeIdx = neighborRow * cols + neighborCol;

                            // We only evaluate valid operations if that specific neighbor node contains active land
                            if (isVisitedLand[neighborNodeIdx]) {
                                int currentLandRoot = disjointSet.findUltimateParent(nodeIdx);
                                int neighborLandRoot = disjointSet.findUltimateParent(neighborNodeIdx);

                                // If their top roots don't match, they belong to disconnected islands.
                                // We link them together and safely reduce our net island tracker count.
                                if (currentLandRoot != neighborLandRoot) {
                                    disjointSet.unionBySize(nodeIdx, neighborNodeIdx);
                                    currentIslandCount--;
                                }
                            }
                        }
                    }

                    // Save the definitive state calculation milestone achieved after this operation
                    islandCountHistory.add(currentIslandCount);
                }

                return islandCountHistory;
            }
        }

     */

    // Making A Large Island

    /*

        class Solution {

            static class DisjointSet {
                private final int[] size;
                private final int[] parent;

                DisjointSet(int V) {
                    int allocationSize = V + 1;
                    size = new int[allocationSize];
                    parent = new int[allocationSize];

                    for (int idx = 0; idx < allocationSize; idx++) {
                        parent[idx] = idx;
                        size[idx] = 1;
                    }
                }

                int findUltimateParent(int u) {
                    if (u == parent[u]) {
                        return u;
                    }
                    return parent[u] = findUltimateParent(parent[u]);
                }

                void unionBySize(int u, int v) {
                    int rootU = findUltimateParent(u);
                    int rootV = findUltimateParent(v);

                    if (rootU == rootV) {
                        return;
                    }

                    if (size[rootU] < size[rootV]) {
                        parent[rootU] = rootV;
                        size[rootV] += size[rootU];
                    } else {
                        parent[rootV] = rootU;
                        size[rootU] += size[rootV];
                    }
                }

                // Returns the total size of the connected component that element 'u' belongs to
                int getSize(int u) {
                    int root = findUltimateParent(u);
                    return size[root];
                }
            }

            public int largestIsland(int[][] grid) {

                int n = grid.length;
                int totalNodes = n * n;
                DisjointSet ds = new DisjointSet(totalNodes);

                // Direction offsets for traversing Up, Down, Left, and Right neighbors
                int rowOffset[] = {0, 0, 1, -1};
                int colOffset[] = {1, -1, 0, 0};

                // STEP 1: Scan the grid to group and union all existing land mass components
                for (int row = 0; row < n; row++) {
                    for (int col = 0; col < n; col++) {
                        if (grid[row][col] == 1) {

                            int nodeIdx = row * n + col;

                            // Check all 4 cardinal directions for neighboring land
                            for (int i = 0; i < 4; i++) {
                                int newRow = row + rowOffset[i];
                                int newCol = col + colOffset[i];

                                // Ensure neighbor is inside the grid matrix boundaries
                                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {

                                    if (grid[newRow][newCol] == 1) {
                                        int neighborIdx = newRow * n + newCol;
                                        ds.unionBySize(nodeIdx, neighborIdx);
                                    }

                                }
                            }
                        }
                    }
                }

                // EDGE CASE GUARD: If the grid contains absolutely no '0's (it's 100% land),
                // the largest island size is simply the total grid capacity size (n * n).
                // We initialize this using the component size of the first cell if it is land.
                int largestIslandSize = 0;
                if (grid[0][0] == 1) {
                    largestIslandSize = ds.getSize(0);
                }

                // STEP 2: Find every '0' and simulate turning it into a '1'
                for (int row = 0; row < n; row++) {
                    for (int col = 0; col < n; col++) {

                        if (grid[row][col] == 0) {

                            // A HashSet ensures that if multiple neighbors belong to the exact
                            // same island component, we only add its size contribution exactly once.
                            Set<Integer> uniqueComponentParents = new HashSet<>();

                            // Inspect the 4 surrounding neighbors of this water cell
                            for (int i = 0; i < 4; i++) {
                                int newRow = row + rowOffset[i];
                                int newCol = col + colOffset[i];

                                // Validate boundary safety constraints
                                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {

                                    if (grid[newRow][newCol] == 1) {
                                        int neighborIdx = newRow * n + newCol;
                                        // Identify the ultimate structural parent root of this neighbor island
                                        uniqueComponentParents.add(ds.findUltimateParent(neighborIdx));
                                    }

                                }
                            }

                            // Base size starts at 1 (the current '0' cell transformed into land)
                            int currentIslandSize = 1;

                            // Combine the sizes of all unique adjacent island components
                            for (int parentRoot : uniqueComponentParents) {
                                currentIslandSize += ds.getSize(parentRoot);
                            }

                            // Keep track of the peak maximum size recorded across all simulated flips
                            largestIslandSize = Math.max(largestIslandSize, currentIslandSize);
                        }
                    }
                }

                return largestIslandSize;
            }
        }

     */


}
