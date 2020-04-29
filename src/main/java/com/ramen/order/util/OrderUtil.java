package com.ramen.order.util;

import com.alibaba.fastjson.JSON;
import com.ramen.order.Constants;
import com.ramen.order.model.Order;

import java.util.*;
import java.util.stream.Collectors;


public class OrderUtil {
    private static OrderUtil orderUtil;
    private static JsonFileUtil jsonFileUtil;
    private List<Order> orders;
    private Map<Long,Order> orderMap;

    public static OrderUtil getOrderUtil() {
        if (orderUtil == null) {
            orderUtil = new OrderUtil();
            jsonFileUtil = JsonFileUtil.getJsonFileUtil(Constants.JsonUtil.Order);
        }
        return orderUtil;
    }

    public void storeOrder(Order order) {
        String jsonString = JSON.toJSONString(order);
        jsonFileUtil.writeToJson(Constants.JsonUtil.Order,jsonString);
        orders = getOrders();
        orders.add(order);
        orderMap.put(order.getId(),order);
    }

    public List<Order> getOrders() {
        if (orders == null) {
            List<String> jsonStrings = jsonFileUtil.readFromJson(Constants.JsonUtil.Order);
            orders = new ArrayList<Order>();
            for (int i = 0; i < jsonStrings.size(); i++) {
                orders.add(JSON.parseObject(jsonStrings.get(i), Order.class));
            }
            orderMap = orders.stream().collect(Collectors.toMap(Order::getId, order->order));
        }
        return orders;
    }

    public List<Order> getOrders(Date beginDate,Date endDate) {
        orders = getOrders();
        List<Order> timeOrders = new ArrayList<Order>();
        for (int i = 0; i < orders.size(); i++) {
            Date time = orders.get(i).getIssueTime();
            if(time.after(beginDate) && time.before(endDate))
                timeOrders.add(orders.get(i));
        }
        return timeOrders;
    }

    private int getDishSum(Long dishId, List<Order> orders) {
        int sum = 0;
        for (int i = 0; i < orders.size(); i++) {
            sum+= Collections.frequency(orders.get(i).getDishOptions().keySet(),dishId) ;
        }
        return sum;
    }

    public int getDishSum(Long dishId) {
        orders = getOrders();
        return getDishSum(dishId,orders);
    }

    public int getDishSum(Long dishId,Date beginDate,Date endDate) {
        orders = getOrders(beginDate,endDate);
        return getDishSum(dishId,orders);
    }

    public Order getOrder(Long id){
        orders = getOrders();
        return orderMap.get(id);
    }

}

