package com.cache.demo.controller;
import com.cache.demo.bean.CacheAttribute;
import com.cache.demo.bean.Order;
import com.cache.demo.service.cache_service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class DemoController {
    //Cache<Integer, Object> cache = null;

    @Autowired
    CacheService cacheService;

    @RequestMapping("/")
    public String welcome(Model model) {

        CacheAttribute cacheAttribute = new CacheAttribute();
        model.addAttribute("cacheAttribute" , cacheAttribute);
        model.addAttribute("message" , "No messages");
        return "main";
    }


    @RequestMapping("/initCache")
    public String initCache(@ModelAttribute("cacheAttribute") CacheAttribute cacheAttribute, Model model) {
       String errorMessage="";

        errorMessage = cacheService.init(cacheAttribute);

        if(errorMessage.isEmpty()){
            Order order = new Order();
            model.addAttribute("order" , order);
            return "cache";
        }else{

            model.addAttribute("message" , errorMessage);
            return "main";
        }

    }


    @PostMapping("/add")
    public String addToCache(@ModelAttribute("order") Order order, Model model) {

        List<Order> list = cacheService.add(order);

        model.addAttribute("orderlist", list);

        Order ordernew = new Order();
        model.addAttribute("order" , ordernew);

        return "cache";
    }

    @RequestMapping(value = "/update/{orderNo}", method=RequestMethod.GET)
    public String getOrder(@PathVariable String orderNo, Model model){

        Order order = cacheService.update(orderNo);

        model.addAttribute("order" , order);

        List<Order> list = cacheService.getAll();
        model.addAttribute("orderlist", list);

        return "cache";
    }


}
