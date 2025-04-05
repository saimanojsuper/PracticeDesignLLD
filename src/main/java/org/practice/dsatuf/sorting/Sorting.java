package org.practice.dsatuf.sorting;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Sorting {

  public static void main(String... Args) {

    int[] arr = {8, 8, 6, 2, 3, 1};
    int[] arr2 = {1,0,2,2,1,0};
    //    int[] arr = {1,2,2,3,4,4};
    //    bubbleSort(arr);
//    insertionSort(arr);
//    mergeSort(arr, 0 , arr.length-1);
//    insertionSortRec(arr, 0);
    quickSort(arr2,0, arr2.length-1);

    Arrays.stream(arr2).forEach(System.out::println);

  }

  public static void bubbleSort(int[] arr) {

    for (int i = 0; i < arr.length; i++) {
      int swap = 0; // This will make the best case as O(n) instead of O(n2)

      for (int j = 0; j < arr.length - i - 1; j++) {

        int k = j + 1;
        if (arr[j] > arr[k]) {
          swap(arr, j, k);
          System.out.println("swap : " + swap++);
        }
      }
      // Array is already sorted if there is no swap occured
      if (swap == 0) {
        System.out.println("Array is swapped");
        break;
      }
    }

  }

  public static void insertionSort(int[] arr) {

    //best case almost sorted - O(n), reverse ordered worst case - O(n2)
    for (int i = 0; i < arr.length; i++) {
      int k = i;
      while (k > 0 && arr[k - 1] > arr[k]) {
        swap(arr, k, k - 1);
        k--;
      }
    }
  }

  /**
   *
   * @param arr
   * @param start = 0
   * @param end = arr.length -1
   */
  public static void mergeSort(int[] arr, int start, int end){

    if(start >= end) return;

    int mid = (start+end)/2;

    mergeSort(arr, start, mid);

    mergeSort(arr, mid+1, end);

    merge(arr, start, mid, end);
  }

  public static void merge(int[] arr, int start, int mid, int end){

    int[] temp = new int[end-start+1];

    int start1 = start;
    int start2 = mid+1;
    int i=0;

    while (start1 <= mid && start2<=end){
      if(arr[start2] > arr[start1]){
        temp[i] = arr[start1];
        start1++;
      }else {
        temp[i] = arr[start2];
        start2++;
      }
      i++;
    }

    if (i != temp.length) {
      if (start1 <= mid) {
        while (start1 <= mid) {
          temp[i] = arr[start1];
          start1++;
          i++;
        }
      } else {
        while (start2 <= end) {
          temp[i] = arr[start2];
          start2++;
          i++;
        }
      }
    }

    int finalStart = start;
    // Adds the sorted temp array
    IntStream.range(start, end+1).forEach(ele -> arr[ele] = temp[ele-finalStart]);

  }

  /**
   *
   * @param arr
   * @param n - start with zero length subArray which is sorted
   */
  public static void insertionSortRec(int[]arr, int n){

    if(n == arr.length) return;

    int i=n;
    while(i > 0 && arr[i-1] > arr[i]){
        swap(arr, i, i-1);
      i--;
    }
    insertionSortRec(arr, n+1);
  }


  public static void quickSort(int[]arr, int low, int high){

    if(low < high){

      // taking pivot element to be low
      int pivotIndex = partition(arr, low, high);

      quickSort(arr, low, pivotIndex-1);
      quickSort(arr, pivotIndex+1, high);

    }

  }

  public static int partition(int[] arr,int low, int high){

    int pivot = low;
    int left = low;
    int right = high;

    while(left < right){

      while (left <= high-1 && arr[left] <= arr[pivot])
        left++;

      while(right >= low+1 && arr[right] > arr[pivot])
        right--;

      if(left < right) {
        swap(arr, left, right);
        left++;
        right--;
      }

//      if(arr[left] > arr[pivot] && arr[right] < arr[pivot]){
//        swap(arr, left, right);
//        left++;
//        right--;
//      } else if(arr[right] > arr[pivot]){
//        right--;
//      } else {
//        left++;
//      }
    }


    swap(arr, right, pivot);
    return right;

  }

  private static void swap(int[] arr, int j, int k) {
    int temp = arr[j];
    arr[j] = arr[k];
    arr[k] = temp;
  }

}
