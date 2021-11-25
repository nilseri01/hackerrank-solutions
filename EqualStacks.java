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

// https://www.hackerrank.com/challenges/equal-stacks/problem

class Result {

    /*
     * Complete the 'equalStacks' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY h1
     *  2. INTEGER_ARRAY h2
     *  3. INTEGER_ARRAY h3
     */
    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
    // Write your code here

        int maxHeight = 0;

        Stack<Integer> st1 = getStack(h1);
        Stack<Integer> st2 = getStack(h2);
        Stack<Integer> st3 = getStack(h3);

        // Run a loop until every stack has at-least 1 element
        while (!st1.isEmpty() && !st2.isEmpty() && !st3.isEmpty()) {

            int stack1Height = st1.peek();
            int stack2Height = st2.peek();
            int stack3Height = st3.peek();

            // If all stacks are of same height, just return the height
            if (stack1Height == stack2Height && stack2Height == stack3Height) {
                return st1.peek();
            }

            int highestArrayIndex = findHighestArrayIndex(stack1Height, stack2Height, stack3Height);
            switch (highestArrayIndex) {
                case 1:
                    st1.pop();
                    break;
                case 2:
                    st2.pop();
                    break;
                case 3:
                    st3.pop();
                    break;
            }
        }

        return 0;
    }
    
    private static Stack<Integer> getStack(List<Integer> h) {
        Stack<Integer> s = new Stack<>();
        Integer height = 0;
        for (int i = h.size() - 1; i >= 0; i--) {
            height = h.get(i);
            if (!s.isEmpty()) {
                height += s.peek();
            }
            s.push(height);
        }
        return s;
    }
    
    public static Integer findHighestArrayIndex(Integer sum1, Integer sum2, Integer sum3) { 
        Integer maxSum = sum1, index = 1;
        if (sum2 > maxSum) {
            index = 2;
            maxSum = sum2;
        }
        if (sum3 > maxSum) {
            index = 3;
        }
        
        return index;
    }
    
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n1 = Integer.parseInt(firstMultipleInput[0]);

        int n2 = Integer.parseInt(firstMultipleInput[1]);

        int n3 = Integer.parseInt(firstMultipleInput[2]);

        List<Integer> h1 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> h2 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> h3 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.equalStacks(h1, h2, h3);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
