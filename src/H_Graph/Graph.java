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




}
