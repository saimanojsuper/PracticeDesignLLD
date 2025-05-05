package org.practice.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSumTwoPointerArray {

  public static void main(String[] Args){

    int[] nums = {1,0,-1,0,-2,2};

    fourSum(nums, 0);
  }

  public static List<List<Integer>> fourSum(int[] nums, int target) {

    List<List<Integer>> result = new ArrayList<>();

    Arrays.sort(nums);

    for(int i=0;i<nums.length - 3;i++){

      if(i>0 && nums[i-1] == nums[i]){
        continue;
      }

      for(int j=i+1;j<nums.length - 2;j++){

        if(nums[j-1] == nums[j] ||  i == j){
          continue;
        }

        findTarget(i,j,nums,target,result);

      }

    }
    return result;
  }


  public static void findTarget(int i, int j, int[] nums, int target, List<List<Integer>> result){

    int initialSum = nums[i] + nums[j];

    int left = j+1;
    int right = nums.length - 1;

    System.out.print(" i : "+i);
    System.out.print(" j : "+j);
    System.out.print(" left : "+left);
    System.out.println(" right : "+right);

    while(left < right){

      System.out.print(" i : "+i);
      System.out.print(" j : "+j);
      System.out.print(" left : "+left);
      System.out.println(" right : "+right);

      int sum = nums[left]+nums[right]+initialSum;

      if(sum == target){

        result.add(List.of(nums[i],nums[j],nums[left],nums[right]));

        left++;
        right--;

        if(left<right && nums[left] == nums[left+1]){
          left++;
        }

        if(left<right &&nums[right] == nums[right-1]){
          right--;
        }

      } else if(sum < target){
        left++;
      } else{
        right--;
      }

    }

  }
}
