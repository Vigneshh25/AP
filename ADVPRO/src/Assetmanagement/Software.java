package Assetmanagement;

/**
 * Created by Vignesh.V on 05/06/24.
 */
class Software {
    private String name;
    private Vendor vendor;
    private double pricePerLicense;

    public Software(String name, Vendor vendor, double pricePerLicense) {
        this.name = name;
        this.vendor = vendor;
        this.pricePerLicense = pricePerLicense;
    }

    public String getName() {
        return name;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public double getPricePerLicense() {
        return pricePerLicense;
    }
}
