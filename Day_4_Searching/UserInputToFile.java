import java.io.*;

public class UserInputToFile {
    public static void main(String[] args) {
        String fileName = "output.txt"; 

        try (
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            FileWriter fw = new FileWriter(fileName)
        ) {
            String inputLine;
            System.out.println("Enter text (type 'exit' to quit):");
            while (!(inputLine = br.readLine()).equalsIgnoreCase("exit")) {
                fw.write(inputLine + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
