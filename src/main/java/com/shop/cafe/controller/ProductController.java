package com.shop.cafe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cafe.dto.Product;
import com.shop.cafe.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	Map<String,Object> storage = new HashMap();
	
	@GetMapping("getAllProducts")
	public List<Product> getAllProducts() {
		try {
			Object o = storage.get("firstPageProducts");
			if(o==null) {
				List<Product> list = productService.gettAllProducts();
				storage.put("firstPageProducts", list);
				return list;
			} 
			return (List<Product>)o;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
