// src/Survey.java
import java.util.Scanner;

public class Survey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of users: ");
        int numberOfUsers = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        int[] cokeCounts = new int[numberOfUsers];
        int[] pepsiCounts = new int[numberOfUsers];

        for (int i = 0; i < numberOfUsers; i++) {
            System.out.println("User " + (i + 1) + ": Please select your preference for each pair of products:");

            int cokeCount = 0;
            cokeCount += getUserChoice(scanner, "Coke", "Pepsi");
            cokeCount += getUserChoice(scanner, "Sprite", "7-Up");
            cokeCount += getUserChoice(scanner, "Fanta", "Crush");
            cokeCount += getUserChoice(scanner, "Minute Maid", "Tropicana");
            cokeCount += getUserChoice(scanner, "Barq's Root Beer", "MUG Root Beer");
            cokeCount += getUserChoice(scanner, "Dasani", "Aquafina");

            int pepsiCount = 6 - cokeCount; // Since there are 6 questions, the remaining choices are for Pepsi

            cokeCounts[i] = cokeCount;
            pepsiCounts[i] = pepsiCount;
        }

        System.out.println("Survey Results:");
        for (int i = 0; i < numberOfUsers; i++) {
            System.out.println("User " + (i + 1) + ":");
            System.out.println("Coke products selected: " + cokeCounts[i]);
            System.out.println("Pepsi products selected: " + pepsiCounts[i]);

            if (cokeCounts[i] > pepsiCounts[i]) {
                System.out.println("You prefer Coke products.");
            } else if (pepsiCounts[i] > cokeCounts[i]) {
                System.out.println("You prefer Pepsi products.");
            } else {
                System.out.println("You have no clear preference between Coke and Pepsi products.");
            }
        }

        scanner.close();
    }

    private static int getUserChoice(Scanner scanner, String option1, String option2) {
        System.out.println("1. " + option1);
        System.out.println("2. " + option2);
        System.out.print("Enter 1 or 2: ");
        int choice = scanner.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.print("Invalid choice. Please enter 1 or 2: ");
            choice = scanner.nextInt();
        }
        return choice == 1 ? 1 : 0;
    }
}