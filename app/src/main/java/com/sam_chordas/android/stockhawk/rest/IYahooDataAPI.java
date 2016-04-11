package com.sam_chordas.android.stockhawk.rest;

import com.sam_chordas.android.stockhawk.entities.HistoricData;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by subrahmanyam on 19-03-2016.
 */
public interface IYahooDataAPI {
    @GET("/yql")
    void fetchHistoricData(@Query("q") String query,
                           @Query("format") String format,
                           @Query("diagnostics") String diagnostics,
                           @Query("env") String env,
                           @Query("callback") String callback, Callback<HistoricData> response);
}
