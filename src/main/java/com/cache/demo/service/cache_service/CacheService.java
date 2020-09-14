package com.cache.demo.service.cache_service;

import com.cache.demo.bean.CacheAttribute;
import com.cache.demo.bean.Order;

import java.util.List;

public interface CacheService {

    String init(CacheAttribute cacheAttribute);

    List<Order> add(Order order);

    Order update(String orderNo);

    List<Order> getAll();
}
