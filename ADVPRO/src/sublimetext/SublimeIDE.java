package sublimetext;

import java.util.*;

// Observer Pattern - File Change Listener Interface
interface FileChangeListener {
    void onFileChanged(String fileName);
}

// Observer Pattern - Editor as a Listener
class FileEditor implements FileChangeListener {
    private String content;

    public FileEditor(String content) {
        this.content = content;
    }

    @Override
    public void onFileChanged(String fileName) {
        System.out.println(fileName + " has been modified.");
    }

    public void editContent(String newContent) {
        this.content = newContent;
        System.out.println("File content edited.");
    }

    public String getContent() {
        return content;
    }
}

// Observer Pattern - FileManager with Notifier
class FileManager {
    private List<FileChangeListener> listeners = new ArrayList<>();
    private Map<String, FileEditor> openFiles = new HashMap<>();

    public void addListener(FileChangeListener listener) {
        listeners.add(listener);
    }

    public void openFile(String fileName, String content) {
        FileEditor fileEditor = new FileEditor(content);
        openFiles.put(fileName, fileEditor);
        notifyListeners(fileName);
    }

    public void editFile(String fileName, String newContent) {
        FileEditor fileEditor = openFiles.get(fileName);
        if (fileEditor != null) {
            fileEditor.editContent(newContent);
            notifyListeners(fileName);
        } else {
            System.out.println("File not found.");
        }
    }

    public void notifyListeners(String fileName) {
        for (FileChangeListener listener : listeners) {
            listener.onFileChanged(fileName);
        }
    }

    public FileEditor getFileEditor(String fileName) {
        return openFiles.get(fileName);
    }
}

// Command Pattern - Command Interface
interface Command {
    void execute();
    void undo();
}

// Command Pattern - Undo Command
class UndoCommand implements Command {
    private FileEditor fileEditor;
    private String prevContent;

    public UndoCommand(FileEditor fileEditor) {
        this.fileEditor = fileEditor;
        this.prevContent = fileEditor.getContent();
    }

    @Override
    public void execute() {
        System.out.println("Undo operation performed.");
        fileEditor.editContent(prevContent);
    }

    @Override
    public void undo() {
        System.out.println("Redo operation performed.");
        // Redo is technically restoring the new content, but for simplicity we retain old content in this demo
        fileEditor.editContent(prevContent);
    }
}

// Strategy Pattern - Syntax Highlighter
interface SyntaxHighlighter {
    void highlight(String code);
}

// Strategy Pattern - Python Highlighter
class PythonHighlighter implements SyntaxHighlighter {
    @Override
    public void highlight(String code) {
        System.out.println("Syntax Highlighting for Python code: " + code);
    }
}

// Strategy Pattern - Java Highlighter
class JavaHighlighter implements SyntaxHighlighter {
    @Override
    public void highlight(String code) {
        System.out.println("Syntax Highlighting for Java code: " + code);
    }
}

// Factory Pattern - Syntax Highlighter Factory
class SyntaxHighlighterFactory {
    public static SyntaxHighlighter getHighlighter(String language) {
        switch (language.toLowerCase()) {
            case "python":
                return new PythonHighlighter();
            case "java":
                return new JavaHighlighter();
            default:
                throw new IllegalArgumentException("Unsupported language");
        }
    }
}

// Decorator Pattern - CodeEditor base class
abstract class CodeEditor {
    public abstract void display();
}

// Decorator Pattern - Basic Code Editor
class BasicEditor extends CodeEditor {
    @Override
    public void display() {
        System.out.println("Basic editor without additional features.");
    }
}

// Decorator Pattern - Adding Auto-Completion to CodeEditor
class AutoCompletionDecorator extends CodeEditor {
    private CodeEditor editor;

    public AutoCompletionDecorator(CodeEditor editor) {
        this.editor = editor;
    }

    @Override
    public void display() {
        editor.display();
        System.out.println("Auto-completion feature enabled.");
    }
}

// Memento Pattern - Saving and Restoring Editor State
class EditorState {
    private String content;

    public EditorState(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

// Memento Pattern - Editor History to Save States
class EditorHistory {
    private Stack<EditorState> history = new Stack<>();

    public void saveState(EditorState state) {
        history.push(state);
    }

    public EditorState undo() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }
}

// Singleton Pattern - Theme Manager
class ThemeManager {
    private static ThemeManager instance;

    private ThemeManager() {}

    public static ThemeManager getInstance() {
        if (instance == null) {
            instance = new ThemeManager();
        }
        return instance;
    }

    public void applyTheme(String theme) {
        System.out.println("Applying theme: " + theme);
    }
}

// Sublime IDE Simulation
public class SublimeIDE {
    public static void main(String[] args) {
        // Observer Pattern Usage: Editing a File and Notifying Listeners
        FileManager fileManager = new FileManager();
        FileEditor fileEditor = new FileEditor("Initial content of test.java");
        fileManager.addListener(fileEditor);
        fileManager.openFile("test.java", "Initial content of test.java");
        fileManager.editFile("test.java", "Modified content of test.java");

        // Command Pattern Usage: Undo Command
        UndoCommand undoCommand = new UndoCommand(fileManager.getFileEditor("test.java"));
        undoCommand.execute();

        // Strategy Pattern Usage: Syntax Highlighting
        SyntaxHighlighter pythonHighlighter = SyntaxHighlighterFactory.getHighlighter("python");
        pythonHighlighter.highlight("def hello_world():");

        SyntaxHighlighter javaHighlighter = SyntaxHighlighterFactory.getHighlighter("java");
        javaHighlighter.highlight("public static void main(String[] args) {}");

        // Decorator Pattern Usage: Adding Auto-completion to Basic Editor
        CodeEditor basicEditor = new BasicEditor();
        CodeEditor editorWithAutoComplete = new AutoCompletionDecorator(basicEditor);
        editorWithAutoComplete.display();

        // Memento Pattern Usage: Saving and Restoring Editor State
        EditorHistory editorHistory = new EditorHistory();
        editorHistory.saveState(new EditorState(fileEditor.getContent()));

        // Modifying content again
        fileManager.editFile("test.java", "Second modification.");
        editorHistory.saveState(new EditorState(fileEditor.getContent()));

        // Undo to restore previous state
        EditorState previousState = editorHistory.undo();
        if (previousState != null) {
            fileManager.getFileEditor("test.java").editContent(previousState.getContent());
            System.out.println("Restored content: " + fileManager.getFileEditor("test.java").getContent());
        }

        // Singleton Pattern Usage: Applying a Theme
        ThemeManager themeManager = ThemeManager.getInstance();
        themeManager.applyTheme("Dark Mode");

        // Final demonstration of file state
        System.out.println("Final content of test.java: " + fileManager.getFileEditor("test.java").getContent());
    }
}
