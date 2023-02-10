package set1;

import java.io.*;
import java.util.Scanner;


public class FileManageMent {

    public static void fileCreation(String dir, String filename) throws IOException {

        boolean success = false;
        // Creating new directory in Java, if it doesn't exists
        File directory = new File(dir);
        if (directory.exists()) {
            System.out.println("Directory already exists ...");

        } else {
            System.out.println("Directory not exists, creating now");

            success = directory.mkdir();
            if (success) {
                System.out.printf("Successfully created new directory : %s%n", dir);
            } else {
                System.out.printf("Failed to create new directory: %s%n", dir);
            }
        }

        // Creating new file in Java, only if not exists
        filename = dir+filename;

        File f = new File(filename);
        if (f.exists()) {
            System.out.println("File already exists");
        } else {
            System.out.println("No such file exists, creating now");
            success = f.createNewFile();
            if (success) {
                System.out.printf("Successfully created new file: %s%n", f);
            } else {
                System.out.printf("Failed to create new file: %s%n", f);
            }
        }
        // close Scanner to prevent resource leak
    }

    static void displayFile(File directoryPath)
    {
        File[] filesList = directoryPath.listFiles();
        System.out.println("List of files and directories in the specified directory:");
        for(File file : filesList) {
            System.out.println("File name: "+file.getName());
            System.out.println("File path: "+file.getAbsolutePath());
            System.out.println("Size :"+file.getTotalSpace());
            System.out.println(" ");
        }
    }

    static void modifyFile(String filePath, String oldString, String newString)
    {
        File fileToBeModified = new File("/home/admin/Downloads/a.txt");
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();
            while (line != null)
            {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = oldContent.replaceAll(oldString, newString);
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    static void renameFile()
    {
        File file = new File("/home/admin/Downloads/a.txt");
        File rename = new File("/home/admin/Downloads/Solution11.txt");
        boolean flag = file.renameTo(rename);
        if (flag == true)
            System.out.println("File Successfully Rename");
        else
            System.out.println("Operation Failed");
    }

    public static void deleteDirectory(File file)
    {
        // store all the paths of files and folders present
        // inside directory
        if (file.isDirectory()) {
            for (File subfile : file.listFiles()) {
                // if it is a subfolder,e.g Rohan and Ritik,
                //  recursively call function to empty subfolder
                if (subfile.isDirectory()) {
                    deleteDirectory(subfile);
                }
                subfile.delete();
            }
        }
        else
            file.delete();
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String dir = "/home/admin/Downloads/";
        String FileName ="";
        while (true) {
            System.out.println("1.Create a new directory and file in all Levels");
            System.out.println("2.List all directories and files");
            System.out.println("3.Update file content");
            System.out.println("4.Update directory and file names");
            System.out.println("5.Delete directory and file.");
            int choice = sc.nextInt();
            switch (choice) {

                case 1: {
                    while (true) {
                        System.out.println("Enter File Name : ");
                        FileName = sc.next();
                        fileCreation(dir + FileName, FileName);
                        System.out.println("press One to Create another file 0 to exit");
                        String s = sc.next();
                        if (s.equals("0"))
                            break;
                    }
                }
                break;
                case 2: {
                    System.out.println("Enter Directory Name  ");
                    String directoryName = sc.next();
                    displayFile(new File(dir));
                    break;
                }
                case 3:
                    modifyFile(dir,"hi","yes");
                    break;
                case 4:
                    renameFile();
                    break;
                case 5:
                    deleteDirectory(new File(dir+"Solution.cpp"));
                default:
                    break;
            }
        }
    }
}

