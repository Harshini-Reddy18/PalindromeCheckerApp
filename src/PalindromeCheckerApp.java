import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    // UC5: Input Validation
    public static boolean isValidInput(String input) {
        // Check if input is null or empty
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        // Check if input contains only alphabetic characters and spaces
        if (!input.matches("[a-zA-Z\\s]+")) {
            return false;
        }

        return true;
    }

    public static void validatedUserInputPalindrome() {
        System.out.println("--- UC5: Input Validation Palindrome Check ---\n");

        Scanner scanner = new Scanner(System.in);
        boolean continueChecking = true;

        while (continueChecking) {
            System.out.print("Enter a string to check if it's a palindrome (only alphabets and spaces allowed, or 'exit' to quit): ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("\nThank you for using Palindrome Checker App! Goodbye!\n");
                continueChecking = false;
            } else if (!isValidInput(userInput)) {
                System.out.println("❌ Invalid input! Please enter only alphabetic characters and spaces.\n");
            } else {
                boolean result = isPalindrome(userInput);
                System.out.println("✓ Result: \"" + userInput + "\" is " + (result ? "a palindrome" : "NOT a palindrome") + "\n");
            }
        }
    }

    // UC6: Export Results to File
    public static void exportResultsToFile() {
        System.out.println("--- UC6: Export Results to File ---\n");

        Scanner scanner = new Scanner(System.in);
        String fileName = "palindrome_results.txt";

        System.out.print("Enter number of palindromes to check: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Palindrome Checker Results\n");
            writer.write("Generated: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("=".repeat(50) + "\n\n");

            for (int i = 1; i <= count; i++) {
                System.out.print("Enter string " + i + ": ");
                String input = scanner.nextLine();

                if (isValidInput(input)) {
                    boolean result = isPalindrome(input);
                    String resultText = "\"" + input + "\" is " + (result ? "a palindrome" : "NOT a palindrome");
                    System.out.println("✓ " + resultText + "\n");
                    writer.write(i + ". " + resultText + "\n");
                } else {
                    System.out.println("❌ Invalid input! Skipping...\n");
                    writer.write(i + ". INVALID INPUT: \"" + input + "\"\n");
                }
            }

            writer.write("\n" + "=".repeat(50) + "\n");
            writer.write("End of Report\n");

            System.out.println("✓ Results exported to '" + fileName + "' successfully!\n");

        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        displayWelcomeMessage();
        checkHardcodedPalindromes();
        checkUserInputPalindrome();
        repeatedUserInputPalindrome();
        validatedUserInputPalindrome();
        exportResultsToFile();
    }
}