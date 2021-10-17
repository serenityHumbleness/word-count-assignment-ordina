package com.wordcountordinaassignment.wordcountordinaassignment;

import com.wordcountordinaassignment.wordcountordinaassignment.services.SimpleWordFrequencyAnalyzer;
import com.wordcountordinaassignment.wordcountordinaassignment.services.WordFrequencyAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class CalculateHighestFrequencyTester {

    private final WordFrequencyAnalyzer simpleWordFrequencyAnalyzer = new SimpleWordFrequencyAnalyzer();

    @Test
    public void test() throws Exception {
        String text = "test test test hey hey hey hey hey service service service";
        Object result = (Integer) simpleWordFrequencyAnalyzer.calculateHighestFrequency(text);
        Object expectation = (Integer) 5;
        assertEquals("Show results: ", expectation, result);
    }

    @Test
    public void textIsNull() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
            simpleWordFrequencyAnalyzer.calculateHighestFrequency(null),
            "text:String is null so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("text is null or empty"));
    }

    @Test
    public void textIsEmpty() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateHighestFrequency(""),
                "text:String is empty so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("text is null or empty"));
    }

    @Test
    public void textContainsNoWord() throws IllegalArgumentException {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () ->
                        simpleWordFrequencyAnalyzer.calculateHighestFrequency("....,?!;:"),
                "text:String does not contain words so it should throw an exception"
        );

        assertTrue(e.getMessage().contains("text contains no words"));
    }
}
