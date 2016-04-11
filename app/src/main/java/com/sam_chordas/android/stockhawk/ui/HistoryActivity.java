package com.sam_chordas.android.stockhawk.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.entities.HistoricData;
import com.sam_chordas.android.stockhawk.entities.SymbolData;
import com.sam_chordas.android.stockhawk.rest.YahooDataSource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by subrahmanyam on 18-03-2016.
 */
public class HistoryActivity extends AppCompatActivity implements IHistoryView {

    private static final String LOG = HistoryActivity.class.getSimpleName();
    @Bind(R.id.linechart)
    LineChart lineChart;
    private YahooDataSource dataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph);
        ButterKnife.bind(this);
        lineChart.setBackgroundColor(Color.WHITE);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextSize(12f);
        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        dataSource = new YahooDataSource(this);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sdf.format(calendar.getTime());
        calendar.add(Calendar.DATE, -365);
        String startDate = sdf.format(calendar.getTime());
        Log.d(LOG, todayDate + "----" + startDate);
        dataSource.fetchHistoricData("select * from yahoo.finance.historicaldata where symbol = \"YHOO\" and startDate = \"" +
                        startDate + "\" and endDate = \"" + todayDate + "\"",
                "json", "true", "store://datatables.org/alltableswithkeys", "");
    }

    @Override
    public void showHistoryData(HistoricData history) {
        List<String> xVals = new ArrayList<>();
        ArrayList<Entry> closedListEntry = new ArrayList<>();
        ArrayList<Entry> openingListEntry = new ArrayList<>();
        List<SymbolData> quote = history.getQuery().getResults().getQuote();
        for (int i = 0; i < quote.size(); i++) {
            Entry closedEntry = new Entry(Float.parseFloat(quote.get(i).getClose()), i);
            Entry openingEntry = new Entry(Float.parseFloat(quote.get(i).getOpen()), i);
            closedListEntry.add(closedEntry);
            openingListEntry.add(openingEntry);
            xVals.add(quote.get(i).getDate());
        }
        LineDataSet closedDataSet = new LineDataSet(closedListEntry, "CLOSED");
        LineDataSet openDataSet = new LineDataSet(openingListEntry, "OPENING");
        openDataSet.setColor(Color.RED);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(closedDataSet);
        dataSets.add(openDataSet);
        LineData data = new LineData(xVals, dataSets);
        lineChart.setData(data);
        lineChart.invalidate();
    }

    @OnClick({R.id.history_week, R.id.history_month, R.id.history_year})
    void changeChart(View view) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sdf.format(calendar.getTime());
        String startDate = "";
        switch (view.getId()) {
            case R.id.history_week:
                calendar.add(Calendar.DATE, -7);
                startDate = sdf.format(calendar.getTime());
                Log.d(LOG, todayDate + "----" + startDate);
                break;
            case R.id.history_month:
                calendar.add(Calendar.DATE, -30);
                startDate = sdf.format(calendar.getTime());
                Log.d(LOG, todayDate + "----" + startDate);
//                dataSource.fetchHistoricData("select * from yahoo.finance.historicaldata where symbol = \"YHOO\" and startDate = \"" +
//                                startDate + "\" and endDate = \"" + todayDate + "\"",
//                        "json", "true", "store://datatables.org/alltableswithkeys", "");
                break;
            case R.id.history_year:
                calendar.add(Calendar.DATE, -365);
                startDate = sdf.format(calendar.getTime());
                Log.d(LOG, todayDate + "----" + startDate);
//                dataSource.fetchHistoricData("select * from yahoo.finance.historicaldata where symbol = \"YHOO\" and startDate = \"" +
//                                startDate + "\" and endDate = \"" + todayDate + "\"",
//                        "json", "true", "store://datatables.org/alltableswithkeys", "");
                break;
        }
        dataSource.fetchHistoricData("select * from yahoo.finance.historicaldata where symbol = \"YHOO\" and startDate = \"" +
                        startDate + "\" and endDate = \"" + todayDate + "\"",
                "json", "true", "store://datatables.org/alltableswithkeys", "");
    }
}
