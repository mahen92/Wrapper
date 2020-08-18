package org.springframework.boot.spring_boot_starter_parent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.protobuf.ByteString;

public class FetchData {
	
	  private static final String transactionFamilyName = "pharmhedge";
	  private static final String transactionFamilyVersion = "1.0";
	  private  String transactionFamilyNameSpaceAddress=NameSpaceUtils.calculateNameSpace(transactionFamilyName,6);
	  String sensorEntries=NameSpaceUtils.calculateNameSpace("sensor-entries",6);
	  String TRACKING =  NameSpaceUtils.calculateNameSpace("tracking", 6);
	  String TRACKING_TABLE = transactionFamilyNameSpaceAddress + TRACKING;


	  private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }
	  
	  /*public static void main(String[] args)
	  {
		  FetchData test= new FetchData();
		  try {
			test.getData("1a062952-9e09-40ed-b437-30035f1b548a");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }*/

	  public String getData(String device) throws IOException, JSONException {
		String address=getSensorAddress(device);
	    JSONObject json = readJsonFromUrl("http://18.208.230.251:8008/state/"+address);
	    System.out.println(json.toString());
	    String data=json.get("data").toString();
	    byte[] encodedString = Base64.getDecoder().decode(data);
	    String decodedString=ByteString.copyFrom(encodedString).toStringUtf8();
	    System.out.println(decodedString);
	    return decodedString;
	    
	  }
	  
	 
	  
	  public String getSensorAddress(String sensorID) {
          return transactionFamilyNameSpaceAddress+sensorEntries+NameSpaceUtils.calculateNameSpace(sensorID,58);
      }

	}
