package org.springframework.boot.spring_boot_starter_parent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonObject {
    public static void main(String[] args)
    {
    	Date date6=null;
    	String str="2019-12-19T15:29:39-0500";
    	String[] dateSplit1=str.split("T");
    	System.out.println(dateSplit1[1].split("-")[0]);
    	String dateString=dateSplit1[0]+" "+dateSplit1[1].split("-")[0];
    	SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	 try {
			date6=formatter6.parse("2020-08-17 12:44:02");
			System.out.println(date6);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
    	 
    	Date today=Calendar.getInstance().getTime();
    	Calendar todayCal=Calendar.getInstance();
    	todayCal.setTime(today);
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(date6);
    	System.out.println(today);
    	if(isSameDay(todayCal,cal))
    	{
    		System.out.println("equals");
    	}
    	 
    	JSONObject jsonObject = new JSONObject();
    	JSONObject jsonObject1 = new JSONObject();
    	try {
			jsonObject.put("JSON1", "Hello World!");
			jsonObject1.put("JSON2", "Hello my World!");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
       
    	JSONArray ja = new JSONArray();
    	ja.put(jsonObject);
    	ja.put(jsonObject1);
    	System.out.println("che:"+ja);
    }
    
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
}
