package com.wordcountordinaassignment.wordcountordinaassignment.views;

public class WordFrequencyResponse implements WordFrequency {

    private String word;
    private int frequency;

    public void setWord(String word) {
        this.word = word;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String getWord() {
        return this.word;
    }

    @Override
    public int getFrequency() {
        return this.frequency;
    }
}
