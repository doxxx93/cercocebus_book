package com.realworldjavasoftware.cercocebus_book.chapter_02;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BankStatementCSVParserTest {

    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    @DisplayName("BankStatementParser 클래스의 parseFrom() 메서드가 특정 입력을 제대로 처리하는지 테스트")
    void shouldParseOneCorrectLine() {
        //given
        final String line = "30-01-2017,-50,Tesco";
        //when
        final BankTransaction result = statementParser.parseFrom(line);
        //then
        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30),
            -50, "Tesco");
        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getAmount(), result.getAmount(), 0.0d);
        assertEquals(expected.getDescription(), result.getDescription());
    }
}