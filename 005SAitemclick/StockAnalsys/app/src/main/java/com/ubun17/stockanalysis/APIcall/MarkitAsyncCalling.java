package com.ubun17.stockanalysis.APIcall;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by wanmac on 9/20/16.
 */
public class MarkitAsyncCalling extends AsyncTask<String, Double, List<String>> {
    @Override
    protected List<String> doInBackground(String... arg) {
        MarkitChartAPI apiCalling = new MarkitChartAPI(arg[0]);

        return null;
    }


}
