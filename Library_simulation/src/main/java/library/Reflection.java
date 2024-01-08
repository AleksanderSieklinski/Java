package library;

import java.lang.reflect.*;

// This is a class to test reflection in Java.
// It prints the methods, fields, constructors and superclass of Book.

public class Reflection {
    public static void main(String[] args) throws Exception {
        Class<?> bookClass = Class.forName("library.Book");
        Class<?> bookClass1 = new Book("1", "Title", "Author").getClass();
        Class<?> bookClass2 = Book.class;


        Method[] methods = bookClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("\nMethod name: " + method.getName());
            System.out.println("Return type: " + method.getReturnType());
            System.out.println("Access modifier: " + Modifier.toString(method.getModifiers()));
        }

        Field pagesNrField = bookClass1.getDeclaredField("pagesNr");
        pagesNrField.setAccessible(true);
        Book book = new Book("1", "Title", "Author");
        pagesNrField.setInt(book, 200);
        System.out.println("\nModified pagesNr: " + book.getPagesNr());

        Field[] fields = bookClass2.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("\nField name: " + field.getName());
            System.out.println("Field type: " + field.getType());
            System.out.println("Access modifier: " + Modifier.toString(field.getModifiers()));
        }

        Class<?> superclass = bookClass.getSuperclass();
        System.out.println("\nSuperclass: " + superclass.getName());

        Field[] superclassFields = superclass.getDeclaredFields();
        for (Field field : superclassFields) {
            System.out.println("\nField name: " + field.getName());
            System.out.println("Field type: " + field.getType());
            System.out.println("Access modifier: " + Modifier.toString(field.getModifiers()));
        }

        Method[] superclassMethods = superclass.getDeclaredMethods();
        for (Method method : superclassMethods) {
            System.out.println("\nMethod name: " + method.getName());
            System.out.println("Return type: " + method.getReturnType());
            System.out.println("Access modifier: " + Modifier.toString(method.getModifiers()));
        }

        Constructor<?>[] constructors = bookClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("\nConstructor: " + constructor);
        }
    }
}