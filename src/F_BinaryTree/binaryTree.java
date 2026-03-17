package F_BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class binaryTree {

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

    /// ///////// TRAVERSALS //////////////////

    //PREORDER INORDER POSTORDER TRAVERSALS IN ONE TRAVERSAL
    public ArrayList<ArrayList<Integer>> PreInPostInOneTraversal(Node root) {
        ArrayList<Integer> preorder = new ArrayList<>();
        ArrayList<Integer> inorder = new ArrayList<>();
        ArrayList<Integer> postorder = new ArrayList<>();

        if (root == null) return new ArrayList<ArrayList<Integer>>();

        // in recursion we visit a node 3 times , first when we reach it for the first time, second when we come back
        // to it after visiting its right left subtree and third after visiting its right subtree

        Stack<Node> node = new Stack<>();
        Stack<Integer> state = new Stack<>();

        node.push(root);
        state.push(1); // 1 means node is visited for 1st time

        while (!state.isEmpty()) {
            if (state.peek() == 1) {
                //we are visiting node for the first time, add it to preorder, add the left child of node
                //to stack and increase the state of stack by 1
                preorder.add(node.peek().data);
                state.push(state.pop() + 1);
                //adding left child of node
                if (node.peek().left != null) {
                    node.push(node.peek().left);
                    state.push(1);
                }

            } else if (state.peek() == 2) {
                // means we have visited the entire left subtree and now came back to the node
                // add it inorder, increment the state and add the right child of node now
                inorder.add(node.peek().data);
                state.push(state.pop() + 1);
                if (node.peek().right != null) {
                    node.push(node.peek().right);
                    state.push(1);
                }
            } else if (state.peek() == 3) {
                // means we are visting the node 3rd time after visiting the right subtree
                // add it to postorder and remove it
                postorder.add(node.peek().data);
                node.pop();
                state.pop();
            }
        }
        ArrayList<ArrayList<Integer>> solution = new ArrayList<>();
        solution.add(preorder);
        solution.add(inorder);
        solution.add(postorder);
        return solution;
    }

    //PREORDER TRAVERSAL OF BINARY TREE
    public void preorderTraversal(Node root, ArrayList<Integer> al) {
        if (root == null) return;

        al.add(root.data);
        preorderTraversal(root.left, al);
        preorderTraversal(root.right, al);
    }

    //INORDER TRAVERSAL OF BINARY TREE
    public void inorderTraversal(Node root, ArrayList<Integer> al) {
        if (root == null) return;
        inorderTraversal(root.left, al);
        al.add(root.data);
        inorderTraversal(root.right, al);
    }

    //POSTORDER TRAVERSAL OF BINARY TREE
    public void postorderTraversal(Node root, ArrayList<Integer> al) {
        if (root == null) return;
        postorderTraversal(root.left, al);
        postorderTraversal(root.right, al);
        al.add(root.data);
    }

    //LEVEL ORDER TRAVERSAL OF BINARY TREE
    public void levelOrderTraversal(Node root, ArrayList<Integer> al) {
        if (root == null) return;

        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            Node current = q.remove();
            if (current == null) {
                if (q.isEmpty()) return;
                q.add(null);
            } else {
                al.add(current.data);
                if (current.left != null) q.add(current.left);
                if (current.right != null) q.add(current.right);
            }
        }
    }

    //ITERATIVE PREORDER TRAVERSAL OF BINARY TREE
    class NodeAndState {
        Node node;
        int state;

        NodeAndState(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    //using state
    public void iterativePreorderTraversal(Node root, ArrayList<Integer> al) {
        if (root == null) return;
        Stack<NodeAndState> s = new Stack<>();
        s.push(new NodeAndState(root, 1));
        while (!s.isEmpty()) {
            NodeAndState current = s.pop();
            if (current.state == 1) { // we are visiting node for the first time
                al.add(current.node.data);
                current.state = 2;
                s.push(current);
                if (current.node.left != null)
                    s.push(new NodeAndState(current.node.left, 1)); // add the left child
            } else if (current.state == 2) { // root and left subtree are traversed
                if (current.node.right != null)
                    s.push(new NodeAndState(current.node.right, 1));
            }
        }
    }

    //without using state(less complex and preferred)
    public void iterativePreorder(Node root, ArrayList<Integer> al) {
        if (root == null) return;
        Stack<Node> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            Node current = s.pop();
            al.add(current.data);
            if (current.right != null) s.push(current.right);
            if (current.left != null) s.push(current.left);
        }
    }

    //ITERATIVE INORDER TRAVERSAL OF BINARY TREE
    //using state
    public void iterativeInorderTraversal(Node root, ArrayList<Integer> al) {
        if (root == null) return;
        Stack<NodeAndState> s = new Stack<>();
        s.push(new NodeAndState(root, 1));
        while (!s.isEmpty()) {
            NodeAndState top = s.pop();
            if (top.state == 1) {
                top.state = 2;
                s.push(top);
                if (top.node.left != null) s.push(new NodeAndState(top.node.left, 1));
            } else if (top.state == 2) {
                al.add(top.node.data);
                if (top.node.right != null) s.push(new NodeAndState(top.node.right, 1));
            }
        }
    }

    //without using state (less complex and more clean)
    public void iterativeInorder(Node root, ArrayList<Integer> al) {
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {

            // go as far left as possible
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // process node
            current = stack.pop();
            al.add(current.data);

            // move to right subtree
            current = current.right;
        }
    }

    //POSTORDER TRAVERSAL OF BINARY TREE
    //using state
    public void iterativePostorderTraversal(Node root, ArrayList<Integer> al) {
        if (root == null) return;
        Stack<NodeAndState> s = new Stack<>();
        s.push(new NodeAndState(root, 1));
        while (!s.isEmpty()) {
            NodeAndState current = s.pop();
            if (current.state == 1) {
                current.state = 2;
                s.push(current);
                if (current.node.left != null) s.push(new NodeAndState(current.node.left, 1));
            } else if (current.state == 2) {
                current.state = 3;
                s.push(current);
                if (current.node.right != null) s.push(new NodeAndState(current.node.right, 1));
            } else if (current.state == 3) {
                al.add(current.node.data);
            }
        }
    }

    //without using state(less complex)
    public void iterativePostorderUsing2Stacks(Node root, ArrayList<Integer> al) {
        if (root == null) return;
        Stack<Node> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            Node current = s1.pop();
            s2.push(current.data);
            if (current.left != null) s1.push(current.left);
            if (current.right != null) s1.push(current.right);
            // like this when we are transferring elements from s1 to s2 it will be root at bottom then entire right
            // subtree traversed in postorder then left subtree traversed in postorder at top
        }
        while (!s2.isEmpty()) al.add(s2.pop());

    }

    public void iterativePostorderUsing1Stack(Node root, ArrayList<Integer> al) {
        if (root == null) return;

        Node current = root;
        Node lastVisited = null;
        Stack<Node> s = new Stack<>();
        while (current != null || !s.isEmpty()) {
            //go as far left as possible
            while (current != null) {
                s.push(current);
                current = current.left;
            }
            // look at the top node
            Node topNode = s.peek();
            //if right child exists and is not yet visited
            if (topNode.right != null && topNode.right != lastVisited) {
                //move to right subtree
                current = topNode.right;
            } else { //otherwise process the node and mark it as last visited
                al.add(topNode.data);
                lastVisited = s.pop(); //lastVisited = topNode
            }
        }

    }

    /// ///////// MEDIUM PROBLEMS //////////////////

    //MAXIMUM DEPTH OF A BINARY TREE
    public int maxDepth(Node root) {
        if (root == null) return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //CHECK IF THE BINARY TREE IS BALANCED BINARY TREE

    //brute tc = n^2, sc= height of tree
    public boolean isBalancedBinaryTree(Node root) {
        if (root == null) return true;

        return (Math.abs(maxDepth(root.left)- maxDepth(root.right)) <= 1) && isBalancedBinaryTree(root.left) && isBalancedBinaryTree(root.right);
    }

    //optimal
    public int[] isBalancedOptimal(Node root){ //[balanced,height]
        if(root==null) return new int[]{1,0};
        int left[] = isBalancedOptimal(root.left);
        int right[] = isBalancedOptimal(root.right);
        int balancedFlag =( Math.abs(left[1]-right[1])<=1)&&(left[0]==1)&&(right[0]==1)?1:0;
        int height = Math.max(left[1],right[1])+1;
        return new int[]{balancedFlag,height};
    }

    //another optimal (just a wrapper over our logic)
    //returns height if tree is balanced otherwise -1
    public int dfsHeight(Node root) {
        // returns balanced?height:-1
        // Base case: if the current node is NULL, return 0 (height of an empty tree)
        if (root == null) return 0;

        // Recursively calculate the height of the left subtree
        int leftHeight = dfsHeight(root.left);

        // If the left subtree is unbalanced, propagate the unbalance status
        if (leftHeight == -1)
            return -1;

        // Recursively calculate the height of the right subtree
        int rightHeight = dfsHeight(root.right);

        // If the right subtree is unbalanced, propagate the unbalance status
        if (rightHeight == -1)
            return -1;

        // Check if the difference in height between left and right subtrees is greater than 1
        // If it's greater, the tree is unbalanced, return -1 to propagate the unbalance status
        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        // Return the maximum height of left and right subtrees plus 1 (for the current node)
        return Math.max(leftHeight, rightHeight) + 1;
    }
    public boolean isBalanced(Node root) {
        return dfsHeight(root) != -1;
    }

    //CALCULATE THE DIAMETER OF A BINARY TREE
    int diameter = 0;
    int heightForDia(Node root) {
        if (root == null) return 0;
        int leftHeight = heightForDia(root.left);
        int rightHeight = heightForDia(root.right);
        diameter = Math.max(diameter, leftHeight + rightHeight);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    int diameterOfTree(Node root){
        heightForDia(root);
        return diameter;
    }

    //MAXIMUM SUM PATH IN BINARY TREE

    // Returns the maximum path sum starting from this node and extending downward
    int maxPathDown(Node root, int maxVal[]) {
        if (root == null) return 0;

        // Get max contribution from left subtree (ignore if negative)
        int left = Math.max(0, maxPathDown(root.left, maxVal));

        // Get max contribution from right subtree (ignore if negative)
        int right = Math.max(0, maxPathDown(root.right, maxVal));

        // Update global maximum:
        // Case where current node is the highest point (path passes through it)
        maxVal[0] = Math.max(maxVal[0], root.data + left + right);

        // Return max path sum including current node and ONE subtree
        // (only one branch can be extended upwards)
        return root.data + Math.max(left, right);
    }

    int maxPathSum(Node root){
        if(root == null) return 0;

        // Stores the overall maximum path sum across all nodes
        int maxVal[] = new int[]{Integer.MIN_VALUE};

        maxPathDown(root, maxVal);

        return maxVal[0];
    }

    //CHECK IF TWO TREES ARE IDENTICAL
    public boolean isIdentical(Node rootA, Node rootB) {
        //check root
        if (rootA == null || rootB == null) return rootA == rootB;
        if(rootA.data!=rootB.data) return false;

        //check for subtrees
        boolean leftSubtrees = isIdentical(rootA.left, rootB.left);
        boolean rightSubtrees = isIdentical(rootA.right, rootB.right);

        return leftSubtrees && rightSubtrees ;
    }


}
