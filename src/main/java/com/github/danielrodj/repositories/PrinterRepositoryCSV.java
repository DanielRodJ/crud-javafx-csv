package com.github.danielrodj.repositories;

import com.github.danielrodj.database.BasicRepository;
import com.github.danielrodj.models.Printer;

public class PrinterRepositoryCSV extends BasicRepository<Printer> {

    private static final String CSV_FILE = "src/main/resources/com/github/danielrodj/csv/Printers.csv";
    private static final String HEADER = "ID;Printer Name;Printer Ip;Printer Netmask;Location";

    public PrinterRepositoryCSV() {
        super(CSV_FILE, HEADER);
    }

    @Override
    protected int getId(Printer printer) {
        return printer.getPrinterId();
    }

    @Override
    protected Printer parse(String[] values) {
        return new Printer(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4]);
    }

    @Override
    protected String format(Printer printer) {
        return String.join(";", new String[] {
                String.valueOf(printer.getPrinterId()),
                printer.getPrinterName(),
                printer.getPrinterIp(),
                printer.getPrinterNetmask(),
                printer.getPrinterLocation()
        });
    }

}
