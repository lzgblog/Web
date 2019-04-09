package com.client;

import com.mobile.MobileCodeWS;
import com.mobile.MobileCodeWSSoap;

public class ClientWeatherMethod_1 {
//远处访问公网  方式一
	public static void main(String[] args) {
		/**
		 * 解析wsdl  生成java客户端代码
		 * cd D:\Eclipse EE\wsimport\src
		 * wsimport -p com.mobile -s . http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL
		 * -p   为存储路径
		 */
		//服务视图
		MobileCodeWS mobileCodeWS=new MobileCodeWS();
		//确定服务实现类
		MobileCodeWSSoap port = mobileCodeWS.getPort(MobileCodeWSSoap.class);
		String mobileCodeInfo = port.getMobileCodeInfo("18078540247", null);
		System.out.println(mobileCodeInfo);
	}

}
