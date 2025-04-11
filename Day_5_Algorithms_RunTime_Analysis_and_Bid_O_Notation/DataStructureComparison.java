import java.util.HashSet;
import java.util.TreeSet;

public class DataStructureComparison {

    public static boolean arraySearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean hashSetSearch(HashSet<Integer> set, int target) {
        return set.contains(target);
    }

    public static boolean treeSetSearch(TreeSet<Integer> set, int target) {
        return set.contains(target);
    }

    public static void main(String[] args) {
        int[] array = new int[1000000]; 
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        HashSet<Integer> hashSet = new HashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < array.length; i++) {
            hashSet.add(array[i]);
            treeSet.add(array[i]);
        }

        int target = 500000;

        long startTime = System.nanoTime();
        System.out.println("Array Search Result: " + arraySearch(array, target));
        long endTime = System.nanoTime();
        System.out.println("Array Search Time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        System.out.println("HashSet Search Result: " + hashSetSearch(hashSet, target));
        endTime = System.nanoTime();
        System.out.println("HashSet Search Time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        System.out.println("TreeSet Search Result: " + treeSetSearch(treeSet, target));
        endTime = System.nanoTime();
        System.out.println("TreeSet Search Time: " + (endTime - startTime) + " ns");
    }
}
