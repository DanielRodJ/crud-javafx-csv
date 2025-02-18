package com.github.danielrodj.models;

/**
 *
 * @author daAkn
 */
public class Printer {

    private int printerId;
    private String name;
    private String ip;
    private String netmask;
    private String location;

    public Printer() {
    }

    public Printer(int printerId, String name, String ip, String netmask, String location) {
        this.printerId = printerId;
        this.name = name;
        this.ip = ip;
        this.netmask = netmask;
        this.location = location;
    }

    public int getPrinterId() {
        return printerId;
    }

    public String getPrinterName() {
        return name;
    }

    public String getPrinterIp() {
        return ip;
    }
    
    public String getPrinterNetmask(){
        return netmask;
    }
    
    public String getPrinterLocation(){
        return location;
    }

}
