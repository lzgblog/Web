package com.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.cxf.weather.WeatherServiceInterface;

public class ClientWeatherXCXF {
//生成客户端
	public static void main(String[] args) {
		/**
		 * 生成客户端代码方式    jdk1.8不支持此命令      、jaxb-xjc-2.2.7对jdk8有这个bug,
		 * cmd命令解析wsdl  
		 * 1、先定位到src目录  cd D:\Eclipse EE\wsimport\src
		 * 2、执行命令     wsdl2java -p com.cxf.weather -d . http://127.0.0.1:34567/weather?wsdl
		 */
		
		//JaxWsProxyFactoryBean调用服务端
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean=new JaxWsProxyFactoryBean();
		//设置服务接口
		jaxWsProxyFactoryBean.setServiceClass(WeatherServiceInterface.class);
		//设置服务地址
		jaxWsProxyFactoryBean.setAddress("http://127.0.0.1:34567/weather");
		//获取服务接口实例
		WeatherServiceInterface create = jaxWsProxyFactoryBean.create(WeatherServiceInterface.class);
		String queryWeather = create.queryWeather("上海");
		System.out.println(queryWeather);
	}

}
