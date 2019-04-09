package com.solr_springmvc.service;

import com.solr_springmvc.po.ResultModel;

public interface ProductData {
	public ResultModel getProductData(String queryString,String catalogName,
			String price, String sort, Integer page) throws Exception;
}
