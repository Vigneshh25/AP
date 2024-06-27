package PaymentGatewaySystem.entity;

public class NetBankingDetails implements PaymentDetails {
    private String bankName;
    private String userId;
    private String password;

    public NetBankingDetails(String bankName, String userId, String password) {
        this.bankName = bankName;
        this.userId = userId;
        this.password = password;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "NetBankingDetails{bankName='" + bankName + '\'' + ", userId='" + userId + '\'' + '}';
    }
}
