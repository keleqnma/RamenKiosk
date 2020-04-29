package com.ramen.order.util;

import com.alibaba.fastjson.JSON;
import com.ramen.order.Constants;
import com.ramen.order.model.dish.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SuitUtil {
    private static SuitUtil suitUtil;
    private static JsonFileUtil jsonFileUtil;

    private List<Suit> suits;
    private Map<Long,Suit> suitIdMap;
    private Map<String,Suit> suitNameMap;

    public static SuitUtil getSuitUtil() {
        if (suitUtil == null) {
            suitUtil = new SuitUtil();
            jsonFileUtil = JsonFileUtil.getJsonFileUtil(Constants.JsonUtil.Suit);
        }
        return suitUtil;
    }

    public void storeSuit(Suit suit) {
        suits = getSuits();
        if(!suitNameMap.containsKey(suit.getName())) {
            String jsonString = JSON.toJSONString(suit);
            jsonFileUtil.writeToJson(Constants.JsonUtil.Suit, jsonString);

            suits.add(suit);
            suitIdMap.put(suit.getId(), suit);
            suitNameMap.put(suit.getName(), suit);
        }
    }

    public List<Suit> getSuits() {
        if (suits == null) {
            List<String> jsonStrings = jsonFileUtil.readFromJson(Constants.JsonUtil.Suit);
            suits = new ArrayList<Suit>();
            for (int i = 0; i < jsonStrings.size(); i++) {
                suits.add(JSON.parseObject(jsonStrings.get(i), Suit.class));
            }
            suitIdMap = suits.stream().collect(Collectors.toMap(Suit::getId, suit->suit));
            suitNameMap = suits.stream().collect(Collectors.toMap(Suit::getName, suit->suit));
        }
        return suits;
    }

    public Suit getSuit(Long id){
        suits = getSuits();
        return suitIdMap.get(id);
    }

    public Suit getSuit(String name){
        suits = getSuits();
        return suitNameMap.get(name);
    }
}
