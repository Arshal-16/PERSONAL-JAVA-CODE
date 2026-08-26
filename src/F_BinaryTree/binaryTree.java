package F_BinaryTree;

import java.util.*;

public class binaryTree {

    /////////////////////// LEARNING ////////////////////////////

    /*

    Binary Tree Notes

        Binary Tree:
        A binary tree is a tree data structure in which each node can have at most two children, called the left child and the right child.

        Node:
        A node is a basic element of a tree that stores data and links to its children.

        Edge:
        An edge is the connection between a parent node and its child.

        Root:
        The root is the topmost node of the tree. It has no parent.

        Parent:
        A parent is a node that has one or more child nodes.

        Child:
        A child is a node directly connected below a parent node.

        Sibling:
        Sibling nodes are nodes that share the same parent.

        Leaf (External Node):
        A leaf node is a node that has no children.

        Internal Node (Non-leaf Node):
        An internal node is any node that has at least one child. (The root is also an internal node if it has children.)

        Ancestor:
        An ancestor of a node is any node on the path from the root to that node, including its parent, grandparent, etc.

        Descendant:
        A descendant of a node is any node that can be reached by moving downward from that node, including its children, grandchildren, etc.

        Degree of a Node:
        The degree of a node is the number of children it has.
        In a binary tree, the maximum degree of a node is 2.

        Degree of a Tree:
        The degree of a tree is the maximum degree of any node in the tree.
        For a binary tree, the degree of the tree is at most 2.

        Level:
        The level of a node is its position from the root.
        If the root is considered Level 0:
        Root = Level 0
        Children of root = Level 1
        Grandchildren = Level 2
        (Some books start the root at Level 1.)

        Depth:
        The depth of a node is the number of edges from the root to that node.
        Root depth = 0.

        Height of a Node:
        The height of a node is the number of edges on the longest path from that node to a leaf.
        Leaf node height = 0.

        Height of a Tree:
        The height of a tree is the height of the root.
        It is equal to the maximum depth of any node.

        Subtree:
        A subtree is a tree formed by any node and all of its descendants.

        Path:
        A path is a sequence of nodes connected by edges.

        Path Length:
        The path length is the number of edges in the path.

        Distance:
        The distance between two nodes is the number of edges in the shortest path connecting them.

        Binary Tree Traversals

        Inorder Traversal (Left → Root → Right)
        Visit the left subtree, then the root, then the right subtree.
        For Binary Search Trees (BST), inorder traversal gives nodes in sorted order.

        Preorder Traversal (Root → Left → Right)
        Visit the root first, then the left subtree, then the right subtree.
        Useful for copying a tree or creating a prefix expression.

        Postorder Traversal (Left → Right → Root)
        Visit the left subtree, then the right subtree, and finally the root.
        Useful for deleting a tree or evaluating postfix expressions.

        Level Order Traversal (Breadth-First Traversal)
        Visit nodes level by level from left to right using a queue.

        Types of Binary Trees

        Full Binary Tree (Strict / Proper Binary Tree):
        A full binary tree is a binary tree in which every node has either exactly two children or no children. No node has only one child.
        All internal nodes have exactly two children, and all remaining nodes are leaf nodes.

        Complete Binary Tree:
        A complete binary tree is a binary tree in which all levels are completely filled except possibly the last level,
        and all nodes in the last level are as far left as possible.

        Perfect Binary Tree:
        A perfect binary tree is a binary tree in which all internal nodes have exactly two children and all leaf nodes are at the same level.
        Every level is completely filled.

        Balanced Binary Tree:
        A balanced binary tree is a binary tree in which the heights of the left and right subtrees of every node differ by at most one.
        This keeps the tree height small, making operations efficient.

        Degenerate Binary Tree (Skewed Tree):
        A degenerate binary tree is a binary tree in which every parent node has only one child. It behaves like a linked list,
        making the tree height equal to the number of nodes and reducing efficiency.

        Left Skewed Tree:
        A degenerate tree where every node has only a left child.

        Right Skewed Tree:
        A degenerate tree where every node has only a right child.

        Binary Search Tree (BST):
        A binary tree where:

        * Left subtree contains values smaller than the node.
        * Right subtree contains values greater than the node.
        * Both left and right subtrees are also BSTs.

        Other Important Terms

        Null Node:
        A missing child pointer is called a null node.

        Empty Tree:
        A tree with no nodes.

        Size of a Tree:
        The total number of nodes in the tree.

        Width of a Tree:
        The maximum number of nodes present at any level.

        Diameter of a Tree:
        The number of edges in the longest path between any two nodes in the tree.

        Lowest Common Ancestor (LCA):
        The deepest node that is a common ancestor of two given nodes.

        Forest:
        A collection of one or more disjoint trees.

        Key Formulas

        Maximum nodes at level l:
        2^l (when root is at level 0)

        Maximum nodes in a binary tree of height h:
        2^(h+1) - 1

        Minimum nodes in a binary tree of height h:
        h + 1 (degenerate tree)

        Leaf nodes in a perfect binary tree:
        2^h

        Total nodes in a perfect binary tree:
        2^(h+1) - 1

        Edges in a tree with n nodes:
        n - 1

        Maximum children of a node in a binary tree:
        2

            static class Node {
                int data;
                Node left;
                Node right;

                Node(int data) {
                    this.data = data;
                    this.left = null;
                    this.right = null;
                }
            }

     */

    /////////////////////// TRAVERSALS ////////////////////////////

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Binary Tree Traversals:
    // DFS (Depth-First Search): Preorder, Inorder, and Postorder traversals.
    // Naming of DFS traversals is based on when the root node is visited:
    // Preorder  : Root is visited before its left and right subtrees.
    // Inorder   : Root is visited between the left and right subtrees.
    // Postorder : Root is visited after both the left and right subtrees.
    // BFS (Breadth-First Search): Level Order traversal.

    // Preorder Traversal

