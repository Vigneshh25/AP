package bikerental.version;

/**
 * Created by Vignesh.V on 05/06/24.
 */
class MemberNotificationService implements Observer {
    private Member member;

    public MemberNotificationService(Member member) {
        this.member = member;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification to " + member.getName() + ": " + message);
    }
}
