package com.wordcountordinaassignment.wordcountordinaassignment.controllers;

import com.wordcountordinaassignment.wordcountordinaassignment.services.WordCountService;
import com.wordcountordinaassignment.wordcountordinaassignment.views.WordFrequency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/count")
public class WordCountController {

    private Logger logger = LoggerFactory.getLogger(WordCountController.class);

    @Autowired
    private WordCountService wordCountService;

    @PostMapping
    public WordFrequency countWord(@RequestBody TextRequest textRequest) throws Exception {
        if(textRequest.getText() == null || textRequest.getText().isEmpty()) {
            throw new Exception("No text given.");
        }

        if(textRequest.getWord() == null || textRequest.getWord().isEmpty()) {
            throw new Exception("No word given to identify");
        }

        String text = textRequest.getText();
        String word = textRequest.getWord();

        return wordCountService.getWordFrequency(text, word);
    }

    @PostMapping("/max/frequency")
    public WordFrequency countHighestFrequencyWord (@RequestBody TextRequest textRequest) throws Exception {
        if(textRequest.getText() == null || textRequest.getText().isEmpty()) {
            throw new Exception("No text given.");
        }

        String text = textRequest.getText();

        return wordCountService.getHighestFrequentWord(text);
    }

    @PostMapping("/max/frequencies")
    public List<WordFrequency> countHighestFrequentWords(@RequestBody TextRequest textRequest) throws Exception {
        if(textRequest.getText() == null || textRequest.getText().isEmpty()) {
            throw new Exception("No text given.");
        }

        if(textRequest.getNumberOfWords() <= 0) {
            throw new Exception("The minimum number of words to identify should be: 1.");
        }

        String text = textRequest.getText();
        int numberOfWords = textRequest.getNumberOfWords();

        return wordCountService.getHighestFrequentWords(text, numberOfWords);
    }
}

class TextRequest {
    private String text;
    private String word;
    private int numberOfWords;

    public String getText() {
        return text;
    }

    public String getWord() {
        return word;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }
}
