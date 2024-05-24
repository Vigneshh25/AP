package VariableSystem;

import java.util.*;

public class VariableSystem {

    // main HashMap to store the key-value pairs of the variables
    private HashMap<String, String> map;

    // Stack to keep track of transactions
    private Stack<HashMap<String, String>> transactionStack;

    public VariableSystem() {
        this.map = new HashMap<String, String>();
        this.transactionStack = new Stack<HashMap<String, String>>();
    }

    // SET a variable
    public void set(String key, String value) {
        // create a new transaction if one is not already open
        if (transactionStack.empty()) {
            transactionStack.push(new HashMap<String, String>());
        }
        transactionStack.peek().put(key, value);
    }

    // GET a variable
    public String get(String key) {
        // check if the variable exists in the main HashMap or in the open transactions
        if (transactionStack.empty() || !transactionStack.peek().containsKey(key)) {
            if (!map.containsKey(key)) {
                return "null";
            }
            return map.get(key);
        }
        return transactionStack.peek().get(key);
    }

    // UNSET a variable
    public void unset(String key) {
        // create a new transaction if one is not already open
        if (transactionStack.empty()) {
            transactionStack.push(new HashMap<String, String>());
        }
        transactionStack.peek().put(key, null);
    }

    // COUNT number of variables with given value
    public int count(String value) {
        int count = 0;
        // count the number of variables with the given value in the main HashMap
        for (String key : map.keySet()) {
            if (map.get(key) != null && map.get(key).equals(value)) {
                count++;
            }
        }
        // count the number of variables with the given value in the open transactions
        for (int i = transactionStack.size() - 1; i >= 0; i--) {
            HashMap<String, String> transaction = transactionStack.get(i);
            for (String key : transaction.keySet()) {
                if (transaction.get(key) != null && transaction.get(key).equals(value)) {
                    count++;
                }
            }
        }
        return count;
    }

    // BEGIN a new transaction
    public void begin() {
        transactionStack.push(new HashMap<String, String>());
    }

    // ROLLBACK all the commands in the open transaction
    public void rollback() {
        // throw an exception if there is no open transaction to rollback
        if (transactionStack.empty()) {
            throw new RuntimeException("NO TRANSACTION");
        }
        transactionStack.pop();
    }

    // COMMIT the transaction
    public void commit() {
        if (transactionStack.empty()) {
            throw new RuntimeException("NO TRANSACTION");
        }
        HashMap<String, String> transaction = transactionStack.pop();
        for (String key : transaction.keySet()) {
            if (transaction.get(key) == null) {
                map.remove(key);
            } else {
                map.put(key, transaction.get(key));
            }
        }
    }

    public static void main(String[] args) {
        VariableSystem.versions.VariableSystem variableSystem = new VariableSystem.versions.VariableSystem();

        // Example 1
        System.out.println(variableSystem.get("a")); // null
        variableSystem.set("a", "20");
        System.out.println(variableSystem.get("a")); // 20
        variableSystem.set("b", "30");
        System.out.println(variableSystem.get("b")); // 30
        variableSystem.set("a", "10");
        System.out.println(variableSystem.get("a")); // 10
        // UPDATE c 40 No variable named “c”
        variableSystem.set("c", "30");
        System.out.println(variableSystem.count("30")); // 2
        System.out.println(variableSystem.count("40")); // 0
        variableSystem.unset("a");
        System.out.println(variableSystem.get("a")); // null

        // Example 2
        System.out.println(variableSystem.get("a")); // null
        variableSystem.set("a", "30");
        System.out.println(variableSystem.get("a")); // 30

        // Example 3
        variableSystem.set("a", "30");
        variableSystem.begin();
        System.out.println(variableSystem.get("a")); // 30
        variableSystem.set("a", "40");
        System.out.println(variableSystem.get("a")); // 40
        variableSystem.set("b", "40");
        System.out.println(variableSystem.get("b")); // 40
        variableSystem.rollback();
        System.out.println(variableSystem.get("b")); // null
        System.out.println(variableSystem.get("a")); // 30

        // Example 4
        variableSystem.begin();
        variableSystem.set("a", "40");
        variableSystem.set("b", "40");
        variableSystem.set("c", "50");
        System.out.println(variableSystem.count("40")); // 2
        variableSystem.begin();
        System.out.println(variableSystem.count("40")); // 0
        variableSystem.commit();
        System.out.println(variableSystem.count("40")); // 2
        variableSystem.begin();
        variableSystem.set("c", "10");
        System.out.println(variableSystem.get("c")); // 10
        variableSystem.rollback();
        System.out.println(variableSystem.get("c")); // 50
    }
}
