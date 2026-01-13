package C_BinarySearch;

import java.util.ArrayList;

public class binarySearch {
    /// //////////// BS ON 1D ARRAYS //////////////////

    //IMPLEMENT LOWER BOUND
    public static int lowerBound(int arr[], int target) {
        int idx = arr.length;
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] >= target) {
                idx = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return idx;

    }

    //IMPLEMENT UPPER BOUND
    public static int upperBound(int arr[], int target) {
        if (arr == null || arr.length == 0) return arr.length;

        int start = 0, end = arr.length - 1, idx = arr.length;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] > target) {
                idx = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return idx;
    }

    //SEARCH INSERT POSITION
    public static int searchInsertPos(int arr[], int target) {
        int start = 0, end = arr.length - 1, idx = arr.length;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] >= target) {
                idx = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return idx;
    }

    //FLOOR AND CEIL OF ELEMENT IN AN ARRAY
    public static ArrayList<Integer> floorAndCeil(int arr[], int target) {
        int start = 0, end = arr.length - 1, floor = -1, ceil = -1;
        ArrayList<Integer> al = new ArrayList<>();// [FLOOR,CEIL]
        //floor
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] <= target) {
                floor = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        // reset start and end
        start = 0;
        end = arr.length - 1;
        //ceil
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] >= target) {
                ceil = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        al.add(floor);
        al.add(ceil);
        return al;

        // Floor = move right on <=
        //Ceil = move left on >=

    }

    // LAST OCCURRENCE OF ELEMENT IN SORTED ARRAY
    public static int lastOccurrence(int arr[], int target){
        int start=0,end=arr.length-1,idx=-1;

        while(start<=end){
            int mid= start +(end-start)/2;
            if(arr[mid]==target){
                idx=mid;
                start=mid+1;
            }else if(arr[mid]>target){
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return idx;

    }

    //COUNT OCCURRENCES IN SORTED ARRAY
    public static int countOccurrences(int arr[], int key) {
        int start = 0, end = arr.length - 1, firstOcc = -1, lastOcc = -1;

        // finding first occurrence
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == key) {
                firstOcc = mid;
                end = mid - 1;
            } else if (arr[mid] > key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        start=0;end=arr.length-1;

        //finding last occurrence
        while(start<=end){
            int mid = start + (end - start) / 2;
            if(arr[mid]==key){
                lastOcc=mid;
                start=mid+1;
            }else if(arr[mid]>key){
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        if(firstOcc==-1) return 0;
        return lastOcc-firstOcc+1;

    }



    /////////////// BS ON ANSWERS ////////////////////


    /////////////// BS ON 2D ARRAYS //////////////////
}
