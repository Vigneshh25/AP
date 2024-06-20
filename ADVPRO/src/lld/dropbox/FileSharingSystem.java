package lld.dropbox;

import java.util.ArrayList;
import java.util.List;

// File class
class File {
    private String name;
    private byte[] data;
    private String owner;

    public File(String name, byte[] data, String owner) {
        this.name = name;
        this.data = data;
        this.owner = owner;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

// Folder class
class Folder {
    private String name;
    private List<File> files;
    private List<Folder> folders;

    public Folder(String name) {
        this.name = name;
        this.files = new ArrayList<>();
        this.folders = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<File> getFiles() {
        return files;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void addFile(File file) {
        files.add(file);
    }

    public void removeFile(File file) {
        files.remove(file);
    }

    public void addFolder(Folder folder) {
        folders.add(folder);
    }

    public void removeFolder(Folder folder) {
        folders.remove(folder);
    }
}

// User class
class User {
    private String username;
    private String password;
    private List<File> files;
    private List<Folder> folders;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.files = new ArrayList<>();
        this.folders = new ArrayList<>();
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<File> getFiles() {
        return files;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void uploadFile(String name, byte[] data) {
        File file = new File(name, data, username);
        files.add(file);
    }

    public void createFolder(String name) {
        Folder folder = new Folder(name);
        folders.add(folder);
    }
}

// FileStorageManager singleton class
class FileStorageManager {
    private static FileStorageManager instance;
    private List<File> files;
    private List<Folder> folders;

    private FileStorageManager() {
        this.files = new ArrayList<>();
        this.folders = new ArrayList<>();
    }

    public static synchronized FileStorageManager getInstance() {
        if (instance == null) {
            instance = new FileStorageManager();
        }
        return instance;
    }

    // Getters and setters
    public List<File> getFiles() {
        return files;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void uploadFile(File file) {
        files.add(file);
    }

    public void deleteFile(File file) {
        files.remove(file);
    }

    public void createFolder(Folder folder) {
        folders.add(folder);
    }

    public void deleteFolder(Folder folder) {
        folders.remove(folder);
    }
}

// FileProxy class
class FileProxy {
    private File file;
    private List<User> sharedWithUsers;

    public FileProxy(File file) {
        this.file = file;
        this.sharedWithUsers = new ArrayList<>();
    }

    // Getters and setters
    public File getFile() {
        return file;
    }

    public List<User> getSharedWithUsers() {
        return sharedWithUsers;
    }

    public void shareFileWithUser(User user) {
        sharedWithUsers.add(user);
    }

    public void revokeFileAccessForUser(User user) {
        sharedWithUsers.remove(user);
    }
}

// WebCrawlerWithSameHostnameMain Class
public class FileSharingSystem {
    public static void main(String[] args) {
        // Create users
        User user1 = new User("user1", "password1");
        User user2 = new User("user2", "password2");

        // User1 uploads a file
        user1.uploadFile("file1.txt", new byte[]{1, 2, 3});

        // User1 creates a folder
        user1.createFolder("Folder 1");

        // Access FileStorageManager singleton
        FileStorageManager fileStorageManager = FileStorageManager.getInstance();

        // Upload user1's file to the file storage manager
        fileStorageManager.uploadFile(user1.getFiles().get(0));

        // Create file proxy for user1's file
        FileProxy fileProxy = new FileProxy(user1.getFiles().get(0));

        // Share the file with user2
        fileProxy.shareFileWithUser(user2);

        // Print uploaded files in the file storage manager
        for (File file : fileStorageManager.getFiles()) {
            System.out.println("File: " + file.getName() + " owned by " + file.getOwner());
        }

        // Print shared users of the file
        for (User user : fileProxy.getSharedWithUsers()) {
            System.out.println("File shared with: " + user.getUsername());
        }
    }
}
