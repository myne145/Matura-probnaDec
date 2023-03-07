package Main;

import java.util.Date;

public class Stay {
    private final int idPobytu;
    private final Date arrivalDate;
    private final Date leaveDate;
    private final String nrDowodu;
    private final int roomNumber;

    public Stay(String client) {
        String[] s = client.split("\t")[1].split("-");
        String[] s2 = client.split("\t")[2].split("-");
        idPobytu = Integer.parseInt(client.split("\t")[0]);
        arrivalDate = new Date(Integer.parseInt(s[0]) - 1900, Integer.parseInt(s[1]) - 1, Integer.parseInt(s[2]));
        leaveDate = new Date(Integer.parseInt(s2[0]) - 1900, Integer.parseInt(s2[1]) - 1, Integer.parseInt(s2[2]));
        nrDowodu = client.split("\t")[3];
        roomNumber = Integer.parseInt(client.split("\t")[4]);
    }

    public int getIdPobytu() {
        return idPobytu;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public String getNrDowodu() {
        return nrDowodu;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
