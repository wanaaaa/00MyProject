package com.ubun17.stockanalysis.APIcall;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ubun17.stockanalysis.CSVFile;
import com.ubun17.stockanalysis.R;

import java.io.InputStream;
import java.util.List;

/**
 * Created by wanmac on 9/21/16.
 */

public class QuandlAPIasyncByCSV extends AsyncTask<Context, Double, List<String>> {
    @Override
    protected List<String> doInBackground(Context... params) {
        InputStream inputStream = params[0].getResources().openRawResource(R.raw.nasdaq_companylist);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> stockList = csvFile.read();
        String test = stockList.get(1)[0];
        Log.d("csv", test+"///////////////////////////////////////////////////");

        int numCom = stockList.size();
        Log.d("the size of scoreList", String.valueOf(numCom));
        for (int i = 1; i < 15; i ++) {
            String stSymbol = stockList.get(i)[0];

            Log.d("Symbol List", stSymbol);
            MarkitChartAPI call = new MarkitChartAPI(stSymbol);
            call.MarkitChartCalling();

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
