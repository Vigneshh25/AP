package FileMangeMent.version;

import java.util.Date;

public abstract class FileEntity {
    protected String name;
    protected Date lastModified;
    
    public FileEntity(String name) {
        this.name = name;
        this.lastModified = new Date();
    }
    
    public String getName() {
        return name;
    }
    
    public Date getLastModified() {
        return lastModified;
    }
    
    public abstract int getSize();
}
