package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
			//1. GET Method without headers

		 public CloseableHttpResponse get(String URL) throws ClientProtocolException, IOException {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(URL); //http get request
			CloseableHttpResponse closeablehttpresponse=  httpclient.execute(httpget);	// Hit the get URL
			
			return closeablehttpresponse;
		 }
			
			//2. get method with for headers
			public CloseableHttpResponse get(String URL, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpGet httpget = new HttpGet(URL); //http get request
				
				for(Map.Entry<String, String> entry: headermap.entrySet()){
					httpget.addHeader(entry.getKey(), entry.getValue());
				}
				
				CloseableHttpResponse closeablehttpresponse=  httpclient.execute(httpget);	// Hit the get URL
				
				return closeablehttpresponse;
			
			
		 }
		 
		 // POST Method
		 public void post (String url, String entityString, HashMap<String, String> headermap) throws UnsupportedEncodingException {
			 CloseableHttpClient httpclient = HttpClients.createDefault();	
			 HttpPost httppost= new HttpPost();// http request
			 httppost.setEntity(new StringEntity(entityString));// for payload
			 
			 // for headers
			 
			 
		 }
		 
		 
} 
