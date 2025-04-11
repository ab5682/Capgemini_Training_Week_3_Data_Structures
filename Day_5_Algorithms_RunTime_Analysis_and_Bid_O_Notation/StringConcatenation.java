public class StringConcatenation {

    public static String concatUsingString(int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += "a";
        }
        return result;
    }

    public static String concatUsingStringBuilder(int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append("a");
        }
        return result.toString();
    }

    public static String concatUsingStringBuffer(int n) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < n; i++) {
            result.append("a");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        int n = 1000000;

        long startTime = System.nanoTime();
        concatUsingString(n);
        long endTime = System.nanoTime();
        System.out.println("String Concatenation Time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        concatUsingStringBuilder(n);
        endTime = System.nanoTime();
        System.out.println("StringBuilder Concatenation Time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        concatUsingStringBuffer(n);
        endTime = System.nanoTime();
        System.out.println("StringBuffer Concatenation Time: " + (endTime - startTime) + " ns");
    }
}
