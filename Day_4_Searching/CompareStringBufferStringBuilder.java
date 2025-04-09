public class CompareStringBufferStringBuilder {
    public static void main(String[] args) {
        int numStrings = 1000000;
        String sampleString = "hello";

        long startTimeBuffer = System.nanoTime();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < numStrings; i++) {
            stringBuffer.append(sampleString);
        }
        long endTimeBuffer = System.nanoTime();
        long timeTakenBuffer = endTimeBuffer - startTimeBuffer;

        long startTimeBuilder = System.nanoTime();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numStrings; i++) {
            stringBuilder.append(sampleString);
        }
        long endTimeBuilder = System.nanoTime();
        long timeTakenBuilder = endTimeBuilder - startTimeBuilder;

        System.out.println("Time taken by StringBuffer: " + timeTakenBuffer + " nanoseconds");
        System.out.println("Time taken by StringBuilder: " + timeTakenBuilder + " nanoseconds");
    }
}
