package org.springframework.boot.spring_boot_starter_parent;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import sawtooth.sdk.protobuf.Batch;
import sawtooth.sdk.protobuf.BatchHeader;
import sawtooth.sdk.protobuf.BatchList;
import sawtooth.sdk.protobuf.Transaction;
import sawtooth.sdk.protobuf.TransactionHeader;
import sawtooth.sdk.protobuf.TransactionList;
import sawtooth.sdk.signing.PrivateKey;
import sawtooth.sdk.signing.Secp256k1Context;
import sawtooth.sdk.signing.Signer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


import com.google.protobuf.ByteString;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.spring_boot_starter_parent.Product;
import org.springframework.boot.spring_boot_starter_parent.SendData;
@RestController
public class ProductServiceController {
   private static Map<String, Product> productRepo = new HashMap<>();
   static {
      Product honey = new Product();
      honey.setId("1");
      honey.setName("Honey");
      productRepo.put(honey.getId(), honey);
      
      Product almond = new Product();
      almond.setId("2");
      almond.setName("Almond");
      productRepo.put(almond.getId(), almond);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
      productRepo.remove(id);
      return new ResponseEntity("Product is deleted successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
      productRepo.remove(id);
      product.setId(id);
      productRepo.put(id, product);
      return new ResponseEntity("Product is updated successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/RastroGW/gateway/setup", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody String jsonString) {
     String payloadString="";
	   try {
  		 //JSONArray array = new JSONArray(jsonString);
      	 
      	 JSONObject object = new JSONObject(jsonString);
      	 
			//String s=object.get("sensorTags").toString();
			
			//System.out.println(s);
			String deviceID=object.get("deviceId").toString();
			String gatewayID=object.get("gatewayId").toString();
			String readingInterval=object.get("readingInterval").toString();
			String time=(object.get("time").toString());
			String locationName=object.get("locationName").toString();
			JSONArray arr=object.getJSONArray("sensorTags");
			String strObj = arr.getJSONObject(0).toString();
			JSONObject obj = new JSONObject(strObj);
			String zone=obj.get("zone").toString();
			String temperature=obj.get("temperature").toString();
			String humidity=obj.get("humidity").toString();
			
			payloadString=deviceID+";"+gatewayID+";"+readingInterval+";"+time+";"+locationName+";"+
			                 zone+";"+temperature+";"+humidity;
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
      System.out.println("Payload:"+payloadString);
       SendData test=new SendData();
	   String response=test.encode(payloadString);
	   System.out.println("After Response:");
      return new ResponseEntity(response, HttpStatus.CREATED);
   }
   
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity(productRepo.values(), HttpStatus.OK);
   }
   
   
   
   @RequestMapping(value = "/api/DeviceReadingTransaction",method = RequestMethod.GET,
			produces = { "application/json" })
  @ResponseBody
  public ResponseEntity<Object> getDeviceHistory(@RequestParam(required = false) String deviceName) {
	
	   FetchData fetchdata=new FetchData();
	   String data="";
	   try {
		data=fetchdata.getData(deviceName);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   System.out.println("check:"+data);
	   String[] dataSplits=data.split(",");
	   List<Map<String,String>> list=new ArrayList<>();
	   Map<String,String> jsonObject1 = new HashMap<String,String>();
	   Map<String,String> jsonObject2 = new HashMap<String,String>();
	   
	   for(String singleData:dataSplits)
	   {
		   
		   String[] splitData=singleData.split(";");
		   
		   Map<String,String> jsonObject = new HashMap<>();
		   jsonObject.put("deviceAsset", splitData[0]);
	       
		   jsonObject.put("newGatewayId", splitData[1]);
	       
		   jsonObject.put("newReadingId", splitData[2]);
	       
		   jsonObject.put("newLocationId", splitData[3]);
	      
		   jsonObject.put("newZone", splitData[5]);
	      
		   jsonObject.put("newTemperature", splitData[6]);
	       
		   jsonObject.put("newHumidity", splitData[7]);
	       
		   jsonObject.put("transactionID", splitData[8]);
		   
		   jsonObject.put("timeStamp", splitData[4]);
		   list.add(jsonObject);
		   
		  
		  }
	 
	   return new ResponseEntity(list, HttpStatus.OK);
  }
   

   
   @RequestMapping(value = "/api/DeviceAsset/{deviceID}",method = RequestMethod.GET,
			produces = { "application/json" })
   @ResponseBody
   public ResponseEntity<Object> getDeviceData(@PathVariable("deviceID") String deviceID,@RequestParam(required = false) String startDate,@RequestParam(required = false) String endDate) {
	   //String s=(@PathVariable("deviceID") String deviceID;
	   System.out.println("startDate:"+startDate);
	   System.out.println("endDate:"+endDate);
	   
	   Date fromDate=sanitizeDate(startDate);
	   Date toDate=sanitizeDate(endDate);
	  
	   FetchData fetchdata=new FetchData();
	   String data="";
	   try {
		data=fetchdata.getData(deviceID);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   System.out.println("check:"+data);
	   String[] dataSplits=data.split(",");
	   List<Map<String,String>> list=new ArrayList<>();
	   Map<String,String> jsonObject1 = new HashMap<String,String>();
	   Map<String,String> jsonObject2 = new HashMap<String,String>();
	   JSONArray ja = new JSONArray();
	   List<String> strlist=new ArrayList<>();
	   System.out.println("checkpoint1");
	   if((fromDate!=null)&&(toDate!=null))
	   {
	   for(String singleData:dataSplits)
	   {
		   String[] splitData=singleData.split(";");
		   Date deviceDate=sanitizeDate(splitData[3]);
		   if((deviceDate.compareTo(fromDate)>=0)&&(deviceDate.compareTo(toDate)<=0))
		   {
			   strlist.add(singleData);
		   }
		   System.out.println("checkpoint2a");
	   }
	   }
	   else
	   {
		   System.out.println("checkpoint2b");
		   Calendar calToday=Calendar.getInstance();
		   calToday.setTime(Calendar.getInstance().getTime());
		   System.out.println("checkpoint2c");
		   for(String singleData:dataSplits)
		   {
			   System.out.println("checkpoint2l1");
			   String[] splitData=singleData.split(";");
			   System.out.println("checkpoint2l2:"+splitData.length);
			   Calendar deviceDate=sanitizeDay(splitData[3]);
			   System.out.println("sanitizedayAfter");
			   if((isSameDay(calToday,deviceDate)))
			   {
				   System.out.println("isSameDay in");
				   strlist.add(singleData);
			   }
			   System.out.println("checkpoint2c");
			   
		   }
		   System.out.println("checkpoint2d");
		   
	   }
	   
	   for(String singleData:strlist)
	   {
		   
		   String[] splitData=singleData.split(";");
		   
		   Map<String,String> jsonObject = new HashMap<>();
jsonObject.put("deviceAsset", splitData[0]);
	       
		   jsonObject.put("newGatewayId", splitData[1]);
	       
		   jsonObject.put("newReadingId", splitData[2]);
	       
		   jsonObject.put("newLocationId", splitData[3]);
	      
		   jsonObject.put("newZone", splitData[5]);
	      
		   jsonObject.put("newTemperature", splitData[6]);
	       
		   jsonObject.put("newHumidity", splitData[7]);
	       
		   jsonObject.put("transactionID", splitData[8]);
		   
		   jsonObject.put("timeStamp", splitData[4]);
		   list.add(jsonObject);
		   
		  }
	 
      return new ResponseEntity(list, HttpStatus.OK);
   }
   
   public Date sanitizeDate(String strDate)
   {
	   Date date=null;
	   if(strDate!=null)
	   {
	String[] dateSplit1=strDate.split("T");
   	System.out.println(dateSplit1[1].split("-")[0]);
   	String dateString=dateSplit1[0]+" "+dateSplit1[1].split("-")[0];
   	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
   	
   	 try {
			 date=formatter.parse(dateString);
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	   }
   	 return date;
   }
   
   public Calendar sanitizeDay(String strDate)
   {
	   Date date=null;
	   if(strDate!=null)
	   {
	String[] dateSplit1=strDate.split("T");
   
   	String dateString=dateSplit1[0];
   	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd"); 
   	
   	 try {
			 date=formatter.parse(dateString);
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	   }
	 Calendar cal=Calendar.getInstance();
	 cal.setTime(date);
   	 return cal;
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
