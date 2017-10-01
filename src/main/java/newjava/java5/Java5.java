package newjava.java5;

import static java.lang.StrictMath.pow;

import java.util.ArrayList;
import java.util.List;

public class Java5 {

  public static void variableArgument(int number, String... varargs) {
    System.out.println("The varrags allows the method to accept zero or muliple arguments. "
        + "We don't have to provide overloaded methods so less code."
        + "There can be only one variable argument in the method.\n"
        + "Variable argument (varargs) must be the last argument.");

    for (String stringVariable : varargs) {
      System.out.println(number + ": " + stringVariable);
    }
  }

  public static void forEachLoop() {
    System.out.println("For each loop.");
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      list.add(i);
    }
    for (int i : list) {
      System.out.print(i + ", ");
    }
    System.out.println();
  }

  public static void staticImport() {
    System.out.println("Static Import: import static java.lang.StrictMath.*;");
    System.out.println(pow(5, 3));
  }

  public static void autoboxing() {
    System.out.println("Boxing int -> Integer");
    int number = 50;
    Integer boxedNumber = new Integer(number);
    Integer nextBoxedNumber = boxedNumber;
    System.out.println("Sum of boxed numbers: " + (boxedNumber + nextBoxedNumber));
    System.out.println("Unboxing Integer -> int");
    Integer anyIntegerNumber = new Integer(120);
    int intNumber = anyIntegerNumber;
    System.out.println("Unboxed number: " + intNumber);
  }

  public static void enumSize() {

    System.out.println("Enum in java is a data type that contains fixed set of constants. "
        + "The java enum constants are static and final implicitly. "
        + "Enum can have fields, constructors and methods.\n"
        + "Enum may implement many interfaces but cannot extend any class because it internally "
        + "extends Enum class. Constructor of enum type is private. If you don't declare private "
        + "compiler internally creates private constructor.");
    for (Size size : Size.values()) {
      System.out.println(size + " - " + size.getAbbreviation());
    }
    ;
    System.out.println("Size.valueOf(\"SMALL\"): " + Size.valueOf("SMALL"));

  }

  public static void genericTapeSafeObject() {
    System.out.println("Non-generic, casting needed if different objects: "
        + "ArrayList list = new ArrayList();");
    System.out.println("Before generics, we can store any type of objects in collection i.e. non-"
        + "generic. Now generics, forces the java programmer to store specific type of objects.");
    System.out.println("Generic: ArrayList<String> list = new ArrayList<String>();");
    ArrayList<String> list = new ArrayList<String>();
    for (int i = 123; i < 130; i++) {
      list.add("number_" + i);
    }
    for (String str : list) {
      System.out.println(str);
    }
  }


  public static void main(String[] args) {
    variableArgument(10, "a", "b", "c");
    variableArgument(20, "a", "b", "c", "d", "e");
    System.out.println();
    autoboxing();
    System.out.println();
    forEachLoop();
    System.out.println();
    staticImport();
    System.out.println();
    enumSize();
    System.out.println();
    genericTapeSafeObject();
  }

}