    /*

            class Solution {

         Time Complexity: O(N) - Every node is visited exactly once.
         Space Complexity: O(N) - In the worst case (skewed/degenerate tree), the call stack grows up to N frames deep.

            public List<Integer> preorder(Node root) {

                List<Integer> preorderResult = new ArrayList<>();
                traversePreorder(root, preorderResult);

                return preorderResult;
            }

            private void traversePreorder(Node currentNode, List<Integer> preorderResult) {

                // Base case:
                if (currentNode == null) {
                    return;
                }

                // Process current node first (Root)
                preorderResult.add(currentNode.data);

                // Recursively visit left and right subtrees
                traversePreorder(currentNode.left, preorderResult);
                traversePreorder(currentNode.right, preorderResult);
            }
        }

     */

    // Inorder Traversal

    /*

            class Solution {


         Time Complexity: O(N) - Every node is visited exactly once.
         Space Complexity: O(N) - In the worst case (skewed/degenerate tree),
                           the call stack grows up to N frames deep.

            public List<Integer> inorder(Node root) {

                List<Integer> inorderResult = new ArrayList<>();
                traverseInorder(root, inorderResult);

                return inorderResult;
            }

            private void traverseInorder(Node currentNode, List<Integer> inorderResult) {
                // Base case:
                if (currentNode == null) {
                    return;
                }

                // Recursively visit left subtree
                traverseInorder(currentNode.left, inorderResult);

                // Process current node (Root)
                inorderResult.add(currentNode.data);

                // Recursively visit right subtree
                traverseInorder(currentNode.right, inorderResult);
            }
        }

     */

    // Postorder Traversal

    /*

            class Solution {

         Performs a postorder traversal on a binary tree (Left -> Right -> Root).

         Time Complexity: O(N) - Every node is visited exactly once.
         Space Complexity: O(N) - In the worst case (skewed/degenerate tree),
                           the call stack grows up to N frames deep.

            public List<Integer> postorder(Node root) {

                List<Integer> postorderResult = new ArrayList<>();
                traversePostorder(root, postorderResult);

                return postorderResult;
            }

            private void traversePostorder(Node currentNode, List<Integer> postorderResult) {

                // Base case:
                if (currentNode == null) {
                    return;
                }

                // Recursively visit left and right subtrees
                traversePostorder(currentNode.left, postorderResult);
                traversePostorder(currentNode.right, postorderResult);

                // Process current node last (Root)
                postorderResult.add(currentNode.data);
            }
        }

     */

    // Level Order Traversal

    /*

            class Solution {

         Performs a level-order (breadth-first) traversal on a binary tree.

         Time Complexity: O(N) - Every node is processed exactly once.
         Space Complexity: O(W) - Where W is the maximum width of the tree.
                           In the worst case (perfectly balanced tree),
                           the queue holds up to N / 2 leaf nodes at the last level.

            public List<List<Integer>> levelOrder(Node root) {

                List<List<Integer>> levelOrderResult = new ArrayList<>();

                if (root == null) {
                    return levelOrderResult;
                }

                Queue<Node> bfsQueue = new ArrayDeque<>();
                bfsQueue.add(root);

                while (!bfsQueue.isEmpty()) {

                    int currentLevelSize = bfsQueue.size();
                    List<Integer> currentLevelNodes = new ArrayList<>();

                    for (int i = 0; i < currentLevelSize; i++) {

                        Node currentNode = bfsQueue.remove();
                        currentLevelNodes.add(currentNode.val);

                        // Enqueue left and right children if they exist
                        if (currentNode.left != null) {
                            bfsQueue.add(currentNode.left);
                        }

                        if (currentNode.right != null) {
                            bfsQueue.add(currentNode.right);
                        }
                    }

                    // Append the collected nodes for this level to the main result
                    levelOrderResult.add(currentLevelNodes);
                }

                return levelOrderResult;
            }
        }

     */

    // Iterative Preorder Traversal of Binary Tree

    /*

            class Solution {

            public List<Integer> preorder(Node root) {

                List<Integer> preorderResult = new ArrayList<>();

                if (root == null) {
                    return preorderResult;
                }

                Stack<Node> traversalStack = new Stack<>();
                traversalStack.push(root);

                while (!traversalStack.isEmpty()) {

                    Node currentNode = traversalStack.pop();
                    preorderResult.add(currentNode.val);

                    // Push right child first so left child is popped and processed first
                    if (currentNode.right != null) {
                        traversalStack.push(currentNode.right);
                    }

                    if (currentNode.left != null) {
                        traversalStack.push(currentNode.left);
                    }
                }

                return preorderResult;
            }
        }

     */

    // Iterative Inorder Traversal of Binary Tree

    /*

            class Solution {

            public List<Integer> inorder(Node root) {

                List<Integer> inorderResult = new ArrayList<>();

                if (root == null) {
                    return inorderResult;
                }

                Stack<Node> traversalStack = new Stack<>();
                Node currentNode = root;

                while (true) {
                    // Keep going as far left as possible, pushing each node onto the stack
                    if (currentNode != null) {
                        traversalStack.push(currentNode);
                        currentNode = currentNode.left;
                    } else {
                        // If current node is null and stack is empty, all nodes have been visited
                        if (traversalStack.isEmpty()) {
                            break;
                        }

                        // Pop the last visited left-most node whose left subtree is fully processed
                        currentNode = traversalStack.pop();

                        // Process the popped node (Root)
                        inorderResult.add(currentNode.val);

                        // Move to the right child to repeat the process for the right subtree
                        currentNode = currentNode.right;
                    }
                }

                return inorderResult;
            }
        }

     */

    // Iterative Postorder Traversal of Binary Tree (using 2 stacks)

