package com.cxf.weathde;

public class WeatherServiceInterfaceImpl implements WeatherServiceInterface {

	@Override
	public String queryWeather(String city) {
		if("北京".equals(city)) {
			return "很冷";
		}else {
			return "很热";
		}
	}

}
