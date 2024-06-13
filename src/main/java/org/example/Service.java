package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Service {

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
