package B_Arrays;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;


public class arrays {
    /// ////////////////////////////// EASY ////////////////////////////////////

    // FIND SECOND LARGEST AND SECOND SMALLEST
    public static void secondMaxMin(int[] arr) {

        if (arr == null || arr.length < 2) {
            System.out.println("Array must have at least two elements");
            return;
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        boolean foundSecondLargest = false;
        boolean foundSecondSmallest = false;

        for (int num : arr) {

            // Largest & second largest
            if (num > largest) {
                if (largest != Integer.MIN_VALUE) {
                    secondLargest = largest;
                    foundSecondLargest = true;
                }
                largest = num;
            } else if (num < largest) {
                if (num > secondLargest) {
                    secondLargest = num;
                    foundSecondLargest = true;
                }
            }

            // Smallest & second smallest
            if (num < smallest) {
                if (smallest != Integer.MAX_VALUE) {
                    secondSmallest = smallest;
                    foundSecondSmallest = true;
                }
                smallest = num;
            } else if (num > smallest) {
                if (num < secondSmallest) {
                    secondSmallest = num;
                    foundSecondSmallest = true;
                }
            }
        }

        if (foundSecondLargest) {
            System.out.println("Second largest: " + secondLargest);
        } else {
            System.out.println("Second largest does not exist");
        }

        if (foundSecondSmallest) {
            System.out.println("Second smallest: " + secondSmallest);
        } else {
            System.out.println("Second smallest does not exist");
        }
    }

    //CHECK IF ARRAY IS SORTED IS ASCENDING OR DESCENDING ORDER
    public static boolean isSorted(int arr[]) {
        boolean ascendingSorted = true, descendingSorted = true;

        //ascending sorted check
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                ascendingSorted = false;

            }
            if (arr[i] < arr[i + 1]) {
                descendingSorted = false;

            }
        }
        return ascendingSorted || descendingSorted;
    }

    //REMOVE DUPLICATE ELEMENTS IN ASC SORTED ARRAY
    public static void remDuplicate(int arr[]) {
       int pos=0;
       for(int i=1;i<arr.length;i++){
           if(arr[pos]!=arr[i]){
               arr[++pos]=arr[i];
           }
       }
    }

    ///////////////////////////////// MEDIUM ////////////////////////////////////

}
