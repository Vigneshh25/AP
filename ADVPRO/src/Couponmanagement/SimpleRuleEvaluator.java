package Couponmanagement;

public class SimpleRuleEvaluator implements RuleEvaluator {
    private String condition;

    public SimpleRuleEvaluator(String condition) {
        this.condition = condition;
    }

    @Override
    public boolean evaluate(User user, double cartValue) {
        // Simple evaluation logic for demonstration. This can be expanded to a full expression evaluator.
        if (condition.equals("age > 18 && cartValue > 1000")) {
            return user.getAge() > 18 && cartValue > 1000;
        }
        return false;
    }
}
