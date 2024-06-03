package couponmanagement.version;

class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}

class InvalidTransitionException extends RuntimeException {
    public InvalidTransitionException(String message) {
        super(message);
    }
}

class BatchNotFoundException extends RuntimeException {
    public BatchNotFoundException(String message) {
        super(message);
    }
}

class CouponNotFoundException extends RuntimeException {
    public CouponNotFoundException(String message) {
        super(message);
    }
}

class BatchNotActiveException extends RuntimeException {
    public BatchNotActiveException(String message) {
        super(message);
    }
}

class BatchExhaustedException extends RuntimeException {
    public BatchExhaustedException(String message) {
        super(message);
    }
}