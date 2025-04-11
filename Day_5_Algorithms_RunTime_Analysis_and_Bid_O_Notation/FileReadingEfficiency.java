import java.io.*;

public class FileReadingEfficiency {

    public static void readUsingFileReader(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        int i;
        while ((i = fr.read()) != -1) {
        }
        fr.close();
    }

    public static void readUsingInputStreamReader(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(fis);
        int i;
        while ((i = isr.read()) != -1) {
        }
        isr.close();
    }

    public static void main(String[] args) throws IOException {
        String filename = "largeFile.txt";  

        long startTime = System.nanoTime();
        readUsingFileReader(filename);
        long endTime = System.nanoTime();
        System.out.println("FileReader Time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        readUsingInputStreamReader(filename);
         endTime = System.nanoTime();
        System.out.println("InputStreamReader Time: " + (endTime - startTime) + " ns");
    }
}
