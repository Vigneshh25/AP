package QueryEngine;

import java.sql.*;
import java.util.Scanner;

public class QueryEngine {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/Vicky";
    private static final String USER = "root";
    private static final String PASSWORD = "12qwaszx";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/employee_db", "root", "12qwaszx");

            System.out.println("Welcome to the Employee Query Engine");
            System.out.println("1. Show all employee data");
            System.out.println("2. Show employee data based on conditions");
            System.out.println("3. Show reporting hierarchy for an employee");
            System.out.println("4. Show employees reporting to a manager");
            System.out.println("5. Show summary of Department, Designation, ReportingTo");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showAllEmployeeData(connection);
                    break;
                case 2:
                    showEmployeeDataBasedOnConditions(connection, scanner);
                    break;
                case 3:
                    showReportingHierarchy(connection, scanner);
                    break;
                case 4:
                    showEmployeesUnderManager(connection, scanner);
                    break;
                case 5:
                    showSummary(connection);
                    break;
                case 6:
                    System.out.println("Exiting the Employee Query Engine");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }  finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }

    private static void showAllEmployeeData(Connection connection) throws SQLException
    {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
        System.out.println("ID\tName\tAge\tDesignation\tDepartment\tReporting To");
        while (resultSet.next())
        {
            System.out.println(
                    resultSet.getInt("ID") + "\t"
                            + resultSet.getString("Name") + "\t"
                            + resultSet.getInt("Age") + "\t"
                            + resultSet.getString("Designation") + "\t"
                            + resultSet.getString("Department") + "\t"
                            + resultSet.getString("ReportingTo"));
        }
    }

    private static void showEmployeeDataBasedOnConditions(Connection connection, Scanner scanner) throws SQLException
    {
        System.out.print("Enter field to compare: ");
        String field = scanner.next();
        System.out.print("Enter comparison operator: ");
        String operator = scanner.next();
        System.out.print("Enter value: ");
        String query = "SELECT * FROM employees WHERE ";
        if (field.equalsIgnoreCase("age")) {
            int value = scanner.nextInt();
            query += field + " " + operator + " " + value;
        } else {
            String value = scanner.next();
            if (operator.equalsIgnoreCase("startswith")) {
                query += field + " LIKE '" + value + "%'";
            } else if (operator.equalsIgnoreCase("contains")) {
                query += field + " LIKE '%" + value + "%'";
            } else if (operator.equalsIgnoreCase("endswith")) {
                query += field + " LIKE '%" + value + "'";
            } else if (operator.equalsIgnoreCase("notcontains")) {
                query += field + " NOT LIKE '%" + value + "%'";
            } else if (operator.equalsIgnoreCase("equals")) {
                query += field + " = '" + value + "'";
            } else if (operator.equalsIgnoreCase("notequals")) {
                query += field + " != '" + value + "'";
            }
        }

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        System.out.println("ID\tName\tAge\tDesignation\tDepartment\tReporting To");
        while (resultSet.next())
        {
            System.out.println(
                    resultSet.getInt("ID") + "\t"
                            + resultSet.getString("Name") + "\t"
                            + resultSet.getInt("Age") + "\t"
                            + resultSet.getString("Designation") + "\t" + resultSet.getString("Department") + "\t"
                            + resultSet.getString("ReportingTo"));
        }
    }


        private static void showReportingHierarchy(Connection connection, Scanner scanner) throws SQLException
        {
            System.out.print("Enter employee name: ");
            String employeeName = scanner.next();
            String manager = getManagerForEmployee(connection, employeeName);
            System.out.println(employeeName + " -> " + manager);

//            while (!manager.equals("A")) {
//                manager = getManagerForEmployee(connection, manager);
//                System.out.println(manager);
//            }
        }

        private static String getManagerForEmployee(Connection connection, String employeeName) throws SQLException {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ReportingTo FROM employees WHERE Name='" + employeeName + "'");
            if (resultSet.next()) {
                return resultSet.getString("ReportingTo");
            }
            return "";
        }

        private static void showEmployeesUnderManager(Connection connection, Scanner scanner) throws SQLException {
            System.out.print("Enter manager name: ");
            String managerName = "1";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees WHERE ReportingTo='" + managerName + "'");

            System.out.println("ID\tName\tAge\tDesignation\tDepartment\tReporting To");
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("ID") + "\t"
                                + resultSet.getString("Name") + "\t"
                                + resultSet.getInt("Age") + "\t"
                                + resultSet.getString("Designation") + "\t"
                                + resultSet.getString("Department") + "\t"
                                + resultSet.getString("ReportingTo"));
            }
        }

        private static void showSummary(Connection connection) throws SQLException {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Department, Designation, ReportingTo, COUNT(*) as count FROM employees GROUP BY Department, Designation, ReportingTo");

            System.out.println("Department\tDesignation\tReporting To\tCount");
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString("Department") + "\t"
                                + resultSet.getString("Designation") + "\t"
                                + resultSet.getString("ReportingTo") + "\t"
                                + resultSet.getInt("count"));
            }
        }
    }