    /*

            class Solution {

            public List<Integer> postorder(Node root) {
                List<Integer> postorderResult = new ArrayList<>();

                if (root == null) {
                    return postorderResult;
                }

                Stack<Node> traversalStack = new Stack<>();
                Stack<Integer> resultStack = new Stack<>();

                traversalStack.push(root);

                // Step 1: Process nodes in (Root -> Right -> Left) order.
                // Pushing nodes into resultStack reverses them to give (Left -> Right -> Root) order.
                while (!traversalStack.isEmpty()) {
                    Node currentNode = traversalStack.pop();

                    // Store the processed node's value in the output stack
                    resultStack.push(currentNode.val);

                    // Push left child first, so right child is popped and processed next
                    if (currentNode.left != null) {
                        traversalStack.push(currentNode.left);
                    }

                    if (currentNode.right != null) {
                        traversalStack.push(currentNode.right);
                    }
                }

                // Step 2: Pop all elements from resultStack to get the final Postorder traversal
                while (!resultStack.isEmpty()) {
                    postorderResult.add(resultStack.pop());
                }

                // Note: As you mentioned, an alternative to using resultStack is directly
                // adding values to postorderResult and calling Collections.reverse(postorderResult) at the end!

                return postorderResult;
            }
        }

     */

    // Iterative Postorder Traversal of Binary Tree (using 1 stack)

    /*

            class Solution {

            public List<Integer> postorder(Node root) {

                List<Integer> postorderResult = new ArrayList<>();

                if (root == null) {
                    return postorderResult;
                }

                Node curr = root;
                Stack<Node> traversalStack = new Stack<>();

                while (curr != null || !traversalStack.isEmpty()) {

                    // Step 1: Reach the leftmost node of the current subtree
                    if (curr != null) {
                        traversalStack.push(curr);
                        curr = curr.left;
                    } else {
                        // Peek at the parent node to check its right child
                        Node temp = traversalStack.peek().right;

                        // Step 2: If a right child exists and hasn't been visited, move to it
                        if (temp != null) {
                            curr = temp;
                        } else {
                            // Step 3: Otherwise, process the node at the top of stack (Left & Right are done)
                            temp = traversalStack.pop();
                            postorderResult.add(temp.val);

                            // Step 4: Backtrack up the tree if the popped node was a right child of its parent
                            while (!traversalStack.isEmpty() && temp == traversalStack.peek().right) {
                                temp = traversalStack.pop();
                                postorderResult.add(temp.val);
                            }
                        }
                    }
                }

                return postorderResult;
            }
        }

     */

    // Preorder Inorder Postorder Traversals in One Traversal



    /////////////////////// MEDIUM PROBLEMS ////////////////////////////

    // Maximum Depth of Binary Tree

    /* Using recursion

             Note on "Depth" vs "Height":
         - Technically, "Depth" measures top-down (distance from root to a node).
         - "Height" measures bottom-up (distance from a node to its deepest leaf).
         - The Maximum Depth of a tree is numerically equal to the Height of the root node.

         This recursive approach computes the height of the root bottom-up,
         which gives us the maximum depth cleanly without passing an extra depth parameter.

            class Solution {

                public int maxDepth(TreeNode root) {
                    // Base case: An empty tree/sub-tree has a height/depth of 0
                    if (root == null) {
                        return 0;
                    }

                    // Recursively compute the height of left and right subtrees
                    int maxLeftDepth = maxDepth(root.left);
                    int maxRightDepth = maxDepth(root.right);

                    // Current node's height = 1 + max height of its subtrees
                    return 1 + Math.max(maxLeftDepth, maxRightDepth);
                }
            }

     */

    /* Using level order traversal

            class Solution {

            public int maxDepth(TreeNode root) {
                if (root == null) {
                    return 0;
                }

                Queue<TreeNode> traversalQueue = new ArrayDeque<>();
                int maxDepth = 0;
                traversalQueue.add(root);

                while (!traversalQueue.isEmpty()) {
                    int currentLevelSize = traversalQueue.size();
                    maxDepth++;

                    // Process all nodes at the current level
                    for (int i = 0; i < currentLevelSize; i++) {
                        TreeNode currentNode = traversalQueue.remove();

                        if (currentNode.left != null) {
                            traversalQueue.add(currentNode.left);
                        }

                        if (currentNode.right != null) {
                            traversalQueue.add(currentNode.right);
                        }
                    }
                }

                return maxDepth;
            }
        }

     */

    // Balanced Binary Tree

    /*

            class Solution {

            public boolean isBalanced(TreeNode root) {
                // If checkHeight returns -1, the tree is unbalanced
                return checkHeight(root) != -1;
            }

            private int checkHeight(TreeNode currentNode) {
                // Base case: An empty subtree has height 0
                if (currentNode == null) {
                    return 0;
                }

                // Check left subtree
                int leftSubtreeHeight = checkHeight(currentNode.left);
                if (leftSubtreeHeight == -1) {
                    return -1; // Left subtree is unbalanced
                }

                // Check right subtree
                int rightSubtreeHeight = checkHeight(currentNode.right);
                if (rightSubtreeHeight == -1) {
                    return -1; // Right subtree is unbalanced
                }

                // Check current node's balance condition
                if (Math.abs(leftSubtreeHeight - rightSubtreeHeight) > 1) {
                    return -1; // Current node is unbalanced
                }

                // Return height of current node if balanced
                return 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);
            }
        }

     */

    // Diameter of Binary Tree

    /*

     class Solution {

            public int diameterOfBinaryTree(TreeNode root) {
                int[] maxDiameter = new int[1];
                calculateHeightAndDiameter(root, maxDiameter);
                return maxDiameter[0];
            }

            private int calculateHeightAndDiameter(TreeNode currentNode, int[] maxDiameter) {
                // Base case: An empty node has a height of 0
                if (currentNode == null) {
                    return 0;
                }

                // Recursively calculate heights of left and right subtrees
                int leftSubtreeHeight = calculateHeightAndDiameter(currentNode.left, maxDiameter);
                int rightSubtreeHeight = calculateHeightAndDiameter(currentNode.right, maxDiameter);

                // Update maximum diameter assuming the current node is the highest point (turning point) of the path
                maxDiameter[0] = Math.max(maxDiameter[0], leftSubtreeHeight + rightSubtreeHeight);

                // Return height of current subtree
                return 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);
            }
        }

     */

