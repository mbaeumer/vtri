
package com.hackathon.vasttrafik.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hackathon.vasttrafik.DepartureBoard;

public class Station {

    @SerializedName("DepartureBoard")
    @Expose
    private DepartureBoard departureBoard;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Station() {
    }

    /**
     * 
     * @param departureBoard
     */
    public Station(DepartureBoard departureBoard) {
        super();
        this.departureBoard = departureBoard;
    }

    public DepartureBoard getDepartureBoard() {
        return departureBoard;
    }

    public void setDepartureBoard(DepartureBoard departureBoard) {
        this.departureBoard = departureBoard;
    }

}
