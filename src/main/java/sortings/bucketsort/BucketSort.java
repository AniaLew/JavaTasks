package sortings.bucketsort;

import sortings.Sort;

import java.util.List;

public class BucketSort implements Sort {

  @Override
  public List<Integer> sortIncreasingly(List<Integer> numbers) {
    if (numbers.size() == 0) {
      return numbers;
    }
    int min = numbers.stream().min((number1, number2) -> number1 < number2 ? -1 : 1).get();
    int max = numbers.stream().max((number1, number2) -> number1 < number2 ? -1 : 1).get();
    int[] table = new int[max + 1 - min];
    for (int i = 0; i < table.length; i++) {
      table[i] = 0;
    }

    for (int number : numbers) {
      table[number - min]++;
    }

    int index = 0;
    for (int number = 0; number < table.length; number++) {
      for (int j = 0; j < table[number]; j++) {
        numbers.set(index, number + min);
        index++;
      }
    }
    return numbers;
  }

  @Override
  public List<Integer> sortDecreasingly(List<Integer> numbers) {
    if (numbers.size() == 0) {
      return numbers;
    }
    int min = numbers.stream().min((number1, number2) -> number1 < number2 ? -1 : 1).get();
    int max = numbers.stream().max((number1, number2) -> number1 < number2 ? -1 : 1).get();
    int[] table = new int[max + 1 - min];
    for (int i = 0; i < table.length; i++) {
      table[i] = 0;
    }

    for (int number : numbers) {
      table[number - min]++;
    }

    int index = 0;
    for (int number = table.length - 1; number >= 0; number--) {
      for (int j = 0; j < table[number]; j++) {
        numbers.set(index, number + min);
        index++;
      }
    }
    return numbers;
  }

}
