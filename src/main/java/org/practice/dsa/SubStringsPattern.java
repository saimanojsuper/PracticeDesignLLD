package org.practice.dsa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class SubStringsPattern {

  public static void main(String[] Args){
    String hel = "ABC";
    String helloz = "ABDOBCECODEBAANC";


//    int[] arr = generateTheCharArray(hel);
//    minSubStringWithWorstTimeComplexity(helloz, arr);

    twoPointerHashMap(helloz, hel);
  }

  public static void twoPointerHashMap(String word, String subString){

    Map<Integer,Integer> map = generateHashMap(subString);

    int conditions = map.keySet().size();

    Map<Integer,Integer> currentMap = new HashMap<>();

    int left = 0, right = 0;
    int minWindow = Integer.MAX_VALUE;

    int minLeft = 0;
    int minRight = 0;

    while (right < word.length()){

      char current = word.charAt(right);
      int currentInt = (int)current;

      System.out.println("left , "+left+" , right "+right);

      int count = currentMap.getOrDefault(currentInt, 0);

      currentMap.put(currentInt, count+1);

      if(currentMap.get(currentInt) == map.get(currentInt)){
        conditions--;
      }

      if(conditions == 0){

        char currentLeft = word.charAt(left);
        int currentIntLeft = (int)currentLeft;

        while (left < right && !map.containsKey(currentIntLeft)){

          System.out.println("left , "+left+" , right "+right);

          left++;
        }

        if(map.containsKey(currentIntLeft)){
          if(minWindow > right - left) {
            minWindow = right - left;
            minLeft = left;
            minRight = right+1;
            conditions++;
            left++;
            currentMap.put(currentIntLeft, currentMap.get(currentIntLeft)-1);
          }
        }

      }
      right++;
    }

    System.out.println(word.substring(minLeft, minRight));

  }

  public static Map<Integer,Integer> generateHashMap(String subString) {

    Map<Integer,Integer> map = new HashMap<>();

    subString.chars().forEach(ch -> {
      if (map.getOrDefault(ch, null) == null) {
        map.put(ch, 1);
      } else {
        map.put(ch, map.get(ch) + 1);
      }
    });

    return map;
  }

  private static void minSubStringWithWorstTimeComplexity(String helloz, int[] arr) {
    int start = 0;
    int end = helloz.length();
    int minWindow = Integer.MAX_VALUE;
    String minWindowString = "";
    while (start < end) {
      int[] arrCheck = arr.clone();
      for (int i = start; i < end; i++) {
        if(arrCheck[helloz.charAt(i)] != 0){
          arrCheck[helloz.charAt(i)]--;
        }
        if(isArrayEmpty(arrCheck) && minWindow > i-start+1){
          minWindow = i-start+1;
          minWindowString = helloz.substring(start, i+1);
        }
      }
      start++;
    }
    System.out.println("minWindow "+minWindow);
    System.out.println("minWindow string : "+minWindowString);
  }

  public static boolean isArrayEmpty(int[] arr) {
    return Arrays.stream(arr).allMatch(e -> e == 0);
  }


  public static int[] generateTheCharArray(String t){

    int[] charCount = new int[128];

    for(int i=0; i < t.length(); i++){
      charCount[t.charAt(i)]++;
    }

    IntStream.range(0,  charCount.length).forEach(index -> {
      if(charCount[index] != 0){
        System.out.println("index value: "+(char)index+" , count: "+ charCount[index]);
      }
    });

    return charCount;
  }
}
