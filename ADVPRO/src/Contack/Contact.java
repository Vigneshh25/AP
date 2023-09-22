package Contack;

import java.io.*;
import java.util.*;
import java.sql.*;

public class Contact {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
//    private static final String FILENAME = "contacts.txt";

    public static void main(String[] args) {

        Map<String, String> contacts = loadContactsFromDatabase();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Add a contact");
            System.out.println("2. View a contact");
            System.out.println("3. Delete a contact");
            System.out.println("4. Search for a contact");
            System.out.println("5. Sort contacts");
            System.out.println("6. Exit");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.next();
                    if (validateInput(phoneNumber)) {
                        contacts.put(name, phoneNumber);
                        saveContactsToDatabase(name, phoneNumber);
                        System.out.println("Contact added successfully.");
                    } else {
                        System.out.println("Invalid phone number format.");
                    }
                    break;

                case 2:
                    System.out.print("Enter name: ");
                    String searchName = scanner.next();
                    if (contacts.containsKey(searchName)) {
                        String searchNumber = contacts.get(searchName);
                        System.out.println("Name: " + searchName + ", Phone number: " + searchNumber);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter name: ");
                    String deleteName = scanner.next();
                    if (contacts.containsKey(deleteName)) {
                        contacts.remove(deleteName);
                        deleteContactFromDatabase(deleteName);
                        System.out.println("Contact deleted successfully.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter search term: ");
                    String searchTerm = scanner.next();
                    searchContacts(contacts, searchTerm);
                    break;

                case 5:
                    System.out.println("Select a field to sort by:");
                    System.out.println("1. Name");
                    System.out.println("2. Phone number");
                    int field = scanner.nextInt();
                    sortContacts(contacts, field);
                    break;

                case 6:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void saveContactsToDatabase(String name, String phoneNumber) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO contacts (name, phone_number) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, phoneNumber);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving contact to database.");
            e.printStackTrace();
        }
    }

    private static Map<String, String> loadContactsFromDatabase() {
        Map<String, String> contacts = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT name, phone_number FROM contacts";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone_number");
                contacts.put(name, phoneNumber);
            }
        } catch (SQLException e) {
            System.err.println("Error loading contacts from database.");
            e.printStackTrace();
        }
        return contacts;
    }
    private static void deleteContactFromDatabase(String name) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM contacts WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting contact from database.");
            e.printStackTrace();
        }
    }

    private static boolean validateInput(String phoneNumber) {
        // Check if phone number matches the format (xxx) xxx-xxxx
        String regex = "\\(\\d{3}\\)\\s\\d{3}-\\d{4}";
        return phoneNumber.matches(regex);
    }

    private static void searchContacts(Map<String, String> contacts, String searchTerm) {
        boolean found = false;
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            String name = entry.getKey();
            String phoneNumber = entry.getValue();
            if (name.contains(searchTerm) || phoneNumber.contains(searchTerm)) {
                System.out.println("Name: " + name + ", Phone number: " + phoneNumber);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contacts found.");
        }
    }

    private static void sortContacts(Map<String, String> contacts, int field) {
        List<Map.Entry<String, String>> sortedList = new ArrayList<>(contacts.entrySet());
        switch (field) {
            case 1:
                // Sort by name
                sortedList.sort(Map.Entry.comparingByKey());
                break;

            case 2:
                // Sort by phone number
                sortedList.sort(Map.Entry.comparingByValue());
                break;

            default:
                System.out.println("Invalid field.");
                return;
        }

        System.out.println("Sorted contacts:");
        for (Map.Entry<String, String> entry : sortedList) {
            System.out.println("Name: " + entry.getKey() + ", Phone number: " + entry.getValue());
        }
    }
}

    /*private static void saveContactsToFile(Map<String, String> contacts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Error saving contacts to file.");
            e.printStackTrace();
        }
    }

    private static Map<String, String> loadContactsFromFile() {
        Map<String, String> contacts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    contacts.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading contacts from file.");
            e.printStackTrace();
        }
        return contacts;
    }*/

