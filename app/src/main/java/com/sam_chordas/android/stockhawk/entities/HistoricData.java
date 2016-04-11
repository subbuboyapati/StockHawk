package com.sam_chordas.android.stockhawk.entities;

/**
 * Created by subrahmanyam on 19-03-2016.
 */
public class HistoricData {
    private History query;

    public History getQuery() {
        return query;
    }

    public void setQuery(History query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "HistoricData{" +
                "query=" + query +
                '}';
    }
}
