package com.migia.service;


import com.migia.model.History;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Parser {




    public List<History> parseResponse(ArrayList[] response){
        ArrayList<History> currencies = new ArrayList<>();
        for( var i : response){
            currencies.add(parseArrayListToHistory(i));
        }
        return currencies;
    }

    public History parseArrayListToHistory(ArrayList list){
        History currency = new History();
        currency.setOpen(Double.valueOf((String) list.get(1)));
        currency.setHigh(Double.valueOf((String) list.get(2)));
        currency.setLow(Double.valueOf((String) list.get(3)));
        currency.setClose(Double.valueOf((String) list.get(4)));


        return currency;
    }

	/*
	 * public double[] closes(List<History> list){
	 * 
	 * List<Double> res = list.stream() .map((Parser::getDouble))
	 * .collect(Collectors.toList()); Double[] doubleArray = res.toArray(new
	 * Double[0]); return parseDoubleArray(doubleArray); }
	 */



    public double[] parseDoubleArray(Double[] arr){
        double[] res = new double[arr.length];
        int pos = 0;
        for(Double d: arr){
            res[pos++] = d.doubleValue();
        }
        return res;
    }


    private BigDecimal getBigDecimal(String value){
        return BigDecimal.valueOf(Double.valueOf(value));
    }
}
