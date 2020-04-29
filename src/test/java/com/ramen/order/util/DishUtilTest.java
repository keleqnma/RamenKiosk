package com.ramen.order.util;

import com.ramen.order.model.dish.Dish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class DishUtilTest {
    DishUtil du;

    @BeforeEach
    void init(){
        du = DishUtil.getDishUtil();
        System.out.println("@Before: executedBeforeEach");
    }

    @Test
    void storeDish() {
        Dish soup = new Dish("Soup","Tonkotsu","Shoyu","Shio");
        Dish noodle = new Dish("Noodle","Soft","Medium","Firm");
        Dish springOnion = new Dish("SpringOnion","NoPlease","JustALittle","ALot");

        Dish nori = new Dish("Nori",1);
        Dish boiledEgg = new Dish("BoiledEgg",1);
        Dish bambooShoot = new Dish("BambooShoot",1);
        Dish chashu = new Dish("Chashu",2);

        du.storeDish(soup);
        du.storeDish(noodle);
        du.storeDish(springOnion);
        du.storeDish(nori);
        du.storeDish(boiledEgg);
        du.storeDish(bambooShoot);
        du.storeDish(chashu);

    }

    @Test
    void getDishes() {
        List<Dish> dishes = du.getDishes();
        System.out.print(dishes);
    }

    @Test
    void getDish() {
        Dish dish = du.getDish("Nori");
        dish = du.getDish(dish.getId());
    }
}