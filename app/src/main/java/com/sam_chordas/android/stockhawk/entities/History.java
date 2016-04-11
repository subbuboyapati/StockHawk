package com.sam_chordas.android.stockhawk.entities;

/**
 * Created by subrahmanyam on 19-03-2016.
 */
public class History {
    private HistoryResult results;

    public HistoryResult getResults() {
        return results;
    }

    public void setResults(HistoryResult results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "History{" +
                "results=" + results +
                '}';
    }
}
