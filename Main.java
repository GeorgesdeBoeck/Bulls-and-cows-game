package bullscows;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String digits = scanner.next();

        String numChar = scanner.next();

        boolean loopBreaker = checkInputValidity(digits, numChar);

        String code = "";
        if(!loopBreaker){
            code = generateSecretCode(digits, numChar);
        }

        System.out.println("Okay, let's start the game!");

        int counter = 1;

        while (!loopBreaker) {
            String input = scanner.nextLine();
            System.out.println("Turn " + counter + ":");
            loopBreaker = checkForBulls(input, loopBreaker, code, digits);
            counter++;
        }
    }

    public static String generateSecretCode(String inputDigits, String inputChars) {
        int digits = Integer.parseInt(inputDigits);
        int chars = Integer.parseInt(inputChars);

        Character[] chosenCharArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Character[] charArray = Arrays.copyOf(chosenCharArray, chars);
        List<Character> charList = Arrays.asList(charArray);
        System.out.print("*".repeat(digits));
        System.out.println("");
        System.out.println(charList);

        Collections.shuffle(charList);
        String codeString = "";

        for (int i = 0; i < digits; i++) {
            codeString += charList.get(i);
        }

        System.out.println(codeString);
        return codeString;
    }

    public static List<Character> convertStringToCharList (String input) {
        List<Character> chars = new ArrayList<>();

        for (char ch : input.toCharArray()) {
            chars.add(ch);
        }

        return chars;
    }

    public static boolean checkInputValidity (String inputDigits, String inputChars) {
        int digits;
        int chars;
        boolean loopBreaker = false;

        if (inputDigits.matches("^[a-zA-Z]+") || inputDigits.contains(" ")) {
            System.out.println("Error: " + inputDigits +"isn't a valid number.");
            loopBreaker = true;
        } else if (inputChars.matches("^[a-zA-Z]+") || inputChars.contains(" ")) {
            System.out.println("Error: " + inputChars +"isn't a valid number.");
            loopBreaker = true;
        } else {
            digits = Integer.parseInt(inputDigits);
            chars = Integer.parseInt(inputChars);

            if (chars < digits) {
                System.out.println("Error: it's not possible to generate a code with a length of " + digits + " with " + chars + " unique symbols.");
                loopBreaker = true;
            } else if (chars > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                loopBreaker = true;
            } else if (chars < 1 || digits < 1) {
                System.out.println("Error: input can't be 0 or negative.");
                loopBreaker = true;
            } else {
                System.out.println("Generating secret code");
            }
        }

        return loopBreaker;
    }

    public static boolean checkForBulls(String input, boolean loopBreaker, String code, String inputDigits) {
        // generate variables for bulls and cows
        int digits = Integer.parseInt(inputDigits);

        int bulls = 0;
        int cows = 0;

        // split input value and code value into character arrays
        char[] inputArray = input.toCharArray();
        List<Character> codeList = convertStringToCharList(code);

        for (int i = 0; i < inputArray.length; i++) {
            if (codeList.contains(inputArray[i]) && codeList.get(i) == inputArray[i]) {
                bulls++;
            } else if (codeList.contains(inputArray[i])) {
                cows++;
            }
        }

        //checking for bulls & cows
        if (bulls == digits) {
            System.out.println("Grade: " + bulls + " bulls");
            System.out.println("Congrats! The secret code is " + code);
            loopBreaker = true;
        } else if (bulls > 1 && cows > 1) {
            System.out.println("Grade: " + bulls +" bulls and " + cows + " cows.");
        } else if (bulls == 1 && cows == 1) {
            System.out.println("Grade: " + bulls + " bull and " + cows + " cow.");
        } else if (bulls == 1 && cows > 1) {
            System.out.println("Grade: " + bulls + " bull and " + cows + " cows.");
        } else if (bulls > 1 && cows == 0) {
            System.out.println("Grade: " + bulls +" bulls.");
        } else if (bulls == 1 && cows == 0) {
            System.out.println("Grade: " + bulls +" bull.");
        } else if (bulls == 0 && cows > 1) {
            System.out.println("Grade: " + cows +" cows.");
        } else if (bulls == 0 && cows == 1) {
            System.out.println("Grade: " + cows +" cow.");
        } else {
            System.out.println("Grade: None.");
        }

        return loopBreaker;
    }

}
