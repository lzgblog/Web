package com.solr_springmvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solr_springmvc.po.Product;
import com.solr_springmvc.po.ResultModel;
@Service
public class ProductDataImpl implements ProductData {
	//spring管理httpSolrServer对象 并依赖注入地址
	@Autowired
	private HttpSolrServer server;
	
	//获取参数并设置solr的搜索以及过滤信息
	@Override
	public ResultModel getProductData(String queryString,String catalogName, String price,
			String sort, Integer page) throws Exception {
		//创建SolrQuery对象
		SolrQuery query=new SolrQuery();
		//输入查询语句
		if(StringUtils.isNotEmpty(queryString)){
			//如果搜索框不为空
			query.setQuery(queryString);//根据页面搜索框的内容查询		
		}else{
			//为空则全部查出
			query.setQuery("*:*");		
		}
		//设置过滤    设置商品名称关键词过滤
		if(StringUtils.isNotEmpty(catalogName)){
			query.addFilterQuery("product_catalog_name:"+catalogName);
		}
		//设置价格区间价过滤
		if(StringUtils.isNotEmpty(price)){
			//页面价格传入的形式为0-9   10-19等 的字符串类型所以需要把-分割
			String[] pr = price.split("-");
			//product_price:[0 TO 9]
			query.addFilterQuery("product_price:["+pr[0]+" TO "+pr[1]+"]");
		}
		//设置排序
		if("1".equals(sort)){
			query.setSort("product_price",ORDER.asc);
		}else{
			query.setSort("product_price",ORDER.desc);
		}
		//设置默认域
		query.set("df","product_keywords");
		//设置分页
		if(page==null){
			page=1;
		}
		query.setStart((page-1)*20);//从第几个开始
		query.setRows(20);//一页显示多少个
		
		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("<font style=\"color:red\" >");
		query.setHighlightSimplePost("</font>");
		
		//执行查询
		QueryResponse response = server.query(query);
		//获取结果集
		SolrDocumentList results = response.getResults();
		//查询的总数
		long count = results.getNumFound();
		//把查询出的商品封装到Product对象中
		List<Product> productlist=new ArrayList<Product>();
		Product pro;
		//获取高亮信息
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		for (SolrDocument doc : results) {
			pro=new Product();
			//商品id
			pro.setPid(doc.get("id").toString());
			//商品名称
			List<String> list = highlighting.get(doc.get("id")).get("product_name");
			if(list!=null) {
				pro.setName(list.get(0));
			}else{
				pro.setName(doc.get("product_name").toString());
			}		
			
			//商品价格
			pro.setPrice(Float.parseFloat(doc.get("product_price").toString()));
			//商品图片
			pro.setPicture(doc.get("product_picture").toString());
			productlist.add(pro);
		}
		//把所有需要的数据封装到ResultModel对象中
		ResultModel re=new ResultModel();
		re.setProductList(productlist);//商品信息
		re.setCurPage(page);//当前页
		re.setRecordCount(count);//记录的总条数
		//总页数
		//int pageCount=(int)count/20;
		/*if(count%20>0)
			pageCount++;*/
		
		int pageCount = (int) Math.ceil(count*1.0/20);
		re.setPageCount(pageCount);
		
		return re;
	}

}
