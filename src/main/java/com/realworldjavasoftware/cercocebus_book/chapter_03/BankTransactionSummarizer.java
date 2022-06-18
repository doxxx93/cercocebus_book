package com.realworldjavasoftware.cercocebus_book.chapter_03;

@FunctionalInterface
public interface BankTransactionSummarizer {

    double summarize(double accumulator, BankTransaction bankTransaction);
}
