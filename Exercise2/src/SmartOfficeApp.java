import java.util.Scanner;

public class SmartOfficeApp {
    public static void main(String[] args) {
        OfficeConfig office = OfficeConfig.getInstance(); // Singleton instance of OfficeConfig
        Scanner scanner = new Scanner(System.in); // Scanner to read user input

        // Loop to continuously accept commands from the user
        for(int i = 0; i <= 10000; i++) {
            System.out.print("Enter command (config/capacity/booking/add/cancel/vacate/exit): ");
            String command = scanner.next().trim().toLowerCase(); // Read and process the command

            switch (command) {
                case "config":
                    // Configure the number of rooms in the office
                    System.out.print("Enter room count: ");
                    int numberOfRooms = scanner.nextInt();
                    if (numberOfRooms > 0) {
                        office.configureRooms(numberOfRooms);
                    } else {
                        System.out.println("Invalid room count");
                    }
                    break;

                case "capacity":
                    // Set the capacity of a specific room
                    System.out.print("Enter room number and capacity (e.g., 1 10): ");
                    int roomId = scanner.nextInt();
                    int capacity = scanner.nextInt();
                    office.setRoomCapacity(roomId, capacity);
                    break;

                case "add":
                    // Add occupants to a room
                    System.out.print("Enter room number and number of occupants (e.g., 1 2): ");
                    roomId = scanner.nextInt();
                    int numberOfPeople = scanner.nextInt();
                    Room room = office.getRoom(roomId);
                    if (room != null) {
                        room.occupy(numberOfPeople);
                    } else {
                        System.out.println("Room " + roomId + " does not exist.");
                    }
                    break;

                case "booking":
                    // Book a room for a specified duration
                    System.out.print("Enter room number, start time (HH:mm), and duration in minutes (e.g., 1 09:00 60): ");
                    roomId = scanner.nextInt();
                    String startTime = scanner.next();
                    int duration = scanner.nextInt();
                    room = office.getRoom(roomId);
                    if (room != null) {
                        room.book(startTime, duration);
                    } else {
                        System.out.println("Invalid room number. Please enter a valid room number.");
                    }
                    break;

                case "cancel":
                    // Cancel a room booking
                    System.out.print("Enter room number: ");
                    roomId = scanner.nextInt();
                    room = office.getRoom(roomId);
                    if (room != null) {
                        room.cancelRoom();
                    } else {
                        System.out.println("Room " + roomId + " is not booked. Cannot cancel booking.");
                    }
                    break;

                case "vacate":
                    // Vacate a room
                    System.out.print("Enter room number: ");
                    roomId = scanner.nextInt();
                    room = office.getRoom(roomId);
                    if (room != null) {
                        room.vacate();
                    } else {
                        System.out.println("Room " + roomId + " does not exist.");
                    }
                    break;

                case "exit":
                    // Exit the application
                    scanner.close();
                    System.exit(0);

                default:
                    // Handle invalid commands
                    System.out.println("Invalid command. Please enter a valid command.");
            }
        }
    }
}
