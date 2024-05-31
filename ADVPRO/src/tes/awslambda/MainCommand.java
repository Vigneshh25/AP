package tes.awslambda;

// Command Pattern
interface Command {
    void execute();
}

class FunctionExecutionCommand implements Command {
    private Function function;

    public FunctionExecutionCommand(Function function) {
        this.function = function;
    }

    @Override
    public void execute() {
        function.execute();
    }
}

class Function {
    public void execute() {
        // Function logic
        System.out.println("Function executed!");
    }
}

// Example usage
public class MainCommand {
    public static void main(String[] args) {
        Command command = new FunctionExecutionCommand(new Function());
        command.execute();
    }
}
