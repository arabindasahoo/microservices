package com.currency.exchange;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.currency.exchange.Entity.CurrencyExchange;
import com.currency.exchange.repository.CurrencyExchangeRepository;

@SpringBootApplication
public class CurrencyExchangeServiceApplication implements CommandLineRunner{
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//extracted();
	}

	private void extracted() {
		CurrencyExchange currencyExchange = new CurrencyExchange(1000L, "USD", "INR", BigDecimal.valueOf(75L));

		CurrencyExchange currencyExchange1 = new CurrencyExchange(1001L, "EUR", "INR", BigDecimal.valueOf(120L));

		CurrencyExchange currencyExchange2 = new CurrencyExchange(1002L, "DHIRAM", "INR", BigDecimal.valueOf(85L));

		List<CurrencyExchange> list = new ArrayList<>();
		
		list.add(currencyExchange);
		list.add(currencyExchange1);
		list.add(currencyExchange2);
		
		currencyExchangeRepository.saveAll(list);
	}

}
