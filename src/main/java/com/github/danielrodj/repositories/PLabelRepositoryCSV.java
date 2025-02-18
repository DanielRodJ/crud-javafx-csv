package com.github.danielrodj.repositories;

import com.github.danielrodj.database.BasicRepository;
import com.github.danielrodj.models.label.PrintedLabel;

public class PLabelRepositoryCSV extends BasicRepository<PrintedLabel> {

    private static final String CSV_FILE = "src/main/resources/com/github/danielrodj/csv/PrintedLabels.csv";

    private static final String HEADER = "LabelId;" +
            "Reasons;" +
            "RequestDate;" +
            "RequestTime;" +
            "ResponseDate;" +
            "ResponseTime;" +
            "RequesterId;" +
            "CategoryId;" +
            "PrinterId;" +
            "OriginalEditorId;" +
            "LastEditorId;" +
            "PartNumber;" +
            "LabelType;" +
            "Quantity;" +
            "StdPack";

    public PLabelRepositoryCSV() {
        super(CSV_FILE, HEADER);
    }

    @Override
    protected int getId(PrintedLabel printedLabel) {
        return printedLabel.getLabelId();
    }

    @Override
    protected PrintedLabel parse(String[] values) {
        return new PrintedLabel.Builder()
                .labelId(Integer.parseInt(values[0]))
                .reasons(values[1])
                .requestDate(values[2])
                .requestTime(values[3])
                .responseDate(values[4])
                .responseTime(values[5])
                .requesterId(Integer.parseInt(values[6]))
                .categoryId(Integer.parseInt(values[7]))
                .printerId(Integer.parseInt(values[8]))
                .originalEditorId(Integer.parseInt(values[9]))
                .lastEditorId(Integer.parseInt(values[10]))
                .partNumber(values[11])
                .labelType(values[12])
                .quantity(Integer.parseInt(values[13]))
                .stdPack(Integer.parseInt(values[14]))
                .build();
    }

    @Override
    protected String format(PrintedLabel printedLabel) {
        return String.join(";", new String[] {
                Integer.toString(printedLabel.getLabelId()),
                printedLabel.getReasons(),
                printedLabel.getRequestDate(),
                printedLabel.getRequestTime(),
                printedLabel.getResponseDate(),
                printedLabel.getResponseTime(),
                Integer.toString(printedLabel.getRequesterId()),
                Integer.toString(printedLabel.getCategoryId()),
                Integer.toString(printedLabel.getPrinterId()),
                Integer.toString(printedLabel.getOriginalEditorId()),
                Integer.toString(printedLabel.getLastEditorId()),
                printedLabel.getPartNumber(),
                printedLabel.getLabelType(),
                Integer.toString(printedLabel.getQuantity()),
                Integer.toString(printedLabel.getStdPack())
        });
    }

}
