package com.wordcountordinaassignment.wordcountordinaassignment;

import com.wordcountordinaassignment.wordcountordinaassignment.services.SimpleWordFrequencyAnalyzer;
import com.wordcountordinaassignment.wordcountordinaassignment.services.WordFrequencyAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class CalculateFrequencyForWordTester {

    private final WordFrequencyAnalyzer simpleWordFrequencyAnalyzer = new SimpleWordFrequencyAnalyzer();

    private final String TEXT = "Absolutely not. You know that on a gut level, though you might feel a little conflicted, because nonsense fake Christianity has been sloshing around in the culture, addling all our minds. So let me unpack for you the reasons why you already believe that Swiss Guards defending timeless art, or a shop-owner defending his business, using violence, is good and right and Christian, what Jesus wants us to do.";
    private final String WORD = "level";

    @Test
    public void test() {
        Object result = (Integer) simpleWordFrequencyAnalyzer.calculateFrequencyForWord(this.TEXT, this.WORD);
        Object expectation = (Integer) 1;
        assertEquals("successful", expectation, result);
    }

    @Test
    public void textIsNull() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateFrequencyForWord(null, this.WORD),
                "text:String is null so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("text is null or empty"));
    }

    @Test
    public void wordsIsNull() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateFrequencyForWord(this.TEXT, null),
                "word:String is null so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("no word given"));
    }

    @Test
    public void textAndWordAreNull() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateFrequencyForWord(null, null),
                "text:String and word:String are null so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("text is null or empty"));
    }

    @Test
    public void textIsEmpty() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateFrequencyForWord("", this.WORD),
                "text:String is empty so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("text is null or empty"));
    }

    @Test
    public void wordIsEmpty() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateFrequencyForWord(this.TEXT, ""),
                "word:String is empty so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("no word given"));
    }

    @Test
    public void textAndWordAreEmpty() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateFrequencyForWord("", ""),
                "text:String and word:String are empty so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("text is null or empty"));
    }

    @Test
    public void textContainsNoWord() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateFrequencyForWord("....,?!;:", "a"),
                "text:String does not contain words so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("text contains no words"));
    }
}
