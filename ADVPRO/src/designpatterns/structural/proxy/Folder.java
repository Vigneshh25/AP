package designpatterns.structural.proxy;

public class Folder implements IFolder {

      @Override
      public void performOperations() {

      // access folder and perform various operations like copy or cut files 
      System.out.println("Performing operation on folder");

      }
}