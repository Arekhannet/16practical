import java.io.*;
import java.util.*;
import java.text.*;

public class tryHeapsort {

    static final int REPETITIONS = 30;

    // Read words from file, clean and return as array
    static String[] readWords(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            ArrayList<String> list = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                for (String w : line.trim().split("\\s+")) {
                    w = w.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (!w.isEmpty()) list.add(w);
                }
            }
            br.close();
            return list.toArray(new String[0]);
        } catch (IOException e) {
            System.out.println("Could not read file: " + filename);
            return new String[0];
        }
    }

    // Test with small array to visually verify correctness
    static void smallTest() {
        System.out.println("=== Small Test (20 words) ===");
        String[] testWords = {
                "ulysses", "bloom", "stephen", "molly", "dublin",
                "anna", "river", "thunder", "night", "morning",
                "yes", "cat", "dog", "fish", "apple",
                "zebra", "mango", "pear", "kiwi", "grape"
        };

        System.out.println("Input: " + Arrays.toString(testWords));

        // Bottom-up
        Heap h1 = new Heap(testWords.length);
        h1.buildBottomUp(testWords, testWords.length);
        String[] sorted1 = h1.heapSort();
        System.out.println("Bottom-up sorted: " + Arrays.toString(sorted1));

        // Top-down
        Heap h2 = new Heap(testWords.length);
        h2.buildTopDown(testWords, testWords.length);
        String[] sorted2 = h2.heapSort();
        System.out.println("Top-down sorted:  " + Arrays.toString(sorted2));

        System.out.println();
    }

    // Time bottom-up build + sort over 30 repetitions
    static double timeBottomUp(String[] words) {
        int n = words.length;
        double runTime = 0, runTime2 = 0;

        for (int rep = 0; rep < REPETITIONS; rep++) {
            long start = System.currentTimeMillis();

            Heap h = new Heap(n);
            h.buildBottomUp(words, n);
            h.heapSort();

            long finish = System.currentTimeMillis();
            double time = (double)(finish - start);
            runTime  += time;
            runTime2 += (time * time);
        }

        double avems  = runTime / REPETITIONS;
        double stdDev = Math.sqrt(runTime2 - REPETITIONS * avems * avems) / (REPETITIONS - 1);

        System.out.println("Bottom-up build + sort:");
        System.out.printf("  Average time = %.5fs  +/- %.4fms%n", avems / 1000.0, stdDev);
        System.out.printf("  Std dev      = %.4fms%n", stdDev);
        System.out.println("  Repetitions  = " + REPETITIONS);

        return avems / 1000.0;
    }

    // Time top-down build + sort over 30 repetitions
    static double timeTopDown(String[] words) {
        int n = words.length;
        double runTime = 0, runTime2 = 0;

        for (int rep = 0; rep < REPETITIONS; rep++) {
            long start = System.currentTimeMillis();

            Heap h = new Heap(n);
            h.buildTopDown(words, n);
            h.heapSort();

            long finish = System.currentTimeMillis();
            double time = (double)(finish - start);
            runTime  += time;
            runTime2 += (time * time);
        }

        double avems  = runTime / REPETITIONS;
        double stdDev = Math.sqrt(runTime2 - REPETITIONS * avems * avems) / (REPETITIONS - 1);

        System.out.println("Top-down build + sort:");
        System.out.printf("  Average time = %.5fs  +/- %.4fms%n", avems / 1000.0, stdDev);
        System.out.printf("  Std dev      = %.4fms%n", stdDev);
        System.out.println("  Repetitions  = " + REPETITIONS);

        return avems / 1000.0;
    }

    // Print final comparison table
    static void printTable(int n, double bottomUpTime, double topDownTime) {
        DecimalFormat fiveD = new DecimalFormat("0.00000");
        System.out.println("\n________________________________________________");
        System.out.println("Heap Sort Timing Results");
        System.out.println("________________________________________________");
        System.out.printf("%-25s %-15s %-15s%n", "Method", "n (words)", "Avg time (s)");
        System.out.println("________________________________________________");
        System.out.printf("%-25s %-15d %-15s%n",
                "Bottom-up build + sort", n, fiveD.format(bottomUpTime));
        System.out.printf("%-25s %-15d %-15s%n",
                "Top-down build + sort",  n, fiveD.format(topDownTime));
        System.out.println("________________________________________________");
        System.out.printf("Speedup (top-down / bottom-up) = %.2fx%n",
                topDownTime / bottomUpTime);
        System.out.println("Repetitions = " + REPETITIONS);
    }

    public static void main(String[] args) { //111

        // Step (c) — small test first
        smallTest();

        // Load ulysses words
        String[] words = readWords("ulysses.txt");

        if (words.length == 0) {
            System.out.println("ulysses.txt not found — place it in the project root folder.");
            return;
        }

        System.out.printf("Loaded %,d words from ulysses.txt%n%n", words.length);

        // Step (a) — time bottom-up
        System.out.println("--- Timing bottom-up build ---");
        double buTime = timeBottomUp(words);
        System.out.println();

        // Step (b) — time top-down
        System.out.println("--- Timing top-down build ---");
        double tdTime = timeTopDown(words);

        // Step (e) — display table
        printTable(words.length, buTime, tdTime);

        // Verify first 20 sorted words
        System.out.println("\nFirst 20 words alphabetically (bottom-up):");
        Heap verify = new Heap(words.length);
        verify.buildBottomUp(words, words.length);
        String[] sorted = verify.heapSort();
        for (int i = 0; i < Math.min(20, sorted.length); i++)
            System.out.printf("  %2d. %s%n", i + 1, sorted[i]);
    }
}