package Main;

public class Room {
    private final int roomNumber;
    private final String standard;
    private final int price;

    public Room(String room) {
        roomNumber = Integer.parseInt(room.split("\t")[0]);
        standard = room.split("\t")[1];
        price = Integer.parseInt(room.split("\t")[2]);
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getStandard() {
        return standard;
    }

    public int getPrice() {
        return price;
    }
}
