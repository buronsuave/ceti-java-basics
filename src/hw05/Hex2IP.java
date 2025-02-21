package hw05;

/**
 * Program that parses and validates IP Decimal representation and IP Hexadecimal representation. Type of parsing and value is given as an argument.
 */
public class Hex2IP {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid number of arguments. Expected 2 [-hex | -ip] <value>");
            return;
        }

        Hex2IP hex2IP = new Hex2IP();
        if (args[0].equals("-hex")) {
            hex2IP.IPFromHex(args[1]);
            return;
        }

        if (args[0].equals("-ip")) {
            hex2IP.HexFromIP(args[1]);
        }
    }

    private void IPFromHex(String hex) {
        if (!isValidHex(hex)) {
            System.out.println("Error. Giving string is not a valid hex representation of an IP");
            return;
        }

        // Proceed to decode
        StringBuilder ipString = new StringBuilder();
        for (int k = 0; k <= 6; k+=2) {
            ipString.append(hex2Dec(hex.substring(k, k + 2)));
            ipString.append(".");
        }
        ipString.delete(ipString.length()-1, ipString.length());
        System.out.println(ipString);
    }
    private void HexFromIP(String ip) {
        if (!isValidIP(ip)) {
            System.out.println("Error. Giving string is not a valid IP representation");
            return;
        }

        // Proceed to decode
        StringBuilder hex = new StringBuilder();
        String[] oct = ip.split("\\.");
        for (String oc : oct)
            hex.append(dec2Hex(Integer.parseInt(oc)));
        System.out.println(hex);
    }

    private boolean isValidHex(String hex) {
        if (hex.length() != 8) return false;
        for (char c : hex.toCharArray())
            if (c < '0' || (c > '9' && c < 'A') || c > 'F')
                return false;
        return true;
    }

    private boolean isValidIP(String ip) {
        String[] oct = ip.split("\\.");
        if (oct.length != 4) return false;
        for (String oc : oct)
            if (Integer.parseInt(oc) < 0 || Integer.parseInt(oc) > 255)
                return false;
        return true;
    }

    private int hex2Dec(String hex) {
        int dec = 0;
        int currPow = (int) Math.pow(16, hex.length());

        for (int r = 0; r < hex.length(); ++r) {
            currPow /= 16;
            if (hex.toCharArray()[r] >= '0' && hex.toCharArray()[r] <= '9') {
                dec += ((hex.toCharArray()[r] - '0') * currPow);
                continue;
            }
            switch (hex.toCharArray()[r]) {
                case 'A': dec += (10 * currPow); break;
                case 'B': dec += (11 * currPow); break;
                case 'C': dec += (12 * currPow); break;
                case 'D': dec += (13 * currPow); break;
                case 'E': dec += (14 * currPow); break;
                case 'F': dec += (15 * currPow); break;
                default: throw new IllegalStateException("Unexpected value: " + hex.toCharArray()[r]);
            }
        }
        return dec;
    }

    private String dec2Hex(int dec) {
        String hex = "";
        int upper = dec / 16;
        int lower = dec % 16;

        if (upper > 9) {
            switch (upper) {
                case 10: hex += "A"; break;
                case 11: hex += "B"; break;
                case 12: hex += "C"; break;
                case 13: hex += "D"; break;
                case 14: hex += "E"; break;
                case 15: hex += "F"; break;
                default: throw new IllegalStateException("Unexpected value: " + upper);
            }
        } else {
            hex += upper;
        }

        if (lower > 9) {
            switch (lower) {
                case 10: hex += "A"; break;
                case 11: hex += "B"; break;
                case 12: hex += "C"; break;
                case 13: hex += "D"; break;
                case 14: hex += "E"; break;
                case 15: hex += "F"; break;
                default: throw new IllegalStateException("Unexpected value: " + lower);
            }
        } else {
            hex += lower;
        }

        return hex;
    }
}
