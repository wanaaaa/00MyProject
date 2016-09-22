package com.ubun17.stockanalysis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.ubun17.stockanalysis.APIcall.MarkitAsyncCalling;
import com.ubun17.stockanalysis.APIcall.QuandlAPIasyncByCSV;
import com.ubun17.stockanalysis.APIobjectA.CompanySymbol.LookUpCompanies;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {
    Button buTest01, butest02, buSearch;
    EditText searchTerm;

    ListView mListView;
    ArrayAdapter<String> mAdapter;
    ArrayList<String> mArrayList;

    private static String baseUrl = "http://dev.markitondemand.com/MODApis/Api/v2/Lookup/json?input=";
    private static String okURL = "http://dev.markitondemand.com/MODApis/Api/v2/Lookup/json?input=apple";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buTest01 = (Button) findViewById(R.id.buTest01);
        butest02 = (Button) findViewById(R.id.buTest02);
        buSearch = (Button) findViewById(R.id.buSearchTerm);

        searchTerm = (EditText) findViewById(R.id.inputSearch);

        buSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callingComLookUp();
            }//End of onClick
        });//End of BuSearch

        buTest01.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {

                Log.d("Test01", "clicked...................");
                QuandlAPIasyncByCSV quanCall = new QuandlAPIasyncByCSV();
                quanCall.execute(MainActivity.this);


//                InputStream inputStream = getResources().openRawResource(R.raw.nasdaq_companylist);
//                CSVFile csvFile = new CSVFile(inputStream);
//                List<String[]> stockList = csvFile.read();
//                String test = stockList.get(1)[0];
//                Log.d("csv", test+"///////////////////////////////////////////////////");
//
//                int numCom = stockList.size();
//                Log.d("the size of scoreList", String.valueOf(numCom));
//                for (int i = 1; i < 15; i ++) {
//                    String stSymbol = stockList.get(i)[0];
//
//                    Log.d("Symbol List", stSymbol);
//                    MarkitAsyncCalling call = new MarkitAsyncCalling();
//                    call.execute(stSymbol);
//
//                    try {
//                        Thread.sleep(300);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        });//End of Buton01

        mArrayList = new ArrayList<String>();
        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, mArrayList);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String stSymbol = mArrayList.get(position);
                Log.d("List Item", stSymbol + "///////////////////////");
                MarkitAsyncCalling call = new MarkitAsyncCalling();
                call.execute(stSymbol);
            }
        });

        butest02.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });

    }//End of onCreate

    public void callingComLookUp() {
        Log.d("buton search", "clicked/////////");
        String stSearchTerm = searchTerm.getText().toString();
        String stURL = baseUrl+stSearchTerm;

        OkHttpClient client = new OkHttpClient();

        final Request apirequest = new Request.Builder()
                .url(stURL).build();
        client.newCall(apirequest).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.i("asdf", "failrue");
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.d("connection", "failure");
                } else {
                    String stBody = response.body().string();
                    //JSONArray jArray = response.body();
                    stBody = stBody.replace("[{", "{\"companies\": [{");
                    stBody = stBody.replace("}]", "}]}");

                    Gson gson = new Gson();
                    LookUpCompanies lookUpCompanies = gson.fromJson(stBody, LookUpCompanies.class);

                    mArrayList.clear();
                    int num = lookUpCompanies.getCompanies().size();
                    for (int i = 0; i < num; i++) {
                        String stSymbol = lookUpCompanies.getCompanies().get(i).getSymbol();
                        mArrayList.add(stSymbol);
                        Log.d("response", stSymbol+"  sssssssss");
                    }

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

}
