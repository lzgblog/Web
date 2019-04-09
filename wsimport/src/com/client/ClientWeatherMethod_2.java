package com.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.mobile.MobileCodeWSSoap;

public class ClientWeatherMethod_2 {
	//远程访问公网    方式二
	public static void main(String[] args) throws Exception {
		//创建WSDL的URL，注意不是服务地址  wsdl说明书地址
		URL url=new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL");
		//创建服务名称
				//1.namespaceURI - 命名空间地址
				//2.localPart - 服务视图名
		QName qname=new QName("http://WebXml.com.cn/", "MobileCodeWS");
		//new QName(namespaceURI, localPart)
		//创建服务视图
				//参数解释：
				//1.wsdlDocumentLocation - wsdl地址
				//2.serviceName - 服务名称
		Service service = Service.create(url, qname);
		//Service.create(url, qname)
		//服务实现类
		MobileCodeWSSoap port = service.getPort(MobileCodeWSSoap.class);
		String mobileCodeInfo = port.getMobileCodeInfo("18877545427", "");
		System.out.println(mobileCodeInfo);
		
	}

}
