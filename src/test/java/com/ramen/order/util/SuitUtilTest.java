package com.ramen.order.util;

import com.ramen.order.model.dish.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class SuitUtilTest {

    SuitUtil su;
    DishUtil du;

    @BeforeEach
    void init(){
        su = SuitUtil.getSuitUtil();
        du = DishUtil.getDishUtil();
        System.out.println("@Before: executedBeforeEach");
    }

    @Test
    void storeSuit() {
        Suit ramen = new Suit("ramen",9.99);

        ramen.setOptions("One","Two","Three","Four","Five");
        ramen.setFixDishIds(du.getDish("Soup").getId(),du.getDish("Noodle").getId(),du.getDish("SpringOnion").getId());
        ramen.setOptionDishIds(du.getDish("Nori").getId(),du.getDish("BoiledEgg").getId(),du.getDish("BambooShoot").getId(),du.getDish("Chashu").getId());

        System.out.print(ramen);
        su.storeSuit(ramen);
    }

    @Test
    void getSuits() {
        List<Suit> suits = su.getSuits();
        System.out.print(suits);
    }

    @Test
    void getSuit() {
        Suit suit = su.getSuit("ramen");
        suit = su.getSuit(suit.getId());
        System.out.println(suit);
        System.out.println(du.getDish(suit.getFixDishIds().get(0)));
    }
}