package sortings.bucketsort;

import sortings.Sort;
import sortings.SortTest;

public class BucketSortTest extends SortTest {

  @Override
  protected Sort provideSort() {
    return new BucketSort();
  }

}