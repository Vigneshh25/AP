package lld.awslambda;

// Factory Pattern
interface FunctionFactory {
    Function createFunction();
}

class SimpleFunctionFactory implements FunctionFactory {
    @Override
    public Function createFunction() {
        return new SimpleFunction();
    }
}

class ComplexFunctionFactory implements FunctionFactory {
    @Override
    public Function createFunction() {
        return new ComplexFunction();
    }
}

class SimpleFunction extends Function {
    @Override
    public void execute() {
        System.out.println("Executing simple function");
    }
}

class ComplexFunction extends Function {
    @Override
    public void execute() {
        System.out.println("Executing complex function");
    }
}

// Example usage
public class MainFactory {
    public static void main(String[] args) {
        FunctionFactory factory = new SimpleFunctionFactory();
        Function function = factory.createFunction();
        function.execute();
    }
}
