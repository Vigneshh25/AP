import java.util.*;

public class Main {
	public static void main(String[] args) {
		FileSystem fileSystem = new FileSystem();

		// Create some files
		fileSystem.createFile("/documents/text.txt", "This is a text file.");
		fileSystem.createFile("/documents/presentation.pptx", "This is a presentation file.");
		fileSystem.createFile("/documents/spreadsheet.xlsx", "This is a spreadsheet file.");

		// Read a file
		String textContent = fileSystem.readFile("/documents/text.txt");
		System.out.println("Content of text.txt: " + textContent);

		// List files in a directory
		System.out.println("Files in /documents:");
		List<String> files = fileSystem.listFiles("/documents");
		for (String file : files) {
			System.out.println(file);
		}

		// Attempting to read a non-existent file
		String nonExistentFileContent = fileSystem.readFile("/documents/nonexistent.txt");
		System.out.println("Content of nonexistent.txt: " + nonExistentFileContent);

		// Attempting to list files in a non-existent directory
		List<String> nonexistentFiles = fileSystem.listFiles("/nonexistent");
		System.out.println("Files in /nonexistent:");
		if (nonexistentFiles.isEmpty()) {
			System.out.println("Directory not found.");
		}
	}
}

class FileSystem {
	private Directory rootDirectory;

	public FileSystem() {
		rootDirectory = new Directory("/");
	}

	public void createFile(String path, String content) {
		String[] pathComponents = path.split("/");
		Directory currentDir = traverseToDirectory(pathComponents, rootDirectory, 1);
		if (currentDir != null) {
			String fileName = pathComponents[pathComponents.length - 1];
			currentDir.addFile(fileName, content);
			System.out.println("File " + fileName + " created successfully.");
		} else {
			System.out.println("Directory not found.");
		}
	}

	public String readFile(String path) {
		String[] pathComponents = path.split("/");
		Directory currentDir = traverseToDirectory(pathComponents, rootDirectory, 1);
		if (currentDir != null) {
			String fileName = pathComponents[pathComponents.length - 1];
			return currentDir.getFileContent(fileName);
		} else {
			return "Directory not found.";
		}
	}

	public List<String> listFiles(String path) {
		String[] pathComponents = path.split("/");
		Directory currentDir = traverseToDirectory(pathComponents, rootDirectory, 1);
		List<String> fileNames = new ArrayList<>();
		if (currentDir != null) {
			fileNames.addAll(currentDir.getFileNames());
		}
		return fileNames;
	}

	private Directory traverseToDirectory(String[] pathComponents, Directory currentDir, int index) {
		if (index == pathComponents.length - 1) {
			return currentDir;
		}
		String nextDirectoryName = pathComponents[index];
		Directory nextDir = currentDir.getSubDirectory(nextDirectoryName);
		if (nextDir != null) {
			return traverseToDirectory(pathComponents, nextDir, index + 1);
		} else {
			return null;
		}
	}
}

class Directory {
	private String name;
	private Map<String, String> files;
	private Map<String, Directory> directories;

	public Directory(String name) {
		this.name = name;
		files = new HashMap<>();
		directories = new HashMap<>();
	}

	public void addFile(String fileName, String content) {
		files.put(fileName, content);
	}

	public String getFileContent(String fileName) {
		return files.getOrDefault(fileName, "File not found.");
	}

	public List<String> getFileNames() {
		return new ArrayList<>(files.keySet());
	}

	public Directory getSubDirectory(String directoryName) {
		return directories.get(directoryName);
	}

	public String getName() {
		return name;
	}
}
