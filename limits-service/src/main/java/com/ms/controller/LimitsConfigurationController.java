package com.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.config.Configuration;
import com.ms.model.LimitsController;

@RestController
public class LimitsConfigurationController {
	
	
	@Autowired
	private Configuration config;
	@GetMapping("/limits")
	public LimitsController retriveLimitsFromConfiguration()
	{
		return new LimitsController(config.getMaximum(), config.getMinimum());
	}

}