    /*

     class Solution {

            // Instance variable to track the global maximum diameter
            private int maxDiameter = 0;

            public int diameterOfBinaryTree(TreeNode root) {
                maxDiameter = 0; // Reset for every new function call (good practice on LeetCode)
                calculateHeight(root);
                return maxDiameter;
            }

            private int calculateHeight(TreeNode currentNode) {
                // Base case: An empty node has a height of 0
                if (currentNode == null) {
                    return 0;
                }

                // Recursively calculate heights of subtrees
                int leftSubtreeHeight = calculateHeight(currentNode.left);
                int rightSubtreeHeight = calculateHeight(currentNode.right);

                // Update global max diameter if path through currentNode is longer
                maxDiameter = Math.max(maxDiameter, leftSubtreeHeight + rightSubtreeHeight);

                // Return height of current subtree
                return 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);
            }
        }

     */

    // Binary Tree Maximum Path Sum

    /*

        class Solution {

            // Initialized to Integer.MIN_VALUE instead of 0 because the tree might contain only
            // negative nodes, and the problem requires a non-empty path (returning the least
            // negative node value rather than 0).
            private int maxPathSum = Integer.MIN_VALUE;

            public int maxPathSum(TreeNode root) {
                calculateMaxPath(root);
                return maxPathSum;
            }

            private int calculateMaxPath(TreeNode currentNode) {
                if (currentNode == null) {
                    return 0;
                }

                // Cap negative sums to 0 (ignore subtrees with negative totals)
                int maxLeftPath = Math.max(0, calculateMaxPath(currentNode.left));
                int maxRightPath = Math.max(0, calculateMaxPath(currentNode.right));

                // Update maximum path sum treating currentNode as the turning point
                maxPathSum = Math.max(maxPathSum, maxLeftPath + maxRightPath + currentNode.val);

                // Return max path sum extending up to the parent
                return currentNode.val + Math.max(maxLeftPath, maxRightPath);
            }
        }

     */

    // Check if two trees are identical or not

    /*

        class Solution {

            public boolean isSameTree(TreeNode firstNode, TreeNode secondNode) {
                // Base case: If either node is null, both must be null to be identical
                if (firstNode == null || secondNode == null) {
                    return firstNode == secondNode;
                }

                // Structural and value check: Values match and subtrees match
                return (firstNode.val == secondNode.val)
                        && isSameTree(firstNode.left, secondNode.left)
                        && isSameTree(firstNode.right, secondNode.right);
            }
        }

     */

    // Binary Tree Zigzag Level Order Traversal

    /*

      class Solution {

            public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

                List<List<Integer>> traversalOrder = new ArrayList<>();

                if (root == null) {
                    return traversalOrder;
                }

                Queue<TreeNode> traversalQueue = new ArrayDeque<>();
                boolean leftToRight = true;

                traversalQueue.add(root);

                while (!traversalQueue.isEmpty()) {

                    int currentLevelSize = traversalQueue.size();
                    List<Integer> currentLevel = new ArrayList<>();

                    for (int i = 0; i < currentLevelSize; i++) {

                        TreeNode currentNode = traversalQueue.remove();
                        currentLevel.add(currentNode.val);

                        if (currentNode.left != null) {
                            traversalQueue.add(currentNode.left);
                        }
                        if (currentNode.right != null) {
                            traversalQueue.add(currentNode.right);
                        }
                    }

                    // Reverse current level values if processing right-to-left
                    if (!leftToRight) {
                        Collections.reverse(currentLevel);
                    }

                    traversalOrder.add(currentLevel);
                    leftToRight = !leftToRight; // Toggle flag for next level
                }

                return traversalOrder;
            }
        }

     */

    /* Slightly Optimised Version, here we won't have to reverse like we did in arraylist

        class Solution {

            public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

                List<List<Integer>> traversalOrder = new ArrayList<>();

                if (root == null) {
                    return traversalOrder;
                }

                Queue<TreeNode> traversalQueue = new ArrayDeque<>();
                boolean leftToRight = true;

                traversalQueue.add(root);

                while (!traversalQueue.isEmpty()) {

                    int currentLevelSize = traversalQueue.size();

                    // Use LinkedList as a Deque to allow O(1) insertions at both ends
                    LinkedList<Integer> currentLevel = new LinkedList<>();

                    for (int i = 0; i < currentLevelSize; i++) {
                        TreeNode currentNode = traversalQueue.remove();

                        // Insert based on current level direction
                        if (leftToRight) {
                            currentLevel.addLast(currentNode.val);  // Normal order
                        } else {
                            currentLevel.addFirst(currentNode.val); // Reverse order
                        }

                        if (currentNode.left != null) {
                            traversalQueue.add(currentNode.left);
                        }
                        if (currentNode.right != null) {
                            traversalQueue.add(currentNode.right);
                        }
                    }

                    traversalOrder.add(currentLevel);
                    leftToRight = !leftToRight; // Toggle direction for the next level
                }

                return traversalOrder;
            }
        }

     */

    // Boundary Traversal

