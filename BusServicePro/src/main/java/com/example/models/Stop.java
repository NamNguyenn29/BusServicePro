package com.example.models;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Stop {
    private int stopID;
    private String stopName;

    public Stop(int stopID, String stopName) {
        this.stopID = stopID;
        this.stopName = stopName;
    }

    public int getStopID() { return stopID; }
    public String getStopName() { return stopName; }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Stop)) return false;
        Stop other = (Stop) obj;
        return this.stopID == other.stopID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopID);
    }


    @Override
    public String toString() {

        return stopName + " " + stopID;
    }

}