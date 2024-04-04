package com.brushbuddy;

import java.util.Scanner;

public class BrushBuddy {

    private static final Scanner reader = new Scanner(System.in);
    private static final Project project = new Project();

    public static void main(String[] args) {
        System.out.println("Welcome to BrushBuddy, your painting project assistant!");

        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\nPlease select an option:");
            System.out.println("1 - Create a new room");
            System.out.println("2 - Add a wall to a room");
            System.out.println("3 - Finish and calculate paint needed for the project");
            System.out.println("4 - Exit");

            System.out.print("Enter your choice: ");
            int userChoice = reader.nextInt();
            reader.nextLine();

            switch (userChoice) {
                case 1:
                    createRoom();
                    break;
                case 2:
                    addWallToRoom();
                    break;
                case 3:
                    calculatePaintNeeded();
                    break;
                case 4:
                    keepRunning = false;
                    System.out.println("Thank you for using BrushBuddy. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        reader.close(); // Close the reader when finished
    }

    private static void createRoom() {
        System.out.print("Enter room name: ");
        String roomName = reader.nextLine();
        while(roomName.trim().isEmpty()){
            System.out.println("Room name cannot be empty. Please enter a valid name:");
            roomName = reader.nextLine();
        }
        Room room = new Room(roomName);
        project.addRoom(room);
        System.out.println(STR."Room '\{roomName}' added to the project.");
    }

    private static void addWallToRoom() {
        if (project.getRooms().isEmpty()) {
            System.out.println("No rooms have been added to the project yet. Please add a room first.");
            return;
        }

        Room room = project.getRooms().getLast();
        System.out.println(STR."Select the type of wall to add to room: \{room.getName()}");
        System.out.println("1 - Rectangular Wall");
        System.out.println("2 - Irregular Wall");
        System.out.print("Enter your choice: ");
        int wallType = reader.nextInt();
        reader.nextLine();

        Wall wall = null;
        switch (wallType) {
            case 1: // Rectangular Wall
                wall = addRectangularWall();
                break;
            case 2: // Irregular Wall
                wall = addIrregularWall();
                break;
            default:
                System.out.println("Invalid option selected. Please try again.");
                return;
        }

        if (wall != null) {
            addNonPaintableAreas(wall);
            room.addWall(wall);
            System.out.println(STR."Wall added to the room: \{room.getName()}");
        }
    }

    private static Wall  addRectangularWall() {
        System.out.println("Enter width in millimeters: ");
        double width = getPositiveDouble();

        System.out.println("Enter height in millimeters: ");
        double height = getPositiveDouble();

        return new RectangularWall(width, height);
    }

    private  static Wall  addIrregularWall() {
        IrregularWall irregularWall = new IrregularWall();
        boolean addingSections = true;
        double base, height, width;

        System.out.println("For an irregular wall, you will need to break down the wall into triangles and rectangles.");

        while (addingSections) {
            System.out.println("Choose the type of section to add:");
            System.out.println("1 - Triangle section");
            System.out.println("2 - Rectangle section");
            System.out.println("3 - Finished adding sections");
            System.out.print("Your choice: ");
            int sectionChoice = reader.nextInt();
            reader.nextLine();

            switch (sectionChoice) {
                case 1: // Add Triangle section
                    System.out.println("Enter base length in millimeters: ");
                    base = getPositiveDouble();
                    System.out.println("Enter height in millimeters: ");
                    height = getPositiveDouble();
                    TriangularSection triangle = new TriangularSection(base, height);
                    irregularWall.addSection(triangle);
                    System.out.println("Triangle section added.");
                    break;
                case 2: // Add rectangle section
                    System.out.print("Enter width in millimeters: ");
                    width = getPositiveDouble();
                    System.out.print("Enter height in millimeters: ");
                    height = getPositiveDouble();
                    RectangularSection rectangle = new RectangularSection(width, height);
                    irregularWall.addSection(rectangle);
                    System.out.println("Rectangle section added.");
                    break;
                case 3: // Finished adding sections
                    addingSections = false;
                    addNonPaintableAreas(irregularWall);
                    break;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }

        return irregularWall;
    }

    private static void addNonPaintableAreas(Wall wall) {
        System.out.print("Do you want to add non-paintable areas? (yes/no): ");
        String answer = reader.nextLine().trim();
        while (answer.equalsIgnoreCase("yes")) {
            System.out.print("Enter width of non-paintable area in millimeters: ");
            double npWidth = getPositiveDouble();
            System.out.print("Enter height of non-paintable area in millimeters: ");
            double npHeight = getPositiveDouble();
            NonPaintableArea npArea = new NonPaintableArea(npWidth, npHeight);
            wall.addNonPaintableArea(npArea);
            System.out.println("Non-paintable area added.");
            System.out.print("Add another non-paintable area? (yes/no): ");
            answer = reader.nextLine().trim();
        }
    }

    private static void calculatePaintNeeded() {
        System.out.print("Enter coverage per can (square meters): ");
        double coveragePerCan = reader.nextDouble();

        double totalArea = project.calculateTotalPaintableArea();
        int cansRequired = PaintEstimator.calculatePaintCansRequired(totalArea, coveragePerCan);

        System.out.println(STR."Total paintable area: \{totalArea} square meters.");
        System.out.println(STR."Paint cans needed for the project: \{cansRequired}");
    }

    private static double getPositiveDouble() {
        double value;
        do {
            while (!BrushBuddy.reader.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a positive number.");
                BrushBuddy.reader.next(); //discard non-numeric input
            }
            value = BrushBuddy.reader.nextDouble();
            BrushBuddy.reader.nextLine();
            if(value <= 0) {
                System.out.println("Please enter a positive number.");
            }
        } while (value <= 0);
        return value;
    }
}