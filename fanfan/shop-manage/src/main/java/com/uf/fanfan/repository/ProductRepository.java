package com.uf.fanfan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.uf.fanfan.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>,JpaSpecificationExecutor<Product> {

}
