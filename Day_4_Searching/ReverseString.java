import java.util.Scanner;

public class ReverseString {
    public static String reverseString(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(input);  
        sb.reverse();     
        return sb.toString();  
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to reverse: ");
        String input = scanner.nextLine();
        
        String reversedString = reverseString(input);
        System.out.println("Reversed String: " + reversedString);
        
        scanner.close();
    }
}
