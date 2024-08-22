package vendingmachine.factory;

import vendingmachine.model.*;

/**
 * Created by Vignesh.V on 14/06/24.
 */
public class ProductFactory {
    public static Product createProduct(String code, ProductType productType, int cost, int quantity) {
        switch (productType) {
            case CHIPS:
                return new Chips(code, productType, cost, quantity);
            case COOKIES:
                return new Cookies(code, productType, cost, quantity);
            case NACHOS:
                return new Nachos(code, productType, cost, quantity);
            default:
                throw new IllegalArgumentException("Invalid product type");
        }
    }
}
