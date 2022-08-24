package com.migia.restControllers;


import com.migia.model.CryptoRequest;
import com.migia.model.History;
import com.migia.service.BinanceConnectorService;
import com.migia.service.BollingerBandService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bollinger")
public class BollingerController {

    @Autowired
    private BollingerBandService bollingerBandService;

    @Autowired
    BinanceConnectorService binanceService;

    List<History> histories;
    public BollingerController() {
        //histories = binanceService.getData();
    }

    @GetMapping
    public List<History> getHistory(){
        CryptoRequest request = new CryptoRequest();
        request.setName("BTCUSDT");
        request.setInterval(CryptoRequest.Interval.ThirtyMinutes);
        return binanceService.getDataWithSetting(request);
    }



    @GetMapping("/middle")
    public String getMiddleBand(){
        double pos = bollingerBandService.middleBand(histories);
        return String.valueOf(pos);
    }
     @GetMapping("/upper")
    public String getUpperBand(){
        double pos = bollingerBandService.upperBand(histories);
        return String.valueOf(pos);
    }
     @GetMapping("lower")
    public String getLowerBand(){
        double pos = bollingerBandService.lowerBand(histories);
        return String.valueOf(pos);
    }

    @GetMapping("/position")
    public String position(@RequestParam String param,@RequestParam String param2){
      boolean signal = bollingerBandService.signal(param,param2);
        if(signal){
            return "There is a divergence between coin " + param.toUpperCase() + " and coin " + param2.toUpperCase();
        }
        return " There is no trading opportunity";
    }





}
