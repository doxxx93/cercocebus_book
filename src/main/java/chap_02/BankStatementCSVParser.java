package chap_02;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * CSV 파싱 로직을 분리한 클래스
 */
public class BankStatementCSVParser {

    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private BankTransaction parseFromCSV(final String line) {
        final String[] columns = line.split(",");

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);

        return new BankTransaction(date, amount, columns[2]);
    }

    public List<BankTransaction> parseLinesFromCSV(final List<String> lines) {
        return lines.stream().map(this::parseFromCSV).collect(toList());
    }
}
