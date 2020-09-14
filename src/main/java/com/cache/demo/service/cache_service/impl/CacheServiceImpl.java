package com.cache.demo.service.cache_service.impl;

import com.cache.demo.bean.CacheAttribute;
import com.cache.demo.bean.Order;
import com.cache.demo.service.cache_service.CacheService;
import com.cache.demo.service.storage.Cache;
import com.cache.demo.service.storage.impl.LFU;
import com.cache.demo.service.storage.impl.LRU;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheServiceImpl implements CacheService {

    private Cache cache;

    public String init(CacheAttribute cacheAttribute) {

        String errorMessage="";
        if(cacheAttribute.getMaxSize().isEmpty() ||
                cacheAttribute.getMaxSize() == null){
            errorMessage = "Enter Max Size";

        }else if(cacheAttribute.getCacheType() == null || cacheAttribute.getCacheType().isEmpty()){
            errorMessage = "Caching Type was not found";
        }else if(Integer.parseInt(cacheAttribute.getMaxSize()) <= 0){
            errorMessage = "max Size should be more than 0";
        }else {

            try {
                int maxSize = Integer.parseInt(cacheAttribute.getMaxSize());
                if (cacheAttribute.getCacheType().equalsIgnoreCase("LFU")) {
                    cache = new LFU<>(maxSize);
                } else {
                    cache = new LRU<>(maxSize);
                }
            } catch (Exception e) {
                errorMessage = "Internal Server Error Occured";
            }
        }

        return errorMessage;
    }

    public List<Order> add(Order order){

        boolean keyExist = false;
        keyExist = cache.existkey(order.getOrderNo());

        if(!keyExist){
            order.setAccessCount(0);
        }
        cache.put(order.getOrderNo(),order);

        List<Order> list = cache.getAll();

        return list;

    }

    public Order update(String orderNo){

        Order order = (Order) cache.get(orderNo);
        order.setAccessCount(order.getAccessCount()+1);
        cache.put(order.getOrderNo(),order);

        return order;
    }

    public List<Order> getAll() {
        return (List<Order>) cache.getAll();
    }

}
