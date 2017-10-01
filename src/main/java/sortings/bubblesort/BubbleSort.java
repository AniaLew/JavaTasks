package sortings.bubblesort;

import static sortings.DataProvider.switchNumbers;

import sortings.Sort;

import java.util.List;

public class BubbleSort implements Sort {

  @Override
  public List<Integer> sortIncreasingly(List<Integer> numbers) {
    int limit = numbers.size() - 1;
    while (limit > 0) {
      int changes = 0;
      for (int i = 0; i < limit; i++) {
        if (numbers.get(i) > numbers.get(i + 1)) {
          switchNumbers(numbers, i, i + 1);
          changes++;
        }
      }
      limit--;
      if (changes == 0){
        break;
      }
    }
    return numbers;
  }

  @Override
  public List<Integer> sortDecreasingly(List<Integer> numbers) {
    int limit = numbers.size() - 1;
    while (limit > 0) {
      int changes = 0;
      for (int i = 0; i < limit; i++) {
        if (numbers.get(i) < numbers.get(i + 1)) {
          switchNumbers(numbers, i, i + 1);
          changes++;
        }
      }
      limit--;
      if (changes == 0){
        break;
      }
    }
    return numbers;
  }
}



