package com.solr.crud;


import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class IndexManager {
	
	//添加和修改操作  如文档域中没有就添加  有则修改
	@Test
	public void insertAndUpdateIndex() throws Exception, Exception {
		//创建HttpSolrServer
		HttpSolrServer server=new HttpSolrServer("http://localhost:80/solr");
		//创建SolrInputDocument对象
		SolrInputDocument doc=new SolrInputDocument();
		doc.setField("id", "c_123456");
		doc.setField("name", "tom");
		//添加doc内容
		server.add(doc);
		//提交
		server.commit();
	}
	
	//删除数据
	@Test
	public void deleteIndex() throws Exception{
		//创建HttpSolrServer
		HttpSolrServer server = new HttpSolrServer("http://localhost:80/solr");
		//根据id删除
//		server.deleteById("c_123456");
		//根据语句删除
		server.deleteByQuery("id:c_123456");
		//server.deleteByQuery("*:*");  删除所有数据  慎用
		server.commit();
	}
	
	//简单查询数据
	@Test
	public void simpleQueryIndex() throws Exception {
		//创建HttpSolrServer
		HttpSolrServer server=new HttpSolrServer("http://localhost:80/solr");
		//创建SolrQuery对象
		SolrQuery query=new SolrQuery();
		//查询条件
		query.setQuery("product_name:花儿");
		//执行查询条件
		QueryResponse response = server.query(query);
		//返回匹配的所有结果
		SolrDocumentList results = response.getResults();
		//匹配的结果数量
		long numFound = results.getNumFound();
		System.out.println(numFound);
		for (SolrDocument doc : results) {
			System.out.println(doc.get("id"));
			System.out.println(doc.get("product_name"));
			System.out.println(doc.get("product_catalog"));
			System.out.println(doc.get("product_price"));
			
			System.out.println(doc.get("product_picture"));
			System.out.println(doc.get("-----------------"));
		}
	}
	//复杂查询
	@Test
	public void searcher() throws Exception {
		HttpSolrServer server=new HttpSolrServer("http://localhost:80/solr");
		SolrQuery query=new SolrQuery();
		query.setQuery("product_name:小黄人");
		//query.set("q", "product_name:小黄人");
		
		//设置过滤   价格0到10
		//******注意TO都是大写
		query.setFilterQueries("product_price:[10 TO 20]");
		//设置排序
		query.setSort("product_price",ORDER.asc);
		//设置分页信息
		query.setStart(0);
		query.setRows(10);
		
		//设置显示的field域的集合
		query.setFields("id,product_name,product_catalog,product_price,product_picture");
		
		//设置默认域
		query.set("df", "product_keywords");
		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("product_name");//添加product_name为高亮
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		
		//执行查询语句
		QueryResponse response = server.query(query);
		SolrDocumentList results = response.getResults();
		
		long numFound = results.getNumFound();
		System.out.println("匹配的总数："+numFound);
		// 获取高亮显示信息
		Map<String, Map<String, List<String>>> highlighting = response
						.getHighlighting();

		for (SolrDocument doc : results) {
			
			
			System.out.println(doc.get("id"));
			
			
			List<String> list2 = highlighting.get(doc.get("id")).get(
					"product_name");
			if (list2 != null)
				System.out.println("高亮显示的商品名称：" + list2.get(0));
			else {
				System.out.println(doc.get("product_name"));
			}


			System.out.println(doc.get("product_catalog"));
			System.out.println(doc.get("product_price"));
			
			System.out.println(doc.get("product_picture"));
			System.out.println(doc.get("-----------------"));
		}
		
	}
}
