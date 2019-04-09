package com.spring.cxf.server.mobile;

import javax.jws.WebService;

//发布服务
@WebService
public interface MobileInterface {
	
	/**
	 * 手机号码归属地查询
	 * @param num
	 * @return
	 */
	public String queryMobile(String num);
}
