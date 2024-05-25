package meetingroom;

import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws Exception {
        BookingController bookingController = new BookingController();
        Repository repository = Repository.getInstance();

        // Creating Meeting Rooms
        repository.getMeetingRooms().add(MeetingRoomFactory.createMeetingRoom("Room1"));
        repository.getMeetingRooms().add(MeetingRoomFactory.createMeetingRoom("Room2"));

        // Booking Meeting Rooms
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String[] participants1 = {"alice@example.com", "bob@example.com"};
        String[] participants2 = {"charlie@example.com"};

        System.out.println(bookingController.bookMeetingRoom("Room1", sdf.parse("2024-05-25 10:00"), sdf.parse("2024-05-25 11:00"), participants1));
        System.out.println(bookingController.bookMeetingRoom("Room1", sdf.parse("2024-05-25 11:00"), sdf.parse("2024-05-25 12:00"), participants2));
        System.out.println(bookingController.bookMeetingRoom("Room1", sdf.parse("2024-05-25 10:30"), sdf.parse("2024-05-25 11:30"), participants2));

        // Checking available meeting rooms
        System.out.println("Available meeting rooms from 10:30 to 11:30:");
        for (MeetingRoom room : bookingController.getAvailableMeetingRooms(sdf.parse("2024-05-25 10:30"), sdf.parse("2024-05-25 11:30"))) {
            System.out.println(room.getRoomId());
        }
    }
}
