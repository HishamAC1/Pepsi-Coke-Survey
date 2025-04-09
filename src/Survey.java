import java.io.*;
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

        saveResultsToFile(cokePreferenceCount, pepsiPreferenceCount, noPreferenceCount);

        // Load and display past results at the end
        loadAndDisplayPastResults();

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

    private static void saveResultsToFile(int cokeCount, int pepsiCount, int noPreferenceCount) {
        String fileName = "survey_results.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Preference,Count");
            writer.newLine();
            writer.write("Coke," + cokeCount);
            writer.newLine();
            writer.write("Pepsi," + pepsiCount);
            writer.newLine();
            writer.write("No Preference," + noPreferenceCount);
            writer.newLine();
            System.out.println("Results saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the results: " + e.getMessage());
        }
    }

    private static void loadAndDisplayPastResults() {
        String fileName = "survey_results.csv";
        System.out.println("Loading cumulative past survey results...");
        int totalCokeCount = 0;
        int totalPepsiCount = 0;
        int totalNoPreferenceCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine(); // Skip the header row
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    try {
                        String preference = parts[0].trim();
                        int count = Integer.parseInt(parts[1].trim());
                        switch (preference) {
                            case "Coke":
                                totalCokeCount += count;
                                break;
                            case "Pepsi":
                                totalPepsiCount += count;
                                break;
                            case "No Preference":
                                totalNoPreferenceCount += count;
                                break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid line: " + line);
                    }
                }
            }
            System.out.println("Cumulative Results:");
            System.out.println("Coke: " + totalCokeCount);
            System.out.println("Pepsi: " + totalPepsiCount);
            System.out.println("No Preference: " + totalNoPreferenceCount);
        } catch (FileNotFoundException e) {
            System.out.println("No past results found.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the results: " + e.getMessage());
        }
    }
}