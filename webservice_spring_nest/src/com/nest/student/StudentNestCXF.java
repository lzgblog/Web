package com.nest.student;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

public class StudentNestCXF {

	public static void main(String[] args) {
		/**
		 * 查询方式get查询多人
		 * http://127.0.0.1:12345/user/student/query/100
		 * 查询方式post查询多人
		 * http://127.0.0.1:32111/user/student/queryList/110?_type=json
		 */
		JAXRSServerFactoryBean rs=new JAXRSServerFactoryBean();
		rs.setServiceBean(new StudentInterfaceImpl());
		rs.setResourceClasses(StudentInterfaceImpl.class);
		rs.setAddress("http://127.0.0.1:32111/user");
		rs.create();
	}

}
