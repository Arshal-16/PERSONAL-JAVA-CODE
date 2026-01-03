package A_LearnImportantSortingTechniques;

public class sorting_1 {
    //SELECTION SORT
    public static void SelectionSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i <= n - 2; i++) {
            int smallest = arr[i];
            int pos = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < smallest) {
                    pos = j;
                    smallest = arr[j];
                }
            }
            int temp = arr[i];
            arr[i] = smallest;
            arr[pos] = temp;
        }
    }

    //BUBBLE SORT
    public static void bubbleSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swap = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] >= arr[j + 1]) {
                    //swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swap = true;
                }
            }
            if (!swap) break;
        }
    }

    //INSERTION SORT
    public static void insertionSort(int arr[]) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int current = arr[i];
            int prev = i - 1;
            while (prev >= 0 && arr[prev] > current) {
                arr[prev + 1] = arr[prev];
                prev--;
            }
            arr[prev + 1] = current;
        }
    }

/*while (arr[prev] > current && prev >= 0) this is wrong because
while will evaluate arr[prev] first and when prev=-1 it will give
array index out of bound exception .
 */
}
