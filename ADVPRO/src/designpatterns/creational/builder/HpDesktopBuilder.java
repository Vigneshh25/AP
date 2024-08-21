package designpatterns.creational.builder;

class HpDesktopBuilder extends DesktopBuilder {
   public void buildMotherboard() {
       desktop.setMotherboard("HP Motherboard");
   }


   public void buildProcessor() {
       desktop.setProcessor("Intel Core i5");
   }


   public void buildMemory() {
       desktop.setMemory("16GB DDR4 RAM");
   }


   public void buildStorage() {
       desktop.setStorage("512GB SSD");
   }


   public void buildGraphicsCard() {
       desktop.setGraphicsCard("Integrated Graphics");
   }
}
