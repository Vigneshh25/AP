package designpatterns.structural.proxy;

public class FolderProxy implements IFolder {

      Folder folder;
      User user;

      public FolderProxy(User user){
         this.user=user;
      }

      @Override
      public void performOperations() {
        if (user.getUserName().equalsIgnoreCase("dev")
        && user.getPassword().equalsIgnoreCase("dev")) {
         folder = new Folder();
         folder.performOperations();
         } else {
             System.out.println("You don't have access to this folder");
         }
      } 
}