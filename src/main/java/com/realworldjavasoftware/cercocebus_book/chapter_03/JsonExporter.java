package com.realworldjavasoftware.cercocebus_book.chapter_03;

public class JsonExporter implements Exporter {

    @Override
    public String export(SummaryStatistics summaryStatistics) {
        String result = "{";
        result += "\"sum\":" + summaryStatistics.getSum() + ",";
        result += "\"max\":" + summaryStatistics.getMax() + ",";
        result += "\"min\":" + summaryStatistics.getMin() + ",";
        result += "\"average\":" + summaryStatistics.getAverage();
        result += "}";
        return result;
    }
}
