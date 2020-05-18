package com.ramen.order.util;

import com.alibaba.fastjson.JSON;
import com.ramen.order.Constants;
import com.ramen.order.model.Order;
import org.javatuples.Quartet;

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
            orders = new ArrayList<>();
            for (String jsonString : jsonStrings) {
                orders.add(JSON.parseObject(jsonString, Order.class));
            }
            orderMap = orders.stream().collect(Collectors.toMap(Order::getId, order->order));
        }
        return orders;
    }

    public List<Order> getOrders(Date beginDate,Date endDate) {
        orders = getOrders();
        List<Order> timeOrders = new ArrayList<>();
        for (Order order : orders) {
            Date time = order.getIssueTime();
            if (time.after(beginDate) && time.before(endDate))
                timeOrders.add(order);
        }
        return timeOrders;
    }

    //Add-Ons
    private int getAddOnSum(Long dishId, List<Order> orders) {
        int sum = 0;
        for (Order order : orders) {
            sum += Collections.frequency(order.getDishOptions().keySet(), dishId);
        }
        return sum;
    }

    public int getAddOnSum(Long dishId) {
        orders = getOrders();
        return getAddOnSum(dishId,orders);
    }

    public int getAddOnSum(Long dishId, Date beginDate, Date endDate) {
        orders = getOrders(beginDate,endDate);
        return getAddOnSum(dishId,orders);
    }

    private int getSuitDishSum(Long dishId, List<Order> orders){
        int sum = 0;
        for (Order o:orders) {
            for(Quartet<Long,String,Map<Long,String>,Map<Long,Boolean>> quartet:o.getSuitOptions())
                sum += (quartet.getValue3().get(dishId))?1:0;
        }
        return sum;
    }

    public int getSuitDishSum(Long dishId) {
        orders = getOrders();
        return getSuitDishSum(dishId,orders);
    }

    public int getSuitDishSum(Long dishId, Date beginDate, Date endDate) {
        orders = getOrders(beginDate,endDate);
        return getSuitDishSum(dishId,orders);
    }

    public int getAllSum(Long dishId) {
        orders = getOrders();
        return getSuitDishSum(dishId,orders)+getAddOnSum(dishId,orders);
    }

    public int getAllSum(Long dishId, Date beginDate, Date endDate) {
        orders = getOrders(beginDate,endDate);
        return getSuitDishSum(dishId,orders)+getAddOnSum(dishId,orders);
    }

    public Order getOrder(Long id){
        orders = getOrders();
        return orderMap.get(id);
    }

    //dish option sum
    private int getDishesOptionSum(Long dishId, String option, List<Order> orders) {
        int sum = 0;
        for (Order o:orders) {
            sum+= Collections.frequency(o.getDishOptions().values(),option) ;

            for(Quartet<Long,String,Map<Long,String>,Map<Long,Boolean>> quartet:o.getSuitOptions())
                sum += (quartet.getValue2().get(dishId).equals(option)) ? 1:0;
        }
        return sum;
    }

    public int getDishesOptionSum(Long dishId,String option) {
        orders = getOrders();
        return getDishesOptionSum(dishId,option,orders);
    }

    public int getDishesOptionSum(Long dishId, String option, Date beginDate, Date endDate) {
        orders = getOrders(beginDate,endDate);
        return getDishesOptionSum(dishId,option,orders);
    }

    //Suit Option sum
    private int getSuitOptionSum(Long suitId, String option, List<Order> orders) {
        int sum = 0;
        for (Order o:orders) {
            for(Quartet<Long,String,Map<Long,String>,Map<Long,Boolean>> quartet:o.getSuitOptions()) {
                sum += (quartet.getValue0().equals(suitId) && quartet.getValue1().equals(option)) ? 1 : 0;
            }
        }
        return sum;
    }

    public int getSuitOptionSum(Long suitId,String option) {
        orders = getOrders();
        return getSuitOptionSum(suitId,option,orders);
    }

    public int getSuitOptionSum(Long suitId, String option, Date beginDate, Date endDate) {
        orders = getOrders(beginDate,endDate);
        return getSuitOptionSum(suitId,option,orders);
    }

    //Dining Option sum
    private int getDiningOptionSum(String option, List<Order> orders) {
        int sum = 0;
        for (Order o:orders)
            sum += (o.getDiningOption().toString() .equals( option ))?1:0;
        return sum;
    }

    public int getDiningOptionSum(String option) {
        orders = getOrders();
        return getDiningOptionSum(option,orders);
    }

    public int getDiningOptionSum(String option, Date beginDate, Date endDate) {
        orders = getOrders(beginDate,endDate);
        return getDiningOptionSum(option,orders);
    }

    private int getDiningOptionSum(Constants.DiningOption option, List<Order> orders) {
        int sum = 0;
        for (Order o:orders)
            sum += (o.getDiningOption() ==  option )?1:0;
        return sum;
    }

    public int getDiningOptionSum(Constants.DiningOption option) {
        orders = getOrders();
        return getDiningOptionSum(option,orders);
    }

    public int getDiningOptionSum(Constants.DiningOption option, Date beginDate, Date endDate) {
        orders = getOrders(beginDate,endDate);
        return getDiningOptionSum(option,orders);
    }

}

