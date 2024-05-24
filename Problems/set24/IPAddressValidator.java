package Problems.set24;

public class IPAddressValidator {

    public boolean isValid(String ipAddress) {
        // Check for empty or null input
        if (ipAddress == null || ipAddress.isEmpty()) {
            return false;
        }
        
        // Check for leading or trailing dots
        if (ipAddress.charAt(0) == '.' || ipAddress.charAt(ipAddress.length() - 1) == '.') {
            return false;
        }
        
        // Split the input into parts
        String[] parts = ipAddress.split("\\.");
        
        // Check for four parts
        if (parts.length != 4) {
            return false;
        }
        
        // Validate each part
        for (String part : parts) {
            if (!validate(part)) {
                return false;
            }
        }
        
        // If all checks pass, return true
        return true;
    }
    
    private boolean validate(String part) {
        // Check for empty input
        if (part == null || part.isEmpty()) {
            return false;
        }
        
        // Check for leading zeros
        if (part.charAt(0) == '0' && part.length() > 1) {
            return false;
        }
        
        // Attempt to parse the input as an integer
        int value;
        try {
            value = Integer.parseInt(part);
        } catch (NumberFormatException e) {
            return false;
        }
        
        // Check the range of the integer
        return value >= 0 && value <= 255;
    }
}
