package VariableSystem.versions;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class VariableSystem {
    private Map<String, Integer> variables;
    private Stack<Map<String, Integer>> transactions;

    public VariableSystem() {
        this.variables = new HashMap<>();
        this.transactions = new Stack<>();
    }

    public void setVariable(String name, int value) {
        variables.put(name, value);
    }

    public Integer getVariable(String name) {
        return variables.get(name);
    }

    public void unsetVariable(String name) {
        variables.remove(name);
    }

    public int countVariable(int value) {
        int count = 0;
        for (Map.Entry<String, Integer> entry : variables.entrySet()) {
            if (entry.getValue() == value) {
                count++;
            }
        }
        return count;
    }

    public void beginTransaction() {
        transactions.push(new HashMap<>(variables));
    }

    public void rollbackTransaction() {
        if (transactions.isEmpty()) {
            System.out.println("NO TRANSACTION");
        } else {
            variables = transactions.pop();
        }
    }

    public void commitTransaction() {
        if (transactions.isEmpty()) {
            System.out.println("NO TRANSACTION");
        } else {
            transactions.clear();
        }
    }
}
class main {
    public static void main(String[] args) {
        VariableSystem system = new VariableSystem();

        system.setVariable("a", 20);
        System.out.println(system.getVariable("a"));
        system.setVariable("b", 30);
        System.out.println(system.getVariable("b"));
        system.setVariable("a", 10);
        System.out.println(system.getVariable("a"));
        system.countVariable(30);
        system.countVariable(40);
        system.setVariable("c", 30);
        system.countVariable(30);
        system.countVariable(40);
        system.unsetVariable("a");
        System.out.println(system.getVariable("a"));

        system.beginTransaction();
        system.setVariable("a", 40);
        System.out.println(system.getVariable("a"));
        system.setVariable("b", 40);
        System.out.println(system.getVariable("b"));
        system.rollbackTransaction();
        System.out.println(system.getVariable("b"));
        System.out.println(system.getVariable("a"));

        system.beginTransaction();
        system.setVariable("c", 50);
        system.beginTransaction();
        system.countVariable(40);
        system.commitTransaction();
        system.countVariable(40);
        system.beginTransaction();
        system.setVariable("c", 10);
        System.out.println(system.getVariable("c"));
        system.rollbackTransaction();
        System.out.println(system.getVariable("c"));
    }
}

