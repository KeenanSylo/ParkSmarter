package com.parking;

import com.parking.controller.GarageController;
import com.parking.model.GarageSpot;
import com.parking.model.Ticket;
import com.parking.model.Vehicle;
import com.parking.repository.InMemorySpotRepository;
import com.parking.repository.SpotRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 1. WIRING: Create the dependencies
        // We need 5 spots for this demo
        SpotRepository repo = new InMemorySpotRepository(5);
        GarageController controller = new GarageController(repo);
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ParkSmarter System!");
        System.out.println("------------------------------");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Park a Car");
            System.out.println("2. Exit a Car");
            System.out.println("3. Quit");
            System.out.print("> ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                handlePark(scanner, controller);
            } else if (choice.equals("2")) {
                handleExit(scanner, controller);
            } else if (choice.equals("3")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private static void handlePark(Scanner scanner, GarageController controller) {
        System.out.print("Enter License Plate: ");
        String plate = scanner.nextLine();

        System.out.print("Enter Brand: ");
        String brand = scanner.nextLine();

        System.out.print("Enter Model: ");
        String model = scanner.nextLine();

        // Simple vehicle creation (You can add more prompts for Color/Brand if you want)
        Vehicle car = new Vehicle(plate, brand, model, "Unknown");

        GarageSpot spot = controller.enterCar(car);

        if (spot != null) {
            System.out.println("Car is parked Successfully!");
            System.out.println("Please park in Spot ID: " + spot.getId());
            System.out.println("Ticket ID: " + spot.getTicket().getId());
        } else {
            System.out.println("Sorry the garage is full!");
        }
    }

    private static void handleExit(Scanner scanner, GarageController controller) {
        System.out.print("Enter Spot ID to exit (1-5): ");
        try {
            int spotId = Integer.parseInt(scanner.nextLine());
            
            // Note: This returns a double (price) based on your latest Controller code
            double price = controller.exitCar(spotId);

            if (price >= 0) {
                System.out.println("Car exited successfully.");
                System.out.println("Please pay: " + price + " SEK");
            } else {
                System.out.println("Failed to exit car. Check Spot ID or if the spot is occupied.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        }
    }
}