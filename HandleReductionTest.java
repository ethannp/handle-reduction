public class HandleReductionTest {

    /**
     * Test the Handle Reduction algorithm.
     * 
     * @param strands       The number of strands of the braid (MIN: 1, MAX: 26)
     * @param crossings     The number of crossings in the braid
     * @param sampleSize    The number of braids to test
     * @param progressEvery Prints a progress check every (n) iterations
     * @param type          The type algo to run (0 = basic (slow), 1 = DAC (fast))
     */
    public static void testHandleReduction(int strands, int crossings, int sampleSize, int progressEvery, int type) {
        long sumTime = 0;
        long worstTime = 0;
        long sumLength = 0;
        long worstLength = 0;
        for (int n = 1; n <= sampleSize; n++) {
            String braid = "";
            for (int c = 0; c < crossings; c++) {
                braid += "" + (char) ((Math.random() > 0.5 ? 65 : 97) + (int) (Math.random() * (strands - 1)));
            }
            long startTime = System.currentTimeMillis();
            String endBraid;
            if (type == 0) {
                endBraid = HandleReduction.handleReduce(braid, false);
            } else {
                endBraid = HandleReduction.dacHandleReduce(braid, strands);
            }

            long endTime = System.currentTimeMillis();
            sumTime += (endTime - startTime);
            worstTime = Math.max(worstTime, (endTime - startTime));
            sumLength += endBraid.length();
            worstLength = Math.max(worstLength, endBraid.length());
            if (n % progressEvery == 0) {
                System.out.println("(" + n + "/" + sampleSize + ")");
            }
        }
        System.out.println(
                "Crossings: \t\t" + crossings + "\nStrands: \t\t" + strands + "\nSample Size: \t\t" + sampleSize
                        + "\nAverage Time (ms): \t" + (sumTime / (double) sampleSize) + "\nWorst Time (ms): \t"
                        + worstTime
                        + "\nAverage Length: \t" + (sumLength / (double) sampleSize) + "\nWorst Length: \t\t"
                        + worstLength);
    }

    public static void main(String[] args) {
        testHandleReduction(4, 1024, 1000, 200, 1);
    }
}
