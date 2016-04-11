package com.sam_chordas.android.stockhawk.entities;

/**
 * Created by subrahmanyam on 19-03-2016.
 */
public class SymbolData {
    private String Symbol;
    private String Date;
    private String Open;
    private String High;
    private String Low;
    private String Close;

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getOpen() {
        return Open;
    }

    public void setOpen(String open) {
        Open = open;
    }

    public String getHigh() {
        return High;
    }

    public void setHigh(String high) {
        High = high;
    }

    public String getLow() {
        return Low;
    }

    public void setLow(String low) {
        Low = low;
    }

    public String getClose() {
        return Close;
    }

    public void setClose(String close) {
        Close = close;
    }

    @Override
    public String toString() {
        return "SymbolData{" +
                "Symbol='" + Symbol + '\'' +
                ", Date='" + Date + '\'' +
                ", Open='" + Open + '\'' +
                ", High='" + High + '\'' +
                ", Low='" + Low + '\'' +
                ", Close='" + Close + '\'' +
                '}';
    }
}
