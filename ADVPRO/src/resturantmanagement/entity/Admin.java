package resturantmanagement.entity;

public class Admin {
    private int adminId;
    private String name;

    public Admin(int adminId, String name) {
        this.adminId = adminId;
        this.name = name;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getName() {
        return name;
    }
}
