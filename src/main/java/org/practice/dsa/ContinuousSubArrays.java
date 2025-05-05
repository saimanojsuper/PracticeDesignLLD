package org.practice.dsa;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ContinuousSubArrays {

  public static void main(String[] args){

    System.out.println(continuousSubarrays(new int[]{5,4,2,4}));

  }

//  nums =
//      [65,66,67,66,66,65,64,65,65,64]
//
//  Use Testcase
//  Output
//51
//  Expected
//43
  public static long continuousSubarrays(int[] nums) {

//    Deque<Integer> queue = createQueueFromArray(nums);

    long numberOfContinuousSubArrays = 0;

    for(int i=0;i<nums.length;i++){

    }



    for (int i = 0; i < nums.length; i++) {
      int max = Integer.MIN_VALUE;
      int min = Integer.MAX_VALUE;
      for (int j = i; j < nums.length; j++) {

        if(nums[i] > nums[j]){
          if(max < nums[i]) {
            max = nums[i];
          }
          if(min > nums[j]){
            min = nums[j];
          }
        }else {
          if(max < nums[j]) {
            max = nums[j];
          }
          if(min > nums[i]){
            min = nums[i];
          }
        }

        if (isSumDiffLessThanTwo(max, min)) {
          numberOfContinuousSubArrays++;
        } else {
          break;
        }
      }
    }
    return  numberOfContinuousSubArrays;
  }

  private static boolean isSumDiffLessThanTwo(int num, int num1) {
    return num - num1 <= 2;
  }

  private Queue<Integer> createQueueFromArray(int[] nums) {
    Queue<Integer> queue = new LinkedList<>();
    Arrays.stream(nums).forEach(queue::add);
    return queue;
  }

}
