package Couponmanagement;

public class RuleFactory {
    public static Rule createRule(String condition) {
        return new Rule(condition);
    }
}

