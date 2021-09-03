package com.currency.conversion.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.currency.conversion.entity.CurrencyConversion;
import com.currency.conversion.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {

	@Autowired
   private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-convesion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(
									@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity)
	{
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversion> forEntity = new RestTemplate().getForEntity("http://localhost:3333/currency-exchange/from/{from}/to/{to}",
				CurrencyConversion.class, uriVariables);
		
		CurrencyConversion currencyConversion = forEntity.getBody();
		
		return new CurrencyConversion(currencyConversion.getId(), from, to, 
				quantity.multiply(currencyConversion.getConversionMultiple()), quantity, 
				currencyConversion.getConversionMultiple(), currencyConversion.getEnvironment());
	}
	
	@GetMapping("/currency-convesion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(
									@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity)
	{
		
		
		CurrencyConversion currencyConversion = proxy.calculateCurrencyConversion(from, to);
		
		return new CurrencyConversion(currencyConversion.getId(), from, to, 
				quantity.multiply(currencyConversion.getConversionMultiple()), quantity, 
				currencyConversion.getConversionMultiple(), currencyConversion.getEnvironment());
	}
}
