package Main;

public class Client {
    private final String nrDowodu;
    private final String lastName;
    private final String name;
    private final String city;

    public Client(String client) {
        nrDowodu = client.split("\t")[0];
        lastName = client.split("\t")[1];
        name = client.split("\t")[2];
        city = client.split("\t")[3];
    }

    public String getNrDowodu() {
        return nrDowodu;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}