    /*

        import java.util.*;

        class Solution {

            public ArrayList<Integer> boundaryTraversal(Node root) {

                ArrayList<Integer> result = new ArrayList<>();

                if (root == null) {
                    return result;
                }

                // 1. Add root node if it's not a leaf node
                if (!isLeaf(root)) {
                    result.add(root.data);
                }

                // 2. Traverse left boundary (top-down, excluding leaves)
                leftBoundary(root.left, result);

                // 3. Traverse all leaf nodes from left to right
                leafNodes(root, result);

                // 4. Traverse right boundary (bottom-up, excluding leaves)
                rightBoundary(root.right, result);

                return result;
            }

            // Helper method to check if a node is a leaf
            private boolean isLeaf(Node node) {
                if (node == null) {
                    return false;
                }
                return node.left == null && node.right == null;
            }

            // Traverse left boundary top-down (skipping leaf nodes)
            private void leftBoundary(Node root, ArrayList<Integer> result) {

                Node curr = root;

                while (curr != null) {
                    if (!isLeaf(curr)) {
                        result.add(curr.data);
                    }

                    // Prefer left child over right child
                    if (curr.left != null) {
                        curr = curr.left;
                    } else {
                        curr = curr.right;
                    }
                }
            }

            // Inorder traversal to collect all leaf nodes from left to right
            private void leafNodes(Node root, ArrayList<Integer> result) {
                if (root == null) {
                    return;
                }

                if (isLeaf(root)) {
                    result.add(root.data);
                    return;
                }

                leafNodes(root.left, result);
                leafNodes(root.right, result);
            }

            // Traverse right boundary bottom-up (skipping leaf nodes)
            private void rightBoundary(Node root, ArrayList<Integer> result) {
                Node curr = root;
                ArrayList<Integer> temp = new ArrayList<>();

                // Traverse down the right side top-down first
                while (curr != null) {
                    if (!isLeaf(curr)) {
                        temp.add(curr.data);
                    }

                    // Prefer right child over left child
                    if (curr.right != null) {
                        curr = curr.right;
                    } else {
                        curr = curr.left;
                    }
                }

                // Add to result in reverse order (bottom-up)
                for (int i = temp.size() - 1; i >= 0; i--) {
                    result.add(temp.get(i));
                }
            }
        }

     */

    // Vertical Order Traversal of a Binary Tree

    /*

         Goal: Group tree nodes by vertical column (col), ordered from left to right.
         - Nodes in the same column are ordered from top to bottom by row (row).
         - If nodes share both the same row AND column, they must be sorted in
           ascending order by their values.


            class Solution {

                // Helper tuple to store node along with its 2D coordinates (row, col)
                private static class NodeState {
                    TreeNode node;
                    int row;
                    int col;

                    NodeState(TreeNode node, int row, int col) {
                        this.node = node;
                        this.row = row;
                        this.col = col;
                    }
                }

                public List<List<Integer>> verticalTraversal(TreeNode root) {
                    List<List<Integer>> verticalTraversalResult = new ArrayList<>();

                    if (root == null) {
                        return verticalTraversalResult;
                    }

                    // Data structure: Column -> (Row -> Min-Heap of Node Values)
                    Map<Integer, Map<Integer, PriorityQueue<Integer>>> colMap = new HashMap<>();

                    // Bounds tracking to iterate in column/row order without sorting
                    int minCol = 0;
                    int maxCol = 0;
                    int maxRow = 0;

                    Queue<NodeState> queue = new ArrayDeque<>();
                    queue.add(new NodeState(root, 0, 0));

                    // Step 1: BFS Traversal to collect coordinates and node values
                    while (!queue.isEmpty()) {
                        NodeState current = queue.poll();
                        TreeNode currentNode = current.node;
                        int currentRow = current.row;
                        int currentCol = current.col;

                        // Update global boundaries
                        minCol = Math.min(minCol, currentCol);
                        maxCol = Math.max(maxCol, currentCol);
                        maxRow = Math.max(maxRow, currentRow);

                        // Cleanly insert value into nested map structure
                        colMap.computeIfAbsent(currentCol, k -> new HashMap<>())
                                .computeIfAbsent(currentRow, k -> new PriorityQueue<>())
                                .add(currentNode.val);

                        // Process left child (moves down a row, left a column)
                        if (currentNode.left != null) {
                            queue.add(new NodeState(currentNode.left, currentRow + 1, currentCol - 1));
                        }

                        // Process right child (moves down a row, right a column)
                        if (currentNode.right != null) {
                            queue.add(new NodeState(currentNode.right, currentRow + 1, currentCol + 1));
                        }
                    }

                    // Step 2: Build final result list from leftmost column to rightmost column
                    for (int col = minCol; col <= maxCol; col++) {
                        List<Integer> currentColumnList = new ArrayList<>();
                        Map<Integer, PriorityQueue<Integer>> levelMap = colMap.get(col);

                        // Skip if this vertical distance column contains no nodes
                        if (levelMap == null) {
                            continue;
                        }

                        for (int row = 0; row <= maxRow; row++) {
                            if (!levelMap.containsKey(row)) {
                                continue;
                            }

                            PriorityQueue<Integer> minHeap = levelMap.get(row);

                            // Drain sorted elements for current (col, row) position
                            while (!minHeap.isEmpty()) {
                                currentColumnList.add(minHeap.poll());
                            }
                        }

                        if (!currentColumnList.isEmpty()) {
                            verticalTraversalResult.add(currentColumnList);
                        }
                    }

                    return verticalTraversalResult;
                }
            }

     */

    /*

         ----------------------------------------------------------------------------
         Time Complexity: O(N log N) -> dominated by sorting nodes in the same column/row.
         Space Complexity: O(N) -> to store all nodes in a flat list.

         Why DFS + Sorting is optimal here:
         1. Avoids expensive nested Map/PriorityQueue allocations.
         2. Custom sorting handles column order, row order, AND node value tie-breaking
            in a single pass.


            class Solution {

                private static class NodeInfo {
                    int col;
                    int row;
                    int val;

                    NodeInfo(int col, int row, int val) {
                        this.col = col;
                        this.row = row;
                        this.val = val;
                    }
                }

                public List<List<Integer>> verticalTraversal(TreeNode root) {
                    List<NodeInfo> nodeList = new ArrayList<>();

                    // Step 1: Collect all node coordinates via DFS
                    dfs(root, 0, 0, nodeList);

                    // Step 2: Sort based on problem rules
                    // 1. Primary: Leftmost column first (col ASC)
                    // 2. Secondary: Topmost row first (row ASC)
                    // 3. Tertiary: Smaller value first (val ASC)
                    Collections.sort(nodeList, (a, b) -> {
                        if (a.col != b.col) return Integer.compare(a.col, b.col);
                        if (a.row != b.row) return Integer.compare(a.row, b.row);
                        return Integer.compare(a.val, b.val);
                    });

                    // Step 3: Group sorted nodes into columns
                    List<List<Integer>> result = new ArrayList<>();
                    if (nodeList.isEmpty()) return result;

                    List<Integer> currentColumn = new ArrayList<>();
                    int lastCol = nodeList.get(0).col;

                    for (NodeInfo node : nodeList) {
                        // New column reached -> flush previous column to result
                        if (node.col != lastCol) {
                            result.add(currentColumn);
                            currentColumn = new ArrayList<>();
                            lastCol = node.col;
                        }
                        currentColumn.add(node.val);
                    }

                    // Add the final column
                    result.add(currentColumn);

                    return result;
                }

                private void dfs(TreeNode node, int row, int col, List<NodeInfo> nodeList) {
                    if (node == null) return;

                    nodeList.add(new NodeInfo(col, row, node.val));
                    dfs(node.left, row + 1, col - 1, nodeList);
                    dfs(node.right, row + 1, col + 1, nodeList);
                }
            }

     */

