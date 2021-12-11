package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	TestBase testbase;
	String serviceurl;
	String apiURL;
	 String url;
	 RestClient restclient;
	 CloseableHttpResponse closeablehttpresponse; 
	 
	@BeforeMethod
	public void setUP() throws ClientProtocolException, IOException {
		 testbase = new TestBase();
		 serviceurl = prop.getProperty("URL");
		 apiURL= prop.getProperty("serviceURL");
		 
		 url= serviceurl + apiURL;
		
	}
	
	//@Test (priority=1)
	public void getAPITest() throws ClientProtocolException, IOException {
		 restclient = new RestClient();
		 closeablehttpresponse = restclient.get(url);
		 
			//a. Status Code
			int statusCode=  closeablehttpresponse.getStatusLine().getStatusCode();
			System.out.println("Status Code----->"+ statusCode );
			
			Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not 200");
			
			//b. JSON String
			String responseString=  EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
			
			JSONObject responseJson = new JSONObject(responseString);
			System.out.println("Response JSON from API---->"+ responseJson);
			
			//Single Value Assertion
			
			//Per Page:
			String perpagevalue = TestUtil.getValueByJPath(responseJson, "/per_page");
			System.out.println("Value of per_page is "+ perpagevalue);
			Assert.assertEquals(Integer.parseInt(perpagevalue), 6);
			
			//Total :
			String totalvalue = TestUtil.getValueByJPath(responseJson, "/total");
			System.out.println("Value of total is "+ totalvalue);
			Assert.assertEquals(Integer.parseInt(totalvalue), 12);
			
			// get the value from JSON Array
			
			String id= TestUtil.getValueByJPath(responseJson, "/data[0]/id");
			String email= TestUtil.getValueByJPath(responseJson, "/data[0]/email");
			String firstname= TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
			String lastname= TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
			String avatar= TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
			
			System.out.println("id-----"+id);
			System.out.println("email-----"+email);
			System.out.println("Firstname-----"+firstname);
			System.out.println("Lastname-----"+lastname);
			System.out.println("Avatar-----"+avatar);
			
			
			//c.  All Headers
			Header[] headersArray=  closeablehttpresponse.getAllHeaders();
			HashMap <String, String> allheaders = new HashMap<String, String>();
			
			for(Header header :headersArray) {
				allheaders.put(header.getName(), header.getValue());
			}
			
			System.out.println("Headers Array---->"+allheaders);
				
			
		 }
	
	
	@Test (priority=2)
	public void getAPITestwithHeader() throws ClientProtocolException, IOException {
		 restclient = new RestClient();
		 
		 HashMap<String, String>headermap= new HashMap<String, String>();
		 headermap.put("Content-Type", "application/json");
		 headermap.put("Postman-Token", "calculated when request is sent");
		 headermap.put("Host", "calculated when request is sent");
		 headermap.put("User-Agent-", "PostmanRuntime/7.28.4");
		 headermap.put("Accept", "*/*");
		 headermap.put("Accept-Encoding", "gzip, deflate, br");
		 headermap.put("Connection", "keep-alive");
		 
		 closeablehttpresponse = restclient.get(url, headermap);
		 
		 
			//a. Status Code
			int statusCode=  closeablehttpresponse.getStatusLine().getStatusCode();
			System.out.println("Status Code----->"+ statusCode );
			
			Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_400, "Status Code is not 400");
			
			//b. JSON String
			String responseString=  EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
			
			JSONObject responseJson = new JSONObject(responseString);
			System.out.println("Response JSON from API---->"+ responseJson);
			
			//Single Value Assertion
			
			//Per Page:
			String perpagevalue = TestUtil.getValueByJPath(responseJson, "/per_page");
			System.out.println("Value of per_page is "+ perpagevalue);
			Assert.assertEquals(Integer.parseInt(perpagevalue), 6);
			
			//Total :
			String totalvalue = TestUtil.getValueByJPath(responseJson, "/total");
			System.out.println("Value of total is "+ totalvalue);
			Assert.assertEquals(Integer.parseInt(totalvalue), 12);
			
			// get the value from JSON Array
			
			String id= TestUtil.getValueByJPath(responseJson, "/data[0]/id");
			String email= TestUtil.getValueByJPath(responseJson, "/data[0]/email");
			String firstname= TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
			String lastname= TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
			String avatar= TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
			
			System.out.println("id-----"+id);
			System.out.println("email-----"+email);
			System.out.println("Firstname-----"+firstname);
			System.out.println("Lastname-----"+lastname);
			System.out.println("Avatar-----"+avatar);
			
			
			//c.  All Headers
			Header[] headersArray=  closeablehttpresponse.getAllHeaders();
			HashMap <String, String> allheaders = new HashMap<String, String>();
			
			for(Header header :headersArray) {
				allheaders.put(header.getName(), header.getValue());
			}
			
			System.out.println("Headers Array---->"+allheaders);
				
			
		 }

}
