package com.currency.api.domain;

/**
 * @author herasimau on 29.08.2016.
 */
public class CurrencyPair {

    private String instrument;
    private String date;
    private String time;
    private String BID_T1;
    private String ASK_T1;
    private String BID_T2;
    private String ASK_T2;
    private String BID_T3;
    private String ASK_T3;



    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBID_T1() {
        return BID_T1;
    }

    public void setBID_T1(String BID_T1) {
        this.BID_T1 = BID_T1;
    }

    public String getASK_T1() {
        return ASK_T1;
    }

    public void setASK_T1(String ASK_T1) {
        this.ASK_T1 = ASK_T1;
    }

    public String getBID_T2() {
        return BID_T2;
    }

    public void setBID_T2(String BID_T2) {
        this.BID_T2 = BID_T2;
    }

    public String getASK_T2() {
        return ASK_T2;
    }

    public void setASK_T2(String ASK_T2) {
        this.ASK_T2 = ASK_T2;
    }

    public String getBID_T3() {
        return BID_T3;
    }

    public void setBID_T3(String BID_T3) {
        this.BID_T3 = BID_T3;
    }

    public String getASK_T3() {
        return ASK_T3;
    }

    public void setASK_T3(String ASK_T3) {
        this.ASK_T3 = ASK_T3;
    }
}
