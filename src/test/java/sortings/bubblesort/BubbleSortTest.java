package sortings.bubblesort;

import sortings.Sort;
import sortings.SortTest;

public class BubbleSortTest extends SortTest {

  @Override
  protected Sort provideSort() {
    return new BubbleSort();
  }


}
