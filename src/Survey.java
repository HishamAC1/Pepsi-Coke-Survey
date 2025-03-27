// src/Survey.java
import java.util.Scanner;

public class Survey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of users: ");
        int numberOfUsers = getValidInt(scanner);

        int cokePreferenceCount = 0;
        int pepsiPreferenceCount = 0;
        int noPreferenceCount = 0;

        for (int i = 0; i < numberOfUsers; i++) {
            System.out.println("User " + (i + 1) + ": Please select your preference for each pair of products:");

            int cokeCount = 0;
            cokeCount += getUserChoice(scanner, "Coke", "Pepsi");
            cokeCount += getUserChoice(scanner, "Sprite", "7-Up");
            cokeCount += getUserChoice(scanner, "Fanta", "Crush");
            cokeCount += getUserChoice(scanner, "Minute Maid", "Tropicana");
            cokeCount += getUserChoice(scanner, "Barq's Root Beer", "MUG Root Beer");
            cokeCount += getUserChoice(scanner, "Dasani", "Aquafina");

            if (cokeCount > 3) {
                cokePreferenceCount++;
            } else if (cokeCount < 3) {
                pepsiPreferenceCount++;
            } else {
                noPreferenceCount++;
            }
        }

        System.out.println("Overall Survey Results:");
        System.out.println("Number of users who preferred Coke products: " + cokePreferenceCount);
        System.out.println("Number of users who preferred Pepsi products: " + pepsiPreferenceCount);
        System.out.println("Number of users with no clear preference: " + noPreferenceCount);

        scanner.close();
    }

    private static int getUserChoice(Scanner scanner, String option1, String option2) {
        System.out.println("1. " + option1);
        System.out.println("2. " + option2);
        System.out.print("Enter 1 or 2: ");
        return getValidInt(scanner, 1, 2) == 1 ? 1 : 0;
    }

    private static int getValidInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static int getValidInt(Scanner scanner, int min, int max) {
        int value;
        do {
            value = getValidInt(scanner);
            if (value < min || value > max) {
                System.out.print("Invalid choice. Please enter a number between " + min + " and " + max + ": ");
            }
        } while (value < min || value > max);
        return value;
    }
}