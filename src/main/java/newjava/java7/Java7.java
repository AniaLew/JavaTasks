package newjava.java7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Java7 {

  public static void binaryLiterals() {
    System.out.println("Java 7 - binary literals: ");
    byte byteNumber = 0b101;
    System.out.println("Binary literal in byte 0b101 = " + byteNumber);
    short shortNumber = 0b101;
    System.out.println("Binary literal in short 0b101 = " + shortNumber);
    int intNumber = 0b101;
    System.out.println("Binary literal in int 0b101 = " + intNumber);
    long longNumber = 0b0000011111100001;
    System.out.println("Binary literal in long 0b0000011111100001 = " + longNumber);
  }

  public static void stringInSwitch() {
    System.out.println();
    System.out.println("Java 7 - String type in switch statement: ");
    String game = "Card-Games";
    switch (game) {
      case "Hockey":
      case "Cricket":
      case "Football":
        System.out.println("This is a outdoor game");
        break;
      case "Chess":
      case "Card-Games":
      case "Puzzles":
      case "Indoor basketball":
        System.out.println("This is a indoor game");
        break;
      default:
        System.out.println("What game it is?");
    }
  }

  public static void catchingMultipleExceptionTypes() {
    try {
      System.out.println();
      System.out.println("Java 7 - when your are catching multiple exceptions, follow the rule of "
          + "generalized to more specialized. It means that, if you are using super (general) "
          + "class, don't use child (specialized) class.");
      double number = 30 / 0;
    } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
      System.out.println("Result of catching exceptions:");
      System.out.println(e.getMessage());
    }
  }

  public static void tryWithResourcesStatement() {
    System.out.println();
    System.out.println("In a try-with-resources statement, catch or finally block executes "
        + "after closing of the declared resources.");
    try (BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter("src/Java-7/TryExample.txt"))) {
      bufferedWriter.write("This is an example of try-with-resources statement.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void typeInferenceDiamond() {
    System.out.println();
    System.out.println("Type Inference for Generic Instance Creation:");
    List<Integer> list = new ArrayList<>();
    list.add(123);
    System.out.println("Number " + list.get(0) + " was added to ArrayList<>.");
  }

  public static void typeInferenceAndGenericConstructors() {
    System.out.println();
    System.out.println("Type Inference and Generic Constructors GenericClass <X>:");
    GenericClass<String> genericClass = new GenericClass<>("String type", "any string");
    GenericClass<Integer> genericClass2 = new GenericClass<>("Integer type", 123);
    GenericClass<Double> genericClass3 = new GenericClass<>("Double type", 123.456);
  }

  public static void javaNumericLiteralsWithUnderscore() {
    System.out.println();
    System.out.println("Java allows to use underscore in numeric literals to separate groups of "
        + "digits in order to  improve the readability of code.");
    // Underscore in integral literal
    int a = 10_00000;
    System.out.println("a = " + a);
    // Underscore in floating literal
    float b = 10.5_000f;
    System.out.println("b = " + b);
    // Underscore in binary literal
    int c = 0B10_10;
    System.out.println("c = " + c);
    // Underscore in hexadecimal literal
    int d = 0x1_1;
    System.out.println("d = " + d);
    // Underscore in octal literal
    int e = 01_1;
    System.out.println("e = " + e);
  }


  public static void main(String[] args) {
    binaryLiterals();
    stringInSwitch();
    catchingMultipleExceptionTypes();
    typeInferenceAndGenericConstructors();
    javaNumericLiteralsWithUnderscore();
    typeInferenceDiamond();

  }

}