package com.github.danielrodj.repositories;

import com.github.danielrodj.database.BasicRepository;
import com.github.danielrodj.models.label.ReprintedLabel;

public class RLabelRepositoryCSV extends BasicRepository<ReprintedLabel> {

    private static final String CSV_FILE = "src/main/resources/com/github/danielrodj/csv/ReprintedLabels.csv";
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
            "SerialNumber";

    public RLabelRepositoryCSV() {
        super(CSV_FILE, HEADER);
    }

    @Override
    protected int getId(ReprintedLabel reprintedLabel) {
        return reprintedLabel.getLabelId();
    }

    @Override
    protected ReprintedLabel parse(String[] values) {
        return new ReprintedLabel.Builder()
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
                .serialNumber(values[11])
                .build();
    }

    @Override
    protected String format(ReprintedLabel reprintedLabel) {
        return String.join(";", new String[] {
                Integer.toString(reprintedLabel.getLabelId()),
                reprintedLabel.getReasons(),
                reprintedLabel.getRequestDate(),
                reprintedLabel.getRequestTime(),
                reprintedLabel.getResponseDate(),
                reprintedLabel.getResponseTime(),
                Integer.toString(reprintedLabel.getRequesterId()),
                Integer.toString(reprintedLabel.getCategoryId()),
                Integer.toString(reprintedLabel.getPrinterId()),
                Integer.toString(reprintedLabel.getOriginalEditorId()),
                Integer.toString(reprintedLabel.getLastEditorId()),
                reprintedLabel.getSerialNumber()
        });
    }

}
