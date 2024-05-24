package Design_Datastructure.filesystem;

import java.util.List;

public class FileSys {
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
