package org.practice.dp;

public class RodCutting {

  public static void main(String Args[]){

    int[] arr = {3};

    int[] mem = new int[arr.length+1];

    System.out.println("value : "+maxPrice(arr, arr.length, mem));

  }


  public static Integer maxPrice(int[] price,int n,int[] mem){

    if(n <= 0) return  0;
    if(n == 1) return price[0];

    if(mem[n] != 0) return mem[n];

    int value = 0;
    for(int i=1;i<=n;i++){
      value = Math.max(price[i-1]+maxPrice(price, n-i, mem), value);
    }
    mem[n] = value;
    return value;
  }

}
