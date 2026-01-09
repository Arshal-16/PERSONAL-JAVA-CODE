package B_Arrays;

import java.util.*;


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
        int pos = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[pos] != arr[i]) {
                arr[++pos] = arr[i];
            }
        }
    }

    //LEFT ROTATE AN ARRAY BY ONE
    public static void leftRotate(int arr[]) {
        int temp = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = temp;
    }

    // ROTATE ARRAY BY K ELEMENTS
    public static void rotateArray(int arr[], int k) {
        k %= arr.length;
        //LEFT ROTATE BY K PLACES
        revArr(arr, 0, k - 1);
        revArr(arr, k, arr.length - 1);
        revArr(arr, 0, arr.length - 1);

        //RIGHT ROTATE BY K PLACES
        revArr(arr, 0, arr.length - 1);
        revArr(arr, 0, k - 1);
        revArr(arr, k, arr.length - 1);
    }

    public static void revArr(int arr[], int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // MOVE ZEROES TO END WITHOUT CHANGING THE ORDER OF INTEGERS IN ARRAY
    public static void zeroesToEnd(int arr[]) {
        //pointer for first 0 in arr
        int j = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                j = i;
                break;
            }
        }
        //if no zeroes present
        if (j == -1) {
            return;
        }
        for (int i = j + 1; i < arr.length; i++) {
            if (arr[i] != 0) {
                // arr[i] is the first non zero integer that comes after first zero(es),
                // we will put it to start of arr
                // without disturbing the original order
                //swap
                arr[j] = arr[i];
                arr[i] = 0;
                //increment j so it again points to first zero in array
                j++;
                //like this all non-zero digits will come to start in order when i reaches n-1 value
                // (zeroes will automatically move to end)


            }
        }
    }

    //FIND UNION SET OF TWO SORTED ARRAYS
    public ArrayList<Integer> findUnion(int[] arr1, int[] arr2, int n, int m) {
        // List to store union elements
        ArrayList<Integer> Union = new ArrayList<>();

        // Initialize pointers
        int i = 0, j = 0;

        // Iterate while both arrays have elements
        while (i < n && j < m) {
            // If element in arr1 is smaller
            if (arr1[i] < arr2[j]) {
                // Add if empty or not duplicate
                if (Union.isEmpty() || Union.get(Union.size() - 1) != arr1[i])
                    Union.add(arr1[i]);
                i++;  // Move pointer in arr1
            }
            // If element in arr2 is smaller
            else if (arr2[j] < arr1[i]) {
                // Add if empty or not duplicate
                if (Union.isEmpty() || Union.get(Union.size() - 1) != arr2[j])
                    Union.add(arr2[j]);
                j++;  // Move pointer in arr2
            } else {
                // Elements are equal, add once if not duplicate
                if (Union.isEmpty() || Union.get(Union.size() - 1) != arr1[i])
                    Union.add(arr1[i]);
                i++;
                j++;  // Move both pointers
            }
        }

        // Append remaining elements from arr1
        while (i < n) {
            if (Union.isEmpty() || Union.get(Union.size() - 1) != arr1[i])
                Union.add(arr1[i]);
            i++;
        }

        // Append remaining elements from arr2
        while (j < m) {
            if (Union.isEmpty() || Union.get(Union.size() - 1) != arr2[j])
                Union.add(arr2[j]);
            j++;
        }

        // Return the union list
        return Union;
    }

    //COUNT
    public static int countOne(int arr[]) {
        int maxCount = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                count = 0;
            }
        }
        return maxCount;
    }

    //LENGTH OF LONGEST SUBARRAY WITH SUM K
    public static int longestSubArray(int arr[], int k) {
        //prefix array
        int prefix[] = new int[arr.length];
        prefix[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }
        int maxLength = 0;
        for (int start = 0; start < arr.length; start++) {
            for (int end = start; end < arr.length; end++) {
                int sum = start == 0 ? prefix[end] : prefix[end] - prefix[start - 1];
                if (sum == k) {
                    maxLength = Math.max(maxLength, end - start + 1);
                }
            }
        }
        return maxLength;
    }

    /// ////////////////////////////// MEDIUM ////////////////////////////////////

    //TWO SUM
    public int[] twoSum(int[] arr, int key) {
        if (arr == null || arr.length < 2) {
            return new int[]{-1, -1};
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int rem = key - arr[i];

            if (map.containsKey(rem)) {
                return new int[]{map.get(rem), i};
            }

            map.put(arr[i], i);
        }

        return new int[]{-1, -1};
    }

    //MAJORITY ELEMENT
    public static int majorityElement(int arr[]) {
        int majorCount = arr.length / 2;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int num : arr) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
            if (hm.get(num) > majorCount) return num;
        }
        return Integer.MIN_VALUE;
    }

    //MAXIMUM SUBARRAY SUM
    public static int maxSum(int arr[]) {
        if (arr == null || arr.length == 0) return Integer.MIN_VALUE;
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        //KADANE ALGO
        for (int num : arr) {
            currSum += num;
            maxSum = Math.max(currSum, maxSum);
            if (currSum < 0) {
                currSum = 0;
            }

        }

        return maxSum;
    }

    //STOCK BUY AND SELL
    public static int buyAndSell(int arr[]) {
        if (arr == null || arr.length < 2) return 0;
        int profit = 0;
        int lowestPrice = arr[0];
        for (int i = 1; i < arr.length; i++) {
            profit = Math.max(profit, arr[i] - lowestPrice);
            lowestPrice = Math.min(lowestPrice, arr[i]);
        }
        return profit;

    }

    //FIND NEXT LEXICOGRAPHICALLY GREATER PERMUTATION
    public static int[] greaterPermutation(int arr[]) {
        if (arr == null || arr.length == 1) {
            return arr;
        }
        //FINDING BREAKING-POINT
        int breakingPoint = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                breakingPoint = i;
                break;
            }
        }
        // THE ENTIRE ARRAY TO THE RIGHT OF BREAKING POINT WILL BE ARRANGED IN DESCENDING ORDER
        //FINDING THE SMALLEST ELEMENT GREATER THAN ELE ON BREAKING-POINT, FORM THE RIGHT PART OF ARR
        // AND SWAPPING IT WITH ARR[BREAKING POINT]
        // LEFT PART | BREAKING POINT | RIGHT PART

        if (breakingPoint != -1) {
            for (int i = arr.length - 1; i > breakingPoint; i--) {
                if (arr[i] > arr[breakingPoint]) {
                    //swap
                    int temp = arr[i];
                    arr[i] = arr[breakingPoint];
                    arr[breakingPoint] = temp;
                    break;
                    //EVEN AFTER DOING THIS RIGHT PART WILL BE SORTED IN DES... ORDER
                }
            }
            //NOW REVERSE THE RIGHT PART, MAKING IT IN ASC ORDER
            int start = breakingPoint + 1, end = arr.length - 1;
            while (start < end) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }
        } else {
            int start = 0, end = arr.length - 1;
            while (start < end) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }
        }


        return arr;
    }

    //LEADERS IN AN ARRAY
    public static ArrayList<Integer> leadersInArray(int arr[]) {
        if (arr == null || arr.length == 0) return new ArrayList<>();

        ArrayList<Integer> al = new ArrayList<>();
        al.add(arr[arr.length - 1]);
        int currMax = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] >= currMax) {
                al.add(arr[i]);
                currMax = arr[i];
            }
        }
        return al;

    }

    //LONGEST CONSECUTIVE SEQUENCE
    public static int longestSequence(int nums[]) {
        if (nums == null) return 0;
        if (nums.length == 1 || nums.length == 0) return nums.length;

        HashSet<Integer> hs = new HashSet<>();

        for (int num : nums) {
            hs.add(num);
        }
        int longestSeq = 0;
        for (int num : hs) {
            if (!hs.contains(num - 1)) {
                int currNum = num;
                int count = 1;
                while (hs.contains(currNum + 1)) {
                    count++;
                    currNum++;
                }
                longestSeq = Math.max(longestSeq, count);

            }
        }
        return longestSeq;

    }
    ///////////////////////////////// HARD ////////////////////////////////////

}
