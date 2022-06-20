package com.realworldjavasoftware.cercocebus_book.chapter_03;

public class XmlExporter implements Exporter {

    @Override
    public String export(SummaryStatistics summaryStatistics) {
        String result = "<summaryStatistics>";
        result += "<sum>" + summaryStatistics.getSum() + "</sum>";
        result += "<max>" + summaryStatistics.getMax() + "</max>";
        result += "<min>" + summaryStatistics.getMin() + "</min>";
        result += "<average>" + summaryStatistics.getAverage() + "</average>";
        result += "</summaryStatistics>";
        return result;
    }
}