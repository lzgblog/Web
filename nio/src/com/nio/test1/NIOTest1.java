package com.nio.test1;

import java.nio.ByteBuffer;

import org.junit.Test;

public class NIOTest1 {

	@Test
	public void test1() {
		String str="abc";
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put(str.getBytes());//将数据写入缓冲区
		buffer.flip();//转换成读状态
		byte[] by=new byte[buffer.limit()];
		buffer.get(by);
		//buffer.get(by,0,1);
		/*if(buffer.hasRemaining()) {
			System.out.println(buffer.remaining());
		}*/  //判断是否还存在几个未读取的数据
		System.out.println(new String(by,0,3));//数据
		System.out.println(buffer.position());//读取的位置
		System.out.println(buffer.limit());//数据的长度上限
	}
}
