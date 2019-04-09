package com.solr_springmvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solr_springmvc.po.ResultModel;
import com.solr_springmvc.service.ProductData;

@Controller
public class ProductController {
	
	@RequestMapping("login")
	public String login(){
		return "index";
	}
	//注入ProductDataImpl对象
	@Autowired
	private ProductData product;
	
	
	@RequestMapping("/list")
	public String list(String queryString, String catalog_name, String price,
			String sort, Integer page, Model model) throws Exception{
		ResultModel result = product.getProductData(queryString, catalog_name, price, sort, page);
		//把查询结果存入result域中
		model.addAttribute("result",result);
		//返回简单数据
		model.addAttribute("queryString", queryString);
		model.addAttribute("catalog_name", catalog_name);
		model.addAttribute("price", price);
		model.addAttribute("sort", sort);
		model.addAttribute("page", page);
		return "product_list";
	}
}
