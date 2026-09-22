package I_BinarySearchTree;

public class BinarySearchTrees {

    ////////////// CONCEPT ///////////////

    /*
         ============================================================================
         MODULE: BINARY SEARCH TREE (BST) - BASICS & FOUNDATIONS
         ============================================================================

         1. FUNDAMENTAL DEFINITION & INVARIANTS:
            - Left Subtree Invariant: Every node in a node's left subtree must have
              a value strictly smaller than the node's value (node.left.val < node.val).
            - Right Subtree Invariant: Every node in a node's right subtree must have
              a value strictly larger than the node's value (node.right.val > node.val).
            - Recursive Invariant: Both left and right subtrees must also independently
              be valid Binary Search Trees.
            - No Duplicates: Standard BSTs strictly disallow duplicate keys.
              (If duplicates are required, they are handled via frequency counters
               within nodes or by defining strict <= rules on one side).

         2. THE GOLDEN RULE OF BSTs:
            - Performing an INORDER TRAVERSAL (Left -> Root -> Right) on a valid BST
              always yields elements in STRICTLY INCREASING (SORTED) ORDER.
            - Key application: Validating BSTs, finding K-th smallest/largest elements.

         3. COMPLEXITY ANALYSIS:
            -------------------------------------------------------------------------
            Operation     | Average Case (Balanced) | Worst Case (Skewed / Line)
            -------------------------------------------------------------------------
            Search        | O(log N)                | O(N)
            Insertion     | O(log N)                | O(N)
            Deletion      | O(log N)                | O(N)
            Space Stack   | O(log N)                | O(N)
            -------------------------------------------------------------------------
            * Worst Case Note: If elements are inserted in already sorted order
              (1 -> 2 -> 3 -> 4), the tree degenerates into a Linked List (skewed tree)
              where tree height H = N, causing operations to degrade to O(N).
              Self-balancing BSTs (AVL, Red-Black Trees) enforce H = log N.

         4. BINARY TREE (BT) VS. BINARY SEARCH TREE (BST):
            - Search in standard Binary Tree: O(N) time (must scan nodes via BFS/DFS).
            - Search in BST: O(H) time (compares target with current node and
              discards half of the remaining subtrees at each step).
         ============================================================================
*/

    // Search in a Binary Search Tree

    /*

         Approach: Recursive BST Traversal

         Logic:
         1. Base Case: If `root` is null (val not found) or `root.val == val` (val found),
            return `root`.
         2. If `root.val < val`: The target value must lie in the right subtree
            (due to BST invariant: all right subtree nodes > root.val). Recurse right.
         3. Else (`root.val > val`): The target value must lie in the left subtree. Recurse left.

         Time Complexity  : O(H) - Where H is the height of the tree.
                            (O(log N) for balanced trees, O(N) for skewed trees)
         Space Complexity : O(H) - Auxiliary call stack space bounded by tree height H.

            class Solution {

                public TreeNode searchBST(TreeNode root, int val) {
                    // Base Case: empty node or value matched
                    if (root == null || root.val == val) {
                        return root;
                    }

                    // Target is greater -> search right subtree
                    if (root.val < val) {
                        return searchBST(root.right, val);
                    }

                    // Target is smaller -> search left subtree
                    return searchBST(root.left, val);
                }
            }

     */

    /*

         Approach: Iterative Path Traversal (Constant Auxiliary Space)

         Logic:
         1. Initialize `curr = root`.
         2. While `curr` is not null and `curr.val != val`:
            - If `val < curr.val`, move `curr = curr.left`.
            - Else (`val > curr.val`), move `curr = curr.right`.
         3. Return `curr` (either points to target node or null if not found).

         Time Complexity  : O(H) - Visits at most H nodes down a single path.
         Space Complexity : O(1) Auxiliary Space - No recursion stack or additional data structure.

            class Solution {

                public TreeNode searchBST(TreeNode root, int val) {
                    TreeNode curr = root;

                    while (curr != null && curr.val != val) {
                        if (val < curr.val) {
                            curr = curr.left;
                        } else {
                            curr = curr.right;
                        }
                    }

                    return curr;
                }
            }

     */

    //  FLOOR AND CEIL IN A BINARY SEARCH TREE

