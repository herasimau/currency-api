package com.currency.api.parser;

import com.currency.api.domain.CurrencyPair;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
/**
 * @author herasimau on 28.08.2016.
 */

public class Parser {



    private  String type;
    private  String connectionUrl = "http://webrates.truefx.com/rates/connect.html?f=";


    public Parser(String type) {
        this.type=type;
        connectionUrl +=type;
    }

    public String getByInstrument(Instrument instrument) throws JsonProcessingException {
        CurrencyPair currencyPair = new CurrencyPair();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<String> data = parseData(instrument);
        currencyPair.setInstrument(data.get(0));
        currencyPair.setDate(convertDate(Long.parseLong(data.get(1))));
        currencyPair.setTime(convertTime(Long.parseLong(data.get(1))));
        currencyPair.setBID_T1(data.get(2));
        currencyPair.setASK_T1(data.get(3));
        currencyPair.setBID_T2(data.get(4));
        currencyPair.setASK_T2(data.get(5));
        currencyPair.setBID_T3(data.get(6));
        currencyPair.setASK_T3(data.get(7));

        return mapper.writeValueAsString(currencyPair);
    }


    protected  ArrayList<String> parseData(Instrument instrument){
        return type.equals("html")?parseByHtml(instrument):parseByCsv(instrument);
    }

    protected  ArrayList<String> parseByHtml(Instrument instrument){
        ArrayList<ArrayList<String>> allData = getAllDataByHtml();
        ArrayList<String> output = new ArrayList<>();
        switch (instrument){
            case EUR_USD:{
                allData.get(0).forEach(s -> output.add(s));
            }
            case USD_JPY:{
                allData.get(1).forEach(s -> output.add(s));
            }
            case GBP_USD:{
                allData.get(2).forEach(s -> output.add(s));
            }
            case EUR_GBP:{
                allData.get(3).forEach(s -> output.add(s));
            }
            case USD_CHF:{
                allData.get(4).forEach(s -> output.add(s));
            }
            case EUR_JPY:{
                allData.get(5).forEach(s -> output.add(s));
            }
            case EUR_CHF:{
                allData.get(6).forEach(s -> output.add(s));
            }
            case USD_CAD:{
                allData.get(7).forEach(s -> output.add(s));
            }
            case AUD_USD:{
                allData.get(8).forEach(s -> output.add(s));
            }
            case GBP_JPY:{
                allData.get(9).forEach(s -> output.add(s));
            }

        }
        return output;
    }

    protected ArrayList<ArrayList<String>> getAllDataByHtml(){
        ArrayList<ArrayList<String>> allData = new ArrayList<>();
        Document doc = null;

        try {
            doc = Jsoup.connect(connectionUrl)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element table = doc.select("table").get(0);
        Elements rows = table.select("tr");

        for (int i = 0; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            ArrayList<String> data = new ArrayList();
            for (int j = 0; j < cols.size(); j++) {
                data.add(cols.get(j).ownText());
            }
            allData.add(data);
        }
        return allData;
    }

    protected  ArrayList<String> parseByCsv(Instrument instrument){
    return null;
        //TODO csv parse
    }

    protected String convertTime(Long time){
        LocalDateTime date = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);
        return formatterTime.format(date.toLocalTime());
    }

    protected String convertDate(Long time){
        LocalDateTime date = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.US);
        return formatterDate.format(date.toLocalDate());
    }


}




