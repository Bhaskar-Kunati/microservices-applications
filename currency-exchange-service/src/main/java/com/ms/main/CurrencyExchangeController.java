package com.ms.main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment environment;
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@GetMapping("/exchange-service/all")
	public List<ExchangeValue> getExchangeValue()
	{
		List<ExchangeValue> exchangeValue=repository.findAll();
		return exchangeValue;
	}
	@GetMapping("/exchange-service/from/{from}/to/{to}")
	public ExchangeValue getExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to)
	{
		ExchangeValue exchangeValue=repository.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		logger.info("{} ", exchangeValue);
		return exchangeValue;
	}

}
