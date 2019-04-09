package com.spring.cxf.server.mobile;

import com.mobile.MobileCodeWSSoap;

public class MobileInterfaceImpl implements MobileInterface {
	//spring 创建手机号公网对象   公网注入到自己发布的服务端
	private MobileCodeWSSoap mobileCodeWSSoap;
	@Override
	public String queryMobile(String num) {	
		//调用公网的查询方法  并返回
		return mobileCodeWSSoap.getMobileCodeInfo(num, "");
	}

	public MobileCodeWSSoap getMobileCodeWSSoap() {
		return mobileCodeWSSoap;
	}
	//提供setter方法给sping配置
	public void setMobileCodeWSSoap(MobileCodeWSSoap mobileCodeWSSoap) {
		this.mobileCodeWSSoap = mobileCodeWSSoap;
	}

	
}
