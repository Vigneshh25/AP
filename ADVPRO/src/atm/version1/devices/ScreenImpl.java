package atm.version1.devices;

public class ScreenImpl implements Screen {
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
