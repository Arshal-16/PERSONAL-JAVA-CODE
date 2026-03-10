package F_BinaryTree;

import java.util.ArrayDeque;
import java.util.Queue;

public class binaryTree {
    /// //////// LEARNING //////////////////
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

    /// /TRAVERSALS////
    //PREORDER
    void preorderTraversal(Node root) {  //root->left subtree->right subtree
        if (root == null) return;
        System.out.println(root.data);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }

    //INORDER
    void inorderTraversal(Node root) {   //left subtree->root->right subtree
        if (root == null) return;
        inorderTraversal(root.left);
        System.out.println(root.data);
        inorderTraversal(root.right);
    }

    //POSTORDER
    void postorderTraversal(Node root) {  //left subtree->right subtree->root
        if (root == null) return;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.println(root.data);
    }

    //LEVEL ORDER TRAVERSAL
    void levelOrderTraversal(Node root){
        if(root == null) return ;
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node currNode = q.remove();
            if(currNode==null){
                System.out.println();
                if(q.isEmpty()) return;
                q.add(null);
            }else{
                System.out.print(currNode.data+" ");
                if(currNode.left!=null) q.add(currNode.left);
                if(currNode.right!=null) q.add(currNode.right);
            }
        }
    }

    //HEIGHT OF A TREE
    int heightOfTree(Node root){
        if(root==null) return 0;

        int heightLeftSubtree= heightOfTree(root.left);
        int heightRightSubtree = heightOfTree(root.right);
        return Math.max(heightLeftSubtree ,heightRightSubtree) +1;
    } //height in terms of node, height of leaf is assumed to be 1

    //COUNT OF NODES
    int totalNodes(Node root){
        if(root == null) return 0;
        return totalNodes(root.left) + totalNodes(root.right) + 1;
    }

    //SUM OF NODES
    int sumOfNodes(Node root){
        if(root==null) return 0;

        int leftSum = sumOfNodes(root.left);
        int rightSum = sumOfNodes(root.right);
        return leftSum+rightSum+root.data;
    }

     
}
