package com.shiro;

import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

@Service
public class ServiceTest {

	//注解的方式使用权限认证
	@RequiresRoles("{admin}")
	public void test(){
		Date date = new Date();
		System.out.println(date.toLocaleString());
	}
}
