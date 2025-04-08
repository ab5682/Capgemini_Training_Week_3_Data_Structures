import java.util.Scanner;

public class CountingSort {
    public static void countingSort(int[] ages) {
        int maxAge = 18;  
        int minAge = 10;  
        int range = maxAge - minAge + 1;
        
        int[] count = new int[range];
        
        for (int i = 0; i < ages.length; i++) {
            count[ages[i] - minAge]++;
        }

        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[ages.length];

        for (int i = ages.length - 1; i >= 0; i--) {
            output[count[ages[i] - minAge] - 1] = ages[i];
            count[ages[i] - minAge]--;
        }

        System.arraycopy(output, 0, ages, 0, ages.length);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();
        int[] ages = new int[n];
        System.out.println("Enter the students' ages (between 10 and 18):");
        for (int i = 0; i < n; i++) {
            ages[i] = scanner.nextInt();
        }

        countingSort(ages);
        System.out.println("Sorted Students' Ages:");
        for (int age : ages) {
            System.out.print(age + " ");
        }
        scanner.close();
    }
}
