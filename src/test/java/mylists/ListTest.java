package mylists;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import mylists.myarraylist.MyArrayList;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

@RunWith(JUnitParamsRunner.class)
public abstract class ListTest {

  protected abstract MyList provideList();

  private Object[] parametersListSize() {
    return new Object[]{0, 1, 2, 5, 10, 50, 100, 1_000};
  }
//  private Object[] parametersListSize() {
//    return new Object[]{0};
//  }

  @Test
  public void shouldReturnEmptyList() {
    //when
    MyList list = provideList();
    //then
    assertNotNull(list);
    assertEquals(0, list.size());
  }

  @Test
  @Parameters(method = "parametersListSize")
  public void shouldReturnSize(int listSize) {
    //given
    MyList list = generateMyList(listSize);
    //when + then
    int actual = 0;
    for (int i = 0; i < listSize; i++) {
      actual = list.size();
      assertEquals(listSize, actual);
    }
  }

  @Test
  @Parameters(method = "parametersListSize")
  public void shouldCheckIfEmpty(int listSize) {
    //given
    MyList list = generateMyList(listSize);
    boolean expected = false;
    if (listSize == 0) {
      expected = true;
    }
    //when
    boolean actual = list.isEmpty();
    //then
    assertEquals(expected, actual);
  }

  @Test
  @Parameters(method = "parametersListSize")
  public void shouldCheckIfLinkedListContainsElement(int listSize) {
    //given
    MyList<Integer> list = generateMyList(listSize);
    boolean expected = true;
    if (listSize == 0) {
      expected = false;
    }
    boolean actual = false;
    //when
    for (Integer i = 0; i < listSize; i++) {
      actual = list.contains(i);
      assertEquals(expected, actual);
    }
  }

  @Test
  public void shouldCheckIfLinkedListContainsNull() {
    //given
    MyList<Integer> list = generateMyListWithNullElement();
    boolean expected = true;
    boolean actual;
    //when
    actual = list.contains(null);
    //then
    assertEquals(expected, actual);
  }

  @Test
  public void shouldCheckIfLinkedListContainsElements() {
    //given
    int listSize = 50;
    MyList list = generateMyList(listSize);
    boolean actual;
    //when
    for (Integer i = 0; i < listSize; i++) {
      actual = list.contains(i);
      assertEquals(true, actual);
    }
  }

  @Test
  @Parameters(method = "parametersListSize")
  public void shouldReturnArrayOfObjects(int listSize) {
    //given
    MyList list = generateMyList(listSize);
    Object[] expectedList = new Object[listSize];
    Object[] actualList;
    for (int i = 0; i < listSize; i++) {
      expectedList[i] = i;
    }
    //when
    actualList = list.toArray();
    //then
    assertEquals(expectedList.length, actualList.length);
    assertArrayEquals(expectedList, actualList);
  }

  @Test
  @Parameters(method = "parametersListSize")
  public void shouldReturnArrayOfNullObjects(int listSize) {
    //given
    MyList list = generateMyList(listSize);
    Object[] expectedList = new Object[listSize];
    Object[] actualList;
    for (int i = 0; i < listSize; i++) {
      expectedList[i] = i;
    }
    //when
    actualList = list.toArray();
    //then
    assertEquals(expectedList.length, actualList.length);
    assertArrayEquals(expectedList, actualList);
  }
  @Ignore
  @Test
  public void shouldReturnElementsOfListInGivenTArray() {
    //given
    int listSize = 50;
    List list = generateMyList(listSize);
    Object[] expected = new Object[listSize];
    for (int i = 0; i < listSize; i++) {
      if(i % 2 == 0) {
        expected[i] = i;
      }
    }
    Object[] actual = new Object[listSize - 10];
    //when
    actual = list.toArray(actual);
    //then
    assertArrayEquals(expected, actual);
  }
  @Ignore
  @Test
  public void shouldAddOneElementToList() {
    //given
    List list = provideList();
    int expected = 123;
    //when
    boolean actual = list.add(expected);
    //then
    int actualElement = (int) list.get(0);
    assertEquals(true, actual);
    assertEquals(expected, actualElement);
  }
  @Ignore
  @Test
  @Parameters(method = "parametersForAddMethods")
  public void shouldAddElements(List<Integer> expected) {
    //given
    List list = provideList();
    List<Integer> actual = new LinkedList<>();
    //when
    for (int i = 0; i < expected.size(); i++) {
      list.add(expected.get(i));
    }
    Collections.sort(expected);
    //then
    boolean actualResult = false;
    for (int i = 0; i < list.size(); i++) {
      actualResult = actual.add(Integer.valueOf((Integer) list.get(i)));
    }
    Collections.sort(actual);
    assertEquals(true, actualResult);
    assertEquals(expected, actual);
  }

