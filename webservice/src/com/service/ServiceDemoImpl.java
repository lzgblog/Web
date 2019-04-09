package com.service;

import javax.jws.WebService;

@WebService
public class ServiceDemoImpl implements ServiceDemo {

	@Override
	public String queryWeather(String city) {
		System.out.println("from city..."+city);
		String weather="阴";
		return weather;
	}

}
