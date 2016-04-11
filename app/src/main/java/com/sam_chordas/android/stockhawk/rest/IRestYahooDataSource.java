package com.sam_chordas.android.stockhawk.rest;

/**
 * Created by subrahmanyam on 19-03-2016.
 */
public interface IRestYahooDataSource {
    void fetchHistoricData(String query, String format, String diagnostics, String env, String callback);
}
