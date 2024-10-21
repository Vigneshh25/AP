package googledrive;

// StorageStrategy.java
public interface StorageStrategy {
    void saveFile(File file);
    File loadFile(String fileName);
}

// LocalStorageStrategy.java
class LocalStorageStrategy implements StorageStrategy {
    @Override
    public void saveFile(File file) {
        // Implement local storage saving logic
        System.out.println("Saving file locally: " + file.getName());
    }

    @Override
    public File loadFile(String fileName) {
        // Implement local storage loading logic
        System.out.println("Loading file locally: " + fileName);
        return new File(fileName);
    }
}
