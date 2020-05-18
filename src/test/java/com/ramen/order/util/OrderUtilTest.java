package com.ramen.order.util;

import com.ramen.order.Constants;
import com.ramen.order.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Map<Long,String> fixedDishOption = new HashMap<>();
        fixedDishOption.put(du.getDish("Soup").getId(),"Tonkotsu");
        fixedDishOption.put(du.getDish("Noodle").getId(),"Soft");
        fixedDishOption.put(du.getDish("SpringOnion").getId(),"NoPlease");

        Map<Long,Boolean> optionalOption = new HashMap<>();
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
        for(Order o:os) {
            System.out.println(o);
            System.out.println(o.getDiningOption());
            String option = o.getSuitOptions().get(0).getValue2().get(du.getDish("Soup").getId());
            System.out.println(option);
            System.out.println(option == "Tonkotsu");
            System.out.println(option.equals("Tonkotsu"));
        }
    }

    @Test
    void getAddOnSum() {
        System.out.println(ou.getAddOnSum(du.getDish("Nori").getId()));
        System.out.println(ou.getAddOnSum(du.getDish("Chashu").getId()));
        System.out.println(ou.getAddOnSum(du.getDish("BoiledEgg").getId()));
        System.out.println(ou.getAddOnSum(du.getDish("BambooShoot").getId()));
        System.out.println(ou.getAddOnSum(du.getDish("Chashu").getId()));
    }

    @Test
    void getSuitDishSum() {
        System.out.println(ou.getSuitDishSum(du.getDish("Nori").getId()));
        System.out.println(ou.getSuitDishSum(du.getDish("Chashu").getId()));
        System.out.println(ou.getSuitDishSum(du.getDish("BoiledEgg").getId()));
        System.out.println(ou.getSuitDishSum(du.getDish("BambooShoot").getId()));
        System.out.println(ou.getSuitDishSum(du.getDish("Chashu").getId()));
    }

    @Test
    void getAllSum() {
        System.out.println(ou.getAllSum(du.getDish("Nori").getId()));
        System.out.println(ou.getAllSum(du.getDish("Chashu").getId()));
        System.out.println(ou.getAllSum(du.getDish("BoiledEgg").getId()));
        System.out.println(ou.getAllSum(du.getDish("BambooShoot").getId()));
        System.out.println(ou.getAllSum(du.getDish("Chashu").getId()));
    }

    @Test
    void getDishesOptionSum() {
        System.out.println(ou.getDishesOptionSum(du.getDish("Soup").getId(),"Tonkotsu"));
        System.out.println(ou.getDishesOptionSum(du.getDish("Noodle").getId(),"Soft"));
        System.out.println(ou.getDishesOptionSum(du.getDish("SpringOnion").getId(),"NoPlease"));
        System.out.println(ou.getDishesOptionSum(du.getDish("Soup").getId(),"Shoyu"));
        System.out.println(ou.getDishesOptionSum(du.getDish("Soup").getId(),"Shio"));
    }

    @Test
    void getSuitOptionSum() {
        System.out.println(ou.getSuitOptionSum(su.getSuit("ramen").getId(),"One"));
        System.out.println(ou.getSuitOptionSum(su.getSuit("ramen").getId(),"Two"));
        System.out.println(ou.getSuitOptionSum(su.getSuit("ramen").getId(),"Three"));
        System.out.println(ou.getSuitOptionSum(su.getSuit("ramen").getId(),"Four"));
        System.out.println(ou.getSuitOptionSum(su.getSuit("ramen").getId(),"Five"));
    }

    @Test
    void getDiningOptionSum() {
        System.out.println(ou.getDiningOptionSum("EatingIn"));
        System.out.println(ou.getDiningOptionSum("TakeAway"));
        System.out.println(ou.getDiningOptionSum(Constants.DiningOption.EatingIn));
        System.out.println(ou.getDiningOptionSum(Constants.DiningOption.TakeAway));
    }
}