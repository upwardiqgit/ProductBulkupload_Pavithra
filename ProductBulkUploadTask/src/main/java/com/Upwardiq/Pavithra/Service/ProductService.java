package com.Upwardiq.Pavithra.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Upwardiq.Pavithra.Repository.ProductRepo;

@Service
public class ProductService {
	@Autowired 
	private ProductRepo productRepository;
}