    /*

         FLOOR AND CEIL IN A BINARY SEARCH TREE
         ----------------------------------------------------------------------------
         Definitions:
         - Floor : Greatest value in BST <= key
         - Ceil  : Smallest value in BST >= key

         Logic:
         1. Finding Ceil:
            - If current node value == key, ceil is current node value (exact match).
            - If current node value > key, current node is a valid candidate for ceil.
              Save `ceil = temp.data` and search left to see if a smaller valid candidate exists.
            - If current node value < key, current node is too small to be ceil. Search right.

         2. Finding Floor:
            - RESET `temp = root` to search from the top of the tree.
            - If current node value == key, floor is current node value (exact match).
            - If current node value < key, current node is a valid candidate for floor.
              Save `floor = temp.data` and search right to see if a larger valid candidate exists.
            - If current node value > key, current node is too large to be floor. Search left.

         Time Complexity  : O(H) - Two independent BST traversals, each bounded by tree height H.
                            (O(log N) for balanced BSTs, O(N) for skewed BSTs)
         Space Complexity : O(1) Auxiliary Space - Pure iterative pointer manipulation.

            class Solution {

                public List<Integer> floorCeilOfBST(TreeNode root, int key) {
                    int floor = -1;
                    int ceil = -1;

                    // 1. FINDING CEIL (Smallest value >= key)
                    TreeNode temp = root;
                    while (temp != null) {
                        if (temp.data == key) {
                            ceil = temp.data;
                            break; // Exact match is the optimal ceil
                        } else if (temp.data > key) {
                            ceil = temp.data; // Candidate found, try to find a smaller candidate on the left
                            temp = temp.left;
                        } else {
                            temp = temp.right; // Value is too small, go right
                        }
                    }

                    // 2. FINDING FLOOR (Greatest value <= key)
                    temp = root;
                    while (temp != null) {
                        if (temp.data == key) {
                            floor = temp.data;
                            break; // Exact match is the optimal floor
                        } else if (temp.data < key) {
                            floor = temp.data; // Candidate found, try to find a larger candidate on the right
                            temp = temp.right;
                        } else {
                            temp = temp.left; // Value is too large, go left
                        }
                    }

                    List<Integer> result = new ArrayList<>();
                    result.add(floor);
                    result.add(ceil);
                    return result;
                }
            }

     */

    ////////////// PRACTICE PROBLEMS ///////////////

    // Insert into a Binary Search Tree

    /*
         ----------------------------------------------------------------------------
         Approach 1: Iterative Pointer Traversal

         Logic:
         1. Edge Case: If tree is empty (`root == null`), return `new TreeNode(val)`.
         2. Maintain a pointer `temp = root` to navigate down the BST.
         3. While traversing:
            - If `val > temp.val`:
              - If `temp.right != null`, move `temp = temp.right`.
              - Else, attach `temp.right = new TreeNode(val)` and break.
            - If `val < temp.val`:
              - If `temp.left != null`, move `temp = temp.left`.
              - Else, attach `temp.left = new TreeNode(val)` and break.
         4. Return original `root`.

         Time Complexity  : O(H) - Traverses down a single path bounded by tree height H.
                            (O(log N) for balanced BSTs, O(N) for skewed BSTs)
         Space Complexity : O(1) Auxiliary Space - Pure pointer manipulation without call stack.

            class Solution {

                public TreeNode insertIntoBST(TreeNode root, int val) {
                    // Edge Case: Inserting into an empty tree
                    if (root == null) {
                        return new TreeNode(val);
                    }

                    TreeNode temp = root;

                    while (temp != null) {
                        if (temp.val < val) {
                            if (temp.right != null) {
                                temp = temp.right;
                            } else {
                                temp.right = new TreeNode(val);
                                break;
                            }
                        } else {
                            if (temp.left != null) {
                                temp = temp.left;
                            } else {
                                temp.left = new TreeNode(val);
                                break;
                            }
                        }
                    }

                    return root;
                }
            }

     */

    // Delete Node in a BST

