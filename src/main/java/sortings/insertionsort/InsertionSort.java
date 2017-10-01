package sortings.insertionsort;

import static sortings.DataProvider.switchNumbers;

import sortings.Sort;

import java.util.List;

public class InsertionSort implements Sort {

  @Override
  public List<Integer> sortIncreasingly(List<Integer> numbers){
    if (numbers.size() < 2){
      return numbers;
    }
    int j = numbers.size()-2;
    int x = 0;
    int i = 0;
    while (j >= 0){
      x = numbers.get(j);
      i = j + 1;
      while(i < numbers.size()){
        if( x <= numbers.get(i)){
          break;
        }
        switchNumbers(numbers, i-1, i);
        i = i+1;
      }
      numbers.set(i-1, x);
      j = j-1;
    }
    return numbers;
  }
  @Override
  public List<Integer> sortDecreasingly(List<Integer> numbers){
    if (numbers.size() < 2){
      return numbers;
    }
    int j = numbers.size()-2;
    int x = 0;
    int i = 0;
    while (j >= 0){
      x = numbers.get(j);
      i = j + 1;
      while(i < numbers.size()){
        if( x >= numbers.get(i)){
          break;
        }
        switchNumbers(numbers, i-1, i);
        i = i+1;
      }
      numbers.set(i-1, x);
      j = j-1;
    }
    return numbers;
  }

}
