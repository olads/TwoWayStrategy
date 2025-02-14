package com.migia.endpoints;


import com.migia.model.CryptoRequest;
import com.migia.model.History;
import com.migia.service.BinanceApiConnector;
import com.migia.service.CointegrationValidator;
import com.migia.service.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class ConnectorServiceController {


    @Autowired
    BinanceApiConnector connector;

    @Autowired
    CointegrationValidator validator;

    @GetMapping("/data")
    public List<History> getData(){
       return connector.getHistory("BTCUSDT");
    }

    @GetMapping("/data/{limit}")
    public List<History> getData(@PathVariable int limit){
      return connector.getHistory("BTCUSDT", limit);
    }

    @PostMapping("/data")
    public List<History> getData(@RequestBody CryptoRequest request){
       return connector.getHistory(request.getName(),request.getInterval().getInterval());

    }
    @PostMapping("/data/{limit}")
    public List<History> getDataWithLimit(@PathVariable int limit , @RequestBody CryptoRequest request){
       return connector.getHistory(request.getName(),request.getInterval().name(),limit);

    }

	/*
	 * @GetMapping("coint/{pairA},{pairB}") public String coint( @PathVariable
	 * String pairA, @PathVariable String pairB){ return
	 * validator.isCointegrated(connector.getHistoryAsArray(connector.getHistory(
	 * pairA)) ,connector.getHistoryAsArray(connector.getHistory(pairB))); }
	 */






}
