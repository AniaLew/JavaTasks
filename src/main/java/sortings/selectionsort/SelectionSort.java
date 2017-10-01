package sortings.selectionsort;

import static sortings.DataProvider.switchNumbers;

import sortings.Sort;

import java.util.List;

public class SelectionSort implements Sort {

  @Override
  public List<Integer> sortIncreasingly(List<Integer> numbers) {
    int index_min = 0;
    for (int j = 0; j < numbers.size(); j++) {
      index_min = j;
      for (int i = j + 1; i < numbers.size(); i++) {
        if (numbers.get(i) < numbers.get(index_min)) {
          index_min = i;
        }
      }
      switchNumbers(numbers, index_min, j);
    }
    return numbers;
  }
  @Override
  public List<Integer> sortDecreasingly(List<Integer> numbers) {
    int index_max = 0;
    for (int j = 0; j < numbers.size(); j++) {
      index_max = j;
      for (int i = j + 1; i < numbers.size(); i++) {
        if (numbers.get(i) > numbers.get(index_max)) {
          index_max = i;
        }
      }
      switchNumbers(numbers, index_max, j);
    }
    return numbers;
  }

}
