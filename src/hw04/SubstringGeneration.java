package hw04;

/**
 * Program that takes a string as parameter and prints all substrings cropping from start and end. Output example:
 * David
 * Davi
 * Dav
 * Da
 * D
 * d
 * id
 * vid
 * avid
 * David
 */
public class SubstringGeneration {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Empty string. Please provide a non empty string as argument.");
            return;
        }

        StringBuilder concatArgs = new StringBuilder();
        for (String arg : args)
        {
            concatArgs.append(arg);
            concatArgs.append(" ");
        }
        concatArgs.delete(concatArgs.length()-1, concatArgs.length());

        int r = concatArgs.length();
        int l = 0;
        for (; r >= 1; --r)
            System.out.println(concatArgs.substring(l, r));

        r = concatArgs.length();
        l = concatArgs.length() - 1;
        for (; l >= 0; --l)
            System.out.println(concatArgs.substring(l, r));
    }
}
