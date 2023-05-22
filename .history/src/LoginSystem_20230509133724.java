import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginSystem implements Constants {
    private Map<String, String> userAccounts;

    public LoginSystem() {
        userAccounts = new HashMap<>();
    }

    public static void main(String[] args) {
        LoginSystem loginSystem = new LoginSystem();
        Scanner scanner = new Scanner(System.in);
        boolean runProgram = true;

        while (runProgram) {
            System.out.println("1. Login");
            System.out.println("2. Create account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    String userID = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    if (loginSystem.login(userID, password)) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Invalid user ID or password.");
                    }
                    break;
                case 2:
                    System.out.print("Enter User ID: ");
                    userID = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    loginSystem.createAccount(userID, password);
                    break;
                case 3:
                    runProgram = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    public boolean login(String userID, String password) {
        if (userAccounts.containsKey(userID) && userAccounts.get(userID).equals(password)) {
            return true;
        }
        return false;
    }

    public void createAccount(String userID, String password) {
        if (userAccounts.containsKey(userID)) {
            System.out.println("User ID already exists. Please choose a different user ID.");
        } else {
            userAccounts.put(userID, password);
            System.out.println("Account created successfully!");
        }
    }
}