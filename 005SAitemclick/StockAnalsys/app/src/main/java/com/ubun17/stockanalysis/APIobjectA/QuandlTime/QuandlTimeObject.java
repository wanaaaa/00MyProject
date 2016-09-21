
package com.ubun17.stockanalysis.APIobjectA.QuandlTime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuandlTimeObject {

    @SerializedName("dataset")
    @Expose
    private Dataset dataset;

    /**
     * 
     * @return
     *     The dataset
     */
    public Dataset getDataset() {
        return dataset;
    }

    /**
     * 
     * @param dataset
     *     The dataset
     */
    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

//    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this);
//    }

}
