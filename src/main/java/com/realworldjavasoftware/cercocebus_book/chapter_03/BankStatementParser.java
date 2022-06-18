package com.realworldjavasoftware.cercocebus_book.chapter_03;

import java.util.List;

public interface BankStatementParser {

    BankTransaction parseFrom(String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);
}
