package com.ramen.order.util;

import com.alibaba.fastjson.JSON;
import com.ramen.order.Constants;
import com.ramen.order.model.dish.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DishUtil {
    private static DishUtil dishUtil;
    private static JsonFileUtil jsonFileUtil;

    private List<Dish> dishes;
    private Map<Long, Dish> dishIdMap;
    private Map<String, Dish> dishNameMap;

    public static DishUtil getDishUtil() {
        if (dishUtil == null) {
            dishUtil = new DishUtil();
            jsonFileUtil = JsonFileUtil.getJsonFileUtil(Constants.JsonUtil.Dish);
        }
        return dishUtil;
    }

    public void storeDish(Dish dish) {
        dishes = getDishes();
        if(!dishNameMap.containsKey(dish.getName())) {
            String jsonString = JSON.toJSONString(dish);
            jsonFileUtil.writeToJson(Constants.JsonUtil.Dish,jsonString);

            dishes.add(dish);
            dishIdMap.put(dish.getId(), dish);
            dishNameMap.put(dish.getName(), dish);
        }
    }

    public List<Dish> getDishes() {
        if (dishes == null) {
            List<String> jsonStrings = jsonFileUtil.readFromJson(Constants.JsonUtil.Dish);
            dishes = new ArrayList<Dish>();
            for (int i = 0; i < jsonStrings.size(); i++) {
                dishes.add(JSON.parseObject(jsonStrings.get(i), Dish.class));
            }
            dishIdMap = dishes.stream().collect(Collectors.toMap(Dish::getId, dish->dish));
            dishNameMap = dishes.stream().collect(Collectors.toMap(Dish::getName, dish->dish));
        }
        return dishes;
    }

    public Dish getDish(Long id){
        dishes = getDishes();
        return dishIdMap.get(id);
    }

    public Dish getDish(String name){
        dishes = getDishes();
        return dishNameMap.get(name);
    }

}
