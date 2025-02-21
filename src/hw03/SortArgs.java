package hw03;

import java.util.Arrays;

/**
 * Program that receives a list of arguments (strings), parse them to doubles and then sort them using Array.sort() method.
 */
public class SortArgs {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Empty list. Please try again with some parameters");
            return;
        }

        try {
            // Map String[] into double[]
            double[] numbers = Arrays.stream(args)
                    .mapToDouble(Double::parseDouble)
                    .toArray();

            // Sort double[] array using Arrays method
            Arrays.sort(numbers);

            // Print ordered list
            System.out.println("Ordered list: " + Arrays.toString(numbers));

        } catch (NumberFormatException e) {
            System.out.println("Error. Make sure all entries are valid numbers: " + e.getMessage());
        }
    }
}
