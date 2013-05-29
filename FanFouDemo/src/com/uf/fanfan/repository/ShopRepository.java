package com.uf.fanfan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uf.fanfan.entity.Shop;
@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {

}
