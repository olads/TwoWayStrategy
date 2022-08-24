package com.migia.service;

import com.migia.model.CryptoRequest;
import com.migia.model.History;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="binance-connector-service")
public interface BinanceConnectorService {

    @GetMapping("/data")
    public List<History> getData();

    @PostMapping("/data")
    public List<History> getDataWithSetting( CryptoRequest request);
}
