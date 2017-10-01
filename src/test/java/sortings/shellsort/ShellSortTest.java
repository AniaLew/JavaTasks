package sortings.shellsort;

import sortings.Sort;
import sortings.SortTest;

public class ShellSortTest extends SortTest {

  @Override
  protected Sort provideSort() {
    return new ShellSort();
  }

}