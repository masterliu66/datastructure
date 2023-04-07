package com.example.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 *
 * @author CCC
 * @date 2021/8/27 21:06
 */
public class MedianFinder {

    /** 小于等于中位数的集合 */
    PriorityQueue<Integer> low;
    /** 大于中位数的集合 */
    PriorityQueue<Integer> high;


    /** initialize your data structure here. */
    public MedianFinder() {
        this.low = new PriorityQueue<>(Comparator.comparingInt(v -> -v));
        this.high = new PriorityQueue<>(Comparator.comparingInt(Integer::intValue));
    }

    public void addNum(int num) {

       int lowSize = low.size(), highSize = high.size();

       if (lowSize == highSize) {

           if (high.isEmpty() || num <= high.peek()) {
               low.offer(num);
           } else {
               low.offer(high.poll());
               high.offer(num);
           }

       } else {

            if (num >= low.peek()) {
                high.offer(num);
            } else {
                high.offer(low.poll());
                low.offer(num);
            }
       }
    }

    public double findMedian() {
        return low.size() > high.size() ? low.peek() : (low.peek() + high.peek()) / 2.0;
    }

}
