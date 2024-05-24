package Design_Datastructure.filesystem;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {
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
