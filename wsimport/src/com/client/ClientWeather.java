package com.client;

import com.service.ServiceDemoImpl;
import com.service.ServiceDemoImplService;

public class ClientWeather {

	public static void main(String[] args) {
		/**
		 * cmd命令解析wsdl  
		 * 1、先定位到src目录  cd D:\Eclipse EE\wsimport\src
		 * 2、执行    wsimport -s . http://localhost:23459/weather?wsdl
		 */
		//创建服务视图
		ServiceDemoImplService servicDemoImplService=new ServiceDemoImplService();
		//获取服务实现类
		ServiceDemoImpl port = servicDemoImplService.getPort(ServiceDemoImpl.class);
		//调用查询方法，打印
		String queryWeather = port.queryWeather("北京");
		System.out.println(queryWeather);
	}

}
