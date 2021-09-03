package com.currency.conversion.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.currency.conversion.entity.CurrencyConversion;

//@FeignClient(name = "currency-exchange",url = "localhost:3333/")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion calculateCurrencyConversion(
			@PathVariable String from,@PathVariable String to);
}
