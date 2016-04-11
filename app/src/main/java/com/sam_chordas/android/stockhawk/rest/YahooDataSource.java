package com.sam_chordas.android.stockhawk.rest;

import android.util.Log;

import com.sam_chordas.android.stockhawk.entities.HistoricData;
import com.sam_chordas.android.stockhawk.ui.IHistoryView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by subrahmanyam on 19-03-2016.
 */
public class YahooDataSource implements IRestYahooDataSource {
    private static final String LOG = YahooDataSource.class.getSimpleName();
    private final IHistoryView historyActivity;
    IYahooDataAPI yahooDataAPI;
    private Callback<HistoricData> historicData = new Callback<HistoricData>() {
        @Override
        public void success(HistoricData history, Response response) {
            Log.d(LOG, history + ", " + response.getUrl());
            historyActivity.showHistoryData(history);
        }

        @Override
        public void failure(RetrofitError error) {
            error.printStackTrace();
            Log.d(LOG, error.getUrl() + ">>>>>");
        }
    };

    public YahooDataSource(IHistoryView historyActivity) {
        this.historyActivity = historyActivity;
        initializeRestAdapter();
    }

    private void initializeRestAdapter() {
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint("https://query.yahooapis.com/v1/public")
                .build();
        yahooDataAPI = adapter.create(IYahooDataAPI.class);
    }

    @Override
    public void fetchHistoricData(String query, String format, String diagnostics, String env, String callback) {
        yahooDataAPI.fetchHistoricData(query, format, diagnostics, env, callback, historicData);
    }
}
