package org.practice.dsatuf.Arrays;

import java.util.HashMap;
import java.util.Map;

public class Array {

  public static void main(String[] Args){

    int[] arr = {1,2,3,0,1,1,1,1,4,2,3};

    longestSubArrayWithKSum(arr, 3);

  }


  public static int longestSubArrayWithKSum(int[]arr, int k){

    // stores sum of subArray as key & index as value
    Map<Integer, Integer> prefixSum = new HashMap<>();

    int sum = 0;

    int maxLength = 0;

    for(int i=0; i<arr.length; i++){

      sum+=arr[i];

      if(sum == k){
        // i+1 since the index starts with 0
        maxLength = Math.max(maxLength, i+1);
      }

      if(prefixSum.containsKey(sum-k)){
        Integer previousIndex = prefixSum.get(sum-k);
        maxLength = Math.max(maxLength, i-previousIndex);
      }
      if(!prefixSum.containsKey(sum)) {
        prefixSum.put(sum, i);
      }
    }

    System.out.println("maxLength: "+maxLength);
    return maxLength;
  }

  public static int longestSubArrayPositiveNumbers(int[] arr, int k){
     int i=0;


     int sum = 0;
     int longestSize = 0;

     for(int j=0; j<arr.length; j++){

         sum+=arr[j];

         while(sum > k && i <= j){
             sum-=arr[i];
             i++;
         }

         if(sum == k){
             longestSize = Math.max(longestSize, j-i+1);
         }
     }

     return longestSize;
  }

}
