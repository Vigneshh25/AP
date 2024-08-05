package FileMangeMent.version;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Directory extends FileEntity {
    private List<FileEntity> contents;
    
    public Directory(String name) {
        super(name);
        contents = new ArrayList<>();
    }
    
    public void addFileEntity(FileEntity fileEntity) {
        contents.add(fileEntity);
        this.lastModified = new Date();
    }
    
    public void removeFileEntity(String name) {
        contents.removeIf(fileEntity -> fileEntity.getName().equals(name));
        this.lastModified = new Date();
    }
    
    public List<FileEntity> listContents() {
        return contents;
    }
    
    @Override
    public int getSize() {
        return contents.size();
    }
    
    @Override
    public String toString() {
        return "Directory{" +
                "name='" + name + '\'' +
                ", lastModified=" + lastModified +
                ", contents=" + contents +
                '}';
    }
}
