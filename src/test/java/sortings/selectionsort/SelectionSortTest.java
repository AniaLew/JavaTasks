package sortings.selectionsort;

import sortings.Sort;
import sortings.SortTest;

public class SelectionSortTest extends SortTest {

  @Override
  protected Sort provideSort() {
    return new SelectionSort();
  }

}