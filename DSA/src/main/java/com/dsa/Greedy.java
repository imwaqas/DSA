package com.dsa;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Greedy {

    public static void main(String[] args) {

//        int[] arr = {5,10,2,1};
//        System.out.println("minCoins: "+ minCoins(arr, 57));
//        Activity[] activities = new Activity[]{
//            new Activity(2,3),
//            new Activity(1,4),
//            new Activity(5,8),
//            new Activity(6,10)
//        };
//        System.out.println("activitySelection: "+ activitySelection(activities));
//        Item[] items = new Item[] {
//          new Item(600,50),
//          new Item(500,20),
//          new Item(400,30)
//        };
//        System.out.println("fractionalKnapsack: " + fractionalKnapsack(items, 70));

        Job[] jobs = new Job[]{new Job(70,4), new Job(80,1), new Job(30,1), new Job(100,1)};
        System.out.println("jobSequencing: " + jobSequencing(jobs));
    }

    private static int jobSequencing(Job[] jobs) {
       Job[] array = Arrays.stream(jobs).sorted(Comparator.comparing(Job::getProfit).reversed()).toArray(Job[]::new);

       int res = array[0].profit;

       Job prev = array[0];
        for (int i = 1; i <array.length ; i++) {
            if(array[i].deadline> prev.deadline) {
                res+=array[i].profit;
            }
        }
        return res;
    }

    private static int fractionalKnapsack(Item[] items, int W) {
        Item[] array = Arrays.stream(items).sorted((item1, item2)-> Double.compare((double) item2.value/item2.weight, (double) item1.value/item1.weight))
            .toArray(Item[]::new);

        int res = 0;

        for (int i = 0; i < array.length; i++) {
            if(array[i].weight<=W) {
                res = res + array[i].value;
                W = W-array[i].weight;
            } else {
                res = res + array[i].value * W/array[i].weight;
                break;
            }
        }
        return res;
    }

    private static int minCoins(int[] arr, int amount) {
       Integer[] array = Arrays.stream(arr).boxed().toArray(Integer[]::new);
       Arrays.sort(array, Collections.reverseOrder());

        int res = 0;
        for (int i = 0; i <array.length; i++) {
            if(array[i]<=amount) {
                int value = amount/array[i];
                amount = amount - array[i]*value;
                res+=value;
                if(amount==0) {
                    return res;
                }
            }
        }
        return res;
    }

    private static int activitySelection(Activity[] activities) {
        Activity[] activities1 = Arrays.stream(activities).sorted(Comparator.comparing(Activity::getEnd)).toArray(Activity[]::new);

        int res = 1;
        Activity last = activities1[0];
        for (int i = 1; i < activities1.length; i++) {

            if(activities1[i].start>= last.end){
                res+=1;
                last = activities1[i];
            }
        }
        return res;
    }
}

class Activity {
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    int start;
    int end;

    Activity(int start, int end){
        this.start = start;
        this.end = end;
    }
}

 class Item{

    int value;
    int weight;
    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;

    }
}

class Job{
    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    int profit;
    int deadline;
    Job(int profit, int deadline) {
        this.profit = profit;
        this.deadline = deadline;
    }
}
