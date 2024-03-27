package com.example.demo.heap;

import java.util.*;

class FrequencyTracker {
    Map<Integer,Integer> valueToCnt;
    Map<Integer, Set<Integer>> cntToValue;
    int[] arr;
    int size = 0;

    public FrequencyTracker() {
        this.valueToCnt = new HashMap<>();
        this.cntToValue = new HashMap<>();
        this.arr = new int[]{};
    }

    public void add(int number) {
        grow(size + 1);
        arr[size ++] = number;
        int cnt = valueToCnt.getOrDefault(number,0);
        valueToCnt.put(number,cnt + 1);
        if (cntToValue.get(cnt) != null){
            cntToValue.get(cnt).remove(number);
            if (cntToValue.get(cnt).size() == 0){
                cntToValue.remove(cnt);
            }
        }
        Set<Integer> set = cntToValue.getOrDefault(cnt + 1, new HashSet<>());
        set.add(number);
        cntToValue.put(cnt + 1,set);
    }

    //判断是否扩容
    public void grow(int minCapacity){
        int oldCapacity = arr.length;
        if(oldCapacity < minCapacity){
            int newCapacity = oldCapacity + (oldCapacity >> 1) + 1;
            arr = Arrays.copyOf(arr,newCapacity);
        }
    }

    public void deleteOne(int number) {
        if(valueToCnt.containsKey(number)){
            for(int i = 0; i < arr.length; i ++){
                if(arr[i] == number){
                    swap(i, --size);
                    int cnt = valueToCnt.get(number);
                    cntToValue.get(cnt).remove(number);
                    if (cntToValue.get(cnt).size() == 0){
                        cntToValue.remove(cnt);
                    }
                    if(cnt == 1){
                        valueToCnt.remove(number);
                    }else{
                        valueToCnt.put(number,cnt - 1);
                        Set<Integer> set = cntToValue.getOrDefault(cnt - 1,new HashSet<>());
                        set.add(number);
                        cntToValue.put(cnt - 1,set);
                    }
                    break;
                }
            }
        }
    }

    public void swap(int x, int y){
        arr[x] ^= arr[y];
        arr[y] ^= arr[x];
        arr[x] ^= arr[y];
    }

    public boolean hasFrequency(int frequency) {
        return cntToValue.containsKey(frequency);
    }
}

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * FrequencyTracker obj = new FrequencyTracker();
 * obj.add(number);
 * obj.deleteOne(number);
 * boolean param_3 = obj.hasFrequency(frequency);
 */