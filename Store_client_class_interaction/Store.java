package Store_client_class_interaction;
import java.util.ArrayList;

public class Store {
    // Instance variables
    private ArrayList<Client> clientList;

    // Constructor
    public Store() {
        clientList = new ArrayList<Client>();
    }

    // Add client to the list
    public void add(Client aClient) {
        clientList.add(aClient);
    }

    // Print all clients
    public void print() {
        for (Client c : clientList) {
            c.print();
        }
    }

    // Find client by ID
    public Client find(int ID) {
        for (Client c : clientList) {
            if (c.getID() == ID) {
                return c;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Store store = new Store();

        Client c1 = new Client("Paweł");
        Client c2 = new Client("Jarek");
        Client c3 = new Client("Stefan");

        store.add(c1);
        store.add(c2);
        store.add(c3);

        store.print();

        Client foundClient = store.find(1);
        if (foundClient != null) {
            System.out.println("Found client with ID 1: " + foundClient.getName());
        } else {
            System.out.println("Client with ID 1 not found.");
        }
    }
}