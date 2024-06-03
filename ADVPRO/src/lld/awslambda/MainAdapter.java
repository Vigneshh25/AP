package lld.awslambda;

// Adapter Pattern
interface Environment {
    String getVariable(String key);
}

class EnvironmentManager implements Environment {
    @Override
    public String getVariable(String key) {
        // Logic to retrieve environment variable
        return System.getenv(key);
    }
}

class FunctionWithEnvAdapter implements Functio {
    private Environment environment;

    public FunctionWithEnvAdapter(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void execute() {
        String dbHost = environment.getVariable("DB_HOST");
        System.out.println("DB Host: " + dbHost);
    }
}

// Example usage
public class MainAdapter {
    public static void main(String[] args) {
        Environment environment = new EnvironmentManager();
        Functio function = new FunctionWithEnvAdapter(environment);
        function.execute();
    }
}
