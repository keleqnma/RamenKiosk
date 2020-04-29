package com.ramen.order.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;

public class JsonFileUtil {
    private static JsonFileUtil jsonFileUtil;
    private static String path;
    private static BufferedReader reader;
    private static BufferedWriter writer;

    public static JsonFileUtil getJsonFileUtil() {
		if (jsonFileUtil == null) {
            jsonFileUtil = new JsonFileUtil();
            path ="src/com/ramen/order/util/orders.json";
            try{
                reader =  new BufferedReader(new FileReader(path));  
                writer = new BufferedWriter(new FileWriter(path,true));  
            }catch (final Exception e) {  
                e.printStackTrace();  
            }
		}
		return jsonFileUtil;
	}
    

    public List<String> readFromJson() {
        List<String> jsonStrings = new ArrayList<String>();  
        try {  
            String tempString = null;  
            while ((tempString = reader.readLine()) != null) {
                jsonStrings.add(tempString);  
            }  
            reader.close();  
        } catch (final IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (final IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return jsonStrings;  
    }
 
    public void writeToJson(final String data){  
        try {  
            writer.write(data+"\n");  
        } catch (final IOException e) {  
            e.printStackTrace();  
        }finally {  
            try {  
                if(writer != null){ 
                    writer.flush();   
                }  
            } catch (final IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  

}