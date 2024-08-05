package FileMangeMent.version;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystem {
    private Directory root;
    private Map<String, Directory> pathToDirectoryMap;

    public FileSystem() {
        root = new Directory("/");
        pathToDirectoryMap = new HashMap<>();
        pathToDirectoryMap.put("/", root);
    }

    public void addFile(String path, String fileName, int size) {
        Directory directory = pathToDirectoryMap.get(path);
        if (directory != null) {
            File file = new File(fileName, size);
            directory.addFileEntity(file);
        }
    }

    public void addDirectory(String path, String dirName) {
        Directory directory = pathToDirectoryMap.get(path);
        if (directory != null) {
            Directory newDirectory = new Directory(dirName);
            directory.addFileEntity(newDirectory);
            pathToDirectoryMap.put(path + dirName + "/", newDirectory);
        }
    }

    public void removeFileOrDirectory(String path, String name) {
        Directory directory = pathToDirectoryMap.get(path);
        if (directory != null) {
            directory.removeFileEntity(name);
            pathToDirectoryMap.remove(path + name + "/");
        }
    }

    public void listContents(String path) {
        Directory directory = pathToDirectoryMap.get(path);
        if (directory != null) {
            List<FileEntity> contents = directory.listContents();
            for (FileEntity fileEntity : contents) {
                System.out.println(fileEntity);
            }
        } else {
            System.out.println("Path not found");
        }
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        fs.addDirectory("/", "home");
        fs.addDirectory("/home/", "user");
        fs.addFile("/home/user/", "file1.txt", 100);
        fs.addFile("/home/user/", "file2.txt", 200);
        fs.addDirectory("/home/user/", "docs");
        fs.addFile("/home/user/docs/", "doc1.pdf", 300);

        System.out.println("Contents of /home/user/:");
        fs.listContents("/home/user/");

        System.out.println("\nContents of /home/user/docs/:");
        fs.listContents("/home/user/docs/");
    }
}
