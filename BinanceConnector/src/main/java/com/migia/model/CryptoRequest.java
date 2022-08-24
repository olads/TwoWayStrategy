package com.migia.model;

public class CryptoRequest {

    String name;

    Interval interval = Interval.OneHour;

    public CryptoRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public enum Interval{
        ThirtyMinutes("30m"),
        OneHour("1h"),
        FourHour("4h"),
        OneDay("1D"),
        OneWeek("1W");

        private final String interval;

        Interval(String s) {
            this.interval = s;
        }

        public String getInterval(){
            return interval;
        }
    }
}
