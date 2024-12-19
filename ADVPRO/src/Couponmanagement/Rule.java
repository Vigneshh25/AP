package Couponmanagement;

public class Rule {
    private String condition;
    private RuleEvaluator evaluator;

    public Rule(String condition) {
        this.condition = condition;
        this.evaluator = new SimpleRuleEvaluator(condition);
    }

    public boolean evaluate(User user, double cartValue) {
        return evaluator.evaluate(user, cartValue);
    }

    public String getCondition() {
        return condition;
    }
}
