import java.util.HashMap;
import java.util.Map;

public class OfficeConfig {
    private static OfficeConfig instance;
    private Map<Integer, Room> rooms = new HashMap<>();

    private OfficeConfig() {}

    public static synchronized OfficeConfig getInstance() {
        if (instance == null) {
            instance = new OfficeConfig();
        }
        return instance;
    }

    public void configureRooms(int numberOfRooms) {
        rooms.clear(); // Clear existing rooms

        for (int i = 1; i <= numberOfRooms; i++) {
            rooms.put(i, new Room(i));
        }
        System.out.print("Office configured with " + numberOfRooms + " meeting rooms: ");
        for (int i = 1; i <= numberOfRooms; i++) {
            System.out.print("Room " + i + (i == numberOfRooms ? "." : ", "));
        }
        System.out.println();
    }

    public void setRoomCapacity(int roomId, int capacity) {
        Room room = rooms.get(roomId);
        if (room != null) {
            if (capacity > 0) {
                room.setMaxCapacity(capacity);
                System.out.println("Room " + roomId + " maximum capacity set to " + capacity + ".");
            } else {
                System.out.println("Invalid capacity. Please enter a valid positive number.");
            }
        } else {
            System.out.println("Invalid room number. Please enter a valid room number.");
        }
    }

    public Room getRoom(int roomId) {
        return rooms.get(roomId);
    }
}
