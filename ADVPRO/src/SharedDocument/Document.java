package SharedDocument;

import java.util.*;

public class Document {
    private final String id;
    private final String filename;
    private String content;
    private final List<String> versionHistory;

    public Document(String id, String filename, String content) {
        this.id = id;
        this.filename = filename;
        this.content = content;
        this.versionHistory = new ArrayList<>();
        this.versionHistory.add(content);
    }

    public String getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String newContent) {
        this.content = newContent;
        this.versionHistory.add(newContent);
    }

    public List<String> getVersionHistory() {
        return Collections.unmodifiableList(versionHistory);
    }
}
