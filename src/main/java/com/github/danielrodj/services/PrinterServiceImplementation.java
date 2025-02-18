package com.github.danielrodj.services;

import java.util.Map;

import com.github.danielrodj.interfaces.PrinterService;
import com.github.danielrodj.models.Printer;
import com.github.danielrodj.repositories.PrinterRepositoryCSV;

public class PrinterServiceImplementation implements PrinterService{

    PrinterRepositoryCSV  printerRepositoryCSV;

    public PrinterServiceImplementation(){
        this. printerRepositoryCSV = new PrinterRepositoryCSV();
    }

    @Override
    public Printer get(int id) {
        return printerRepositoryCSV.get(id);
    }

    @Override
    public Map<Integer, Printer> getAll() {
        return printerRepositoryCSV.getAll();
    }

    @Override
    public int insert(Printer printer) {
        return printerRepositoryCSV.insert(printer);
    }

    @Override
    public int update(Printer printer) {
        return printerRepositoryCSV.update(printer);
    }

    @Override
    public int delete(Printer printer) {
       return printerRepositoryCSV.delete(printer);
    }

    @Override
    public String getPrinterNameByPrinterId(Integer id) {
        return get(id).getPrinterName();
    }
    
}
