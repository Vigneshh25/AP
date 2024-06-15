package atm.version2.hardware;

public class NetworkInfrastructureImpl implements NetworkInfrastructure {
    @Override
    public void send(String message) {
        // Simulate sending a message over the network
        System.out.println("Sending message: " + message);
    }
}
