package sortings.insertionsort;

import sortings.Sort;
import sortings.SortTest;

public class InsertionSortTest extends SortTest {

  @Override
  protected Sort provideSort() {
    return new InsertionSort();
  }

}