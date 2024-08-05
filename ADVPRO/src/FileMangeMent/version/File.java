package FileMangeMent.version;

public class File extends FileEntity {
    private int size;
    
    public File(String name, int size) {
        super(name);
        this.size = size;
    }
    
    @Override
    public int getSize() {
        return size;
    }
    
    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", lastModified=" + lastModified +
                '}';
    }
}
