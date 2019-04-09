package com.cxf.weathde;

import javax.jws.WebService;


@WebService
//发布soap1.2版本
public interface WeatherServiceInterface {
	public String queryWeather(String city);
}
