package com.cxf.weathde;

import javax.jws.WebService;


@WebService
public interface WeatherServiceInterface {
	public String queryWeather(String city);
}