    /*

            import java.util.*;

         ----------------------------------------------------------------------------
         Approach: Iterative Node Search + Subtree Splice Helper

         Deletion Logic (3 Cases handled by helper):
         1. Case 1 (Leaf Node / No Left Child): Return `root.right`.
         2. Case 2 (No Right Child): Return `root.left`.
         3. Case 3 (Two Children):
            - Find the rightmost node of the left subtree (inorder predecessor).
            - Attach the original `root.right` to the right of this rightmost node.
            - Return `root.left` as the new root of this modified subtree.

         Time Complexity  : O(H) - Search phase takes O(H); splicing takes O(H). Overall linear to tree height H.
                            (O(log N) for balanced BSTs, O(N) for skewed BSTs)
         Space Complexity : O(1) Auxiliary Space - Pure iterative pointer manipulation.

            class Solution {

                public TreeNode deleteNode(TreeNode root, int key) {
                    if (root == null) {
                        return null;
                    }

                    // Target key is at the root node
                    if (root.val == key) {
                        return helperDelete(root);
                    }

                    TreeNode temp = root;

                    while (temp != null) {
                        if (temp.val < key) {
                            // Check if right child is the target node
                            if (temp.right != null && temp.right.val == key) {
                                temp.right = helperDelete(temp.right);
                                break;
                            } else {
                                temp = temp.right;
                            }
                        } else {
                            // Check if left child is the target node
                            if (temp.left != null && temp.left.val == key) {
                                temp.left = helperDelete(temp.left);
                                break;
                            } else {
                                temp = temp.left;
                            }
                        }
                    }

                    return root;
                }

                // Finds the rightmost node of a given subtree (inorder predecessor)
                private TreeNode findLastRight(TreeNode root) {
                    while (root.right != null) {
                        root = root.right;
                    }
                    return root;
                }

                // Splices the subtrees of a node being deleted and returns the new subtree root
                private TreeNode helperDelete(TreeNode root) {
                    if (root.left == null) {
                        return root.right;
                    }
                    if (root.right == null) {
                        return root.left;
                    }

                    // Find rightmost node of the LEFT subtree
                    TreeNode lastRight = findLastRight(root.left);
                    lastRight.right = root.right;

                    return root.left;
                }
            }

     */

    /*

         ----------------------------------------------------------------------------
         Approach: Iterative Node Search + Mirror Subtree Splice Helper (Right Subtree Priority)

         Deletion Logic (Inverted Splice Strategy):
         1. Case 1 (Leaf Node / No Right Child): Return `root.left`.
         2. Case 2 (No Left Child): Return `root.right`.
         3. Case 3 (Two Children):
            - Find the leftmost node of the right subtree (inorder successor).
            - Attach the original `root.left` to `firstLeft.left` (the left of this leftmost node).
            - Return `root.right` as the new root of this modified subtree.

         Time Complexity  : O(H) - Search phase takes O(H); splicing takes O(H). Overall linear to tree height H.
                            (O(log N) for balanced BSTs, O(N) for skewed BSTs)
         Space Complexity : O(1) Auxiliary Space - Pure iterative pointer manipulation.

            class Solution {

                public TreeNode deleteNode(TreeNode root, int key) {
                    if (root == null) {
                        return null;
                    }

                    // Target key is at the root node
                    if (root.val == key) {
                        return helperDelete(root);
                    }

                    TreeNode temp = root;

                    while (temp != null) {
                        if (temp.val < key) {
                            // Check if right child is the target node
                            if (temp.right != null && temp.right.val == key) {
                                temp.right = helperDelete(temp.right);
                                break;
                            } else {
                                temp = temp.right;
                            }
                        } else {
                            // Check if left child is the target node
                            if (temp.left != null && temp.left.val == key) {
                                temp.left = helperDelete(temp.left);
                                break;
                            } else {
                                temp = temp.left;
                            }
                        }
                    }

                    return root;
                }

                // Finds the leftmost node of a given subtree (inorder successor)
                private TreeNode findFirstLeft(TreeNode root) {
                    while (root.left != null) {
                        root = root.left;
                    }
                    return root;
                }

                // Splices the subtrees by attaching left subtree to the leftmost node of the RIGHT subtree
                private TreeNode helperDelete(TreeNode root) {
                    if (root.left == null) {
                        return root.right;
                    }
                    if (root.right == null) {
                        return root.left;
                    }

                    // Find leftmost node of the RIGHT subtree
                    TreeNode firstLeft = findFirstLeft(root.right);
                    firstLeft.left = root.left;

                    return root.right;
                }
            }

     */

    // KTH SMALLEST ELEMENT IN A BST

