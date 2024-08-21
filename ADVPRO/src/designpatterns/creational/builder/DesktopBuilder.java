package designpatterns.creational.builder;

abstract class DesktopBuilder {
   protected Desktop desktop = new Desktop();


   public abstract void buildMotherboard();


   public abstract void buildProcessor();


   public abstract void buildMemory();


   public abstract void buildStorage();


   public abstract void buildGraphicsCard();


   public Desktop getDesktop() {
       return desktop;
   }
}
