import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Room {
    private int roomId;
    private boolean isBooked = false;
    private boolean isOccupied = false;
    private Timer timer = new Timer();
    private Date bookingEndTime;
    private int maxCapacity = 10; // Default capacity
    private static final long OCCUPY_TIMEOUT = 300000; // 5 minutes in milliseconds

    public Room(int roomId) {
        this.roomId = roomId;
    }

    // Set the maximum capacity of the room
    public void setMaxCapacity(int capacity) {
        this.maxCapacity = capacity;
        if (capacity <= 0) {
            System.out.println("Invalid maximum capacity.");
        }
    }

    // Book the room for a specified duration
    public synchronized void book(String startTimeStr, int durationMinutes) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date startTime = sdf.parse(startTimeStr);
            long durationMillis = TimeUnit.MINUTES.toMillis(durationMinutes);
            bookingEndTime = new Date(startTime.getTime() + durationMillis);

            if (isBooked) {
                System.out.println("Room " + roomId + " is already booked during this time. Cannot book.");
            } else {
                isBooked = true;
                System.out.println("Room " + roomId + " booked from " + startTimeStr + " for " + durationMinutes + " minutes.");
                scheduleAutoUnbook();
            }
        } catch (ParseException e) {
            System.out.println("Invalid time format. Please use HH:mm format.");
        }
    }

    // Cancel the room booking
    public synchronized void cancelRoom() {
        if (isBooked) {
            isBooked = false;
            isOccupied = false;
            timer.cancel();
            System.out.println("Booking for Room " + roomId + " cancelled successfully.");
            controlEnvironment();
        } else {
            System.out.println("Room " + roomId + " is not booked. Cannot cancel booking.");
        }
    }

    // Occupy the room with a specified number of people
    public synchronized void occupy(int numberOfPeople) {
        if (numberOfPeople > maxCapacity) {
            System.out.println("Number of occupants exceeds the maximum capacity of Room " + roomId + ".");
        } else if (numberOfPeople >= 2) {
            if (isBooked) {
                isOccupied = true;
                System.out.println("Room " + roomId + " is now occupied by " + numberOfPeople + " persons. AC and lights turned on.");
                controlEnvironment();
            } else {
                System.out.println("Room " + roomId + " is not booked.");
            }
        } else if (numberOfPeople == 0) {
            vacate();
        } else {
            System.out.println("Room " + roomId + " occupancy insufficient to mark as occupied.");
        }
    }

    // Vacate the room
    public synchronized void vacate() {
        if (isOccupied) {
            isOccupied = false;
            System.out.println("Room " + roomId + " is now unoccupied. AC and lights turned off.");
            controlEnvironment();
            // Schedule to check again after releasing
            scheduleAutoUnbook();
        } else {
            System.out.println("Room " + roomId + " is already vacant.");
        }
    }

    // Control the environment of the room (e.g., turn on/off AC and lights)
    private void controlEnvironment() {
        if (!isOccupied && isBooked) {
            System.out.println("Turning off air conditioning and lights in Room " + roomId + ".");
        } else if (isOccupied) {
            System.out.println("Turning on air conditioning and lights in Room " + roomId + ".");
        }
    }

    // Schedule automatic unbooking of the room if it remains unoccupied
    private void scheduleAutoUnbook() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (Room.this) {
                    if (!isOccupied && isBooked) {
                        cancelRoom(); // Automatically unbook the room
                    }
                }
            }
        }, OCCUPY_TIMEOUT);
    }
}
