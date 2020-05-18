package com.ramen.order.model;

import com.ramen.order.Constants;
import com.ramen.order.util.DishUtil;
import com.ramen.order.util.IDUtil;
import com.ramen.order.util.SuitUtil;
import lombok.Data;
import org.javatuples.Quartet;

import java.util.*;

@Data
public class Order{
    private Long id;
    private Long memberId;//example
    private double price;
    private Date issueTime;
    private Constants.DiningOption diningOption;

    private List<Long> suitIds;
    private Map<Long,String> dishOptions;
    //suit id, suit option, fixed dish option,optional dishes option
    private List<Quartet<Long,String,Map<Long,String>,Map<Long,Boolean>>> suitOptions;

    private void setId(Long id){
        this.id = id;
    }
    private void setPrice(double price){this.price = price;}

    public double getPrice(){
        double price = 0 ;

        DishUtil dishUtil = DishUtil.getDishUtil();
        for(Long id:dishOptions.keySet())
            price += dishUtil.getDish(id).getPrice();

        SuitUtil suitUtil = SuitUtil.getSuitUtil();
        for(Long id:suitIds)
            price += suitUtil.getSuit(id).getPrice();

        this.setPrice(price);
        return this.price;
    }


    public void addDishOptions(Long id,String option){
        dishOptions.put(id,option);
    }

    public void addSuitIds(Long id){
        suitIds.add(id);
    }

    public void addSuitOptions(Long id,String option,Map<Long,String> fixedOption, Map<Long, Boolean> optionaloption){
        Quartet<Long,String,Map<Long,String>,Map<Long,Boolean>> suitOption = Quartet.with(id,option,fixedOption,optionaloption);
        suitOptions.add(suitOption);
    }


    public Order(Long memberId){
        setMemberId(memberId);
        setDishOptions(new HashMap<>());
        setId(IDUtil.createID());
        setIssueTime(new Date());
        setSuitIds(new ArrayList<>());
        setSuitOptions(new ArrayList<>());
    }

    public Order(Long id,Long memberId,double price,Date issueTime,Constants.DiningOption diningOption,List<Long> suitIds,Map<Long,String> dishOptions,List<Quartet<Long,String,Map<Long,String>,Map<Long,Boolean>>> suitOptions){
        setId(id);
        setPrice(price);
        setDishOptions(dishOptions);
        setIssueTime(issueTime);
        setMemberId(memberId);
        setSuitIds(suitIds);
        setSuitOptions(suitOptions);
        setDiningOption(diningOption);
    }

}

