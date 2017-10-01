package newjava.java8;

public class RefersToSingleMethodInterface2 {

  public int sumUp(int... args) {
    int sum = 0;
    for (int i : args) {
      sum += i;
    }
    return sum;
  }
}