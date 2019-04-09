package com.service;

import javax.xml.ws.Endpoint;

public class ServerDemo {
	public static void main(String[] args) {
		//Endpoint发布服务
		//参数解释
		//1.address - 服务地址
		//2.implementor - 实现类
		//访问     http://localhost:11122/weather?wsdl
		Endpoint.publish("http://localhost:11122/weather", new ServiceDemoImpl());
	}
}
