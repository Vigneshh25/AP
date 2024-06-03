package lld.awslambda;

// Decorator Pattern
interface Functio {
    void execute();
}

class SimpleFunctio implements Functio {
    @Override
    public void execute() {
        System.out.println("Executing simple function");
    }
}

class LoggingDecorator implements Functio {
    private Function function;

    public LoggingDecorator(Function function) {
        this.function = function;
    }

    @Override
    public void execute() {
        System.out.println("Logging: Function execution started");
        function.execute();
        System.out.println("Logging: Function execution finished");
    }
}

// Example usage
public class Decorator {
    public static void main(String[] args) {
        Function function = new SimpleFunction();
        Functio decoratedFunction = new LoggingDecorator(function);
        decoratedFunction.execute();
    }
}
