package com.uf.fanfan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uf.fanfan.entity.ShopManager;
@Repository
public interface ShopManagerRepository extends JpaRepository<ShopManager, Integer> {

}
