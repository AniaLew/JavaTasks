package newjava.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Stream {

  public static void filteringCollectionUsingStream() {
    System.out.println("Stream - filtering and mapping");
    List<Product> products = new LinkedList<>();
    for (int i = 1; i < 20; i++) {
      products.add(new Product(i, "product_" + i, 120.0 + Double.valueOf(20 * i)));
    }
    List<Double> prices = products
        .stream()
        .filter(product -> product.getPrice() > 300)
        .map(product -> product.getPrice())
        .collect(Collectors.toList());
    prices.forEach(System.out::println);
  }

  public static void reduceCollectionUsingStream() {
    System.out.println("Stream - mapping and summing up with the use of reduce");
    List<Product> products = new LinkedList<>();
    for (int i = 1; i < 20; i++) {
      products.add(new Product(i, "product_" + i, 120.0 + Double.valueOf(20 * i)));
    }

    double sum = products
        .stream()
        .map(product -> product.getPrice())
        .reduce(0.0, Double::sum);
    System.out.println("Sum = " + sum);
    double sum2 = products
        .stream()
        .collect(Collectors.summingDouble(product -> product.getPrice()));
    System.out.println("sum2 = " + sum2);

    long productsNumber = products
        .stream()
        .filter(product -> product.getPrice() > 300)
        .count();
    System.out.println("Number of products which prices are higher then 300 = " + productsNumber);
  }

  public static void maxValueOfCollectionUsingStream() {
    System.out.println("Stream - filtering and mapping");
    List<Product> products = new LinkedList<>();
    for (int i = 1; i < 20; i++) {
      products.add(new Product(i, "product_" + i, 120.0 + Double.valueOf(20 * i)));
    }
    Product max = products
        .stream()
        .max(Comparator.comparing(product -> product.getPrice()))
        .get();
    System.out.println("Max price = " + max.getPrice());
    Product maxPriceProduct = products
        .stream()
        .max((product1, product2) -> product1.getPrice() > product2.getPrice() ? 1 : -1)
        .get();
    System.out.println("Max price = " + maxPriceProduct.getPrice());
  }

  public static void minValueOfCollectionUsingStream() {
    System.out.println("Stream - filtering and mapping");
    List<Product> products = new LinkedList<>();
    for (int i = 1; i < 20; i++) {
      products.add(new Product(i, "product_" + i, 120.0 + Double.valueOf(20 * i)));
    }
    Product min = products
        .stream()
        .min(Comparator.comparing(product -> product.getPrice()))
        .get();
    System.out.println("Min price = " + min.getPrice());
    Product minPriceProduct = products
        .stream()
        .max((product1, product2) -> product1.getPrice() < product2.getPrice() ? 1 : -1)
        .get();
    System.out.println("Min price = " + minPriceProduct.getPrice());
  }

  public static void averageValueOfCollectionUsingStream() {
    System.out.println("Stream - filtering and mapping");
    List<Product> products = new LinkedList<>();
    for (int i = 1; i < 20; i++) {
      products.add(new Product(i, "product_" + i, 120.0 + Double.valueOf(20 * i)));
    }
    double average = products
        .stream()
        .collect(Collectors.averagingDouble(product -> product.getPrice()));
    System.out.println("Average = " + average);
  }

  public static void iteratingCollectionUsingStream() {
    System.out.println("Stream - iterating, filtering, limiting");
    Stream.iterate(1, element -> element + 1)
        .filter(element -> element % 5 == 0)
        .limit(5)
        .forEach(System.out::println);
  }

  public static void methodReferenceInStream() {
    System.out.println("Stream - method reference in stream");
    List<Product> products = new LinkedList<>();
    for (int i = 1; i < 20; i++) {
      products.add(new Product(i, "product_" + i, 120.0 + Double.valueOf(20 * i)));
    }

    List<Double> list = products
        .stream()
        .filter(product -> product.getPrice() > 300)
        .map(Product::getPrice)
        .collect(Collectors.toList());
    list.forEach(System.out::println);
  }

  public static void mutableReductionUsingStream() {
    System.out.println("Stream - grouping into map using stream");
    List<Product> products = new LinkedList<>();
    for (int i = 1; i < 20; i++) {
      products.add(new Product(i, "A_product", 120.0 + Double.valueOf(20 * i)));
    }
    for (int i = 21; i < 40; i++) {
      products.add(new Product(i, "B_product", 120.0 + Double.valueOf(20 * i)));
    }
    for (int i = 41; i < 60; i++) {
      products.add(new Product(i, "C_product", 120.0 + Double.valueOf(20 * i)));
    }
    Map<String, Optional<Product>> map = products
        .stream()
        .collect(Collectors
            .groupingBy(Product::getName,
                Collectors.maxBy(Comparator.comparing(Product::getPrice))));

    for (String key : map.keySet()) {
      System.out.println(
          key + " : " + map.get(key).get().getId() + ",  " + map.get(key).get().getPrice());
    }
  }

  public static void useCollectorsToFetchList() {
    System.out.println("Stream - filtering and mapping");
    List<Product> products = new LinkedList<>();
    for (int i = 1; i < 20; i++) {
      products.add(new Product(i, "product_" + i, 120.0 + Double.valueOf(20 * i)));
    }
    List<Product> list = products
        .stream()
        .filter(product -> product.getPrice() < 350.0)
        .collect(Collectors.toList());
    list.forEach(System.out::println);
  }

  public static void useCollectorsToConverteDataToSet() {
    System.out.println("Stream - filtering and mapping");
    List<Product> products = new LinkedList<>();
    for (int i = 1; i < 20; i++) {
      products.add(new Product(i, "product_" + i, 120.0 + Double.valueOf(20 * i)));
    }
    Set<String> set = products
        .stream()
        .map(product -> product.getName())
        .collect(Collectors.toSet());
    set.forEach(System.out::println);
  }

  public static void forEachLoop() {
    System.out.println("forEach loop and its variations.");
    List<String> list = new ArrayList<>();
    list.add("Zoi");
    list.add("Ala");
    list.add("Peter");
    list.add("Tom");
    list.add("Robert");
    list.add("Kate");
    list.add("Liz");
    list.forEach(System.out::println);
    System.out.println("Ordered elements.");
    list.stream().forEachOrdered(name -> System.out.println(name));
  }

  public static void main(String[] args) {
    filteringCollectionUsingStream();
    iteratingCollectionUsingStream();
    reduceCollectionUsingStream();
    methodReferenceInStream();
    maxValueOfCollectionUsingStream();
    minValueOfCollectionUsingStream();
    averageValueOfCollectionUsingStream();
    mutableReductionUsingStream();
    forEachLoop();
    useCollectorsToFetchList();
    useCollectorsToConverteDataToSet();

  }
}