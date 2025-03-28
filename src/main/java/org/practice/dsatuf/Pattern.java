package org.practice.dsatuf;

import java.util.stream.IntStream;

public class Pattern {

  public static void main(String[] Args) {
    // decreasingCountPattern(10);
//    patternFromCenter(6);
    alphabetPattern(5);
  }

  /**
   * ****
   * ***
   * **
   * *
   *
   * @param n - pattern for n = 4
   */
  public static void decreasingCountPattern(int n) {
    if (n < 0) {
      throw new RuntimeException("Won't print the pattern for negative elements");
    }
    for (int i = n; i > 0; i--) {
      IntStream.range(0, i).forEach(e -> System.out.print("*"));
      System.out.println();
    }

    // Time complexity O(n^2) as for each element wee are printing n times
    // O(1) not storing anything
  }

  /**
   *    *
   *   ***
   *  *****
   *
   * @param n - pattern for n = 3
   */
  public static void patternFromCenter(int n) {
    if (n < 0) {
      throw new RuntimeException("Won't print the pattern for negative elements");
    }

    for (int i = 0; i < n; i++) {
      StringBuilder stringBuilder = new StringBuilder();
      for (int j = 0; j < 2 * n ; j++) {
        if (j <= n + i  && j >= n - i ) {
          stringBuilder.append("*");
        } else {
          stringBuilder.append(" ");
        }
      }
      System.out.println(stringBuilder);
    }

    // Time complexity O(n^2)
    // Space complexity O(n)
  }

  /**
   * C
   * BC
   * ABC
   *
   * @param n
   */
  public static void alphabetPattern(int n) {
    if (n < 0) {
      throw new RuntimeException("Won't print the pattern for negative elements");
    }

    int val = 'A';
    for (int i = 0; i < n; i++) {
      StringBuilder stringBuilder = new StringBuilder();
      for (int j = i; j >= 0; j--) {
        stringBuilder.append((char)(val + n - j - 1));
      }
      System.out.println(stringBuilder);
    }

  }

}
