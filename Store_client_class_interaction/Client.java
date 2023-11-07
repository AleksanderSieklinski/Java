package Store_client_class_interaction;
// This task was to create a client class and a store class that stores clients in an ArrayList.
// The client class has a static variable that counts the number of clients.
public class Client {
    // Instance variables
    private int clientId;
    private String clientName;

    // Static variable to clientCount clients
    private static int clientCount = 0;

    // Static initializer
    {
        clientId = ++clientCount;
        System.out.println("Instance initializer called");
    }

    // Constructor
    public Client(String name) {
        clientName = name;
        System.out.println("Constructor called");
    }

    // Overloaded constructor   
    public Client() {
        clientName = "empty";
        System.out.println("No arguments constructor called");
    }

    // Getter for client name
    public String getName() {
        return clientName;
    }

    // Getter for client ID
    public int getID() {
        return clientId;
    }

    // Print method
    public void print() {
        System.out.println("Client ID: " + clientId);
        System.out.println("Client Name: " + clientName);
    }

    // Static method to get number of clients
    public static int countClients() {
        System.out.println("countClients() called");
        return clientCount;
    }

    public static void main(String[] args) {
        System.out.println("main() called");
        Client c1 = new Client("Paweł");
        Client c2 = new Client("Jarek");
        Client c3 = new Client();

        c1.print();
        c2.print();
        c3.print();

        System.out.println("ID of c1: " + c1.getID() + "\nName of c1: " + c1.getName());

        System.out.println("Number of clients: " + Client.countClients());
    }
}