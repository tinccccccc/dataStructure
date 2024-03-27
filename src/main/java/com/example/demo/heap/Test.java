package com.example.demo.heap;

import java.util.*;

public class Test {
    public static void main(String[] args){
        FrequencyTracker tracker = new FrequencyTracker();
        tracker.add(7);
        tracker.add(5);
        tracker.deleteOne(8);
        tracker.add(8);
        tracker.deleteOne(7);
        System.out.println(tracker.hasFrequency(1));
    }



}
