package org.practice.dsa;

import java.util.HashMap;
import java.util.PriorityQueue;

public class MinHeap {

  public static void main(String[] Args) throws Exception {

    HashMap<String, String> map = new HashMap<>();



    MinHeap minHeap = new MinHeap(10);

    PriorityQueue<Integer> queue= new PriorityQueue<>();


    minHeap.insert(3);
    minHeap.insert(2);
    minHeap.insert(8);
    minHeap.insert(10);
    minHeap.insert(12);
    minHeap.insert(12);
    minHeap.insert(12);
    minHeap.insert(12); minHeap.insert(12); minHeap.insert(12); minHeap.insert(12);


    System.out.println(minHeap.poll());
    System.out.println(minHeap.poll());
    System.out.println(minHeap.poll());
    System.out.println(minHeap.poll());
    System.out.println(minHeap.poll());

  }

  private int[] heap;
  private int currentSize;

  public MinHeap(int size){
    heap = new int[size];
    this.currentSize = 0;
  }

  public int getParent(int child) {
    return (child - 1) / 2;
  }

  public int getLeftChild(int parent) {
    return 2 * parent + 1;
  }

  public int getRightChild(int parent) {
    return 2 * parent + 2;
  }

  public int peek() throws Exception {

    if(heap == null || currentSize == 0) throw new Exception("Heap is empty");

    return heap[0];
  }

  public void insert(int val) throws Exception {

    if(heap == null) throw new Exception("Heap is empty");
    if(heap.length <= currentSize) throw  new Exception("Heap is over the capacity");

    heap[currentSize] = val;



    int index = currentSize;

    while (getParent(index) >= 0 && heap[getParent(index)] > heap[index]){
      swap(heap, index, getParent(index));
      index = getParent(index);
    }
    currentSize++;

  }


  public int poll() throws Exception {

    if(heap == null || currentSize == 0) throw new Exception("Heap is empty");

    int val = heap[0];
    heap[0] = heap[currentSize - 1];

    int index = 0;

    while (getLeftChild(index) < currentSize){

      int smallerIndex = index;

      if(heap[smallerIndex] > heap[getRightChild(index)]) smallerIndex = getRightChild(index);

      if(heap[smallerIndex] > heap[getLeftChild(index)]) smallerIndex = getLeftChild(index);

      if(index == smallerIndex){
        break;
      }else {
        swap(heap, index, smallerIndex);
        index = smallerIndex;
      }
    }

    currentSize--;

    return val;
  }

  public void swap(int[] arr,int i, int j){
    int a = arr[i];
    arr[i] = arr[j];
    arr[j] = a;
  }

}
