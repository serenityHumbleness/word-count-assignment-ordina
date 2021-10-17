package com.wordcountordinaassignment.wordcountordinaassignment.services;

import com.wordcountordinaassignment.wordcountordinaassignment.views.WordFrequency;
import com.wordcountordinaassignment.wordcountordinaassignment.views.WordFrequencyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleWordFrequencyAnalyzer implements WordFrequencyAnalyzer{

    private final Logger logger = LoggerFactory.getLogger(SimpleWordFrequencyAnalyzer.class);

    private final HashMap<String, Integer> wordsHashMap = new HashMap<String, Integer>();

    private LinkedHashMap<String, Integer> wordsHashMapDescending = new LinkedHashMap<String, Integer>();

    private String keyValue = "";

    public String getKeyValue() {
        return this.keyValue;
    }

    /**
     *
     * @param text:String
     * @return the frequency of the most repeated word.
     * @throws IllegalArgumentException when text is null and/or empty.
     *          Also when text does not contain any usable word.
     */
    @Override
    public int calculateHighestFrequency(String text) throws IllegalArgumentException {
        // 1. Check if parameter is null and/or is empty
        if(text == null || text.isEmpty())
            throw new IllegalArgumentException("text is null or empty");

        // 2. Clear HashMaps
        clearHashMaps();

        // 3. Trim and split text:String parameter
        String[] words = trimAndSplitText(text);

        // 4. Check if parameter is usable
        if(words.length == 0)
            throw new IllegalArgumentException("text contains no words");

        // 5. Keep count of each word in the Hash Map
        countEachWordInHashMap(words);

        // 6. Sort the Hashmap in DESCENDING order.
        this.wordsHashMapDescending = sortFrequencyInDescendingOrder();

        // 7. Set keyValue so that the most frequent word is identifiable
        if (this.wordsHashMapDescending.keySet().stream().findFirst().isPresent()) {
            this.keyValue = this.wordsHashMapDescending.keySet().stream().findFirst().get();
        }

        // 8. Return the first element of the sorted HashMap.
        return this.wordsHashMapDescending.get(this.keyValue);
    }

    /**
     *
     * @param text:String
     * @param word:String
     * @return the Frequency of a given @param word:String
     * @throws IllegalArgumentException when text is null or empty and/or when word is null.
     *          Also when the text does not contain any usable word.
     */
    @Override
    public int calculateFrequencyForWord(String text, String word) throws IllegalArgumentException {
        // 1. Check if one of the parameters is null and/or is empty
        if(text == null || text.isEmpty())
            throw new IllegalArgumentException("text is null or empty");

        if(word == null || word.isEmpty())
            throw new IllegalArgumentException("no word given");

        // 2. Clear HashMaps
        clearHashMaps();

        // 3. Trim and split text:String parameter
        String[] words = trimAndSplitText(text);

        // 4. Check if parameter is usable
        if(words.length == 0)
            throw new IllegalArgumentException("text contains no words");

        // 5. Keep count of each word in the Hash Map
        countEachWordInHashMap(words);

        // 6. Return the value using word:String as keyValue.
        return this.wordsHashMap.get(word);
    }

    /**
     *
     * @param text:String
     * @param numberOfWords:int
     * @return A List of Wordfrequency
     * @throws IllegalArgumentException when text is null or empty and/or when number of words is < 0.
     *          Also when the text does not contain any usable word.
     */
    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int numberOfWords) throws IllegalArgumentException {
        // 1. Check if one of the parameters is null and/or is empty
        if(text == null || text.isEmpty())
            throw new IllegalArgumentException("text is null or empty");

        if(numberOfWords <= 0)
            throw new IllegalArgumentException("number of words should be larger than 0");

        // 2. Clear HashMaps
        clearHashMaps();

        // 3. Trim and split text:String parameter
        String[] words = trimAndSplitText(text);

        // 4. Check if parameter is usable
        if(words.length == 0)
            throw new IllegalArgumentException("text contains no words");

        // 5. Keep count of each word in the Hash Map
        countEachWordInHashMap(words);

        // 6. Sort the Hashmap in DESCENDING order.
        this.wordsHashMapDescending = sortFrequencyInDescendingOrder();

        // 7. Create a list of the most frequent words
        return this.wordsHashMapDescending.entrySet().stream()
                .limit(numberOfWords)
                .map(stringIntegerEntry -> {
                    WordFrequencyResponse n = new WordFrequencyResponse();  // a. Create a new instance of WordFrequencyResponse
                    n.setFrequency(stringIntegerEntry.getValue());          // b. Set the frequency value
                    n.setWord(stringIntegerEntry.getKey());                 // c. Set the word value
                    return n;
                })
                .collect(Collectors.toList());                              // d. Collect every instance into a list.
    }

    /**
     * Keeps count on each word:String in the HashMap.
     * @param words:String[]
     */
    private void countEachWordInHashMap(String [] words) {
        for (String word : words) {
            if (this.wordsHashMap.containsKey(word)) {
                this.wordsHashMap.put(word, this.wordsHashMap.get(word) + 1);
            } else {
                this.wordsHashMap.put(word, 1);
            }
        }
    }

    /**
     *
     * @return a Linked HashMap of key:String and value:Integer.
     */
    private LinkedHashMap<String, Integer> sortFrequencyInDescendingOrder() {
        return this.wordsHashMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))     // sort in descending order
                .collect(Collectors.toMap(Map.Entry::getKey,                        // key mapper
                        Map.Entry::getValue,                                        // value mapper
                        (entry1, entry2) -> entry1, LinkedHashMap::new));           // merge into a new Linked HashMap
    }

    private String[] trimAndSplitText(String text) {
        text = text.toLowerCase();
        return text.trim().split("[\\p{Punct}\\s]+");
    }

    private void clearHashMaps() {
        this.wordsHashMap.clear();
        this.wordsHashMapDescending.clear();
    }
}
