package sortings.shellsort;

import sortings.Sort;

import java.util.List;

public class ShellSort implements Sort {

  @Override
  public List<Integer> sortIncreasingly(List<Integer> numbers) {
    int interval = 1;
    int valueToInsert = 0;
    int inner = 0;
    while (interval < numbers.size() / 3) {
      interval = interval * 3 + 1;
    }
    while (interval > 0) {
      for (int outer = interval; outer < numbers.size(); outer++) {
        valueToInsert = numbers.get(outer);
        inner = outer;
        while ((inner > interval - 1) && (numbers.get(inner - interval) >= valueToInsert)) {
          numbers.set(inner, numbers.get(inner - interval));
          inner = inner - interval;
        }
        numbers.set(inner, valueToInsert);
      }
      interval = (interval - 1) / 3;
    }
    return numbers;
  }
  @Override
  public List<Integer> sortDecreasingly(List<Integer> numbers) {
    int interval = 1;
    int valueToInsert = 0;
    int inner = 0;
    while (interval < numbers.size() / 3) {
      interval = interval * 3 + 1;
    }
    while (interval > 0) {
      for (int outer = interval; outer < numbers.size(); outer++) {
        valueToInsert = numbers.get(outer);
        inner = outer;
        while ((inner > interval - 1) && (numbers.get(inner - interval) <= valueToInsert)) {
          numbers.set(inner, numbers.get(inner - interval));
          inner = inner - interval;
        }
        numbers.set(inner, valueToInsert);
      }
      interval = (interval - 1) / 3;
    }
    return numbers;
  }


}
