package A_LearnImportantSortingTechniques;

public class sorting_2 {
    //MERGE SORT
    public static void mergeSort(int arr[], int si, int ei) {
        //BASE CASE
        if (si >= ei) return;
        //LOGIC
        int mid = si + (ei - si) / 2;
        //RECURSIVE CALL
        mergeSort(arr, si, mid);
        mergeSort(arr, mid + 1, ei);
        //MERGING BOTH THE SORTED HALVES
        merge(arr, si, mid, ei);
    }

    public static void merge(int arr[], int si, int mid, int ei) {
        int n = ei - si + 1;
        int temp[] = new int[n];
        int itr1 = si, itr2 = mid + 1, itr = 0;
        while (itr1 <= mid && itr2 <= ei) {
            if (arr[itr1] <= arr[itr2]) {
                temp[itr] = arr[itr1];
                itr++;
                itr1++;
            } else {
                temp[itr] = arr[itr2];
                itr++;
                itr2++;
            }
        }
        while (itr1 <= mid) {
            temp[itr] = arr[itr1];
            itr++;
            itr1++;
        }
        while (itr2 <= ei) {
            temp[itr] = arr[itr2];
            itr++;
            itr2++;
        }
        for (int i = 0; i < n; i++) {
            arr[si + i] = temp[i];
        }


    }

    //RECURSIVE BUBBLE SORT
    public static void recursiveBubbleSort(int arr[], int end) {
        //base case
        if (end <= 0) return;
        //logic
        boolean swapped = false;
        for (int i = 0; i < end - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                int temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
                swapped = true;
            }
        }
        if (!swapped) return;
        //recursive call
        recursiveBubbleSort(arr, end - 1);
    }

    //RECURSIVE INSERTION SORT


    //QUICK SORT
    public static void quickSort(int arr[], int start, int end) {
        //base case
        if (start >= end) return;
        int pvtIdx = partition(arr, start, end);
        quickSort(arr, start, pvtIdx - 1);
        quickSort(arr, pvtIdx + 1, end);
    }

    public static int partition(int arr[], int start, int end) {
        int pivot = arr[end];
        int pos = start-1;
        for (int i = start; i < end; i++) {
            if (arr[i] <= pivot) {
                pos++;
                int temp = arr[pos];
                arr[pos] = arr[i];
                arr[i] = temp;
            }
        }
        int temp = arr[++pos];
        arr[pos] = pivot;
        arr[end] = temp;
        return pos;
    }
}
