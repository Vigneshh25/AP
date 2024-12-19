package Couponmanagement;

public interface RuleEvaluator {
    boolean evaluate(User user, double cartValue);
}