  private Object[] parametersForAddMethods() {
    return new Object[]{
        new Object[]{Arrays.asList(1, 2)},
        new Object[]{generateNumbers(17, 5)},
        new Object[]{generateNumbers(100, 100)},
        new Object[]{generateNumbers(1_000, 100)},
        new Object[]{generateNumbers(10_000, 100)},
    };
  }
  @Ignore
  @Test
  public void shouldCheckIfContainsAllElementsFromCollection() {
    //given
    List list = provideList();
    List<Integer> collectionContain = new LinkedList<>();
    List<Integer> collectionNotContain = new LinkedList<>();
    int arraySize = 50;
    for (int i = 0; i < arraySize; i++) {
      list.add(i);
      if (i % 2 == 0) {
        collectionContain.add(i);
      }
    }
    for (int i = 45; i < 60; i++) {
      collectionNotContain.add(i);
    }
    //when
    boolean actualContains = list.containsAll(collectionContain);
    boolean actualNotContains = list.containsAll(collectionNotContain);
    //then
    assertEquals(true, actualContains);
    assertEquals(false, actualNotContains);
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldCheckIfIteratorHasNextElement(int listSize) {
    //given
    List list = generateMyList(listSize);
    Iterator<Integer> iterator = list.iterator();
    boolean actual = false;
    int actualNumberOfListElements = 0;
    //when + then
    while (iterator.hasNext()) {
      actual = iterator.hasNext();
      assertEquals(true, actual);
      iterator.next();
      actualNumberOfListElements++;
    }
    assertEquals(listSize, actualNumberOfListElements);
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldReturnNextElementByIterator(int listSize) {
    //given
    List list = generateMyList(listSize);
    Iterator<Integer> iterator = list.iterator();
    int index = 0;
    //when
    while (iterator.hasNext()) {
      Integer actualNext = iterator.next();
      assertEquals(list.get(index), actualNext);
      index++;
    }
    //then
    assertEquals(listSize, index);
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldRemoveElementByIterator(int listSize) {
    //given
    List list = generateMyList(listSize);
    Iterator<Integer> iterator = list.iterator();
    int expectedSize = listSize - 1;
    if (listSize == 0) {
      expectedSize = 0;
    }
    Integer actual = 0;
    //when + then
    for (Integer element = 0; element < listSize; element++) {
      while (iterator.hasNext()) {
        actual = iterator.next();
        if (actual.equals(element)) {
          iterator.remove();
        }
      }
      assertEquals(expectedSize, list.size());
    }
  }
  @Ignore
  @Test
  public void shouldAddAllElementsFromCollection() {
    //given
    List list = provideList();
    List<Integer> expected = new LinkedList<>();
    int arraySize = 50;
    for (int i = 0; i < arraySize; i++) {
      if (i % 2 == 0) {
        expected.add(i);
      }
    }
    //when
    boolean actualResult = list.addAll(expected);
    //then
    assertEquals(true, actualResult);
    for (int i = 0; i < list.size(); i++) {
      assertEquals(expected.get(i), list.get(i));
    }
  }
  @Ignore
  @Test
  @Parameters(method = "parametersForAddCollection")
  public void shouldAddGivenElementsFromCollectionAtIndex(int index, List<Integer> collection,
      List<Integer> expected) {
    //given
    List list = provideList();
    for (int i = 0; i < 11; i++) {
      list.add(i);
    }
    //when
    boolean actualResult = list.addAll(index, collection);
    //then
    assertEquals(true, actualResult);
    assertEquals(11 + collection.size(), list.size());
    for (int i = 0; i < list.size(); i++) {
      assertEquals(expected.get(i), list.get(i));
    }
  }

  private Object[] parametersForAddCollection() {
    return new Object[]{
        new Object[]{4, Arrays.asList(99), Arrays.asList(0, 1, 2, 3, 99, 4, 5, 6, 7, 8, 9, 10)},
        new Object[]{10, Arrays.asList(98, 99),
            Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 98, 99, 10)},
        new Object[]{0, Arrays.asList(98, 99),
            Arrays.asList(98, 99, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)},
        new Object[]{6,
            Arrays.asList(99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99),
            Arrays.asList(0, 1, 2, 3, 4, 5, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                99, 99, 99, 99, 6, 7, 8, 9, 10)},
    };
  }
  @Ignore
  @Test
  public void shouldRemoveElementsFromCollection() {
    //given
    int listSize = 100;
    List list = provideList();
    List<Integer> collection = new ArrayList<>();
    List<Integer> expected = new ArrayList<>();
    for (int i = 0; i < listSize; i++) {
      list.add(i);
      if (i % 2 == 0) {
        collection.add(i);
      }
      if (i % 2 == 1) {
        expected.add(i);
      }
    }
    //when
    boolean actualResult = list.removeAll(collection);
    //then
    assertEquals(true, actualResult);
    assertEquals(expected.size(), list.size());
    for (int i = 0; i < list.size(); i++) {
      assertEquals(expected.get(i), list.get(i));
    }
  }
  @Ignore
  @Test
  public void shouldRetainAllElementsFromCollection() {
    //given
    int listSize = 10;
    List list = generateMyList(listSize);
    List<Integer> collection = new ArrayList<>();
    for (int i = 0; i < listSize; i++) {
      if (i % 2 == 0) {
        collection.add(i);
      }
    }
    //when
    boolean actualResult = list.retainAll(collection);
    //then
    assertEquals(true, actualResult);
    assertEquals(collection.size(), list.size());
    for (int i = 0; i < list.size(); i++) {
      assertEquals(collection.get(i), list.get(i));
    }
  }
  @Ignore
  @Test
  public void shouldRetainAllElementsFromCollectionIfContains() {
    //given
    int listSize = 10;
    List list = generateMyList(listSize);
    List<Integer> collection = new ArrayList<>();
    for (int i = 0; i < listSize; i++) {
      if (i % 2 == 0) {
        collection.add(i);
      }
    }
    int elementOutOfList = listSize + 123;
    collection.add(elementOutOfList);
    //when
    boolean actualResult = list.retainAll(collection);
    //then
    assertEquals(true, actualResult);
    assertEquals(collection.size() - 1, list.size());
    for (int i = 0; i < list.size(); i++) {
      assertEquals(collection.get(i), list.get(i));
    }
    boolean actual = list.contains(elementOutOfList);
    assertEquals(false, actual);
  }
  @Ignore
  @Test
  public void shouldClearList() {
    //given
    List list = provideList();
    for (int i = 0; i < 200; i++) {
      list.add(i);
    }
    //when
    list.clear();
    //then
    assertEquals(0, list.size());
  }
  @Ignore
  @Test
  public void shouldGetOneElement() {
    //given
    List list = provideList();
    for (int i = 0; i < 200; i++) {
      list.add(i);
    }
    int index = 45;
    int expected = 45;
    //when
    int actual = (int) list.get(index);
    //then
    assertEquals(expected, actual);
  }
  @Ignore
  @Test
  public void shouldGetElements() {
    //given
    MyArrayList<Integer> list = new MyArrayList<>();
    for (int i = 0; i < 200; i++) {
      list.add(i);
    }
    //when
    for (int i = 0; i < list.size(); i++) {
      int actual = list.get(i);
      assertEquals(i, actual);
    }
  }
  @Ignore
  @Test
  public void shouldSetOneElementOnIndex() {
    //given
    List list = provideList();
    int index = 54;
    int element = 123;
    for (int i = 0; i < 200; i++) {
      list.add(i);
    }
    int expected = (int) list.get(index);
    //when
    int actual = (int) list.set(index, element);
    int actuallySet = (int) list.get(index);
    //then
    assertEquals(expected, actual);
    assertEquals(element, actuallySet);
  }
  @Ignore
  @Test
  public void shouldSetElementsOnIndexes() {
    //given
    List list = provideList();
    List<Integer> elements = new ArrayList<>();
    List<Integer> expected = new ArrayList<>();
    List<Integer> actual = new ArrayList<>();
    for (int i = 0; i < 200; i++) {
      list.add(i);
      elements.add(i + 5);
      expected.add(i);
    }
    //when
    for (int i = 0; i < expected.size(); i++) {
      actual.add((Integer) list.set(i, elements.get(i)));
    }
    //then
    for (int i = 0; i < list.size(); i++) {
      assertEquals(expected.get(i), actual.get(i));
      assertEquals(elements.get(i), list.get(i));
    }
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldAddGivenElementOnIndex(int listSize) {
    //given
    List list = generateMyList(listSize);
    Integer expected = 999;
    int newListSize = listSize;
    //when + then
    for (int index = 0; index < listSize; index++) {
      list.add(index, expected);
      Integer actual = (Integer) list.get(index);
      assertEquals(expected, actual);
      newListSize++;
      assertEquals(newListSize, list.size());
    }
    if (listSize != 0) {
      int lastIndex = list.size() - 1;
      list.add(lastIndex, expected);
      assertEquals(newListSize + 1, list.size());
    }
  }
  @Ignore
  @Test
  public void shouldRemoveOneElementFromIndex() {
    //given
    final int listSize = 100;
    List list = generateMyList(listSize);
    int index = 10;
    int expectedSize = listSize - 1;
    int expectedElement = (int) list.get(index);
    //when
    int actualElement = (int) list.remove(index);
    //then
    assertEquals(expectedSize, list.size());
    assertEquals(expectedElement, actualElement);
  }
  @Ignore
  @Test
  public void shouldRemoveElementsFromIndexes() {
    //given
    final int listSize = 150;
    List list = generateMyList(listSize);
    //when + then
    for (int i = listSize - 1; i > -1; i--) {
      int actual = (int) list.remove(i);
      assertEquals(i, list.size());
      assertEquals(i, actual);
    }
  }
  @Ignore
  @Test
  public void shouldReturnIndexOfObject() {
    //given
    final int listSize = 200;
    List list = generateMyList(listSize);
    int expected = 20;
    //when
    int actual = list.indexOf(expected);
    //then
    assertEquals(expected, actual);
  }
  @Ignore
  @Test
  public void shouldReturnIndexesOfObjects() {
    //given
    final int listSize = 200;
    List list = generateMyList(listSize);
    //when + then
    for (int i = 0; i < listSize; i++) {
      int actual = list.indexOf(i);
      assertEquals(i, actual);
    }
  }
  @Ignore
  @Test
  public void shouldReturnLastIndexOfObject() {
    final int listSize = 200;
    List list = generateMyList(listSize);
    //when + then
    for (int i = 0; i < listSize; i++) {
      int actual = list.lastIndexOf(i);
      assertEquals(i, actual);
    }
  }
  @Ignore
  @Test
  public void shouldReturnLastIndexOfAnyObjectWhichAppearsMoreThenOnce() {
    final int anyNumber = 5;
    final int listSize = 100;
    List list = provideList();
    for (int i = 0; i < listSize; i++) {
      list.add(anyNumber);
    }
    //when
    int actual = list.lastIndexOf(anyNumber);
    assertEquals(listSize - 1, actual);
  }

  // TESTS FOR ListIterator
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldCheckIfListIteratorHasNextElement(int listSize) {
    //given
    List list = generateMyList(listSize);
    ListIterator<Integer> iterator = list.listIterator();
    int actualNumberOfListElements = 0;
    //when + then
    while (iterator.hasNext()) {
      boolean actualHasNext = iterator.hasNext();
      assertEquals(true, actualHasNext);
      iterator.next();
      actualNumberOfListElements++;
    }
    assertEquals(listSize, actualNumberOfListElements);
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldReturnNextElementByListIterator(int listSize) {
    //given
    List list = generateMyList(listSize);
    ListIterator<Integer> iterator = list.listIterator();
    int index = 0;
    //when
    while (iterator.hasNext()) {
      Integer actualNext = iterator.next();
      assertEquals(list.get(index), actualNext);
      index++;
    }
    //then
    assertEquals(listSize, index);
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldCheckIfListIteratorHasPreviousElement(int listSize) {
    //given
    List list = generateMyList(listSize);
    ListIterator<Integer> listIterator = list.listIterator();
    int actualNumberOfListElements = 0;
    while (listIterator.hasNext()) {
      int nextElement = listIterator.next();
      if (nextElement == listSize - 1) {
        break;
      }
    }
    //when + then
    while (listIterator.hasPrevious()) {
      boolean actualHasPrevious = listIterator.hasPrevious();
      assertEquals(true, listIterator.hasPrevious());
      listIterator.previous();
      actualNumberOfListElements++;
    }
    int actualNumberOfReachedElements = listSize - 1;
    if (listSize == 0) {
      actualNumberOfListElements = 0;
    }
    assertEquals(actualNumberOfListElements, actualNumberOfListElements);
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldReturnPreviousElementByListIterator(int listSize) {
    //given
    MyList list = generateMyList(listSize);
    ListIterator<Integer> listIterator = list.listIterator();
    int expectedElement = listSize - 1;
    while (listIterator.hasNext()) {
      int nextElement = listIterator.next();
      if (nextElement == listSize) {
        break;
      }
    }
    //when + then
    while (listIterator.hasPrevious()) {
      int actualElement = listIterator.previous();
      assertEquals(--expectedElement, actualElement);
    }
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldReturnPreviousIndexByListIterator(int listSize) {
    //given
    MyList list = generateMyList(listSize);
    ListIterator<Integer> listIterator = list.listIterator();
    int expectedIndex = listSize - 1;
    while (listIterator.hasNext()) {
      int nextElement = listIterator.next();
      if (nextElement == (listSize - 1)) {
        break;
      }
    }
    int actualIndex;
    //when + then
    while (listIterator.hasPrevious()) {
      actualIndex = listIterator.previousIndex();
      assertEquals(--expectedIndex, actualIndex);
      listIterator.previous();
    }
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldReturnNextIndexByListIterator(int listSize) {
    //given
    MyList list = generateMyList(listSize);
    ListIterator<Integer> iterator = list.listIterator();
    Integer index = 0;
    //when
    while (iterator.hasNext()) {
      Integer nextIndex = iterator.nextIndex();
      assertEquals(index, nextIndex);
      iterator.next();
      index++;
    }
    //then
    assertEquals(Integer.valueOf(listSize), index);
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldRemoveElementByListIterator(int listSize) {
    //given
    MyList list = generateMyList(listSize);
    int counter = listSize;
    int expectedSize = listSize;
    //when
    for (int i = 0; i < counter; i++) {
      ListIterator<Integer> iterator = list.listIterator();
      while (iterator.hasNext()) {
        int element = iterator.next();
        if (element == i) {
          iterator.remove();
          expectedSize--;
        }
        break;
      }
      assertEquals(expectedSize, list.size());
    }
    //then
    assertEquals(0, list.size());
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldRemoveElementByPreviousListIterator(int listSize) {
    //given
    MyList list = generateMyList(listSize);
    int counter = listSize;
    int expectedSize = listSize;
    //when
    for (int i = 0; i < counter; i++) {
      ListIterator<Integer> iterator = list.listIterator();
      while (iterator.hasNext()) {
        if (iterator.nextIndex() == list.size()) {
          break;
        }
        iterator.next();
      }
      while (iterator.hasPrevious()) {
        iterator.previous();
        iterator.remove();
        expectedSize--;
        break;
      }
      assertEquals(expectedSize, list.size());
    }
    int expected = 1;
    if (listSize == 0) {
      expected = 0;
    }
    //then
    assertEquals(expected, list.size());
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldSetElementByListIterator(int listSize) {
    //given
    MyList list = generateMyList(listSize);
    int[] expectedArray = new int[listSize];
    for (int i = 0; i < listSize; i++) {
      expectedArray[i] = 99;
    }
    int index = 0;
    //when
    ListIterator<Integer> iterator = list.listIterator();
    while (iterator.hasNext()) {
      iterator.next();
      iterator.set(99);
      int actual = (int) list.get(index);
      assertEquals(99, actual);
      index++;
    }
    //then
    for (int i = 0; i < list.size(); i++) {
      int actual = (int) list.get(i);
      assertEquals(expectedArray[i], actual);
    }
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldAddGivenElementOnIndexWithListIterator(int listSize) {
    //given
    MyList list = generateMyList(listSize);
    Integer expected = 0;
    int newListSize = listSize;
    //when + then
    ListIterator<Integer> listIterator = list.listIterator();
    while (listIterator.hasNext()) {
      Integer actual = (Integer) listIterator.next();
      listIterator.add(expected);
      assertEquals(expected, actual);
      expected++;
      newListSize++;
      assertEquals(newListSize, list.size());
    }
  }

  //Tests for ListIterator with Index
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldCheckIfIndexListIteratorHasNextElement(int listSize) {
    //given
    int index = listSize / 2;
    MyList list = generateMyList(listSize);
    ListIterator<Integer> iterator = list.listIterator((index));
    int expectedIndex = index;
    int actualIndex = 0;
    //when + then
    while (iterator.hasNext()) {
      boolean actualHasNext = iterator.hasNext();
      assertEquals(true, actualHasNext);
      actualIndex = iterator.next();
      expectedIndex++;
      assertEquals(expectedIndex, actualIndex);
    }
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldReturnNextElementByIndexListIterator(int arraySize) {
    //given
    MyList list = provideList();
    for (int i = 0; i < arraySize; i++) {
      list.add(i);
    }
    int index = arraySize / 2;
    ListIterator<Integer> iterator = list.listIterator(index);
    int expectedLastIndex = list.size() - 1;
    if (list.size() == 0) {
      expectedLastIndex = 0;
    }
    //when
    while (iterator.hasNext()) {
      Integer actualNext = iterator.next();
      assertEquals(list.get(++index), actualNext);
    }
    //then
    assertEquals(expectedLastIndex, index);
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldCheckIfIndexListIteratorHasPreviousElement(int listSize) {
    //given
    MyList list = generateMyList(listSize);
    int index = listSize - 1;
    ListIterator<Integer> listIterator = list.listIterator(index);
    int actualNumberOfListElements = 0;
    int expectedNumberOfListElements = listSize - 1;
    if (listSize == 0) {
      expectedNumberOfListElements = 0;
    }
    //when + then
    while (listIterator.hasPrevious()) {
      boolean actualHasPrevious = listIterator.hasPrevious();
      assertEquals(true, actualHasPrevious);
      listIterator.previous();
      actualNumberOfListElements++;
    }
    assertEquals(expectedNumberOfListElements, actualNumberOfListElements);
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldReturnPreviousElementByIndexListIterator(int listSize) {
    //given
    MyList list = generateMyList(listSize);
    int index = listSize - 1;
    ListIterator<Integer> listIterator = list.listIterator(index);
    int expectedElement = index;
    //when + then
    while (listIterator.hasPrevious()) {
      int actualElement = listIterator.previous();
      assertEquals(--expectedElement, actualElement);
    }
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldReturnPreviousIndexByIndexListIterator(int listSize) {
    //given
    MyList list = generateMyList(listSize);
    int index = listSize - 1;
    ListIterator<Integer> listIterator = list.listIterator(index);
    int expectedIndex = index;
    int actualIndex;
    //when + then
    while (listIterator.hasPrevious()) {
      actualIndex = listIterator.previousIndex();
      assertEquals(--expectedIndex, actualIndex);
      listIterator.previous();
    }
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldReturnNextIndexByIndexListIterator(int listSize) {
    //given
    MyList<Integer> list = generateMyList(listSize);
    int expected = listSize - 1;
    if (listSize == 0) {
      expected = 0;
    }
    //when
    for (int index = 0; index < listSize; index++) {
      ListIterator<Integer> iterator = list.listIterator(index);
      int expectedNextIndex = index;
      while (iterator.hasNext()) {
        expectedNextIndex++;
        int actualNextIndex = iterator.nextIndex();
        assertEquals(expectedNextIndex, actualNextIndex);
        iterator.next();
      }
    }
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldRemoveElementByIndexListIterator(int listSize) {
    //given
    int index = listSize / 2;
    MyList list = generateMyList(listSize);
    int expected = listSize;
    if (listSize == 0) {
      expected = listSize;
    }
    ListIterator<Integer> iterator = list.listIterator(index);
    //when
    while (iterator.hasNext()) {
      iterator.next();
      iterator.remove();
      expected--;
      break;
    }
    //then
    assertEquals(expected, list.size());
  }
  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldRemoveElementByPreviousIndexListIterator(int listSize) {
    //given
    int index = listSize / 2;
    MyList list = generateMyList(listSize);
    int expected = listSize;
    if (listSize == 0) {
      expected = 0;
    }
    ListIterator<Integer> iterator = list.listIterator(index);
    //when
    while (iterator.hasPrevious()) {
      iterator.previous();
      iterator.remove();
      expected--;
      break;
    }
    //then
    assertEquals(expected, list.size());
  }
  @Ignore
  @Test
  public void shouldSetElementByIndexListIterator() {
    //given
    int listSize = 2;
    MyList list = generateMyList(listSize);
    int[] expectedArray = new int[listSize];
    for (int i = 0; i < listSize; i++) {
      expectedArray[i] = 99;
    }
    //when
    for (int index = 0; index < list.size(); index++) {
      ListIterator<Integer> iterator = list.listIterator(index);
      if (iterator.hasNext()) {
        iterator.next();
        iterator.set(99);
        int actual = (int) list.get(index + 1);
        assertEquals(99, actual);
      }
    }
    //then
    for (int i = 1; i < list.size(); i++) {
      int actual = (int) list.get(i);
      assertEquals(expectedArray[i], actual);
    }
  }
  @Ignore
  @Test
  @Parameters(method = "parametersForListIteratorAdd")
  public void shouldAddElementByListIterator(int index, int element, List<Integer> expected) {
    //given
    MyList list = generateMyList(11);
    int expectedSize = list.size();
    //when
    ListIterator<Integer> iterator = list.listIterator(index);
    while (iterator.hasNext()) {
      iterator.next();
      iterator.add(element);
      expectedSize++;
      break;
    }
    assertEquals(expectedSize, list.size());
    //then
    for (int i = 0; i < list.size(); i++) {
      assertEquals(expected.get(i), list.get(i));
    }
  }

  private Object[] parametersForListIteratorAdd() {
    return new Object[]{
        new Object[]{0, 99, Arrays.asList(0, 99, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)},
        new Object[]{1, 99, Arrays.asList(0, 1, 99, 2, 3, 4, 5, 6, 7, 8, 9, 10)},
        new Object[]{3, 99, Arrays.asList(0, 1, 2, 3, 99, 4, 5, 6, 7, 8, 9, 10)},
        new Object[]{10, 99, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 99)},
    };
  }

   @Ignore
  @Test
  @Parameters(method = "parametersForPreviousListIteratorAdd")
  public void shouldAddElementByPreviousListIterator(int index, int element,
      List<Integer> expected) {
    //given
    MyList list = generateMyList(11);
    int expectedSize = list.size();
    ListIterator<Integer> iterator = list.listIterator(index);
    //when
    while (iterator.hasPrevious()) {
      iterator.previous();
      iterator.add(element);
      expectedSize++;
      break;
    }
    assertEquals(expectedSize, list.size());
    //then
    for (int i = 0; i < list.size(); i++) {
      assertEquals(expected.get(i), list.get(i));
    }
  }

  private Object[] parametersForPreviousListIteratorAdd() {
    return new Object[]{

        new Object[]{1, 99, Arrays.asList(99, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)},
        new Object[]{2, 99, Arrays.asList(0, 99, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)},
        new Object[]{3, 99, Arrays.asList(0, 1, 99, 2, 3, 4, 5, 6, 7, 8, 9, 10)},
        new Object[]{10, 99, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 99, 9, 10)},
        new Object[]{11, 99, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 99, 10)},

    };
  }
  @Ignore
  @Test
  public void shouldReturnSubList() {
    //given
    final int listSize = 200;
    MyList list = generateMyList(listSize);
    int fromIndex = 10;
    int toIndex = 60;
    List<Integer> expected = new ArrayList<>();
    List<Integer> actual;
    for (int i = 0; i < listSize; i++) {
      list.add(i);
      if (i >= fromIndex && i <= toIndex) {
        expected.add(i);
      }
    }
    //when
    actual = list.subList(fromIndex, toIndex);
    //then
    assertEquals(expected.size(), actual.size());
    for (int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i), actual.get(i));
    }
  }
//  @Ignore
  @Test
  @Parameters(method = "parametersListSize")
  public void shouldEnsureCapacity(int minCapacity) {
    //given
    MyList list = provideList();
    //when
    list.ensureCapacity(minCapacity);
    for (int i = 0; i < minCapacity; i++) {
      list.add(i);
    }
    //then
    assertEquals(minCapacity, list.size());
  }

  private MyList<Integer> generateMyList(int size) {
    MyList<Integer> list = provideList();
    for (int i = 0; i < size; i++) {
      list.add(i);
    }
    return list;
  }

  private MyList<Integer> generateMyListWithNullElement() {
    MyList<Integer> list = provideList();
    for (int i = 0; i < 4; i++) {
      list.add(i);
    }
    list.add(null);
    list.add(5);
    return list;
  }

  private static List<Integer> generateNumbers(int numberAmount, int numberRange) {
    List<Integer> numbers = new ArrayList<>();
    Random random = new Random();
    if (numberRange > 2_147_483_647) {
      numberRange = 2_147_483_647;
    }
    for (int i = 0; i < numberAmount; i++) {
      numbers.add(random.nextInt(numberRange));
    }
    return numbers;
  }
}



