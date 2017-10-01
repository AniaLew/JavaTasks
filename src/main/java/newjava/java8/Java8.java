package newjava.java8;

import java.util.Optional;
import java.util.StringJoiner;

public class Java8 implements DefaultMethodInterface {

  public static void lambdaWithInterface() {
    System.out.println("Single method interface - lambda.");
    int a = 5;
    int b = 7;
    System.out.println("Lambda operations on numbers: " + a + ", " + b);
    SingleMethodInterfaceLambda sumUp = (x, y) -> x + y;
    SingleMethodInterfaceLambda subtract = (x, y) -> x - y;
    System.out.println("x + y = " + sumUp.mathOperation(a, b));
    System.out.println("x - y = " + subtract.mathOperation(a, b));
  }

  public static void lambdaWithInterfaceAndVarargs() {
    System.out.println("Single method interface - lambda with variable arguments.");
    int[] numbers = {1, 2, 3, 4, 5, 6};
    System.out.println("Lambda operations on numbers: ");
    for (int i : numbers) {
      System.out.print(i + ", ");
    }
    System.out.println();
    SingleMethodInterfaceLambda2 sumUp = (x) -> {
      int sum = 0;
      for (int i : x) {
        sum += i;
      }
      return sum;
    };
    SingleMethodInterfaceLambda2 subtract = (x) -> {
      int subtraction = 12;
      for (int i : x) {
        subtraction -= i;
      }
      return subtraction;
    };

    System.out.println("sum = " + sumUp.mathOperation(numbers));
    System.out.println("subtract = " + subtract.mathOperation(numbers));
  }

  public static void staticMethodReference() {
    System.out.println(" Method reference is used to refer method of functional interface. "
        + "We can replace lambda expression with method reference."
        + "Referring to static method defined in the class: "
        + "ContainingClass::staticMethodName");
    int a = 5;
    int b = 3;
    SingleMethodInterfaceLambda operation = RefersToSingleMethodInterface::sumUp;
    System.out.println("sum = " + operation.mathOperation(a, b));
  }

  public static void instanceMethodReference() {
    System.out.println(" Method reference is used to refer method of functional interface. "
        + "We can replace lambda expression with method reference."
        + "Referring to instance method defined in the class: "
        + "containingObject::instanceMethodName");
    System.out.println("new ClassName()::instanceObjectName");
    int[] a = {5, 7, 3, 8};

    SingleMethodInterfaceLambda2 operation = new RefersToSingleMethodInterface2()::sumUp;
    System.out.println("sum = " + operation.mathOperation(a));
  }

//  public static void constructorReference(){
//    System.out.println(" Method reference is used to refer method of functional interface. "
//        + "We can replace lambda expression with method reference."
//        + "Referring to instance method defined in the class: "
//        + "containingObject::instanceMethodName");
//    System.out.println("new ClassName()::instanceObjectName");
//    int[] a = {5, 7, 3, 8};
//
//    SingleMethodInterfaceLambda2 operation = ConstructorReference::new;
//    System.out.println("sum = "+operation.mathOperation(a));
//  }

  public static void useDefaultAndStaticMethodInInterface() {
    System.out.println("Default method defined in interface was used.");
    DefaultMethodInterface object = new Java8();
    System.out.println("multiply = " + object.multiply(5.0, 7.0));
    DefaultMethodInterface.staticMethodInInterface();
    System.out.println("An interface and an abstract class is almost similar except that you can "
        + "create constructor in the abstract class whereas you can't do this in interface.");
  }

  public static void stringJoinerClass() {
    StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
    stringJoiner.add("Kate");
    stringJoiner.add("Susan");
    stringJoiner.add("Tom");
    StringJoiner stringJoiner2 = new StringJoiner(":", "[", "]");
    stringJoiner2.add("Smith");
    stringJoiner2.add("Thomas");
    StringJoiner mergedString = stringJoiner.merge(stringJoiner2);
    System.out.println(mergedString.toString());
  }

  public static void optional() {
    String[] str = new String[10];
    Optional<String> checkNull = Optional.ofNullable(str[5]);
    if (checkNull.isPresent()) {  // check for value is present or not
      String lowercaseString = str[5].toLowerCase();
      System.out.print(lowercaseString);
    } else {
      System.out.println("Optional - string value is not present");
    }
  }

  public static void main(String[] args) {
    lambdaWithInterface();
    System.out.println();
    lambdaWithInterfaceAndVarargs();
    System.out.println();
    staticMethodReference();
    System.out.println();
    instanceMethodReference();
    System.out.println();
    useDefaultAndStaticMethodInInterface();
    stringJoinerClass();
    optional();

  }

}