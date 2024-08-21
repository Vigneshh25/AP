package designpatterns.creational.builder;

public class DesktopBuilderDemo {
   public static void main(String[] args) {
       DesktopDirector director = new DesktopDirector();


       DellDesktopBuilder dellDesktopBuilder = new DellDesktopBuilder();
       Desktop dellDesktop = director.buildDesktop(dellDesktopBuilder);


       HpDesktopBuilder hpDesktopBuilder = new HpDesktopBuilder();
       Desktop hpDesktop = director.buildDesktop(hpDesktopBuilder);


       dellDesktop.display();
       hpDesktop.display();
   }
}
