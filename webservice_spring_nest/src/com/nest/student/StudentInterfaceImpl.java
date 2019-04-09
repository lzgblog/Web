package com.nest.student;

import java.util.ArrayList;
import java.util.List;

public class StudentInterfaceImpl implements StudentInterface {

	@Override
	public Student query(int id) {
		Student st=new Student();
		st.setId(100);
		st.setName("李四");
		st.setSex("男");
		return st;
	}

	@Override
	public List<Student> queryList(String name) {
		List<Student> list=new ArrayList<Student>();
		Student st1=new Student();
		st1.setId(101);
		st1.setName("张三");
		st1.setSex("男");
		Student st2=new Student();
		st2.setId(102);
		st2.setName("李四");
		st2.setSex("男");
		list.add(st1);
		list.add(st2);
		return list;
	}

}
