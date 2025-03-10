package com.shop.cafe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.shop.cafe.dto.Product;


@Repository
public class ProductDao {
	
	@Value("${spring.datasource.driver-class-name}") // aplication.properties에 있는 내용을 가져오겠다
	private String DB_DRIVER;
	
	@Value("${spring.datasource.url}") // aplication.properties에 있는 내용을 가져오겠다
	private String DB_URL;
	
	@Value("${spring.datasource.username}") // aplication.properties에 있는 내용을 가져오겠다
	private String DB_USER;
	
	@Value("${spring.datasource.password}") // aplication.properties에 있는 내용을 가져오겠다
	private String DB_PW;
	
	
	public List<Product> getAllProducts() throws Exception {
		System.out.println("ProductDao getAllProducts 호출됨");
		Class.forName(DB_DRIVER);
		Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);
		PreparedStatement stmt = con.prepareStatement("select * from product limit 6");
		ResultSet rs = stmt.executeQuery();
		
		List<Product> list = new ArrayList<>();
		
		while(rs.next()) {
		 int prodcode =	rs.getInt("prodcode");
		 String prodname = rs.getString("prodname");
		 String pimg = rs.getString("pimg");
		 int price = rs.getInt("price");
		 
		 list.add(new Product(prodname, pimg, prodcode, price));
		}
		
		return list;
	}
	
	
}
