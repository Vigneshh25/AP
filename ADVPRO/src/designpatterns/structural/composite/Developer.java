package designpatterns.structural.composite;

public class Developer implements Employee {

     private String name; 
     private double salary; 

     public Developer(String name,double salary){ 
        this.name = name; 
        this.salary = salary; 
     }

     @Override
     public void add(Employee emp) {
     // this is leaf node so this method is not applicable to this class.
     }

     @Override
     public void remove(Employee emp) {
     // this is leaf node so this method is not applicable to this class.
     }

     @Override
     public Employee getChild(int i) {
        // this is leaf node so this method is not applicable to this class.
        return null;
     }

     @Override
     public String getName() {
        return name;
     }

     @Override
     public double getSalary() {
        return salary;
     }

     @Override
     public void print() {
        System.out.println("Name = " +getName()); 
        System.out.println("Salary = " +getSalary()); 
        System.out.println("-------------"); 
     }
}