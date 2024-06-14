package atm.ServiceLayer;

import atm.InterfaceLayer.Screen;

public class ScreenImpl implements Screen {
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
