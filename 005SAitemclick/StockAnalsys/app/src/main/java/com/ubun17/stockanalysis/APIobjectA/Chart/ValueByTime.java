
package com.ubun17.stockanalysis.APIobjectA.Chart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class ValueByTime {

    @SerializedName("Labels")
    @Expose
    private Object labels;
    @SerializedName("Positions")
    @Expose
    private List<Double> positions = new ArrayList<>();
    @SerializedName("Dates")
    @Expose
    private List<String> dates = new ArrayList<String>();
    @SerializedName("Elements")
    @Expose
    private List<Element> elements = new ArrayList<Element>();

    /**
     * 
     * @return
     *     The labels
     */
    public Object getLabels() {
        return labels;
    }

    /**
     * 
     * @param labels
     *     The Labels
     */
    public void setLabels(Object labels) {
        this.labels = labels;
    }

    /**
     * 
     * @return
     *     The positions
     */
    public List<Double> getPositions() {
        return positions;
    }

    /**
     * 
     * @param positions
     *     The Positions
     */
    public void setPositions(List<Double> positions) {
        this.positions = positions;
    }

    /**
     * 
     * @return
     *     The dates
     */
    public List<String> getDates() {
        return dates;
    }

    /**
     * 
     * @param dates
     *     The Dates
     */
    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    /**
     * 
     * @return
     *     The elements
     */
    public List<Element> getElements() {
        return elements;
    }

    /**
     * 
     * @param elements
     *     The Elements
     */
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

}
