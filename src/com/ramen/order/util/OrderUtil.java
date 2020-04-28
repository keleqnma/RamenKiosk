package com.ramen.order.util;

import com.alibaba.fastjson.JSON;
import com.ramen.order.model.Order;
import com.ramen.order.Constants; 
import java.util.*;

public class OrderUtil{
    private static OrderUtil orderUtil;
    private static JsonFileUtil jsonFileUtil;
    private List<Order> orders;

    public static OrderUtil getOrderUtil(){
		if (orderUtil == null) {
            orderUtil = new OrderUtil();
            jsonFileUtil = JsonFileUtil.getJsonFileUtil(); 
		}
		return orderUtil;
	}
    public void storeOrder(Order order){
        String jsonString = JSON.toJSONString(order);
        jsonFileUtil.writeToJson(jsonString);
        orders = getOrders();
        orders.add(order);
    }
    public List<Order> getOrders(){
        if(orders == null){
            List<String> jsonStrings = jsonFileUtil.readFromJson();
            orders = new ArrayList<Order>();
            for(int i = 0;i<jsonStrings.size();i++){
                orders.add(JSON.parseObject(jsonStrings.get(i),Order.class));
            }
        }
        return orders;
    }
    
    public int getSoupSum(Constants.Soup soup){
        List<Order> orders = getOrders();
        int sum = 0;
        for(int i = 0;i<orders.size();i++){
            if(orders.get(i).getSoup()==soup) ++sum;
        }
        return sum;
    }

    public int getNoodleSum(Constants.Noodles noodle){
        List<Order> orders = getOrders();
        int sum = 0;
        for(int i = 0;i<orders.size();i++){
            if(orders.get(i).getNoodles()==noodle) ++sum;
        }
        return sum;
    }

    public int getOnionSum(Constants.SpringOnion onion){
        List<Order> orders = getOrders();
        int sum = 0;
        for(int i = 0;i<orders.size();i++){
            if(orders.get(i).getSpringOnion()==onion) ++sum;
        }
        return sum;
    }

    public int getNoriSum(){
        List<Order> orders = getOrders();
        int sum = 0;
        for(int i = 0;i<orders.size();i++){
            if(orders.get(i).getNori()==Constants.AddOn.YES) ++sum;
        }
        return sum;
    }

    public int getChashuSum(){
        List<Order> orders = getOrders();
        int sum = 0;
        for(int i = 0;i<orders.size();i++){
            if(orders.get(i).getChashu()==Constants.AddOn.YES) ++sum;
        }
        return sum;
    }

    public int getBoiledEggSum(){
        List<Order> orders = getOrders();
        int sum = 0;
        for(int i = 0;i<orders.size();i++){
            if(orders.get(i).getBoiledEgg()==Constants.AddOn.YES) ++sum;
        }
        return sum;
    }

    public int getSpicinessSum(Constants.Spiciness spiciness){
        List<Order> orders = getOrders();
        int sum = 0;
        for(int i = 0;i<orders.size();i++){
            if(orders.get(i).getSpiciness()==spiciness) ++sum;
        }
        return sum;
    }

    public int getExtraNoriSum(){
        List<Order> orders = getOrders();
        int sum = 0;
        for(int i = 0;i<orders.size();i++){
            sum+=orders.get(i).getExtraNori();
        }
        return sum;
    }

    public int getExtraChashuSum(){
        List<Order> orders = getOrders();
        int sum = 0;
        for(int i = 0;i<orders.size();i++){
            sum+=orders.get(i).getExtraChashu();
        }
        return sum;
    }

    public int getExtraBoiledEggSum(){
        List<Order> orders = getOrders();
        int sum = 0;
        for(int i = 0;i<orders.size();i++){
            sum+=orders.get(i).getExtraBoiledEgg();
        }
        return sum;
    }

    public int getExtraBambooShootsSum(){
        List<Order> orders = getOrders();
        int sum = 0;
        for(int i = 0;i<orders.size();i++){
            sum+=orders.get(i).getExtraBambooShoots();
        }
        return sum;
    }

    public int getDiningSum(Constants.DiningOption diningOption){
        List<Order> orders = getOrders();
        int sum = 0;
        for(int i = 0;i<orders.size();i++){
            if(orders.get(i).getDiningOption()==diningOption) ++sum;
        }
        return sum;
    }
    
}

class testOrderUtil{
    public static void main(String[] args)
    {
        OrderUtil ou = OrderUtil.getOrderUtil();
        /*
        Order o = new Order(Constants.Soup.Shio,Constants.Noodles.Firm,Constants.SpringOnion.ALot,Constants.AddOn.NO,Constants.AddOn.NO,Constants.AddOn.NO,Constants.Spiciness.Five,2,2,2,2,Constants.DiningOption.EatingIn);
        ou.storeOrder(o);
        o = new Order(Constants.Soup.Tonkotsu,Constants.Noodles.Firm,Constants.SpringOnion.ALot,Constants.AddOn.NO,Constants.AddOn.NO,Constants.AddOn.NO,Constants.Spiciness.Four,2,2,2,2,Constants.DiningOption.EatingIn);
        ou.storeOrder(o);
        */
        List<Order> os = ou.getOrders();
        System.out.println(os);
        int n = ou.getSoupSum(Constants.Soup.Tonkotsu);
        System.out.println(n);
    }
}
