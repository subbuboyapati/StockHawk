package com.sam_chordas.android.stockhawk.entities;

import java.util.List;

/**
 * Created by subrahmanyam on 19-03-2016.
 */
public class HistoryResult {
    private List<SymbolData> quote;

    public List<SymbolData> getQuote() {
        return quote;
    }

    public void setQuote(List<SymbolData> quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "HistoryResult{" +
                "quote=" + quote +
                '}';
    }
}
