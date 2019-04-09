package com.client;

import com.mobile.MobileCodeWS;
import com.mobile.MobileCodeWSSoap;

public class MobileService {

	public static void main(String[] args) {
		MobileCodeWS mobileCodeWS = new MobileCodeWS();
		MobileCodeWSSoap port = mobileCodeWS.getPort(MobileCodeWSSoap.class);
		String result = port.getMobileCodeInfo("18877545427", "");
		System.out.println(result);

	}

}
