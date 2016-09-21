package com.ubun17.stockanalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ubun17 on 9/20/16.
 */

public class CSVFile {
    InputStream inputStream;

    public CSVFile(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List<String[]> read(){
        List resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                //Log.d("row", row[0]);
                resultList.add(row);
                //resultList.add(csvLine.toString());
                //Log.d("inCSVfile", csvLine.toString());
                //resultList.add(csvLine.toString());
            }

        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return resultList;
    }
}
