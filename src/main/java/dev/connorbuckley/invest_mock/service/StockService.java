package dev.connorbuckley.invest_mock.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import dev.connorbuckley.invest_mock.client.StockDataClient;
import dev.connorbuckley.invest_mock.dto.QuoteResponse;
import dev.connorbuckley.invest_mock.exception.InvalidSymbolException;

import java.util.Map;

@Service
public class StockService {
    private StockDataClient client;

    public StockService(StockDataClient stockDataClient) {
        this.client = stockDataClient;
    }

    @Cacheable(value = "quote", key = "#symbol.toUpperCase()")
    public Map<String, QuoteResponse> getQuote(String symbol) {
            QuoteResponse response = client.getQuote(symbol).block();
            if (response == null || response.price() == 0) {
                throw new InvalidSymbolException("Invalid symbol: " + symbol);
            }
            return Map.of(symbol, response);  
    }
}
