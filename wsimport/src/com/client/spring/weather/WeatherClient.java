package com.client.spring.weather;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cxf.weather.WeatherServiceInterface;

public class WeatherClient {

	public static void main(String[] args) {
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		WeatherServiceInterface weather=(WeatherServiceInterface) applicationContext.getBean("weatherClient");
		String queryWeather = weather.queryWeather("北京");
		System.out.println(queryWeather);
	}

}
