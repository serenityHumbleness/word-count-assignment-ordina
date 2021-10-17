package com.wordcountordinaassignment.wordcountordinaassignment;

import com.wordcountordinaassignment.wordcountordinaassignment.services.SimpleWordFrequencyAnalyzer;
import com.wordcountordinaassignment.wordcountordinaassignment.services.WordFrequencyAnalyzer;
import com.wordcountordinaassignment.wordcountordinaassignment.views.WordFrequency;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculateMostFrequentNWordsTester {

    private Logger logger = LoggerFactory.getLogger(CalculateMostFrequentNWordsTester.class);

    private final WordFrequencyAnalyzer simpleWordFrequencyAnalyzer = new SimpleWordFrequencyAnalyzer();

    private final String TEXT = "hallo hallo hallo hallo hallo why why why why what what what here here something";

    @Test
    public void testHaveWords() {
        String text = "hallo hallo hallo hallo hallo why why why why what what what here here something";
        int numberOfWords = 2;
        List<WordFrequency> results = simpleWordFrequencyAnalyzer.calculateMostFrequentNWords(this.TEXT, numberOfWords);

        assertThat(results, containsInAnyOrder(
                hasProperty("word", is("hallo")),
                hasProperty("word", is("why"))));
    }

    @Test
    public void testHaveFrequencies() {
        int numberOfWords = 2;
        List<WordFrequency> results = simpleWordFrequencyAnalyzer.calculateMostFrequentNWords(this.TEXT, numberOfWords);

        assertThat(results, containsInAnyOrder(
                hasProperty("frequency", is(5)),
                hasProperty("frequency", is(4))));
    }

    @Test
    public void textIsNull() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateMostFrequentNWords(null, 1),
                "text:String is null so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("text is null or empty"));
    }

    @Test
    public void numberOfWordsIs0() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateMostFrequentNWords(this.TEXT, 0),
                "number of words is 0 so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("number of words should be larger than 0"));
    }

    @Test
    public void textIsNullNumberOfWordsIs0() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateMostFrequentNWords(null, 0),
                "text:String is null and number of words is 0 so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("text is null or empty"));
    }

    @Test
    public void textIsEmpty() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateMostFrequentNWords("", 1),
                "text:String is empty so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("text is null or empty"));
    }

    @Test
    public void textIsEmptyAndNumberOfWordsIs0() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateMostFrequentNWords("", 0),
                "text:String is empty and number of words is 0 so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("text is null or empty"));
    }

    @Test
    public void textContainsNoWord() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateMostFrequentNWords("....,?!;:", 1),
                "text:String does not contain words so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("text contains no words"));
    }

    @Test
    public void textContainsNoWordAndNumberOfWordsIs0() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateMostFrequentNWords("....,?!;:", 0),
                "text:String does not contain words and number of words is 0 so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("number of words should be larger than 0"));
    }
}
