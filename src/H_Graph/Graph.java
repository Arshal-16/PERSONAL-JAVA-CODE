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




}
