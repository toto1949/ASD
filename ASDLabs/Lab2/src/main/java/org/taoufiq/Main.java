package org.taoufiq;


import java.io.IOException;
import java.util.Scanner;

import org.taoufiq.services.EmployeeService;

public class Main {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. List Employees");
            System.out.println("2. Show Upcoming Enrollees");
            System.out.println("3. Exit");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();

            try {
                switch (option) {
                    case 1:
                        System.out.println(service.getAllEmployeesJson());
                        break;
                    case 2:
                        System.out.println(service.getUpcomingEnrolleesJson());
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
