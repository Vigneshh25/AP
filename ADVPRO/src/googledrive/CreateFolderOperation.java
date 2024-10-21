package googledrive;

import FileMangeMent.version.Directory;

/**
 * Created by Vignesh.V on 23/09/24.
 */
public class CreateFolderOperation extends FileOperation {
    private Folder parentFolder;
    private String folderName;

    public CreateFolderOperation(Folder parentFolder, String folderName) {
        this.parentFolder = parentFolder;
        this.folderName = folderName;
    }

    @Override
    protected boolean validate() {
        return parentFolder.getChild(folderName) == null; // Check for duplicates
    }

    @Override
    protected void performOperation() {
        parentFolder.add(new Folder(folderName)); // Create a Folder
    }
}

