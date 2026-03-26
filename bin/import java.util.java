import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdminPanel {

    private List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void userManagementOptions() {
        int choice;
        while (true) {
            System.out.println("\nWelcome to E-Ryder Administrator Panel. What do you want to do?");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. EXIT");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addNewUsers();
                        break;
                    case 2:
                        viewRegisteredUsers();
                        break;
                    case 3:
                        removeRegisteredUsers();
                        break;
                    case 4:
                        updateRegisteredUsers();
                        break;
                    case 5:
                        System.out.println("Exiting program...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice. Please try again");
            }
        }
    }

    private void addNewUsers() {
        System.out.print("How many users do you want to add? ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Enter details for user " + (i + 1) + " ---");

            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();

            System.out.print("Email Address: ");
            String email = scanner.nextLine();

            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dob = scanner.nextLine();

            System.out.print("Card Number: ");
            String cardNum = scanner.nextLine();

            System.out.print("Card Expiry Date: ");
            String expiry = scanner.nextLine();

            System.out.print("Card Provider: ");
            String provider = scanner.nextLine();

            System.out.print("CVV: ");
            String cvv = scanner.nextLine();

            System.out.print("User Type: ");
            String userType = scanner.nextLine();

            String[] lastThreeTrips = new String[3];

            for (int j = 0; j < 3; j++) {
                System.out.println("\nTrip " + (j + 1) + " Details:");
                System.out.print("Trip Date (YYYY-MM-DD): ");
                String date = scanner.nextLine();

                System.out.print("Source: ");
                String source = scanner.nextLine();

                System.out.print("Destination: ");
                String dest = scanner.nextLine();

                System.out.print("Fare (€): ");
                String fare = scanner.nextLine();

                System.out.print("Feedback (NULL if none): ");
                String feedback = scanner.nextLine();

                StringBuilder trip = new StringBuilder();
                trip.append("Date: ").append(date)
                    .append(", Source: ").append(source)
                    .append(", Destination: ").append(dest)
                    .append(", Fare (€): ").append(fare)
                    .append(", Feedback: ").append(feedback);

                lastThreeTrips[j] = trip.toString();
            }

            RegisteredUsers user = new RegisteredUsers(
                    fullName, email, dob, cardNum, expiry, provider, cvv, userType, lastThreeTrips
            );
            registeredUsersList.add(user);
            System.out.println("User added successfully.");
        }
    }

    private void viewRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to display");
            return;
        }

        System.out.println("\n--- All Registered Users ---");
        for (RegisteredUsers user : registeredUsersList) {
            System.out.println("---------------------------------");
            System.out.println(user);
        }
    }

    private void removeRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }

        System.out.print("Enter email to remove: ");
        String email = scanner.nextLine();
        boolean found = false;

        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equals(email)) {
                iterator.remove();
                found = true;
                System.out.println("User removed successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("No user found with this email address");
        }
    }

    private void updateRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to update");
            return;
        }

        System.out.print("Enter email to update: ");
        String email = scanner.nextLine();
        RegisteredUsers target = null;

        for (RegisteredUsers u : registeredUsersList) {
            if (u.getEmailAddress().equalsIgnoreCase(email)) {
                target = u;
                break;
            }
        }

        if (target == null) {
            System.out.println("No user found with this email address");
            return;
        }

        System.out.println("\nUpdate user info (Press ENTER for no change, 0 for no change on numbers)");

        System.out.print("Type new full name: ");
        String fn = scanner.nextLine();
        if (!fn.isEmpty()) target.setFullName(fn);

        System.out.print("Type new email: ");
        String em = scanner.nextLine();
        if (!em.isEmpty()) target.setEmailAddress(em);

        System.out.print("Type new DOB: ");
        String dob = scanner.nextLine();
        if (!dob.isEmpty()) target.setDateOfBirth(dob);

        System.out.print("Type new card number (0 to keep): ");
        String cn = scanner.nextLine();
        if (!cn.equals("0")) target.setCardNumber(cn);

        System.out.print("Type new expiry date: ");
        String exp = scanner.nextLine();
        if (!exp.isEmpty()) target.setCardExpiryDate(exp);

        System.out.print("Type new provider: ");
        String prov = scanner.nextLine();
        if (!prov.isEmpty()) target.setCardProvider(prov);

        System.out.print("Type new CVV (0 to keep): ");
        String cvv = scanner.nextLine();
        if (!cvv.equals("0")) target.setCvv(cvv);

        System.out.print("Type new user type: ");
        String ut = scanner.nextLine();
        if (!ut.isEmpty()) target.setUserType(ut);

        System.out.println("User updated successfully.");
    }
}