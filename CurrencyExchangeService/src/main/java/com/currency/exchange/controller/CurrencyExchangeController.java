package com.currency.exchange.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.currency.exchange.Entity.CurrencyExchange;
import com.currency.exchange.repository.CurrencyExchangeRepository;

@RestController
class CurrencyExchangeController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepo;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(@PathVariable String from,
													@PathVariable String to)
	{
		
		
	//CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50L) );
	
		CurrencyExchange currencyExchange = currencyExchangeRepo.findByFromAndTo(from, to);
		
		if(currencyExchange == null)
		{
			throw new RuntimeException("Data not found for "+from+" to "+to);
		}
		
	currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
	
	return currencyExchange;
	}
	
	
	
}
