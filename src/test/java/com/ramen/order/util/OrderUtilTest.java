package com.ramen.order.util;

import com.ramen.order.Constants;
import com.ramen.order.model.Order;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class OrderUtilTest {
    OrderUtil ou;
    DishUtil du;
    SuitUtil su;

    @BeforeEach
    void init(){
        ou = OrderUtil.getOrderUtil();
        du = DishUtil.getDishUtil();
        su = SuitUtil.getSuitUtil();
    }

    @org.junit.jupiter.api.Test
    void storeOrder() {
        Order o = new Order((long)0);


        o.setDiningOption(Constants.DiningOption.EatingIn);
        //通过传回来的name获取id
        o.addDishOptions(du.getDish("Nori").getId(),"");
        o.addDishOptions(du.getDish("Chashu").getId(),"");

        o.addSuitIds(su.getSuit("ramen").getId());
        Map<Long,String> fixedDishOption = new HashMap<Long, String>();
        fixedDishOption.put(du.getDish("Soup").getId(),"Tonkotsu");
        fixedDishOption.put(du.getDish("Noodle").getId(),"Soft");
        fixedDishOption.put(du.getDish("SpringOnion").getId(),"NoPlease");

        Map<Long,Boolean> optionalOption = new HashMap<Long, Boolean>();
        optionalOption.put(du.getDish("Nori").getId(),true);
        optionalOption.put(du.getDish("BoiledEgg").getId(),true);
        optionalOption.put(du.getDish("BambooShoot").getId(),true);
        optionalOption.put(du.getDish("Chashu").getId(),true);

        o.addSuitOptions(su.getSuit("ramen").getId(),"One",fixedDishOption,optionalOption);
        o.getPrice();

        System.out.println(o);
        ou.storeOrder(o);
    }

    @org.junit.jupiter.api.Test
    void getOrders() {
        List<Order> os = ou.getOrders();
        for(Order o:os)
            System.out.println(o);
    }

    @org.junit.jupiter.api.Test
    void getDishSum() {

    }
}