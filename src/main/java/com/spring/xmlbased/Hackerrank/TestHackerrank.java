package com.spring.xmlbased.Hackerrank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestHackerrank {

    static String words = "time to write great Code";

    public static void main(String[] args) {
        String word = longestEvenWords(words);
        System.out.println(word);
    }

    private static String longestEvenWords(String input) {
        Pattern pattern = Pattern.compile(" ");
        List<Object> listWords = pattern.splitAsStream(input).collect(Collectors.toList());
        List<String> listEvenWords = new ArrayList<>();

        for(Object word : listWords) {
            String wordString = (String) word;
            if(wordString.length() % 2 == 0) {
                listEvenWords.add(wordString);
            }
        }

        if(listEvenWords.size() > 0) {
            listEvenWords.stream().sorted(Comparator.reverseOrder());
            return listEvenWords.get(0);
        } else {
            return "00";
        }
    }
}
