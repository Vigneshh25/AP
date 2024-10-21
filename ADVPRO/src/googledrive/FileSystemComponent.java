package googledrive;

// FileSystemComponent.java
public interface FileSystemComponent {
    String getName();
    void setName(String name);
    void add(FileSystemComponent component) throws UnsupportedOperationException;
    void remove(FileSystemComponent component) throws UnsupportedOperationException;
    FileSystemComponent getChild(String name) throws UnsupportedOperationException;
    void display(String indent);
    PermissionsDecorator getPermissions();
}
