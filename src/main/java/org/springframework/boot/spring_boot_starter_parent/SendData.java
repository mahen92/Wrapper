package org.springframework.boot.spring_boot_starter_parent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


import com.google.protobuf.ByteString;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

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

public class SendData {

	
	 Secp256k1Context context  = new Secp256k1Context();
	 PrivateKey privateKey = context.newRandomPrivateKey();
	 Signer signer = new Signer(context, privateKey);
	 
	
	
	
	
	 
	 
	  
	
	 
	 public String hash(ByteArrayOutputStream originalString) {
	        byte[] encodedhash=null;
	        
	        try {
	            MessageDigest digest = MessageDigest.getInstance("SHA-512");
	            encodedhash = digest.digest(
	                    originalString.toByteArray());
	        }
	        catch(Exception e)
	        {

	        }
	        String returnString=bytesToHex(encodedhash);
	        return returnString;
	    }
	 
	 private static String bytesToHex(byte[] hash) {
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
	    }
	 
	 public  String encode(String s)
	 {
		 
		 ByteArrayOutputStream payload = new ByteArrayOutputStream();
		 for (int i = 0; i < s.length(); ++i)
		 {
			 payload.write(s.charAt(i));
		 }
		
		 
		 try {
		     new CborEncoder(payload).encode(new CborBuilder() .build());
		 } catch (CborException e) {
		     e.printStackTrace();
		 }
		 byte[] payloadBytes = payload.toByteArray(); 
		 
		 TransactionHeader header = TransactionHeader.newBuilder()
				  .setSignerPublicKey(signer.getPublicKey().hex())
				  .setFamilyName("pharmhedge")
				  .setFamilyVersion("1.0")
				  .addInputs("6e9c3c")
				  .addOutputs("6e9c3c")
				  .setPayloadSha512(hash(payload))
				  .setBatcherPublicKey(signer.getPublicKey().hex())
				  .setNonce(UUID.randomUUID().toString())
				  .build();
		 
		 String signature = signer.sign(header.toByteArray());

		 Transaction transaction =  Transaction.newBuilder()
		                                .setHeader(header.toByteString())
		                                .setPayload(ByteString.copyFrom(payloadBytes))
		                                .setHeaderSignature(signature)
		                                .build();
		 
		 ByteString txn_list_bytes = TransactionList.newBuilder()
				    .addTransactions(transaction)
				    .build()
				    .toByteString();

		ByteString txn_bytes = transaction.toByteString();
				
		 List<Transaction> transactions = new ArrayList();
		 transactions.add(transaction);

				BatchHeader batchHeader = BatchHeader.newBuilder()
				    .setSignerPublicKey(signer.getPublicKey().hex())
				    .addAllTransactionIds(
				            transactions
				                    .stream()
				                    .map(Transaction::getHeaderSignature)
				                    .collect(Collectors.toList())
				    )
				    .build();
				
				String batchSignature = signer.sign(batchHeader.toByteArray());

				Batch batch = Batch.newBuilder()
				         .setHeader(batchHeader.toByteString())
				         .addAllTransactions(transactions)
				         .setHeaderSignature(batchSignature)
				         .build();
				byte[] batchListBytes = BatchList.newBuilder()
				        .addBatches(batch)
				        .build()
				        .toByteArray();
				
				String string = new String(batchListBytes, StandardCharsets.UTF_8);
				System.out.println(string);
				String returnString=send(batchListBytes);
				System.out.println("After returnString:");
				return returnString;
	 }
	 
	 public String send(byte[] batchListBytes)
	 {
		 String returnString="";
		 OkHttpClient client = new OkHttpClient();
				MediaType mediaType = MediaType.parse("application/json,application/json");
				RequestBody body = RequestBody.create(mediaType, batchListBytes);
				Request request = new Request.Builder()
				  .url("http://18.208.230.251:8008/batches")
				  .method("POST", body)
				  .addHeader("Content-Type", "application/octet-stream")
				  .build();
				try {
					Response response = client.newCall(request).execute();
					//System.out.println("resp:"+response.body().string());
				    returnString=response.body().string();
					System.out.println("after resp string");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("After returnString1:");
				return returnString;
	 }

}
