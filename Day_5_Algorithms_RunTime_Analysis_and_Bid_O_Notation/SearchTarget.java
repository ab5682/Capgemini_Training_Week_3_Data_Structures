import java.util.Arrays;

public class SearchTarget {

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1; 
    }

    public static int binarySearch(int[] arr, int target) {
        Arrays.sort(arr); 
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; 
    }

    public static void main(String[] args) {
        int[] data = {5, 2, 9, 1, 7, 3, 6};
        int target = 7;

        long startTime = System.nanoTime();
        System.out.println("Linear Search Result: " + linearSearch(data, target));
        long endTime = System.nanoTime();
        System.out.println("Linear Search Time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        System.out.println("Binary Search Result: " + binarySearch(data, target));
        endTime = System.nanoTime();
        System.out.println("Binary Search Time: " + (endTime - startTime) + " ns");
    }
}
