package com.wordcountordinaassignment.wordcountordinaassignment.services;

import com.wordcountordinaassignment.wordcountordinaassignment.views.WordFrequency;

import java.util.List;

public interface WordFrequencyAnalyzer {
    int calculateHighestFrequency(String text) throws Exception;
    int calculateFrequencyForWord(String text, String word);
    List<WordFrequency> calculateMostFrequentNWords(String text, int n);
}
