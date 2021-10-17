package com.wordcountordinaassignment.wordcountordinaassignment.services;

import com.wordcountordinaassignment.wordcountordinaassignment.views.WordFrequency;
import com.wordcountordinaassignment.wordcountordinaassignment.views.WordFrequencyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordCountService {

    private final Logger logger = LoggerFactory.getLogger(WordCountService.class);

    private final SimpleWordFrequencyAnalyzer simpleWordFrequencyAnalyzer = new SimpleWordFrequencyAnalyzer();

    private final WordFrequencyResponse wordFrequencyResponse = new WordFrequencyResponse();

    public WordFrequency getHighestFrequentWord(String text) {
        this.wordFrequencyResponse.setFrequency(this.simpleWordFrequencyAnalyzer.calculateHighestFrequency(text));
        this.wordFrequencyResponse.setWord(this.simpleWordFrequencyAnalyzer.getKeyValue());

        WordFrequency wordFrequency = this.wordFrequencyResponse;

        return wordFrequency;
    }

    public WordFrequency getWordFrequency(String text, String word) {
        this.wordFrequencyResponse.setWord(word);
        this.wordFrequencyResponse.setFrequency(this.simpleWordFrequencyAnalyzer.calculateFrequencyForWord(text, word));

        WordFrequency wordFrequency = this.wordFrequencyResponse;

        return wordFrequency;
    }

    public List<WordFrequency> getHighestFrequentWords(String text, int numberOfWords) {
        return this.simpleWordFrequencyAnalyzer.calculateMostFrequentNWords(text, numberOfWords);
    }
}
