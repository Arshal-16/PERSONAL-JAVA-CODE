package C_BinarySearch;

import java.util.ArrayList;

public class binarySearch {

    /*
    BS NOTE:
    Use start <= end
    When you are searching for an answer that may be exactly at start or end
    Classic search for a value
    Floor / ceiling
    Last valid value
    First invalid value
    You shrink a closed range: [start, end]

    Use start < end
     When you are converging to a single index
    Finding a boundary
    Lower bound / upper bound
    Peak / minimum / maximum
    Binary search on answer (monotonic predicate)
    You shrink an open-ended range until one value remains

    If stuck, ask yourself:
    “Am I searching or converging?”
    Searching → <=
    Converging → <
     */


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

    //FIND PEAK ELEMENT
    public static int peakElement(int arr[]) {
        if (arr.length == 1 || arr[0] > arr[1]) return 0;

        if (arr[arr.length - 1] > arr[arr.length - 2]) return arr.length - 1;

        int start = 1, end = arr.length - 2;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] >= arr[mid - 1] && arr[mid] >= arr[mid + 1]) {
                return mid;
            } else if (arr[mid] > arr[mid - 1]) {
                start = mid + 1;
            } else if (arr[mid] > arr[mid + 1]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    /// //////////// BS ON ANSWERS ////////////////////

    //FIND SQUARE ROOT USING BINARY SEARCH
    public static int floorSqrt(int n) {
        if (n < 0) return -1;
        if (n == 0) return 0;
        // sqrt will lie between 1 and n so we can apply binary search
        int start = 1, end = n, ans = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            // used long to prevent overflow
            if ((long) mid * mid <= n) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    //FIND Nth ROOT OF M
    public static int nthRoot(int n, int m) {
        if (m < 0) return -1;
        if (m == 0) return 0;

        int start = 1, end = m;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            long value = power(mid, n, m);

            if (value == m) {
                return mid;
            } else if (value < m) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    //DONT USE Math.pow use this fn
    public static long power(int base, int exp, int limit) {
        long result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
            if (result > limit) return result; // stop early
        }
        return result;
    }

    //KOKO EATING BANANAS
    public static int bananasPerHour(int arr[], int h) {
        int start = 1, end = arr[0], ans = 1;
        for (int num : arr) {
            if (num > end) end = num;
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int timeToEat = 0;
            for (int num : arr) {
                timeToEat += (int) Math.ceil((double) num / mid);
            }
            if (timeToEat <= h) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    //MINIMUM DAYS TO MAKE M BOUQUETS
    public static int makeBouquets(int arr[], int k, int m) {
        if ((long) m * k > arr.length) return -1;

        int start = 1, ans = -1;
        int end = arr[0];
        for (int num : arr) {
            if (num > end) {
                end = num;
            }
        }

        while (start <= end) {
            int days = start + (end - start) / 2;
            int bouquetsMade = 0;
            int count = 0;
            for (int num : arr) {
                if (days >= num) {
                    count++;
                    if (count >= k) {
                        bouquetsMade++;
                        count = 0;
                    }
                } else {
                    count = 0;
                }
            }
            if (bouquetsMade >= m) {
                ans = days;
                end = days - 1;
            } else {
                start = days + 1;
            }
        }
        return ans;
    }

    //FIND THE SMALLEST DIVISOR GIVEN A THRESHOLD
    public static int smallestDivisor(int arr[], int limit) {
        int start = 1, end = arr[0], ans = -1;
        for (int num : arr) {
            if (num > end) end = num;
        }
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int divResult = 0;
            for (int num : arr) {
                divResult += (int) Math.ceil((double) num / mid);
            }
            if (divResult <= limit) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    //CAPACITY TO SHIP WITHIN D DAYS
    public int shipWithinDays(int[] weights, int days) {
        if (weights.length == 0)
            return 0;
        int minWt = weights[0];
        int maxWt = 0;
        for (int wt : weights) {
            maxWt += wt;
            minWt = Math.max(minWt, wt);
        }

        while (minWt < maxWt) {
            int wt = minWt + (maxWt - minWt) / 2;
            //daysRedq start at 1 because we will start filling the first day and when the capacity is reached we will increment the day
            int daysReqd = 1;
            int todaysWt = 0;
            //logic to calculate daysReqd
            for (int weight : weights) {
                if ((todaysWt + weight) <= wt) {
                    todaysWt += weight;
                } else {
                    daysReqd++;
                    todaysWt = weight;
                }
            }
            if (daysReqd <= days) {
                maxWt = wt;
            } else {
                minWt = wt + 1;
            }
        }
        return minWt;
    }


    //Kth MISSING POSITIVE INTEGER

    // linear traversal approach
    public int findKthPositive(int[] arr, int k) {
        //array index pointer
        int i = 0;
        //expected number
        int num = 1;
        //missing count
        int count = 0;

        while (true) {
            if (i < arr.length && arr[i] == num) {
                i++;
            } else {
                count++;
                if (count == k) break;
            }
            num++;
        }
        return num;
    }

    //binary search approach

    public int findKthPositiveBS(int[] arr, int k) {

        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int missing = arr[mid] - (mid + 1);
            if (missing < k) {
                low = mid + 1;
            } else {
                //missing>=k
                high = mid - 1;
            }
        }
        /*
        after while loop high will be at index where no. of integers missing before arr[high] will be less than k, but the Kth missing
        integer will be bw arr[high] and arr[low] so arr[high]+more will give us the ans
        eg: 2,3,4,7,11 and k=5
        here no of elements missing before 7 are 3 and no. of elements missing before 11 are 6 so high will be at 7 and low at 11
        ans = 7+more =7+(5-3)==>9 Ans
        now ans will be arr[high]+more where more = k-(numbers missing till arr[high]) but in cases when missing number is before the
        first index(0) then high will be pointing at -1 and arr[-1] is not defined.
        Missing Num = arr[high]+(k-(arr[high]-(high+1)))==> arr[high]+k-arr[high]+high+1
        ==>k+(high+1) ==> k + (low)
        missing num= low + k
        */
        return low + k;
    }


    /////////////// BS ON 2D ARRAYS //////////////////
}
