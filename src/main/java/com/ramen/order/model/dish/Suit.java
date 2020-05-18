package com.ramen.order.model.dish;
import com.ramen.order.util.IDUtil;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class Suit{
    private Long id;
    private double price;
    private String name;

    //fixed dishes
    private List<Long> fixDishIds;

    //optional dishes
    private List<Long> optionDishIds;

    private List<String> options;

    public void setOptions(String ... options){
        this.options = (Arrays.asList(options));
    }

    public void setFixDishIds(Long... ids){
        this.fixDishIds = (Arrays.asList(ids));
    }

    public void setOptionDishIds(Long... ids){
        this.optionDishIds = (Arrays.asList(ids));
    }

    public Suit(String name,double price){
        setId(IDUtil.createID());
        setName(name);
        setPrice(price);
    }

    public Suit(Long id,String name,Double price,String[] options,Long[] fixDishIds,Long[] optionDishIds){
        setId(id);
        setName(name);
        setPrice(price);
        setOptions(options);
        setFixDishIds(fixDishIds);
        setOptionDishIds(optionDishIds);
    }
}