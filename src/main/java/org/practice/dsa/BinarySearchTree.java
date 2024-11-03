package org.practice.dsa;

import java.util.List;

public class BinarySearchTree {

  public static void main(String[] args) {

    List<Integer> integerList = List.of(1, 3, 5, 2, 6);

    Node node = new Node(4);

    integerList.forEach(integer -> insertIntoTree(integer, node));

    printInAscendingOrder(node);

    System.out.println("element 6 : " + search(node, 6));

    try {
      System.out.println("element 6 : " + search(node, 10));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  public static void insertIntoTree(Integer value, Node node) {
    if (node == null)
      node = new Node(null);

    if (node.value == value)
      throw new RuntimeException("Value is already inserted");

    if (node.value < value) {
      if (node.right == null) {
        node.right = new Node(value);
      } else {
        insertIntoTree(value, node.right);
      }
    } else if (node.value > value) {
      if (node.left == null) {
        node.left = new Node(value);
      } else {
        insertIntoTree(value, node.left);
      }
    }
  }

  public static Node search(Node node, Integer value) {

    if (node == null)
      throw new RuntimeException("Invalid search element not present");

    if (node.value == value)
      return node;

    if (value > node.value)
      return search(node.right, value);
    else
      return search(node.left, value);
  }

  //   4
  //  / \
  // 2   5
  public static void printInAscendingOrder(Node node) {
    if (node == null)
      return;

    printInAscendingOrder(node.left);
    System.out.println(node.value);
    printInAscendingOrder(node.right);

  }
}
