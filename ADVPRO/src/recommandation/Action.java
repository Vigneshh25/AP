package recommandation;

// Represents an Action entity
class Action {
    private String type;
    private String content;

    public Action(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
