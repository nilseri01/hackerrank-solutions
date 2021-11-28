import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

// https://www.hackerrank.com/challenges/swap-nodes-algo/problem

class Result {

    /*
     * Complete the 'swapNodes' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY indexes
     *  2. INTEGER_ARRAY queries
     */
     
    public static class Node {
        public int data;
        public int depth;
        public Node left;
        public Node right;
        
        public Node(int data, int depth) {
            this.data=data;
            this.depth=depth;
        }
    }
    
    public static Node buildTree(List<List<Integer>> indexes) {
        Queue<Node> parentQueue = new LinkedList<>();
        Node root = new Node(1, 1);
        parentQueue.add(root);
        for (int i = 0; i < indexes.size(); i++) {
            Node parent = parentQueue.poll(); 
            List<Integer> nodeValues = indexes.get(i);
            Node rightNode = null, leftNode = null;
            
            if (nodeValues.get(0) != -1) {
                leftNode = new Node(nodeValues.get(0), parent.depth + 1);
                parentQueue.add(leftNode);
            }   
            if (parent != null && leftNode != null) {
                parent.left = leftNode;
            }
            
            if (nodeValues.get(1) != -1) {
                rightNode = new Node(nodeValues.get(1), parent.depth + 1);
                parentQueue.add(rightNode);
            }   
            if (parent != null && rightNode != null) {
                parent.right = rightNode;
            }      
        }
        return root;
    }
    
    public static Node switchNodes(Node root, int depth) {      
      Queue<Node> queue = new LinkedList<Node>();
      queue.add(root);
      while (!queue.isEmpty()) {
          Node node = queue.poll();
          if (node.depth % depth == 0) {
              Node temp = node.left;
              node.left = node.right;
              node.right = temp;
          }

          if (node.left != null) {
              queue.add(node.left);
          }

          if (node.right != null) {
              queue.add(node.right);
          }
      }
      return root;
    }

    public static List<Integer> printInorder(Node node, List<Integer> values) {
        if (node == null) {
            return values;
        }

        values = printInorder(node.left, values);
 
        values.add(node.data);
 
        values = printInorder(node.right, values);
        
        return values;
    }
    
    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
    // Write your code here
        List<List<Integer>> updatedIndexesList = new ArrayList<>();
        Node root = buildTree(indexes);
        for (Integer query : queries) {
            root = switchNodes(root, query);
            updatedIndexesList.add(printInorder(root, new ArrayList<Integer>()));
        }
        return updatedIndexesList;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> indexes = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                indexes.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<List<Integer>> result = Result.swapNodes(indexes, queries);

        result.stream()
            .map(
                r -> r.stream()
                    .map(Object::toString)
                    .collect(joining(" "))
            )
            .map(r -> r + "\n")
            .collect(toList())
            .forEach(e -> {
                try {
                    bufferedWriter.write(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
