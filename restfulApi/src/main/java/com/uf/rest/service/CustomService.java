package com.uf.rest.service;

import java.util.List;

import com.uf.rest.entity.Shop;

public interface CustomService {
	public List<Shop> findNearShops(int start, int count,Double longitude,Double latitude);
}
