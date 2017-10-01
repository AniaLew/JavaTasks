package mylists.myarraylist;

import mylists.MyList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MyArrayList<E> implements MyList<E>, Iterable<E>, Cloneable {

  private int size = 0;
  private int capacity = 16;
  private E[] elements;

  public MyArrayList() {
    elements = (E[]) new Object[capacity];
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0 ? true : false;
  }

  @Override
  public boolean contains(Object object) {
    if (elements == null) {
      return false;
    }
    if (object == null) {
      for (E element : elements) {
        if (!isListElementNotNull(element)) {
          return true;
        }
      }
    }
    for (E element : elements) {
      if (element.equals(object)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private int currentIndex = -1;

      @Override
      public boolean hasNext() {
        return (currentIndex + 1 < size);
      }

      @Override
      public E next() {
        return elements[++currentIndex];
      }

      @Override
      public void remove() {
        Object[] newArray = new Object[size - 1];
        for (int i = 0; i < newArray.length; i++) {
          if (i < currentIndex) {
            newArray[i] = elements[i];
          }
          if (i >= currentIndex) {
            newArray[i] = elements[i + 1];
          }
        }
        size--;
        if ((size == capacity / 2) && (size > 0)) {
          capacity = capacity / 2;
        }
        elements = (E[]) new Object[size];
        for (int i = 0; i < elements.length; i++) {
          elements[i] = (E) newArray[i];
        }
      }
    };
  }

  @Override
  public Object[] toArray() {
    return Arrays.copyOf(elements, size);
  }

  @Override
  public <T> T[] toArray(T[] table) throws NullPointerException, ArrayStoreException {
    if (table == null) {
      throw new NullPointerException();
    }
    if (table.length < size) {
      return (T[]) Arrays.copyOf(elements, size, table.getClass());
    } else {
      Arrays.fill(table, size, table.length - 1, null);
      return Arrays.copyOfRange((T[]) elements, 0, size - 1);
    }
  }

  @Override
  public boolean add(E element) {
    ensureCapacity(size + 1);
    elements[size++] = element;
    return true;
  }

  @Override
  public boolean remove(Object object) {
    Iterator integer = iterator();
    while (integer.hasNext()) {
      if (integer.next().equals(object)) {
        integer.remove();
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> collection) {
    for (Object element : collection) {
      if (!contains(element)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean addAll(Collection<? extends E> collection) {
    if (collection.equals(null)) {
      throw new NullPointerException();
    }
    boolean changed = false;
    for (Object object : collection) {
      add((E) object);
      changed = true;
    }
    return changed;
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> collection) {
    if (index < 0 || index > size) {
      if (collection.equals(null)) {
        throw new NullPointerException();
      }
    }
    Object[] objectCollection = new Object[collection.size()];
    int indexCounter = 0;
    for (Object object : collection) {
      objectCollection[indexCounter] = object;
      indexCounter++;
    }
    Object[] objects = new Object[size + collection.size()];
    boolean changed = false;
    for (int i = 0; i < objects.length; i++) {
      if (i < index) {
        objects[i] = elements[i];
        changed = true;
      }
      if ((i >= index) && (i < index + objectCollection.length)) {
        objects[i] = objectCollection[i - index];
        changed = true;
      }
      if (i >= index + objectCollection.length) {
        objects[i] = elements[i - objectCollection.length];
        changed = true;
      }
    }
    if (changed == true) {
      ensureCapacity(size + collection.size());
      size = size + collection.size();
      elements = (E[]) objects;
    }
    return changed;
  }

  @Override
  public boolean removeAll(Collection<?> collection) {
    if (collection == null) {
      return false;
    }
    boolean result = false;
    for (Object object : collection) {
      if (contains(object)) {
        remove(object);
        result = true;
      }
    }
    return result;
  }

  @Override
  public boolean retainAll(Collection<?> collection) {
    if (collection == null) {
      throw new NullPointerException();
    }
    boolean result = false;
    for (int i = 0; i < size; i++) {
      if (!collection.contains(elements[i])) {
        remove(elements[i]);
        result = true;
      }
    }
    return result;
  }

  @Override
  public void clear() {
    capacity = 16;
    size = 0;
    elements = (E[]) new Object[capacity];
  }

  @Override
  public E get(int index) throws ArrayIndexOutOfBoundsException{
    if (index < 0 || index >= size) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return elements[index];
  }

  @Override
  public E set(int index, E element) throws IndexOutOfBoundsException{
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    E previousElement = elements[index];
    elements[index] = element;
    return previousElement;
  }

  @Override
  public void add(int index, E element) throws IndexOutOfBoundsException{
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
    E[] array = Arrays.copyOf(elements, size);
    ensureCapacity(++size);
    elements[index] = element;
    for (int i = index + 1; i < size; i++) {
      elements[i] = array[i - 1];
    }
  }

  @Override
  public E remove(int index) throws IndexOutOfBoundsException{
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }
    E element = elements[index];
    Object[] newArray = new Object[size - 1];
    for (int i = 0; i < newArray.length; i++) {
      if (i < index) {
        newArray[i] = elements[i];
      }
      if (i >= index) {
        newArray[i] = elements[i + 1];
      }
    }
    size--;
    if ((size == capacity / 2) && (size > 0)) {
      capacity = capacity / 2;
    }
    elements = (E[]) new Object[size];
    elements = (E[]) newArray;
    return element;
  }

  @Override
  public int indexOf(Object object) {
    for (int i = 0; i < size; i++) {
      if (elements[i].equals(object)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public int lastIndexOf(Object object) {
    int index = -1;
    for (int i = 0; i < size; i++) {
      if (elements[i].equals(object)) {
        index = i;
      }
    }
    return index;
  }

  // LIST ITERATOR
  @Override
  public ListIterator<E> listIterator() {
    return new ListIterator<E>() {
      private int currentIndex = -1;

      @Override
      public boolean hasNext() {
        return (currentIndex + 1 < size) && (elements[currentIndex + 1] != null);
      }

      @Override
      public E next() {
        return elements[++currentIndex];
      }

      @Override
      public boolean hasPrevious() {
        return (currentIndex - 1 > -1) && (elements[currentIndex - 1] != null);
      }

      @Override
      public E previous() {
        currentIndex--;
        return elements[currentIndex];
      }

      @Override
      public int nextIndex() {
        return currentIndex + 1;
      }

      @Override
      public int previousIndex() {
        return currentIndex - 1;
      }

      @Override
      public void remove() {
        E[] newArray = (E[]) new Object[size - 1];
        for (int i = 0; i < newArray.length; i++) {
          if (i < currentIndex) {
            newArray[i] = elements[i];
          }
          if (i >= currentIndex) {
            newArray[i] = elements[i + 1];
          }
        }
        size--;
        if ((size == capacity / 2) && (size > 0)) {
          capacity = capacity / 2;
        }
        elements = (E[]) new Object[size];
        elements = newArray;
      }

      @Override
      public void set(E element) {
        elements[currentIndex] = element;
      }

      @Override
      public void add(E element) {
        E[] newArray = (E[]) new Object[size + 1];
        for (int i = 0; i < newArray.length; i++) {
          if (i < currentIndex) {
            newArray[i] = elements[i];
          }
          if (i == currentIndex) {
            newArray[i] = element;
          }
          if (i > currentIndex) {
            newArray[i] = elements[i - 1];
          }
        }
        size++;
        ensureCapacity(size);
        elements = (E[]) new Object[size];
        elements = newArray;
        currentIndex++;
      }
    };
  }

  //LIST ITERATOR WITH INDEX
  @Override
  public ListIterator<E> listIterator(int index) {
    return new ListIterator<E>() {
      int currentIndex = -1;

      @Override
      public boolean hasNext() {
        if (size == 0) {
          return false;
        }
        if ((index < 0) && (index >= size)) {
          throw new IndexOutOfBoundsException();
        }
        if (currentIndex == -1) {
          currentIndex = index;
        }
        return ((currentIndex + 1) < size) && (elements[currentIndex + 1] != null);
      }

      @Override
      public E next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        currentIndex++;
        return elements[currentIndex];
      }

      @Override
      public boolean hasPrevious() {
        if (size == 0) {
          return false;
        }
        if ((index < 0) && (index >= size)) {
          throw new IndexOutOfBoundsException();
        }
        if (currentIndex == -1) {
          currentIndex = index;
        }
        return (currentIndex - 1 > -1) && (elements[currentIndex - 1] != null);
      }

      @Override
      public E previous() {
        return elements[--currentIndex];
      }

      @Override
      public int nextIndex() {
        return currentIndex + 1;
      }

      @Override
      public int previousIndex() {
        return currentIndex - 1;
      }

      @Override
      public void remove() {
        E[] newArray = (E[]) new Object[size - 1];
        for (int i = 0; i < newArray.length; i++) {
          if (i < currentIndex) {
            newArray[i] = elements[i];
          }
          if (i >= currentIndex) {
            newArray[i] = elements[i + 1];
          }
        }
        size--;
        if ((size == capacity / 2) && (size > 0)) {
          capacity = capacity / 2;
        }
        elements = (E[]) new Object[size];
        elements = newArray;

      }

      @Override
      public void set(E element) {
        elements[currentIndex] = element;
      }

      @Override
      public void add(E element) {
        E[] newArray = (E[]) new Object[size + 1];
        for (int i = 0; i < newArray.length; i++) {
          if (i < currentIndex) {
            newArray[i] = elements[i];
          }
          if (i == currentIndex) {
            newArray[i] = element;
          }
          if (i > currentIndex) {
            newArray[i] = elements[i - 1];
          }
        }
        size++;
        ensureCapacity(size);
        elements = (E[]) new Object[size];
        elements = newArray;
      }

    };
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException {
    if ((fromIndex < 0 || toIndex > size - 1 || fromIndex > toIndex)) {
      throw new IndexOutOfBoundsException();
    }
    List<Object> list = new ArrayList<>();
    for (int i = fromIndex; i < toIndex; i++) {
      list.add(elements[i]);
    }
    return (List<E>) list;
  }

  public void ensureCapacity(int minCapacity) throws ArrayIndexOutOfBoundsException {
    if (minCapacity > (Integer.MAX_VALUE - 10)) {
      throw new ArrayIndexOutOfBoundsException();
    }
    if (minCapacity > capacity) {
      int newCapacity;
      if (2 * capacity > (Integer.MAX_VALUE - 10)) {
        newCapacity = Integer.MAX_VALUE - 10;
      } else {
        newCapacity = 2 * capacity;
      }
      E[] array = elements;
      capacity = newCapacity;
      elements = Arrays.copyOf(array, capacity);
    }
  }

  private boolean isListElementNotNull(E element) {
    Optional<E> optional = Optional.ofNullable(element);
    return optional.isPresent();
  }

  private int numberOfNotNullElements() {
    int counter = 0;
    for (int i = 0; i < size; i++) {
      if (isListElementNotNull(elements[i])) {
        counter++;
      }
    }
    return counter;
  }

  /**
   * Trims the array list to the size of array list if size is more than 16.
   */
  public void trimToSize() {
    if (size > 16) {
      capacity = size;
    }
  }

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    Integer[] array = new Integer[5];
    System.out.println(array.getClass().isInstance(list.getClass()));
  }

}

