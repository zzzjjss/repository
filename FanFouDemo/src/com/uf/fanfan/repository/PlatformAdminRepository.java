package com.uf.fanfan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uf.fanfan.entity.PlatformAdmin;
@Repository
public interface PlatformAdminRepository extends JpaRepository<PlatformAdmin, Integer> {

}
