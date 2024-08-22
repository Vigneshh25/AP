package Design_Datastructure.java8.reflection;

import java.lang.reflect.*;

public class ReflectionDemo {

    // A sample class for demonstrating reflection
    public static class SampleClass {
        public String publicField;
        private int privateField;

        public SampleClass() {
            this.publicField = "Default Public Field";
            this.privateField = 10;
        }

        public SampleClass(String publicField, int privateField) {
            this.publicField = publicField;
            this.privateField = privateField;
        }

        public void publicMethod() {
            System.out.println("Public Method called. publicField = " + publicField);
        }

        private void privateMethod() {
            System.out.println("Private Method called. privateField = " + privateField);
        }

        @Deprecated
        public void annotatedMethod() {
            System.out.println("This method is deprecated.");
        }
    }

    public static void main(String[] args) {
        try {
            // 1. Loading a class dynamically using reflection
            Class<?> clazz = Class.forName("ReflectionDemo$SampleClass");

            // 2. Listing all fields of the class
            System.out.println("Fields:");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(" - " + field.getName() + " (" + field.getType().getName() + ")");
            }

            // 3. Listing all methods of the class
            System.out.println("\nMethods:");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(" - " + method.getName() + " (Return type: " + method.getReturnType().getName() + ")");
            }

            // 4. Listing all constructors of the class
            System.out.println("\nConstructors:");
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println(" - " + constructor);
            }

            // 5. Accessing and modifying public field
            Object obj = clazz.getDeclaredConstructor().newInstance(); // Create instance using default constructor
            Field publicField = clazz.getField("publicField");
            System.out.println("\nPublic Field Value: " + publicField.get(obj)); // Get value of publicField
            publicField.set(obj, "Modified Public Field"); // Modify the value of publicField
            System.out.println("Modified Public Field Value: " + publicField.get(obj));

            // 6. Accessing and modifying private field
            Field privateField = clazz.getDeclaredField("privateField");
            privateField.setAccessible(true); // Required to access private fields
            System.out.println("\nPrivate Field Value: " + privateField.get(obj));
            privateField.set(obj, 42); // Modify the value of privateField
            System.out.println("Modified Private Field Value: " + privateField.get(obj));

            // 7. Invoking a public method
            Method publicMethod = clazz.getMethod("publicMethod");
            publicMethod.invoke(obj);

            // 8. Invoking a private method
            Method privateMethod = clazz.getDeclaredMethod("privateMethod");
            privateMethod.setAccessible(true); // Required to access private methods
            privateMethod.invoke(obj);

            // 9. Invoking constructor with parameters
            Constructor<?> paramConstructor = clazz.getConstructor(String.class, int.class);
            Object paramObj = paramConstructor.newInstance("Param Public Field", 99);
            publicMethod.invoke(paramObj); // Calling public method on newly created object

            // 10. Checking for and processing annotations
            Method annotatedMethod = clazz.getMethod("annotatedMethod");
            if (annotatedMethod.isAnnotationPresent(Deprecated.class)) {
                System.out.println("\nAnnotated Method is deprecated.");
            }
            annotatedMethod.invoke(paramObj);

        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException |
                 IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
