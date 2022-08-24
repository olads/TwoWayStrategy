package com.migia.service;


import com.migia.model.CryptoRequest;
import com.migia.model.History;
import com.migia.restControllers.BollingerController;
import com.migia.model.Position;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BollingerBandService {

    @Autowired
    BinanceConnectorService binanceService;

    private int SD = 2;
    private int period = 20;

    public void setSD(int SD) {
        this.SD = SD;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    private final DescriptiveStatistics stats = new DescriptiveStatistics();

    public double middleBand(List<History> histories){
        populate(histories);
        double mean = stats.getMean();
        return mean;
    }
       public double upperBand(List<History> histories){
        populate(histories);
        double mean = stats.getMean();
        double standardDeviation = stats.getStandardDeviation();
        double pos = mean + (SD * standardDeviation);

        return pos;
    }
       public double lowerBand(List<History> histories){
           double mean = stats.getMean();
           double standardDeviation = stats.getStandardDeviation();
           double pos = mean - (SD * standardDeviation);

           return pos;
    }


    public void populate(List<History> histories){
        stats.clear();
        histories.stream().forEach((history -> {
            stats.addValue(history.getClose());
        }));
    }


    public Position getPosition(double price, double up, double mid, double low){
        double[] prices = {up, mid, low};
        int pos = getNearest(prices,price);
        double atPos = prices[pos];
        System.out.println("The closest band to the price is " + atPos + " pos is " + pos);
        switch (pos){
            case 0:
                if(price > up)
                    return Position.ABOVE_UPPER;
                else if(price < up)
                    return Position.BELOW_UPPER;
            case 1:
                if(price > mid){
                    return Position.ABOVE_MIDDLE;
                }
                else if(price < mid)
                    return Position.BELOW_MIDDLE;
            case 2:
                if(price > low)
                    return Position.ABOVE_LOWER;
                else if(price < low)
                    return Position.BELOW_LOWER;
            default:
                return Position.UNKNOWN;
        }

    }

    public int getNearest(double[] prices,double price) {
        int pos = 0;
        ArrayList<Double> diff = new ArrayList<>();
        for (int i = 0; i < prices.length; i++) {
            diff.add(Math.abs(price-prices[i]));
        }
        Optional<Double> min = diff.stream().min(Double::compareTo);
        return diff.indexOf(min.get());
    }

    public Position position(String param){
        CryptoRequest request = new CryptoRequest(param.toUpperCase(), CryptoRequest.Interval.OneHour);
            List<History> histories =  binanceService.getDataWithSetting(request);
        double mid = middleBand(histories);
        double up = upperBand(histories);
        double low = lowerBand(histories);
        double price = histories.get(histories.size()-1).getClose();
        return getPosition(price,up,mid,low);

    }

    public boolean signal(String coinA, String coinB){

        Position posA = position(coinA);
        Position posB = position(coinB);

        System.out.println(posA + " ===== " + posB);
        System.out.println(posA.getValue() + " ===== " + posB.getValue());

        if(posA.getValue() != posB.getValue()){
            return true;
        }
        return false;
    }

}
