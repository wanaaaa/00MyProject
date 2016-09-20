package com.ubun17.stockanalysis.APIcall;

import android.util.Log;

import com.google.gson.Gson;
import com.ubun17.stockanalysis.APIobjectA.Chart.ValueByTime;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wanmac on 9/20/16.
 */
public class MarkitChartAPI {
    String examURL = "http://dev.markitondemand.com/MODApis/Api/v2/InteractiveChart/json?parameters=%7B%22Normalized%22%3Afalse%2C%22NumberOfDays%22%3A365%2C%22DataPeriod%22%3A%22Day%22%2C%22Elements%22%3A%5B%7B%22Symbol%22%3A%22AAPL%22%2C%22Type%22%3A%22price%22%2C%22Params%22%3A%5B%22c%22%5D%7D%5D%7D";
    String mSymbol;

    public MarkitChartAPI(String str) {
        mSymbol = str;
    }

    public void MarkitChartCalling() {
        OkHttpClient markitClient = new OkHttpClient();

        final Request markitRequest = new Request.Builder().url(examURL).build();

        try {
            Response response = markitClient.newCall(markitRequest).execute();
            if(!response.isSuccessful()) throw  new IOException("Unexpeted " + response);
            if (!response.isSuccessful()) {
                Log.d("Markit", "Bad request"+"///////////////");
            } else {
                String responBody = response.body().string();
                Gson gson = new Gson();
                ValueByTime valueByTime =gson.fromJson(responBody, ValueByTime.class);
                //ValueByTime valueByTime = gson.fromJson(responBody, ValueByTime.class);
                double test = valueByTime.getElements().get(0).getDataSeries()
                        .getClose().getValues().get(0);
                Log.d("The respond is ", String.valueOf(test));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }// End of MarkitChartCalling
}
