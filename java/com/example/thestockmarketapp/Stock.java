package com.example.thestockmarketapp;

public class Stock {

    private String ticker;
    private String close;
    private String open;
    private String high;
    private String low;
    private String volume;

    public Stock(String ticker, String close, String open, String high, String low, String volume)
    {
        this.ticker = ticker;
        this.close = close;
        this.open = open;
        this.high = high;
        this.low = low;
        this.volume= volume;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