    // Top View of Binary Tree

    /*


         WHY BFS (LEVEL ORDER) IS BETTER THAN DFS (RECURSIVE) FOR TOP / BOTTOM VIEW:
         ----------------------------------------------------------------------------
         1. In Top View, for any vertical distance column, we want the node that appears FIRST
            at the SMALLEST depth (height).
         2. Level Order Traversal naturally visits nodes level-by-level from top to bottom.
            Therefore, the first node we encounter at any vertical distance is GUARANTEED to be
            the highest node.
         3. If we used DFS, a deep node on the left branch could be processed before a higher node
            on the right branch at the same vertical distance. To fix this in DFS, we would be
            forced to track both vertical distance AND node height/depth, adding extra complexity.


            class Solution {

                // Helper tuple to track a node alongside its vertical column coordinate
                static class QueueEntry {
                    Node node;
                    int hd; // Horizontal distance relative to root (0)

                    QueueEntry(Node node, int hd) {
                        this.node = node;
                        this.hd = hd;
                    }
                }

                public ArrayList<Integer> topView(Node root) {
                    ArrayList<Integer> topViewResult = new ArrayList<>();

                    if (root == null) {
                        return topViewResult;
                    }

                    // Stores map of Horizontal Distance -> First Node's Value
                    Map<Integer, Integer> topViewMap = new HashMap<>();

                    // Tracks bounds of horizontal distance to avoid sorting keys later
                    int minHd = 0;
                    int maxHd = 0;

                    Queue<QueueEntry> queue = new ArrayDeque<>();
                    queue.add(new QueueEntry(root, 0));

                    while (!queue.isEmpty()) {
                        QueueEntry current = queue.remove();
                        Node currentNode = current.node;
                        int currentHd = current.hd;

                        // Add value to map ONLY if this vertical column hasn't been seen yet
                        if (!topViewMap.containsKey(currentHd)) {
                            topViewMap.put(currentHd, currentNode.data);
                            minHd = Math.min(minHd, currentHd);
                            maxHd = Math.max(maxHd, currentHd);
                        }

                        // Process left child (moves left by -1)
                        if (currentNode.left != null) {
                            queue.add(new QueueEntry(currentNode.left, currentHd - 1));
                        }

                        // Process right child (moves right by +1)
                        if (currentNode.right != null) {
                            queue.add(new QueueEntry(currentNode.right, currentHd + 1));
                        }
                    }

                    // Collect node values from leftmost to rightmost column
                    for (int hd = minHd; hd <= maxHd; hd++) {
                        topViewResult.add(topViewMap.get(hd));
                    }

                    return topViewResult;
                }
            }

     */

    // Bottom View of Binary Tree

    /*


         WHY BFS (LEVEL ORDER) IS BETTER THAN DFS (RECURSIVE) FOR BOTTOM VIEW:
         ----------------------------------------------------------------------------
         1. In Bottom View, for any horizontal distance column, we want the node that appears LAST
            at the GREATEST depth (height).
         2. Level Order Traversal processes nodes level-by-level from top to bottom.
            Therefore, simply overwriting the value in our map as we process nodes top-to-bottom
            GUARANTEES that the final stored value for each horizontal distance is the deepest node.
         3. If we used DFS, a shallower node processed later in the recursion could overwrite
            a deeper node processed earlier at the same horizontal distance. To prevent this in DFS,
            we would be forced to explicitly track and compare node depth/height for every entry.


            class Solution {

                // Helper tuple to track a node alongside its horizontal distance
                static class QueueEntry {
                    Node node;
                    int hd; // Horizontal distance relative to root (0)

                    QueueEntry(Node node, int hd) {
                        this.node = node;
                        this.hd = hd;
                    }
                }

                public ArrayList<Integer> bottomView(Node root) {
                    ArrayList<Integer> bottomViewResult = new ArrayList<>();

                    if (root == null) {
                        return bottomViewResult;
                    }

                    // Stores map of Horizontal Distance -> Latest Node's Value
                    Map<Integer, Integer> bottomViewMap = new HashMap<>();

                    // Tracks bounds of horizontal distance to construct result in order without TreeMap
                    int minHd = 0;
                    int maxHd = 0;

                    Queue<QueueEntry> queue = new ArrayDeque<>();
                    queue.add(new QueueEntry(root, 0));

                    while (!queue.isEmpty()) {
                        QueueEntry current = queue.remove();
                        Node currentNode = current.node;
                        int currentHd = current.hd;

                        // OVERWRITE map value every time so the lowest/latest node seen wins
                        bottomViewMap.put(currentHd, currentNode.data);

                        minHd = Math.min(minHd, currentHd);
                        maxHd = Math.max(maxHd, currentHd);

                        // Process left child (moves left by -1)
                        if (currentNode.left != null) {
                            queue.add(new QueueEntry(currentNode.left, currentHd - 1));
                        }

                        // Process right child (moves right by +1)
                        if (currentNode.right != null) {
                            queue.add(new QueueEntry(currentNode.right, currentHd + 1));
                        }
                    }

                    // Collect node values from leftmost to rightmost column
                    for (int hd = minHd; hd <= maxHd; hd++) {
                        bottomViewResult.add(bottomViewMap.get(hd));
                    }

                    return bottomViewResult;
                }
            }

     */

