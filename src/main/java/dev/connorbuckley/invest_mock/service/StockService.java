package dev.connorbuckley.invest_mock.service;

import org.springframework.stereotype.Service;

import dev.connorbuckley.invest_mock.client.StockDataClient;
import dev.connorbuckley.invest_mock.dto.QuoteResponse;

@Service
public class StockService {
    private StockDataClient client;

    public StockService(StockDataClient stockDataClient) {
        this.client = stockDataClient;
    }

    public QuoteResponse getQuote(String symbol) {
        return client.getQuote(symbol.trim().toUpperCase())
            .block();
    }
}
