package org.practice.dsa;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversals {

  public static void main(String[] Args){
    ListNode one = new ListNode(1);
    ListNode two = new ListNode(2);
    ListNode three = new ListNode(3);
    ListNode four = new ListNode(4);
    ListNode five = new ListNode(5);
    ListNode six = new ListNode(6);

    one.left = two;
    one.right = three;
    two.left = four;
    two.right = five;
    three.right = six;

//    preOrderTraversal(one);
//    postOrderTraversal(one);

    levelOrderTraversal(one);
    List<List<Integer>> lists = levelOrder(one);
    lists.forEach(
        list -> {
          System.out.println();
          list.forEach(System.out::print);
        }
    );

  }

  public static void preOrderTraversal(ListNode head) {

    ListNode root = head;
    Stack<ListNode> list = new Stack<>();
    list.push(root);
    while (!list.isEmpty()) {
      root = list.pop();
      System.out.println(root.value);
      if (root.right != null) {
        list.push(root.right);
      }
      if (root.left != null) {
        list.push(root.left);
      }
    }
  }

  public static void postOrderTraversal(ListNode head){
//    ListNode root = head;
//    Stack<ListNode> list = new Stack<>();
//    List<Integer> listOrder = new LinkedList<>();
//    list.push(root);
//
//    while (!list.isEmpty()){
//      root = list.peek();
//
//      if(root.right != null){
//        list.push(root.right);
//      }
//
//      if(root.left != null){
//        list.push(root.left);
//      }
//
//      if(root.left == null && root.right==null)
//        listOrder.add(list.pop().value);

    if(head == null)
      return;

      postOrderTraversal(head.left);
      postOrderTraversal(head.right);
    System.out.println(head.value);
    }

  public static void levelOrderTraversal(ListNode node){


    List<Integer> list = new LinkedList<>();
    Queue<ListNode> queue = new LinkedList<>();

    queue.offer(node);

    while (!queue.isEmpty()){
        ListNode pollNode = queue.poll();
        list.add(pollNode.value);
        if(pollNode.left != null){
          queue.add(pollNode.left);
        }
        if(pollNode.right != null){
          queue.add(pollNode.right);
        }
    }
    list.forEach(System.out::println);

  }

  public static List<List<Integer>> levelOrder(ListNode root) {

    List<List<Integer>> result = new LinkedList<>();
    if(root == null){
      return result;
    }

    Queue<ListNode> queue = new LinkedList<>();


    queue.offer(root);
    ListNode temp = null;

    while(!queue.isEmpty()){
      List<Integer> resultTemp = new LinkedList<>();
      Queue<ListNode> tempQueue = new LinkedList<>();

      while(!queue.isEmpty()) {
        temp = queue.poll();
        resultTemp.add(temp.value);

        if(temp.left != null){
          tempQueue.offer(temp.left);
        }

        if(temp.right != null){
          tempQueue.offer(temp.right);
        }
      }

      result.add(resultTemp);
      queue = tempQueue;

    }
    return result;
  }




}

class ListNode {
  public ListNode(int value) {
    this.value = value;
  }

  int value;
  ListNode left;
  ListNode right;
}