    // Binary Tree Right Side View

    /*

         Approach: Root -> Right -> Left Recursive DFS

         Why it works:
         1. By visiting right children first, the first node encountered at any given
            depth/level is guaranteed to be the rightmost node visible from that side.
         2. We use `level == rightViewResult.size()` to detect if it's our first time
            visiting this level.


            class Solution {

                public List<Integer> rightSideView(TreeNode root) {
                    List<Integer> rightViewResult = new ArrayList<>();

                    // Start DFS from root at level 0
                    fetchRightSideViewDFS(root, 0, rightViewResult);

                    return rightViewResult;
                }

                private void fetchRightSideViewDFS(TreeNode currentNode, int currentLevel, List<Integer> rightViewResult) {
                    if (currentNode == null) {
                        return;
                    }

                    // First time reaching this level -> current node is the rightmost node
                    if (currentLevel == rightViewResult.size()) {
                        rightViewResult.add(currentNode.val);
                    }

                    // Prioritize right branch first, then left branch
                    fetchRightSideViewDFS(currentNode.right, currentLevel + 1, rightViewResult);
                    fetchRightSideViewDFS(currentNode.left, currentLevel + 1, rightViewResult);
                }
            }

     */

    // Symmetric Tree

    /*


         Approach: Mirror Comparison via Recursive DFS

         Logic:
         Two subtrees are mirror images if:
         1. Their root values are equal.
         2. Left subtree of the left node is a mirror of the right subtree of the right node.
         3. Right subtree of the left node is a mirror of the left subtree of the right node.


            class Solution {

                public boolean isSymmetric(TreeNode root) {

                    if (root == null) {
                        return true;
                    }

                    return isMirror(root.left, root.right);
                }

                private boolean isMirror(TreeNode leftNode, TreeNode rightNode) {

                    // Base case: If either node is null, both must be null to be symmetric
                    if (leftNode == null || rightNode == null) {
                        return leftNode == rightNode;
                    }

                    // Values must match
                    if (leftNode.val != rightNode.val) {
                        return false;
                    }

                    // Cross-compare subtrees (Outer branches & Inner branches)
                    boolean isOuterSymmetric = isMirror(leftNode.left, rightNode.right);
                    boolean isInnerSymmetric = isMirror(leftNode.right, rightNode.left);

                    return isOuterSymmetric && isInnerSymmetric;
                }
            }

     */

    // ROOT TO NODE PATH IN BINARY TREE

    /*

         ROOT TO NODE PATH IN BINARY TREE
         ----------------------------------------------------------------------------
         Approach: Backtracking via DFS

         Logic:
         1. Add current node to path list.
         2. If current node is the target, return true.
         3. Recursively search left and right subtrees.
         4. If target is not found in either branch, backtrack by removing the node.


            class Solution {

                public List<Integer> rootToNodePath(TreeNode root, TreeNode targetNode) {
                    List<Integer> pathResult = new ArrayList<>();

                    if (root == null || targetNode == null) {
                        return pathResult;
                    }

                    findPathDFS(root, targetNode, pathResult);
                    return pathResult;
                }

                private boolean findPathDFS(TreeNode currentNode, TreeNode targetNode, List<Integer> path) {
                    if (currentNode == null) {
                        return false;
                    }

                    // Add current node data to tentative path
                    path.add(currentNode.data);

                    // Target reached
                    if (currentNode == targetNode || currentNode.data == targetNode.data) {
                        return true;
                    }

                    // Explore left and right branches
                    if (findPathDFS(currentNode.left, targetNode, path) ||
                            findPathDFS(currentNode.right, targetNode, path)) {
                        return true;
                    }

                    // Backtrack if target node is not found in subtrees
                    path.remove(path.size() - 1);
                    return false;
                }
            }

     */

    // Lowest Common Ancestor of a Binary Tree

    /* OPTIMAL SOLUTION


         Logic:
         1. Base Case: If root is null, or matches either target node (p or q), return root.
         2. Recursively search left and right subtrees.
         3. Evaluation:
            - If left is null -> return right result.
            - If right is null -> return left result.
            - If both left and right are non-null -> current node is the LCA split point!


            class Solution {

                public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

                    // Base case: empty node or found one of the target nodes
                    if (root == null || root == p || root == q) {
                        return root;
                    }

                    TreeNode leftLca = lowestCommonAncestor(root.left, p, q);
                    TreeNode rightLca = lowestCommonAncestor(root.right, p, q);

                    // If target node is missing in left branch, return right result
                    if (leftLca == null) {
                        return rightLca;
                    }

                    // If target node is missing in right branch, return left result
                    if (rightLca == null) {
                        return leftLca;
                    }

                    // Both branches returned non-null -> root is the lowest common ancestor
                    return root;
                }
            }

     */

    /*

         Approach: Path Finding + Array Comparison

         Logic:
         1. Find the sequence of nodes from root to p (pathP).
         2. Find the sequence of nodes from root to q (pathQ).
         3. Compare pathP and pathQ from start to finish.
         4. The last matching node before divergence is the Lowest Common Ancestor.

         Time Complexity  : O(N) - Two DFS traversals O(N) + path comparison O(H)
         Space Complexity : O(H) - Lists to store path nodes up to tree height H


            class Solution {

                public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

                    List<TreeNode> pathP = new ArrayList<>();
                    List<TreeNode> pathQ = new ArrayList<>();

                    // Generate paths from root to both nodes
                    getPath(root, p, pathP);
                    getPath(root, q, pathQ);

                    // Find the last common node in both paths
                    TreeNode lcaNode = null;
                    int minPathLength = Math.min(pathP.size(), pathQ.size());

                    for (int i = 0; i < minPathLength; i++) {
                        if (pathP.get(i) == pathQ.get(i)) {
                            lcaNode = pathP.get(i);
                        } else {
                            break; // Paths diverged
                        }
                    }

                    return lcaNode;
                }

                private boolean getPath(TreeNode currentNode, TreeNode targetNode, List<TreeNode> currentPath) {
                    if (currentNode == null) {
                        return false;
                    }

                    currentPath.add(currentNode);

                    if (currentNode == targetNode) {
                        return true;
                    }

                    // Search left and right subtrees
                    if (getPath(currentNode.left, targetNode, currentPath) ||
                            getPath(currentNode.right, targetNode, currentPath)) {
                        return true;
                    }

                    // Backtrack if target is not found in this path
                    currentPath.remove(currentPath.size() - 1);
                    return false;
                }
            }

     */

