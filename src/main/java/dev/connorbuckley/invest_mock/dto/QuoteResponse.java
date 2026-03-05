package dev.connorbuckley.invest_mock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QuoteResponse(
    @JsonProperty("c") double price,
    @JsonProperty("d") double change,
    @JsonProperty("dp") double percentChange,
    @JsonProperty("h") double high,
    @JsonProperty("l") double low,
    @JsonProperty("o") double open,
    @JsonProperty("pc") double previousClose
) {}