package atm.ServiceLayer;

import atm.InterfaceLayer.CardReader;

public class CardReaderImpl implements CardReader {
    @Override
    public String readCard() {
        // Simulate card reading
        return "123456789";  // Example card number
    }
}
