import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class PalindromeCheckerApp {

    // Statistics tracking
    static ArrayList<String> checkedStrings = new ArrayList<>();
    static HashMap<String, Boolean> results = new HashMap<>();

    // UC8: Case-sensitive mode flag
    static boolean caseSensitiveMode = false;

    // UC9: Special characters handling flag
    static boolean ignoreSpecialChars = true;

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
        String cleaned;

        // UC9: Handle special characters
        if (ignoreSpecialChars) {
            cleaned = str.replaceAll("[^a-zA-Z0-9]", "");
        } else {
            cleaned = str.replaceAll("\\s+", "");
        }

        // UC8: Case-sensitive mode handling
        if (!caseSensitiveMode) {
            cleaned = cleaned.toLowerCase();
        }

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

                    // Add to statistics
                    checkedStrings.add(input);
                    results.put(input, result);
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

    // UC7: Palindrome Statistics
    public static void displayPalindromeStatistics() {
        System.out.println("--- UC7: Palindrome Statistics ---\n");

        if (checkedStrings.isEmpty()) {
            System.out.println("⚠️  No palindromes checked yet.\n");
            return;
        }

        int totalChecked = checkedStrings.size();
        int palindromeCount = 0;
        int nonPalindromeCount = 0;

        for (Boolean isPalin : results.values()) {
            if (isPalin) {
                palindromeCount++;
            } else {
                nonPalindromeCount++;
            }
        }

        double palindromePercentage = (totalChecked > 0) ? (palindromeCount * 100.0) / totalChecked : 0;

        System.out.println("📊 STATISTICS SUMMARY:");
        System.out.println("=".repeat(50));
        System.out.println("Total Strings Checked: " + totalChecked);
        System.out.println("Palindromes Found: " + palindromeCount);
        System.out.println("Non-Palindromes: " + nonPalindromeCount);
        System.out.println("Palindrome Percentage: " + String.format("%.2f", palindromePercentage) + "%");
        System.out.println("=".repeat(50));

        System.out.println("\n📋 DETAILED LIST:");
        int count = 1;
        for (String str : checkedStrings) {
            boolean result = results.get(str);
            System.out.println(count + ". \"" + str + "\" -> " + (result ? "✓ Palindrome" : "✗ Not Palindrome"));
            count++;
        }
        System.out.println();
    }

    // UC8: Case-Sensitive Mode
    public static void caseSensitiveModePalindromeCheck() {
        System.out.println("--- UC8: Case-Sensitive Mode Palindrome Check ---\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose mode:");
        System.out.println("1. Case-Insensitive (default)");
        System.out.println("2. Case-Sensitive");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 2) {
            caseSensitiveMode = true;
            System.out.println("✓ Case-Sensitive Mode ENABLED\n");
        } else {
            caseSensitiveMode = false;
            System.out.println("✓ Case-Insensitive Mode ENABLED\n");
        }

        boolean continueChecking = true;

        while (continueChecking) {
            System.out.print("Enter a string to check (or 'exit' to quit): ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("\n✓ Exiting Case-Sensitive Mode Check\n");
                continueChecking = false;
            } else if (!isValidInput(userInput)) {
                System.out.println("❌ Invalid input! Please enter only alphabetic characters and spaces.\n");
            } else {
                boolean result = isPalindrome(userInput);
                String mode = caseSensitiveMode ? "[CASE-SENSITIVE]" : "[CASE-INSENSITIVE]";
                System.out.println(mode + " \"" + userInput + "\" is " + (result ? "a palindrome" : "NOT a palindrome") + "\n");
            }
        }
    }

    // UC9: Special Characters Handling
    public static void specialCharactersHandling() {
        System.out.println("--- UC9: Special Characters Handling ---\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose mode:");
        System.out.println("1. Ignore Special Characters (default)");
        System.out.println("2. Keep Special Characters");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 2) {
            ignoreSpecialChars = false;
            System.out.println("✓ Special Characters Mode ENABLED (only spaces removed)\n");
        } else {
            ignoreSpecialChars = true;
            System.out.println("✓ Ignore Special Characters Mode ENABLED\n");
        }

        boolean continueChecking = true;

        while (continueChecking) {
            System.out.print("Enter a string to check (or 'exit' to quit): ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("\n✓ Exiting Special Characters Mode\n");
                continueChecking = false;
            } else if (userInput.trim().isEmpty()) {
                System.out.println("❌ Input cannot be empty!\n");
            } else {
                boolean result = isPalindrome(userInput);
                String mode = ignoreSpecialChars ? "[IGNORE SPECIAL CHARS]" : "[KEEP SPECIAL CHARS]";
                System.out.println(mode + " \"" + userInput + "\" is " + (result ? "a palindrome" : "NOT a palindrome") + "\n");
            }
        }
    }

    public static void main(String[] args) {
        displayWelcomeMessage();
        checkHardcodedPalindromes();
        checkUserInputPalindrome();
        repeatedUserInputPalindrome();
        validatedUserInputPalindrome();
        exportResultsToFile();
        displayPalindromeStatistics();
        caseSensitiveModePalindromeCheck();
        specialCharactersHandling();
    }
}