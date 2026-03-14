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

    /// //////// TRAVERSALS //////////////////

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
    public void iterativePostorder(Node root, ArrayList<Integer> al) {
        if (root == null) return;

    }

}
