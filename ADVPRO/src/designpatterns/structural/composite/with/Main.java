package designpatterns.structural.composite.with;

import java.util.ArrayList;
import java.util.List;

// Component
interface FileSystemComponent {
    void display();
}

// Leaf
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("File: " + name);
    }
}

// Composite
class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void display() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components) {
            component.display();
        }
    }
}

// Main class to demonstrate Composite Pattern
public class Main {
    public static void main(String[] args) {
        FileSystemComponent file1 = new File("file1.txt");
        FileSystemComponent file2 = new File("file2.txt");
        FileSystemComponent file3 = new File("file3.txt");

        Directory rootDirectory = new Directory("root");
        Directory subDirectory = new Directory("subdir");

        rootDirectory.addComponent(file1);
        rootDirectory.addComponent(file2);
        subDirectory.addComponent(file3);
        rootDirectory.addComponent(subDirectory);

        // Uniformly calling display on root directory, which contains both files and directories
        rootDirectory.display();
    }
}
