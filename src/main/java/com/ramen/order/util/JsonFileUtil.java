package com.ramen.order.util;


import com.ramen.order.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;

public class JsonFileUtil {
    private static List<JsonFileUtil> jsonFileUtils;
    private static List<String> paths;
    private static List<BufferedReader> readers;
    private static List<BufferedWriter> writers;

    public static JsonFileUtil getJsonFileUtil(Constants.JsonUtil type) {
        if (jsonFileUtils == null) {
            paths = new ArrayList<String>();
            jsonFileUtils = new ArrayList<JsonFileUtil>();
            readers = new ArrayList<BufferedReader>();
            writers = new ArrayList<BufferedWriter>();
            paths.add("src/main/java/com/ramen/order/util/dishes.json");
            paths.add("src/main/java/com/ramen/order/util/suits.json");
            paths.add("src/main/java/com/ramen/order/util/orders.json");
            for(int i = 0;i<3;i++) {
                jsonFileUtils.add(new JsonFileUtil());
                try {
                    readers.add(new BufferedReader(new FileReader(paths.get(i))));
                    writers.add(new BufferedWriter(new FileWriter(paths.get(i), true)));
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        }
		return jsonFileUtils.get(type.ordinal());
	}
    

    public List<String> readFromJson(Constants.JsonUtil type) {
        List<String> jsonStrings = new ArrayList<String>();  
        try {  
            String tempString = null;  
            while ((tempString = readers.get(type.ordinal()).readLine()) != null) {
                jsonStrings.add(tempString);  
            }  
            readers.get(type.ordinal()).close();
        } catch (final IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (readers != null) {
                try {  
                    readers.get(type.ordinal()).close();
                } catch (final IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return jsonStrings;  
    }
 
    public void writeToJson(Constants.JsonUtil type,final String data){
        try {  
            writers.get(type.ordinal()).write(data+"\n");
        } catch (final IOException e) {  
            e.printStackTrace();  
        }finally {  
            try {  
                if(writers != null){
                    writers.get(type.ordinal()).flush();
                }  
            } catch (final IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  

}