package com.lucene.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lucene.po.Book;

public class UseDataImpl implements UseData {

	@Override
	public List<Book> findBook(){
		Connection conn=null;
		PreparedStatement stat=null;
		ResultSet result=null;
		List<Book> list=new ArrayList();
		
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/solr", "root", "root");
			//sql语句
			String sql="SELECT * FROM book";
			stat = conn.prepareStatement(sql);
			//获取结果
			result = stat.executeQuery();
			
			while(result.next()) {
				//把数据封装到Book对象中
				Book book=new Book();
				book.setId(result.getInt("id"));
				book.setName(result.getString("name"));
				book.setPrice(result.getFloat("price"));
				book.setPic(result.getString("pic"));
				book.setDescription(result.getString("description"));
				list.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
