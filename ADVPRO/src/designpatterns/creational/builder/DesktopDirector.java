package designpatterns.creational.builder;

class DesktopDirector {
   public Desktop buildDesktop(DesktopBuilder builder) {
       builder.buildMotherboard();
       builder.buildProcessor();
       builder.buildMemory();
       builder.buildStorage();
       builder.buildGraphicsCard();
       return builder.getDesktop();
   }
}
