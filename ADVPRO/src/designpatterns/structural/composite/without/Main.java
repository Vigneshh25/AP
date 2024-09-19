package designpatterns.structural.composite.without;// Without Composite Pattern


import java.util.*;

/*
Problems:

The client needs to handle both File and Directory differently.
Adding more operations (e.g., remove, copy) makes the code more complicated.

*/
class File {
    private String name;

    public File(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("File: " + name);
    }
}

class Directory {
    private String name;
    private List<File> files = new ArrayList<>();
    private List<Directory> directories = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void addFile(File file) {
        files.add(file);
    }

    public void addDirectory(Directory directory) {
        directories.add(directory);
    }

    public void display() {
        System.out.println("Directory: " + name);
        for (File file : files) {
            file.display();
        }
        for (Directory directory : directories) {
            directory.display();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Directory root = new Directory("root");
        root.addFile(new File("file1.txt"));
        root.addFile(new File("file2.txt"));

        Directory subDir = new Directory("subdir");
        subDir.addFile(new File("file3.txt"));
        root.addDirectory(subDir);

        root.display();
    }
}
