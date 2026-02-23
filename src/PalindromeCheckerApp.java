import java.util.Scanner;

public class PalindromeCheckerApp {

    // UC1: Welcome Message Module
    public static void displayWelcomeMessage() {
        System.out.println("====================================");
        System.out.println("   Welcome to Palindrome Checker App ");
        System.out.println("====================================");
        System.out.println("This application checks if a string is a palindrome.");
        System.out.println("====================================\n");
    }

    // UC2: Hardcoded Palindrome Check
    public static boolean isPalindrome(String str) {
        // Remove spaces and convert to lowercase
        String cleaned = str.replaceAll("\\s+", "").toLowerCase();

        // Check if string is palindrome
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }

    public static void checkHardcodedPalindromes() {
        System.out.println("--- UC2: Hardcoded Palindrome Check ---\n");

        String[] testStrings = {"racecar", "hello", "level", "noon", "world"};

        for (String test : testStrings) {
            boolean result = isPalindrome(test);
            System.out.println("\"" + test + "\" is " + (result ? "a palindrome" : "NOT a palindrome"));
        }
        System.out.println();
    }

    // UC3: User Input Palindrome Check
    public static void checkUserInputPalindrome() {
        System.out.println("--- UC3: User Input Palindrome Check ---\n");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string to check if it's a palindrome: ");
        String userInput = scanner.nextLine();

        boolean result = isPalindrome(userInput);

        System.out.println("\nResult: \"" + userInput + "\" is " + (result ? "a palindrome" : "NOT a palindrome\n"));
    }

    // UC4: Repeated User Input
    public static void repeatedUserInputPalindrome() {
        System.out.println("--- UC4: Repeated User Input Palindrome Check ---\n");

        Scanner scanner = new Scanner(System.in);
        boolean continueChecking = true;

        while (continueChecking) {
            System.out.print("Enter a string to check if it's a palindrome (or 'exit' to quit): ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("\nThank you for using Palindrome Checker App! Goodbye!\n");
                continueChecking = false;
            } else {
                boolean result = isPalindrome(userInput);
                System.out.println("Result: \"" + userInput + "\" is " + (result ? "a palindrome" : "NOT a palindrome") + "\n");
            }
        }
    }

    public static void main(String[] args) {
        displayWelcomeMessage();
        checkHardcodedPalindromes();
        checkUserInputPalindrome();
        repeatedUserInputPalindrome();
    }
}