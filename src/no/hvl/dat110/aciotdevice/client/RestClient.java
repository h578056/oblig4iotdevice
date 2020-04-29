package no.hvl.dat110.aciotdevice.client;

import java.io.IOException;

import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClient {
	private static String logpath = "/accessdevice/log";
	public static final MediaType JSON = MediaType.parse(logpath+"; charset=utf-8");
	
	public RestClient() {
		// TODO Auto-generated constructor stub
	}


	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		OkHttpClient client = new OkHttpClient();
		Gson gson = new Gson();
		@SuppressWarnings("deprecation")
		AccessMessage am= new AccessMessage(message);
		String strJ=gson.toJson(am);
		//System.out.println(strJ+"********************************************");
		RequestBody body= RequestBody.create(JSON,strJ);
		Request request= new Request.Builder().url("http://localhost:8080/accessdevice/log/").post(body).build();
		try {
			Response response = client.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
	}
	private static String codepath = "/accessdevice/code";
	
	
	public AccessCode doGetAccessCode() {

		AccessCode code = new AccessCode();
		
		// TODO: implement a HTTP GET on the service to get current access code
		Gson gson = new Gson();
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://localhost:8080/accessdevice/code/")
		  .get()
		  .build();
		
		try (Response response = client.newCall(request).execute()) {		
		     String responseStr= response.body().string();
		     System.out.println (responseStr);
		     AccessCode ac=gson.fromJson(responseStr, AccessCode.class);
		     System.out.println(ac.toString());
		     
		    // String str2 = str.replaceAll("[^0-9]", "");
		     //char[] c= str.toCharArray(); 
		     //int f[]=new int[c.length];
		     //for(int i=0; i<c.length; i++) {
		    	// f[i]=Integer.parseInt(String.valueOf(c[i]));
		     //}
		     code.setAccesscode(ac.getAccesscode());
		}
	   catch (IOException e) {
		   e.printStackTrace();
	   }

		return code;
	}
}
