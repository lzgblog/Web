package com.nio.test1;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class ChannelTest2 {
	@Test
	public void test1() throws IOException {
		FileChannel inChannel = FileChannel.open(Paths.get("D:\\Eclipse EE\\nio\\src\\com\\nio\\test1\\1.jpg"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("D:\\Eclipse EE\\nio\\src\\com\\nio\\test1\\2.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
		MappedByteBuffer in = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer out = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
		
		//直接对缓存区进行读写操作
		byte[] b=new byte[in.limit()];
		in.get(b);
		out.put(b);
		
		inChannel.close();
		outChannel.close();
	}
	@Test
	public void test2() throws IOException {
		FileChannel inChannel = FileChannel.open(Paths.get("D:\\Eclipse EE\\nio\\src\\com\\nio\\test1\\1.jpg"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("D:\\Eclipse EE\\nio\\src\\com\\nio\\test1\\3.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
		inChannel.transferTo(0, inChannel.size(), outChannel);
		
		inChannel.close();
		outChannel.close();
	}
}
