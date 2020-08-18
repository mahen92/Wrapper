package org.springframework.boot.spring_boot_starter_parent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class JsonTest {
    static String jsonString="{\r\n" + 
    		"  \"sensorTags\": [\r\n" + 
    		"    {\r\n" + 
    		"      \"accelX\": 0.878,\r\n" + 
    		"      \"accelY\": 0.403,\r\n" + 
    		"      \"accelZ\": -0.219,\r\n" + 
    		"      \"alertSettings\": {\r\n" + 
    		"        \"alerts\": [\r\n" + 
    		"          {\r\n" + 
    		"            \"alertType\": \"temperature\",\r\n" + 
    		"            \"enabled\": true,\r\n" + 
    		"            \"maxValue\": 7,\r\n" + 
    		"            \"minValue\": -16,\r\n" + 
    		"            \"readValue\": 6,\r\n" + 
    		"            \"setValue\": -10\r\n" + 
    		"          },\r\n" + 
    		"          {\r\n" + 
    		"            \"alertType\": \"temperature\",\r\n" + 
    		"            \"enabled\": false,\r\n" + 
    		"            \"maxValue\": 42,\r\n" + 
    		"            \"minValue\": 17,\r\n" + 
    		"            \"readValue\": 6,\r\n" + 
    		"            \"setValue\": 32\r\n" + 
    		"          },\r\n" + 
    		"          {\r\n" + 
    		"            \"alertType\": \"humidity\",\r\n" + 
    		"            \"enabled\": false,\r\n" + 
    		"            \"maxValue\": 75,\r\n" + 
    		"            \"minValue\": 35,\r\n" + 
    		"            \"readValue\": 30,\r\n" + 
    		"            \"setValue\": 50\r\n" + 
    		"          },\r\n" + 
    		"          {\r\n" + 
    		"            \"alertType\": \"pressure\",\r\n" + 
    		"            \"enabled\": false,\r\n" + 
    		"            \"maxValue\": 1050,\r\n" + 
    		"            \"minValue\": 1000,\r\n" + 
    		"            \"readValue\": 994.32,\r\n" + 
    		"            \"setValue\": 950\r\n" + 
    		"          }\r\n" + 
    		"        ]\r\n" + 
    		"      },\r\n" + 
    		"      \"dataFormat\": 3,\r\n" + 
    		"      \"defaultBackground\": 7,\r\n" + 
    		"      \"favorite\": true,\r\n" + 
    		"      \"humidity\": 30,\r\n" + 
    		"      \"id\": \"F0:A7:07:36:A3:92\",\r\n" + 
    		"      \"measurementSequenceNumber\": 0,\r\n" + 
    		"      \"movementCounter\": 0,\r\n" + 
    		"      \"pressure\": 994.32,\r\n" + 
    		"      \"rawDataBlob\": {\r\n" + 
    		"        \"blob\": [\r\n" + 
    		"          3,\r\n" + 
    		"          60,\r\n" + 
    		"          -114,\r\n" + 
    		"          50,\r\n" + 
    		"          -63,\r\n" + 
    		"          24,\r\n" + 
    		"          3,\r\n" + 
    		"          110,\r\n" + 
    		"          1,\r\n" + 
    		"          -109,\r\n" + 
    		"          -1,\r\n" + 
    		"          37,\r\n" + 
    		"          11,\r\n" + 
    		"          -59,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0,\r\n" + 
    		"          0\r\n" + 
    		"        ]\r\n" + 
    		"      },\r\n" + 
    		"      \"rssi\": -93,\r\n" + 
    		"      \"tagId\": \"S200021\",\r\n" + 
    		"      \"temperature\": 6,\r\n" + 
    		"      \"txPower\": 0,\r\n" + 
    		"      \"updateAt\": \"2019-12-19T15:02:30-0500\",\r\n" + 
    		"      \"voltage\": 3.013,\r\n" + 
    		"      \"zone\": \"Freezer\"\r\n" + 
    		"    }\r\n" + 
    		"  ],\r\n" + 
    		"  \"batteryLevel\": 78,\r\n" + 
    		"  \"deviceId\": \"1a062952-9e09-40ed-b437-30035f1b548a\",\r\n" + 
    		"  \"eventId\": \"0759b77b-0a08-40ca-a7c3-75c2b8d32d67\",\r\n" + 
    		"  \"gatewayId\": \"G100021\",\r\n" + 
    		"  \"instanceId\": \"6527d40b-ab0d-43d3-b34d-0c18b1efd71f\",\r\n" + 
    		"  \"locationName\": \"244331\",\r\n" + 
    		"  \"locationType\": \"Trailer\",\r\n" + 
    		"  \"readingInterval\": \"00:05:00\",\r\n" + 
    		"  \"sensorZones\": [\r\n" + 
    		"    \"Freezer\",\r\n" + 
    		"    \"Cooler\",\r\n" + 
    		"    \"Chiller\"\r\n" + 
    		"  ],\r\n" + 
    		"  \"syncInterval\": \"00:30:00\",\r\n" + 
    		"  \"temperatureUnit\": \"Fahrenheit\",\r\n" + 
    		"  \"time\": \"2019-12-19T15:29:39-0500\",\r\n" + 
    		"  \"uploadInterval\": \"00:05:00\"\r\n" + 
    		"}";
    
    public static void main(String[] args) {
    {
    	
    	 try {
    		 JSONArray array = new JSONArray(jsonString);
        	 
        	 JSONObject object = (JSONObject) array.get(0);
			String s=(String) object.get("sensorTags");
			System.out.println(s);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    }
       
   

}
