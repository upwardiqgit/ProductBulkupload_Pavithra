package com.Upwardiq.Pavithra.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ProductController {
	//@Autowired
	//private ProductService productService;
	@GetMapping("/")
	public String getpage() {
		return "index";
	}
}
