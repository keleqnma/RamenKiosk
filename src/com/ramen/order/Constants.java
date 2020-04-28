package com.ramen.order;
//测试用包名，后续可以更改


public interface Constants{
    double PACKAGE_PRICE = 9.99;
    double EXTRA_NORI_PRICE = 1;
    double EXTRA_BOILEDEGG_PRICE = 1;
    double EXTRA_BAMBOOSHOOT_PRICE = 1;
    double EXTRA_CHASHU_PRICE = 2; 
    
    public enum Soup{
        Tonkotsu,Shoyu,Shio  
    }

    public enum Noodles{
        Soft,Medium,Firm 
    }

    public enum SpringOnion{
        NoPlease,JustALittle,ALot
    } 

    public enum AddOn{YES,NO}

    public enum Spiciness{
        One,Two,Three,Four,Five
    }

    public enum DiningOption{
        EatingIn,TakeAway
    } 
}