package com.github.danielrodj.interfaces;

import com.github.danielrodj.models.Printer;

public interface PrinterService extends BasicService<Printer>{

    String getPrinterNameByPrinterId(Integer id);

}
