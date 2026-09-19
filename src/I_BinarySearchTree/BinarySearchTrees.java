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




}
