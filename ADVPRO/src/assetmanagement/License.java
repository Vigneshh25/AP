package assetmanagement;

import java.time.LocalDate;

/**
 * Created by Vignesh.V on 05/06/24.
 */
class License {
    private Software software;
    private LocalDate installationDate;
    private LocalDate expiryDate;

    public License(Software software, LocalDate installationDate, LocalDate expiryDate) {
        this.software = software;
        this.installationDate = installationDate;
        this.expiryDate = expiryDate;
    }

    public Software getSoftware() {
        return software;
    }

    public LocalDate getInstallationDate() {
        return installationDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean isExpired(LocalDate date) {
        return expiryDate.isBefore(date);
    }
}
