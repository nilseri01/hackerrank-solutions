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

// https://www.hackerrank.com/challenges/qheap1/problem

class Result {

    /*
     * Complete the 'getMax' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY operations as parameter.
     */

    public static List<Integer> getMax(List<String> operations) {
    // Write your code here
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
        
        for (String operation : operations) {
            String[] values = operation.split(" ");
            switch (values[0]) {
                case "1":
                    Integer addedValue = Integer.valueOf(values[1]);
                    pQueue.add(addedValue);
                    break;
                case "2":
                    Integer removedValue = Integer.valueOf(values[1]);
                    if (pQueue.contains(removedValue)) {
                        pQueue.remove(removedValue);
                    }
                    break;
                case "3":
                    if (!pQueue.isEmpty()) {
                        result.add(pQueue.peek());
                    }
                    break;
            }
        }
        return result;
    }
}

public class Solution {

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> ops = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }).collect(toList());

        List<Integer> res = Result.getMax(ops);

        bufferedWriter.write(
            res.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
