package sortings;

import static org.junit.Assert.assertEquals;
import static sortings.DataProvider.generateNumbers;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(JUnitParamsRunner.class)
public abstract class SortTest {

  protected abstract Sort provideSort();

  @Test
  @Parameters(method = "parameters")
  public void shouldReturnIncreasinglySortedNumbers(List<Integer> numbers, List<Integer> expected) {
    //given
    Sort sort = provideSort();
    //when
    List<Integer> actual = sort.sortIncreasingly(numbers);
    //then
    assertEquals(expected, actual);
  }

  private Object parameters() {
    List<Integer> hundredNumbers = generateNumbers(100, 10);
    List<Integer> hundredNumbersTo100 = generateNumbers(100, 100);
    List<Integer> thousandNumbersTo100 = generateNumbers(1000, 100);
    return new Object[]{
        new Object[]{Arrays.asList(), new ArrayList()},
        new Object[]{Arrays.asList(5), Arrays.asList(5)},
        new Object[]{Arrays.asList(10, 9), Arrays.asList(9, 10)},
        new Object[]{Arrays.asList(10, 9, 8), Arrays.asList(8, 9, 10)},
        new Object[]{Arrays.asList(10, 9, 9), Arrays.asList(9, 9, 10)},
        new Object[]{hundredNumbers, hundredNumbers.stream().sorted().collect(Collectors.toList())},
        new Object[]{hundredNumbersTo100,
            hundredNumbersTo100.stream().sorted().collect(Collectors.toList())},
        new Object[]{thousandNumbersTo100,
            thousandNumbersTo100.stream().sorted().collect(Collectors.toList())}

    };
  }

  @Test
  @Parameters(method = "parametersForDecreasingSort")
  public void shouldReturnDecreasinglySortedNumbers(List<Integer> numbers, List<Integer> expected) {
    //given
    Sort sort = provideSort();
    //when
    List<Integer> actual = sort.sortDecreasingly(numbers);
    //then
    assertEquals(expected, actual);
  }

  private Object parametersForDecreasingSort() {
    List<Integer> hundredNumbers = generateNumbers(100, 10);
    List<Integer> hundredNumbersTo100 = generateNumbers(100, 100);
    List<Integer> thousandNumbersTo100 = generateNumbers(1000, 100);
    return new Object[]{
        new Object[]{Arrays.asList(), new ArrayList()},
        new Object[]{Arrays.asList(5), Arrays.asList(5)},
        new Object[]{Arrays.asList(1, 2), Arrays.asList(2, 1)},
        new Object[]{Arrays.asList(1, 2, 3), Arrays.asList(3, 2, 1)},
        new Object[]{Arrays.asList(1, 2, 2), Arrays.asList(2, 2, 1)},
        new Object[]{hundredNumbers, hundredNumbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList())},
        new Object[]{hundredNumbersTo100,
            hundredNumbersTo100.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList())},
        new Object[]{thousandNumbersTo100,
            thousandNumbersTo100.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList())}
    };
  }

}