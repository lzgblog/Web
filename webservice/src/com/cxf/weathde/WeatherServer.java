package com.cxf.weathde;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

//发布服务
public class WeatherServer {

	public static void main(String[] args) {
		//cxf框架
		JaxWsServerFactoryBean jaxWsServerFactoryBean=new JaxWsServerFactoryBean();
		//设置服务接口
		jaxWsServerFactoryBean.setServiceClass(WeatherServiceInterface.class);
		//设置服务实现类
		jaxWsServerFactoryBean.setServiceBean(new WeatherServiceInterfaceImpl());
		//设置服务地址
		jaxWsServerFactoryBean.setAddress("http://127.0.0.1:34567/weather");
		
		//拦截器
		//jaxWsServerFactoryBean.getInInterceptors().add(new LoggingInInterceptor());
		//jaxWsServerFactoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
		//发布服务
		jaxWsServerFactoryBean.create();
	}

}
