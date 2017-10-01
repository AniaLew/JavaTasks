package sortings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataProvider {

  public static List<Integer> generateNumbers(int numberAmount, int numberRange) {
    List<Integer> numbers = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < numberAmount; i++) {
      numbers.add(random.nextInt(numberRange));
    }
    return numbers;
  }

  public static void switchNumbers(List<Integer> numbers, int index1, int index2) {
    int value = numbers.get(index1);
    numbers.set(index1, numbers.get(index2));
    numbers.set(index2, value);
  }

}
