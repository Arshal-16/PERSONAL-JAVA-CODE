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
    public static int lastOccurrence(int arr[], int target) {
        int start = 0, end = arr.length - 1, idx = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                idx = mid;
                start = mid + 1;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
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
        start = 0;
        end = arr.length - 1;

        //finding last occurrence
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == key) {
                lastOcc = mid;
                start = mid + 1;
            } else if (arr[mid] > key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (firstOcc == -1) return 0;
        return lastOcc - firstOcc + 1;

    }

    //SEARCH ELEMENT IN ROTATED SORTED ARRAY (NO DUPLICATES ARE PRESENT)
    public static int searchInRotated(int arr[], int target) {
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) return mid;
            if (arr[start] <= arr[mid]) {
                //left part is sorted
                if (target >= arr[start] && target < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                //right part is sorted
                if (target > arr[mid] && target <= arr[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;

    }

    //SEARCH ELEMENT IN ROTATED SORTED ARRAY (DUPLICATES ARE PRESENT)
    public static boolean searchInSortedDup(int arr[], int target) {
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) return true;
            if (arr[mid] == arr[start] && arr[mid] == arr[end]) {
                start++;
                end--;
                continue;
            }
            //if left part is sorted
            if (arr[start] <= arr[mid]) {
                if (target >= arr[start] && target < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            //if right part is sorted
            else {
                if (target > arr[mid] && target <= arr[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return false;
    }

    //MINIMUM IN ROTATED SORTED ARRAY
    public static int minInRotatedSorted(int arr[]) {
        int start = 0, end = arr.length - 1, min = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            //left half is sorted
            if (arr[start] <= arr[mid]) {
                min = Math.min(min, arr[start]);
                //after taking min discard the sorted half and search in remaining half
                start = mid + 1;

            }
            //right half is sorted
            else {
                min = Math.min(min, arr[mid]);
                end = mid - 1;
            }
        }
        return min;
    }

    //HOW MANY TIMES ARRAY HAS BEEN ROTATED
    public static int timesRotated(int arr[]) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] <= arr[end]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    //FIND SINGLE ELEMENT IN SORTED ARRAY
    public static int findSingleElement(int arr[]) {
        //edge cases
        int n = arr.length;
        if (arr.length == 1) return arr[0];
        if (arr[0] != arr[1]) return arr[0];
        if (arr[n - 2] != arr[n - 1]) return arr[n - 1];

        int start = 1, end = arr.length - 2;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid - 1] != arr[mid] && arr[mid + 1] != arr[mid]) return arr[mid];
            if ((mid % 2 == 0 && arr[mid] == arr[mid + 1]) || (mid % 2 == 1 && arr[mid - 1] == arr[mid]))
                start = mid + 1;
            else end = mid - 1;
        }
        return -1;
    }

    /////////////// BS ON ANSWERS ////////////////////


    /////////////// BS ON 2D ARRAYS //////////////////
}
