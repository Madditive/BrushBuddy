package com.brushbuddy;
import java.util.Scanner;

public class BrushBuddy {

    public static void main(String[] args) {
        Project myProject = new Project();
        Scanner reader = new Scanner(System.in); // Create a Scanner object for input

        System.out.println("Welcome to BrushBuddy, your painting project assistant!");

        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\nPlease select an option:");
            System.out.println("1 - Create a new room");
            System.out.println("2 - Add a wall to a room");
            System.out.println("3 - Finish and calculate paint needed");
            System.out.println("4 - Exit");

            System.out.print("Enter your choice: ");
            int userChoice = reader.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.println("Enter room name: ");
                    reader.nextLine(); // Add this line to consume the leftover newline character
                    String roomName = reader.nextLine();

                    // Ensure roomName is not empty
                    while(roomName.trim().isEmpty()){
                        System.out.println("Room name cannot be empty. Please enter a valid name:");
                        roomName = reader.nextLine();
                    }

                    Room newRoom = new Room(roomName);
                    myProject.addRoom(newRoom);
                    System.out.println(STR."Room '\{roomName}' added.");
                    break;
                case 2:
                    if (myProject.getRooms().isEmpty()) {
                        System.out.println("No rooms available. Please create a room first.");
                        break;
                    }

                    Room currentRoom = myProject.getRooms().getLast();
                    System.out.println(STR."Adding wall to room: \{currentRoom.getName()}");

                    System.out.print("Enter wall height (millimeters): ");
                    double height = getPositiveDouble(reader);
                    System.out.print("Enter wall width (millimeters): ");
                    double width = getPositiveDouble(reader);

                    Wall wall = new Wall(height, width);

                    System.out.println("Would you like to add non-paintable areas? (yes/no)");
                    reader.nextLine(); // Consume the newline
                    String answer = reader.nextLine();
                    while (answer.equalsIgnoreCase("yes")) {
                        System.out.print("Enter non-paintable area height (millimeters): ");
                        double npHeight = getPositiveDouble(reader);
                        System.out.print("Enter non-paintable area width (millimeters): ");
                        double npWidth = getPositiveDouble(reader);
                        NonPaintableArea npArea = new NonPaintableArea(npHeight, npWidth);
                        wall.addNonPaintableArea(npArea);
                        System.out.println("Non-paintable area added.");

                        System.out.println("Add another non-paintable area? (yes/no)");
                        reader.nextLine(); // Consume the newline
                        answer = reader.nextLine().trim();
                    }

                    currentRoom.addWall(wall);
                    System.out.println(STR."Wall added to \{currentRoom.getName()}.");
                    break;
                case 3:
                    // Use Project to calculate total paintable area
                    double totalPaintableArea = myProject.calculateTotalPaintable();
                    System.out.println(STR."Total paintable area: \{totalPaintableArea} square meters.");

                    System.out.println("Enter the number of coats needed: ");
                    int coats = reader.nextInt();
                    if (coats < 1) coats = 1; // Ensure at least one coat

                    System.out.println("Enter coverage per can (in square meters): ");
                    double coveragePerCan = reader.nextDouble();

                    totalPaintableArea *= coats; // Adjust area based on number of coats
                    int totalCansNeeded = PaintEstimator.calculatePaintCansRequired(totalPaintableArea, coveragePerCan);

                    System.out.println(STR."Total cans of paint needed: \{totalCansNeeded}");
                    break;
                case 4:
                    keepRunning = false; // Exit the loop
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        System.out.println("Thank you for using BrushBuddy. Goodbye!");
        reader.close(); // Close the reader when finished
    }

    private static double getPositiveDouble(Scanner reader) {
        double value;
        do {
            while (!reader.hasNextDouble()) {
                System.out.println("Please enter a valid number.");
                reader.next(); //discard non-numeric input
            }
            value = reader.nextDouble();
            if(value <= 0) {
                System.out.println("Please enter a positive number.");
            }
        } while (value <= 0);
        return value;
    }
}