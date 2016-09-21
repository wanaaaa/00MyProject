package com.ubun17.stockanalysis.APIcall;

import android.util.Log;

import com.google.gson.Gson;
import com.ubun17.stockanalysis.APIobjectA.QuandlTime.QuandlTimeObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wanmac on 9/20/16.
 */
public class MarkitChartAPI {
    String examURL = "https://www.quandl.com/api/v3/datasets/WIKI/BBY.json?api_key=3ft2BVtjZUXUKfvnRxhN";
    String beforeURL = "https://www.quandl.com/api/v3/datasets/WIKI/";
    String afterURL = ".json?api_key=3ft2BVtjZUXUKfvnRxhN";
    String mSymbol;

    public MarkitChartAPI(String str) {
        mSymbol = str;
    }

    public void MarkitChartCalling() {
        OkHttpClient markitClient = new OkHttpClient();
        mSymbol= mSymbol.replace("\"", "");

        final Request markitRequest = new Request.Builder()
                .url(beforeURL+mSymbol+afterURL).build();

        try {
            Response response = markitClient.newCall(markitRequest).execute();
            Log.d("ss after","/////////////////////");
//            if(!response.isSuccessful()) {
//                Log.d("before Throw new", "+++++++++++++");
//                Log.d("before Throw new  ", beforeURL+mSymbol+afterURL);
//                throw  new IOException("Unexpeted " + response);
//            }
            if (!response.isSuccessful()) {
                Log.d("Markit", "Bad request"+"///////////////");
            } else {
                String responBody = response.body().string();
                Gson gson = new Gson();
                QuandlTimeObject quandlTimeObject = gson.fromJson(responBody, QuandlTimeObject.class);
                String test = quandlTimeObject.getDataset().getData().get(0).get(1);
                Log.d("The respond is ", test+"~~~~~~~~~~~~~~~~~~~~~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                Log.d("sssss  ", beforeURL+mSymbol+afterURL);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }// End of MarkitChartCalling
}
