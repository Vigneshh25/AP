package truecaller.DatabaseLayer;

public class PhoneNumber {
    private String number;
    private String name;

    public PhoneNumber(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
