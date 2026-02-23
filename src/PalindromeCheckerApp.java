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

    public static void main(String[] args) {
        displayWelcomeMessage();
        checkHardcodedPalindromes();
    }
}