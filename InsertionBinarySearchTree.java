import java.util.*;
import java.io.*;

// https://www.hackerrank.com/challenges/binary-search-tree-insertion/problem

class Node {
    Node left;
    Node right;
    int data;
    
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {
   
  	public static void preOrder( Node root ) {
      
    	if( root == null)
        	return;
      
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
     
    }

 /* Node is defined as :
 class Node 
    int data;
    Node left;
    Node right;
    
    */

	public static Node insert(Node root,int data) {
        if (root == null) {
            return new Node(data);
        }
        Node parent = root, temp = root;
        boolean isAddedToRight = false;
        while (temp != null) {
            parent = temp;
            if (temp.data < data) {
                temp = temp.right;
                isAddedToRight = true;
            } else {
                temp = temp.left;
                isAddedToRight = false;
            }
        }
        
        if (isAddedToRight) {
            parent.right = new Node(data);
        } else {
            parent.left = new Node(data);
        }
        return root;
    }

	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
      	preOrder(root);
    }	
}
