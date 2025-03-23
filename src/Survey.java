import java.util.Scanner;

public class Survey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cokeCount = 0;
        int pepsiCount = 0;

        System.out.println("Please select your preference for each pair of products:");

        cokeCount += getUserChoice(scanner, "Coke", "Pepsi");
        cokeCount += getUserChoice(scanner, "Sprite ", "7-Up");
        cokeCount += getUserChoice(scanner, "Fanta", "Crush");
        cokeCount += getUserChoice(scanner, "Minute Maid", "Tropicana");
        cokeCount += getUserChoice(scanner, "Barq's Root Beer", "MUG Root Beer");
        cokeCount += getUserChoice(scanner, "Dasani", "Aquafina ");

        pepsiCount = 6 - cokeCount; // Since there are 6 questions, the remaining choices are for Pepsi

        System.out.println("Survey Results:");
        System.out.println("Coke products selected: " + cokeCount);
        System.out.println("Pepsi products selected: " + pepsiCount);

        if (cokeCount > pepsiCount) {
            System.out.println("You prefer Coke products.");
        } else if (pepsiCount > cokeCount) {
            System.out.println("You prefer Pepsi products.");
        } else {
            System.out.println("You have no clear preference between Coke and Pepsi products.");
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