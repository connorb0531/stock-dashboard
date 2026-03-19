package dev.connorbuckley.invest_mock.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import dev.connorbuckley.invest_mock.dto.QuoteResponse;
import reactor.core.publisher.Mono;

@Component
public class StockDataClient {
    private WebClient webClient;
    private String apiKey;

    public StockDataClient(WebClient.Builder builder, 
            @Value("${api.base.url}") String baseUrl,
            @Value("${api.key}") String apiKey) {
        this.apiKey = apiKey;
        webClient = builder
            .baseUrl(baseUrl)
            .build();
    }

    public Mono<QuoteResponse> getQuote(String symbol) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/quote")
                .queryParam("symbol", symbol)
                .queryParam("token", apiKey)
                .build())
            .retrieve()
            .bodyToMono(QuoteResponse.class);
    }
}
