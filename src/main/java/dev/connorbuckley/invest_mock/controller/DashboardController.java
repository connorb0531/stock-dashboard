package dev.connorbuckley.invest_mock.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.connorbuckley.invest_mock.dto.QuoteResponse;
import dev.connorbuckley.invest_mock.exception.InvalidSymbolException;
import dev.connorbuckley.invest_mock.service.StockService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private StockService stockService;

    public DashboardController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public String dashboard(@RequestParam(required = false) String symbol, Model model) {
        if (symbol == null || symbol.isBlank()) {
            return "dashboard";
        }

        try {
            symbol = symbol.trim().toUpperCase();
            Map<String, QuoteResponse> quoteMap = stockService.getQuote(symbol);
            QuoteResponse quote = quoteMap.get(symbol);

            model.addAttribute("symbol", symbol);
            model.addAttribute("quote", quote);       
        } 
        catch (InvalidSymbolException e) {
            model.addAttribute("error", e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
        }

        return "dashboard";
    }

}
