package tes.awslambda;

// Singleton Pattern
class ContainerManager {
    private static volatile ContainerManager instance;

    private ContainerManager() {
        // Initialize container manager
    }

    public static ContainerManager getInstance() {
        if (instance == null) {
            synchronized (ContainerManager.class) {
                if (instance == null) {
                    instance = new ContainerManager();
                }
            }
        }
        return instance;
    }

    public void createContainer(String functionName) {
        System.out.println("Creating container for function: " + functionName);
    }
}

// Example usage
public class MainSingleton {
    public static void main(String[] args) {
        ContainerManager containerManager = ContainerManager.getInstance();
        containerManager.createContainer("Function A");
    }
}
