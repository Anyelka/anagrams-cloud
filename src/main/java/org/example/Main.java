package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    private static List<String> texts = new ArrayList<>();
    private static Map<String, List<String>> textsWithAnagrams = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String text1;
        String text2;
        while(true) {
            System.out.println("\nPlease add the first text:");
            text1 = reader.readLine();
            System.out.println("\nPlease add the second text:");
            text2 = reader.readLine();

            // Part 1
            boolean areAnagrams = areAnagrams(text1, text2);
            System.out.printf("\n = Part1 = \n The two texts: (%s,%s) are %sanagrams%n", text1, text2, areAnagrams ? "" : "NOT ");

            // Part 2
            // Solution 1 :only saving the words & calculating their anagrams on each new addition.
            if(!texts.contains(text1)) {
                texts.add(text1);
            }
            if(!texts.contains(text2)) {
                texts.add(text2);
            }
            System.out.printf("\n = Part2 = \n ");
            texts.forEach(text -> {
                System.out.printf("\n   - Anagrams for '%s' are: %s",text, getAllAnagrams(text, texts));
            });
        }
    }

    private static List<String> getAllAnagrams(String text, List<String> texts) {
        return texts.stream()
                .filter(text2 -> !Objects.equals(text2, text) && areAnagrams(text, text2))
                .collect(Collectors.toList());
    }

    private static boolean areAnagrams(String text1, String text2) {
        char[] word1Chars = text1.replaceAll(" ", "").toLowerCase().toCharArray();
        char[] word2Chars = text2.replaceAll(" ", "").toLowerCase().toCharArray();
        Arrays.sort(word1Chars);
        Arrays.sort(word2Chars);
        return Arrays.equals(word1Chars, word2Chars);
    }


}