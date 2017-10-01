package sortings;

import static sortings.DataProvider.generateNumbers;

import org.junit.Test;
import sortings.sorttestprovider.Sorting;
import sortings.bubblesort.BubbleSort;
import sortings.bucketsort.BucketSort;
import sortings.insertionsort.InsertionSort;
import sortings.selectionsort.SelectionSort;
import sortings.shellsort.ShellSort;

import java.util.ArrayList;
import java.util.List;

public class TimeComparisonOfSortingAlgorithms {

  Sort bubbleSort = new BubbleSort();
  Sort bucketSort = new BucketSort();
  Sort insertionSort = new InsertionSort();
  Sort selectionSort = new SelectionSort();
  Sort shellSort = new ShellSort();

  @Test
  public void shouldReturnTimeNeededToSortNumbers() {
    List<Integer> thousandNumbersTo100 = generateNumbers(1000, 100);

    long timeAtStart = System.nanoTime();
    bubbleSort.sortIncreasingly(thousandNumbersTo100);
    long timeAtEnd = System.nanoTime();
    long bubbleSortTime = timeAtEnd - timeAtStart;

    timeAtStart = System.nanoTime();
    bucketSort.sortIncreasingly(thousandNumbersTo100);
    timeAtEnd = System.nanoTime();
    long bucketSortTime = timeAtEnd - timeAtStart;

    timeAtStart = System.nanoTime();
    insertionSort.sortIncreasingly(thousandNumbersTo100);
    timeAtEnd = System.nanoTime();
    long insertionSortTime = timeAtEnd - timeAtStart;

    timeAtStart = System.nanoTime();
    selectionSort.sortIncreasingly(thousandNumbersTo100);
    timeAtEnd = System.nanoTime();
    long selectionSortTime = timeAtEnd - timeAtStart;

    timeAtStart = System.nanoTime();
    shellSort.sortIncreasingly(thousandNumbersTo100);
    timeAtEnd = System.nanoTime();
    long shellSortTime = timeAtEnd - timeAtStart;

    List<Sorting> sortings = new ArrayList<>();
    sortings.add(new Sorting("Bubble sort", bubbleSortTime));
    sortings.add(new Sorting("Bucket sort", bucketSortTime));
    sortings.add(new Sorting("Insertion sort", insertionSortTime));
    sortings.add(new Sorting("Selection sort", selectionSortTime));
    sortings.add(new Sorting("Shell sort", shellSortTime));

    Sorting min = sortings
        .stream()
        .min((sort1, sort2) -> sort1.getTime() < sort2.getTime() ? -1 : 1)
        .get();
    Sorting max = sortings
        .stream()
        .max((sort1, sort2) -> sort1.getTime() < sort2.getTime() ? -1 : 1)
        .get();
    System.out.println(
        "The fastest sorting algorithm is " + min.getKind() + " with time " + min.getTime() + ".");
    System.out.println(
        "The slowest sorting algorithm is " + max.getKind() + " with time " + max.getTime() + ".");

  }

}
