package com.Upwardiq.Pavithra.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Upwardiq.Pavithra.Model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

}