    /*

         Approach: Iterative Inorder Traversal using Explicit Stack

         Logic:
         1. An Inorder Traversal (Left -> Root -> Right) of a BST visits nodes in
            strictly ascending order.
         2. We use an explicit stack to simulate the recursive call stack:
            - Push all left descendants of `current` until reaching `null`.
            - Pop the top node from stack (this is the next smallest element in BST).
            - Increment `counter`.
            - If `counter == k`, we have found our answer; return `node.val`.
            - Set `current = node.right` and repeat.

         Time Complexity  : O(H + K) - Traverses down to the left leaf O(H), then pops K nodes.
         Space Complexity : O(H) Auxiliary Space - Stack holds at most H nodes (tree height).

            class Solution {

                public int kthSmallest(TreeNode root, int k) {
                    Stack<TreeNode> traversalStack = new Stack<>();
                    TreeNode current = root;
                    int counter = 0;

                    while (current != null || !traversalStack.isEmpty()) {
                        // 1. Go to the leftmost node
                        if (current != null) {
                            traversalStack.push(current);
                            current = current.left;
                        } else {
                            // 2. Process node (pop from stack)
                            TreeNode node = traversalStack.pop();
                            counter++;

                            if (counter == k) {
                                return node.val;
                            }

                            // 3. Move to right subtree
                            current = node.right;
                        }
                    }

                    return -1; // Fallback for invalid input
                }
            }

     */

    /*

         Approach: Morris Inorder Traversal (O(1) Auxiliary Space)

         Logic:
         1. For current node, if `left` is null, process current node, increment `counter`,
            check if `counter == k`, and move `current = current.right`.
         2. If `left` is not null, find predecessor (rightmost node in left subtree).
            - If `predecessor.right == null`: establish temporary thread (`predecessor.right = current`),
              move `current = current.left`.
            - If `predecessor.right == current`: break thread (`predecessor.right = null`),
              process current node, increment `counter`, check `k`, move `current = current.right`.
         3. Continue loop to completely restore all modified pointers before returning.

         Time Complexity  : O(N) - Every node/edge is visited at most twice.
         Space Complexity : O(1) Auxiliary Space - Modifies tree pointers in-place without recursion stack.

            class Solution {

                public int kthSmallest(TreeNode root, int k) {
                    TreeNode current = root;
                    int counter = 0;
                    int result = -1;

                    while (current != null) {
                        if (current.left == null) {
                            counter++;
                            if (counter == k) {
                                result = current.val;
                            }
                            current = current.right;
                        } else {
                            // Find the inorder predecessor (rightmost node in left subtree)
                            TreeNode predecessor = current.left;
                            while (predecessor.right != null && predecessor.right != current) {
                                predecessor = predecessor.right;
                            }

                            if (predecessor.right == null) {
                                predecessor.right = current; // Establish temporary thread
                                current = current.left;
                            } else {
                                predecessor.right = null; // Remove thread to restore tree structure
                                counter++;
                                if (counter == k) {
                                    result = current.val;
                                }
                                current = current.right;
                            }
                        }
                    }

                    return result;
                }
            }

     */

    /*

         Approach: Recursive Inorder Traversal (Replicating Iterative Left-First Logic)

         Logic:
         1. An Inorder Traversal (Left -> Root -> Right) visits nodes in strictly
            ascending order.
         2. We maintain two global state variables:
            - `counter`: tracks how many nodes have been processed.
            - `result`: stores the k-th smallest value once found.
         3. Helper function `inorder(root, k)`:
            - Base Case: if `root == null` or `counter >= k`, return early.
            - Traverse left subtree: `inorder(root.left, k)` (simulates pushing lefts onto stack).
            - Process current node:
                * `counter++`
                * if (`counter == k`), record `result = root.val` and stop processing.
            - Traverse right subtree: `inorder(root.right, k)` (simulates moving to root.right).

         Time Complexity  : O(H + K) - Traverses down to leftmost leaf O(H), then visits K nodes.
         Space Complexity : O(H) - Call stack depth equals tree height H.

            class Solution {

                private int counter = 0;
                private int result = -1;

                public int kthSmallest(TreeNode root, int k) {
                    inorder(root, k);
                    return result;
                }

                private void inorder(TreeNode node, int k) {
                    if (node == null || counter >= k) {
                        return;
                    }

                    // 1. Traverse left subtree (equivalent to stack.push(left))
                    inorder(node.left, k);

                    // 2. Process current node (equivalent to stack.pop())
                    counter++;
                    if (counter == k) {
                        result = node.val;
                        return; // Target found, stop further recursion
                    }

                    // 3. Traverse right subtree (equivalent to current = node.right)
                    inorder(node.right, k);
                }
            }

     */



}
