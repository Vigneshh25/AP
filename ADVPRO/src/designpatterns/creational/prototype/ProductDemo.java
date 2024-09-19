package designpatterns.creational.prototype;

/*
    * It allows you to create a new object by copying or cloning existing objects
*/
public class ProductDemo {
   public static void main(String[] args) {
       ProductPrototype product1 = new Product("Laptop", 999.99);
       ProductPrototype product2 = new Product("Smartphone", 499.99);
       ProductPrototype newProduct1 = product1.clone();
       ProductPrototype newProduct2 = product2.clone();
       System.out.println("Original Products:");
       product1.display();
       product2.display();
       System.out.println("\nCloned Products:");
       newProduct1.display();
       newProduct2.display();
   }
}
