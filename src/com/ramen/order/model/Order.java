package com.ramen.order.model;

import com.ramen.order.Constants; 

public class Order {
    private Constants.Soup soup;
    private Constants.Noodles noodles;
    private Constants.SpringOnion springOnion;
    
    private Constants.AddOn nori;
    private Constants.AddOn chashu;
    private Constants.AddOn boiledEgg;
    private Constants.Spiciness spiciness;

    private int extraNori;
    private int extraChashu;
    private int extraBoiledEgg;
    private int extraBambooShoots;
    private Constants.DiningOption diningOption;
    private double orderPrice;

    public Order(){

    }

    public Order(Constants.Soup soup, Constants.Noodles noodles, Constants.SpringOnion springOnion, Constants.AddOn nori, Constants.AddOn chashu, Constants.AddOn boiledEgg, Constants.Spiciness spiciness, int extraNori, int extraChashu, int extraBoiledEgg, int extraBambooShoots, Constants.DiningOption diningOption) {
        this.soup = soup;
        this.noodles = noodles;
        this.springOnion = springOnion;
        this.nori = nori;
        this.chashu = chashu;
        this.boiledEgg = boiledEgg;
        this.spiciness = spiciness;
        this.extraNori = extraNori;
        this.extraChashu = extraChashu;
        this.extraBoiledEgg = extraBoiledEgg;
        this.extraBambooShoots = extraBambooShoots;
        this.diningOption = diningOption;
        this.orderPrice = Constants.PACKAGE_PRICE + extraNori*Constants.EXTRA_NORI_PRICE + extraChashu*Constants.EXTRA_CHASHU_PRICE + extraBoiledEgg*Constants.EXTRA_BOILEDEGG_PRICE + extraBambooShoots*Constants.EXTRA_BAMBOOSHOOT_PRICE;
    }

    public Constants.Soup getSoup() {
        return this.soup;
    }

    public void setSoup(Constants.Soup soup) {
        this.soup = soup;
    }

    public Constants.Noodles getNoodles() {
        return this.noodles;
    }

    public void setNoodles(Constants.Noodles noodles) {
        this.noodles = noodles;
    }

    public Constants.SpringOnion getSpringOnion() {
        return this.springOnion;
    }

    public void setSpringOnion(Constants.SpringOnion springOnion) {
        this.springOnion = springOnion;
    }

    public Constants.AddOn getNori() {
        return this.nori;
    }

    public void setNori(Constants.AddOn nori) {
        this.nori = nori;
    }

    public Constants.AddOn getChashu() {
        return this.chashu;
    }

    public void setChashu(Constants.AddOn chashu) {
        this.chashu = chashu;
    }

    public Constants.AddOn getBoiledEgg() {
        return this.boiledEgg;
    }

    public void setBoiledEgg(Constants.AddOn boiledEgg) {
        this.boiledEgg = boiledEgg;
    }

    public Constants.Spiciness getSpiciness() {
        return this.spiciness;
    }

    public void setSpiciness(Constants.Spiciness spiciness) {
        this.spiciness = spiciness;
    }

    public int getExtraNori() {
        return this.extraNori;
    }

    public void setExtraNori(int extraNori) {
        this.extraNori = extraNori;
    }

    public int getExtraChashu() {
        return this.extraChashu;
    }

    public void setExtraChashu(int extraChashu) {
        this.extraChashu = extraChashu;
    }

    public int getExtraBoiledEgg() {
        return this.extraBoiledEgg;
    }

    public void setExtraBoiledEgg(int extraBoiledEgg) {
        this.extraBoiledEgg = extraBoiledEgg;
    }

    public int getExtraBambooShoots() {
        return this.extraBambooShoots;
    }

    public void setExtraBambooShoots(int extraBambooShoots) {
        this.extraBambooShoots = extraBambooShoots;
    }

    public Constants.DiningOption getDiningOption() {
        return this.diningOption;
    }

    public void setDiningOption(Constants.DiningOption diningOption) {
        this.diningOption = diningOption;
    }

    public double getOrderPrice() {
        return this.orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "{" +
            " soup='" + getSoup() + "'" +
            ", noodles='" + getNoodles() + "'" +
            ", springOnion='" + getSpringOnion() + "'" +
            ", nori='" + getNori() + "'" +
            ", chashu='" + getChashu() + "'" +
            ", boiledEgg='" + getBoiledEgg() + "'" +
            ", spiciness='" + getSpiciness() + "'" +
            ", extraNori='" + getExtraNori() + "'" +
            ", extraChashu='" + getExtraChashu() + "'" +
            ", extraBoiledEgg='" + getExtraBoiledEgg() + "'" +
            ", extraBambooShoots='" + getExtraBambooShoots() + "'" +
            ", diningOption='" + getDiningOption() + "'" +
            ", orderPrice='" + getOrderPrice() + "'" +
            "}";
    }


}

/*
class testOrder{
    public static void main(String[] args)
    {
        Order o = new Order(Constants.Soup.Shio,Constants.Noodles.Firm,Constants.SpringOnion.ALot,Constants.AddOn.NO,Constants.AddOn.NO,Constants.AddOn.NO,Constants.Spiciness.One,2,2,2,2,Constants.DiningOption.EatingIn);
        System.out.println(o);
    }
}
*/
