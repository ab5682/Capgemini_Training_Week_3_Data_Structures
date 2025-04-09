import java.io.*;

public class PerformanceComparison {
    public static void main(String[] args) {
        compareStringBuilderAndBuffer();
        compareFileReaderAndInputStreamReader("largefile.txt"); 
    }

    private static void compareStringBuilderAndBuffer() {
        String str = "hello";

        long startSB = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1_000_000; i++) {
            sb.append(str);
        }
        long endSB = System.currentTimeMillis();
        System.out.println("StringBuilder Time: " + (endSB - startSB) + " ms");

        long startSBF = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < 1_000_000; i++) {
            sbf.append(str);
        }
        long endSBF = System.currentTimeMillis();
        System.out.println("StringBuffer Time: " + (endSBF - startSBF) + " ms");
    }

    private static void compareFileReaderAndInputStreamReader(String fileName) {
        try (FileReader fr = new FileReader(fileName);
             BufferedReader br = new BufferedReader(fr)) {
            long startFR = System.currentTimeMillis();
            String line;
            int wordCount = 0;
            while ((line = br.readLine()) != null) {
                wordCount += line.split("\\s+").length;
            }
            long endFR = System.currentTimeMillis();
            System.out.println("FileReader Word Count: " + wordCount);
            System.out.println("FileReader Time: " + (endFR - startFR) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
             BufferedReader br = new BufferedReader(isr)) {
            long startISR = System.currentTimeMillis();
            String line;
            int wordCount = 0;
            while ((line = br.readLine()) != null) {
                wordCount += line.split("\\s+").length;
            }
            long endISR = System.currentTimeMillis();
            System.out.println("InputStreamReader Word Count: " + wordCount);
            System.out.println("InputStreamReader Time: " + (endISR - startISR) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
