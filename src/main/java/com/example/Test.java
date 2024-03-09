package com.example;

import java.util.*;

public class Test {

    public static void main(String[] args){
        System.out.println(lengthOfLongestSubstring("tmmzuxt"));
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int left = 0, right = 0;
        int len = 0;
        while(right < arr.length){
            char c = arr[right];
            right ++;
            int cnt = map.getOrDefault(c, 0);
            len ++;

            if(cnt == 0){
                max = Math.max(max,len);
                map.put(c,cnt + 1);
            }else {
                while(arr[left] != c){
                    int count = map.getOrDefault(arr[left], 0);
                    if (count > 0){
                        map.put(arr[left], 0);
                    }
                    left ++;
                    len --;
                }
                len --;
                left ++;
            }

        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    private static int calculateSize(int numElements) {
        int initialCapacity = 8;
        // Find the best power of two to hold elements.
        // Tests "<=" because arrays aren't kept full.
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        }
        return initialCapacity;
    }
}
