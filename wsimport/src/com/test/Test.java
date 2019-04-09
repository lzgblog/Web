package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mobile.MobileCodeWSSoap;

public class Test {

	public static void main(String[] args) {
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		MobileCodeWSSoap bean = (MobileCodeWSSoap) applicationContext.getBean("client");
		String mobileCodeInfo = bean.getMobileCodeInfo("18877545427", "");
		System.out.println(mobileCodeInfo);
	}

}
