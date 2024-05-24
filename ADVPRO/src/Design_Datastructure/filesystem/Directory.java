package Design_Datastructure.filesystem;

import java.util.*;

public class Directory {
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
