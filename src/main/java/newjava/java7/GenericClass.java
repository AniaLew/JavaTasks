package newjava.java7;

public class GenericClass<X> {

  <T> GenericClass(String description, T t) {
    System.out.println(description + ": " + t);
  }
}