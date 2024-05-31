package tes.awslambda;

// Proxy Pattern
interface Functi {
    void execute();
}

class RealFunction implements Functi {
    @Override
    public void execute() {
        System.out.println("Executing real function");
    }
}

class FunctionProxy implements Functi {
    private RealFunction realFunction;
    private String user;

    public FunctionProxy(String user) {
        this.user = user;
        this.realFunction = new RealFunction();
    }

    @Override
    public void execute() {
        if ("admin".equals(user)) {
            realFunction.execute();
        } else {
            System.out.println("Unauthorized access");
        }
    }
}

// Example usage
public class MainProxy {
    public static void main(String[] args) {
        Functi function = new FunctionProxy("admin"); // Change user to test access
        function.execute();
    }
}
