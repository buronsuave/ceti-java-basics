package hw02;

/**
 * Program that get two random numbers and prints a message with the biggest
 */
public class MaxRandom {
    public static void main(String[] args) {
        int a = (int) (Math.random()*100);
        int b = (int) (Math.random()*100);

        String message = "The max of (" + a + ", " + b + ") is " + Math.max(a, b);
        System.out.println(message);
    }
}
