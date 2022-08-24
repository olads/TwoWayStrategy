package com.migia.service;


import com.migia.model.CryptoRequest;
import com.migia.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BinanceApiConnector {

    @Value("${binance-url}")
    private String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Parser parser;


    public List<History> getHistory(String symbol,String interval,int limit){
        System.out.println("The url is " + baseUrl);
        var url = parseUrl(symbol.toUpperCase(), interval, limit);
        System.out.println("The url is " + url);
        ArrayList[] historiesList = restTemplate.getForObject(url,ArrayList[].class);
        List<History> parsedHistories = parser.parseResponse(historiesList);
        return parsedHistories;
    }

    public List<History> getHistory(String symbol,String interval){
        return getHistory(symbol,interval,20);
    }
    public List<History> getHistory(String symbol){
        return getHistory(symbol,"1h",20);
    }

    private String parseUrl(String symbol,String interval,int limit){
        StringBuilder builder = new StringBuilder(baseUrl);
        builder.append("?symbol=")
                .append(symbol)
                .append("&interval=")
                .append(interval)
                .append("&limit=")
                .append(limit);
        return builder.toString();
    }

	/*
	 * public double[] getHistoryAsArray(List<History> parsedHistories){ double[]
	 * parsedArray = parser.closes(parsedHistories); return parsedArray; }
	 */

}
