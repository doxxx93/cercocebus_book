package com.realworldjavasoftware.cercocebus_book.chapter_03;

@FunctionalInterface
public interface BankTransactionFilter {

    boolean test(BankTransaction bankTransaction);
}