    // Maximum Width of Binary Tree

    /*

         CONCEPT: BINARY TREE INDEXING & LEVEL WIDTH
         ----------------------------------------------------------------------------
         1. Complete Binary Tree Indexing:
            We can map binary tree nodes to array indices as if it were a Complete Binary Tree.

            - 0-Based Indexing (Current node = i):
              * Left Child  = 2 * i + 1
              * Right Child = 2 * i + 2

            - 1-Based Indexing (Current node = i):
              * Left Child  = 2 * i
              * Right Child = 2 * i + 1

         2. Overflow Problem in Skewed Trees:
            If a tree is deeply skewed (e.g., up to 10^5 nodes), calculating `2 * i + 1`
            repetitively will exponentially blow past `Integer.MAX_VALUE` and cause integer overflow.

         3. Solution (Level-Wise Index Normalization):
            To prevent overflow, we normalize node indices at every level.
            Before generating child indices, we subtract the level's minimum index (`startIdx`)
            from the current node's index:
              `normalizedIdx = currentIndex - startIdx`

            This resets the first node of every level to index `0`, keeping indices small
            and safe while preserving relative distances between nodes.

         4. Width Definition:
            Width of any level = `(endIdx - startIdx) + 1`
            (including null node spaces between the leftmost and rightmost non-null nodes).
         ----------------------------------------------------------------------------


            class Solution {

                // Helper tuple storing node reference and its index at the current level
                private static class NodeInfo {
                    TreeNode node;
                    int index;

                    NodeInfo(TreeNode node, int index) {
                        this.node = node;
                        this.index = index;
                    }
                }

                public int widthOfBinaryTree(TreeNode root) {

                    if (root == null) {
                        return 0;
                    }

                    int maxWidth = 0;
                    ArrayDeque<NodeInfo> queue = new ArrayDeque<>();
                    // Following 0-based indexing
                    queue.add(new NodeInfo(root, 0));

                    while (!queue.isEmpty()) {
                        int levelSize = queue.size();

                        // First and last node indices at the current level
                        int startIdx = queue.getFirst().index;
                        int endIdx = queue.getLast().index;

                        // Calculate width for current level
                        maxWidth = Math.max(maxWidth, (endIdx - startIdx) + 1);

                        for (int i = 0; i < levelSize; i++) {
                            NodeInfo current = queue.remove();
                            TreeNode currentNode = current.node;

                            // Subtract startIdx to reset index origin to 0 (prevents integer overflow)
                            int normalizedIdx = current.index - startIdx;

                            if (currentNode.left != null) {
                                queue.add(new NodeInfo(currentNode.left, 2 * normalizedIdx + 1));
                            }

                            if (currentNode.right != null) {
                                queue.add(new NodeInfo(currentNode.right, 2 * normalizedIdx + 2));
                            }
                        }
                    }

                    return maxWidth;
                }
            }

     */

    // Children Sum in a Binary Tree

    /*

         CONCEPT: CONVERT ARBITRARY BINARY TREE TO CHILDREN SUM PROPERTY
         ----------------------------------------------------------------------------
         Rule: Node value MUST equal sum of its left and right children values:
               `node.data = node.left.data + node.right.data`
         Constraint: You can ONLY INCREASE node values (decrementing is NOT allowed).

         Strategy (Top-Down Increment + Bottom-Up Backpropagation):
         1. Top-Down Phase:
            - Compare parent (`root.data`) with child sum (`childSum`).
            - If `childSum >= root.data`: Update parent value `root.data = childSum`.
            - If `childSum < root.data`: Push the larger parent value DOWN to both children
              (`root.left.data = root.data`, `root.right.data = root.data`). This ensures children
              never decrease in value.
         2. Recursive Step:
            - Recursively process left and right subtrees.
         3. Bottom-Up Backpropagation Phase:
            - On the return journey up the recursion stack, re-calculate the exact sum
              of updated children and update the parent `root.data = left.data + right.data`.
         ----------------------------------------------------------------------------


            class Solution {

                public void changeTreeToChildrenSumTree(Node root) {
                    if (root == null) {
                        return;
                    }

                    // Step 1: Top-Down Phase - Ensure children values are sufficient
                    int childSum = 0;

                    if (root.left != null) {
                        childSum += root.left.data;
                    }

                    if (root.right != null) {
                        childSum += root.right.data;
                    }

                    if (childSum >= root.data) {
                        root.data = childSum;
                    } else {
                        // Push parent value down to children to avoid decreasing values
                        if (root.left != null) {
                            root.left.data = root.data;
                        }
                        if (root.right != null) {
                            root.right.data = root.data;
                        }
                    }

                    // Step 2: Recurse into subtrees
                    changeTreeToChildrenSumTree(root.left);
                    changeTreeToChildrenSumTree(root.right);

                    // Step 3: Bottom-Up Phase - Sum up subtrees to set exact parent value
                    int totalChildSum = 0;

                    if (root.left != null) {
                        totalChildSum += root.left.data;
                    }

                    if (root.right != null) {
                        totalChildSum += root.right.data;
                    }

                    // Only update non-leaf nodes (leaves must retain their increased values)
                    if (root.left != null || root.right != null) {
                        root.data = totalChildSum;
                    }
                }
            }

     */

    //




}


