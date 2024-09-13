package designpatterns.structural.proxy;

public class ProxyPatternTest {
/*
   The Proxy Design Pattern provides a placeholder for another object to control access to it.
    the Proxy Pattern is used to create a representative object that controls access to another object.
      It may be remote, expensive to create or in need of being secured.

   In the Proxy Design Pattern, a client does not directly talk to the original object,
    it delegates calls to the proxy object which calls the methods of the original object.
     Moreover, the important point is that the client does not know about the proxy.
      The proxy acts as an original object for the client.
*/
       public static void main(String[] args) {

          User user = new User("dev", "dev");
          FolderProxy folderProxy = new FolderProxy(user);
          System.out.println("When userName and password are correct:");
          folderProxy.performOperations();
          System.out.println("------------------------------------");
          // if we give wrong userName and Password
          User userWrong = new User("abc", "abc");
          FolderProxy folderProxyWrong = new FolderProxy(userWrong);
          System.out.println("When userName and password are incorrect:");
          folderProxyWrong.performOperations();
      }
}