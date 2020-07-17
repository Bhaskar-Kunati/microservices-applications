package com.ms.main;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("currency-calculation/from/{from}/to/{to}/quantity/{quantity}")
	private CurrencyConversionBean convertCurrency(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity)
	{
		Map<String, String> uriEntities=new HashMap<>();
		uriEntities.put("from", from);
		uriEntities.put("to", to);
		
		ResponseEntity<CurrencyConversionBean> responseEntity=new RestTemplate().getForEntity("http://localhost:8000/exchange-service/from/{from}/to/{to}", CurrencyConversionBean.class, uriEntities);
		
		CurrencyConversionBean response=responseEntity.getBody();
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()),response.getPort());
	}
	@GetMapping("currency-calculation-feign/from/{from}/to/{to}/quantity/{quantity}")
	private CurrencyConversionBean convertCurrencyFromFeign(@PathVariable("from") String from, @PathVariable("to") String to,@PathVariable("quantity") BigDecimal quantity)
	{
		
		CurrencyConversionBean response=proxy.getExchangeValue(from, to);
		
		logger.info("{} ", response);
		
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()),response.getPort());
	}

}
