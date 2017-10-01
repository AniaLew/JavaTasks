package newjava.java8;

public interface DefaultMethodInterface {

  default double multiply(double x, double y) {
    return x * y;
  }

  static void staticMethodInInterface() {
    System.out.println("Text was printed using static method defined in interface.");
  }

}