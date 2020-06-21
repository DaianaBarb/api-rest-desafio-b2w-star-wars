package com.b2w.api.challenge.utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Api {
	public Api() {
		
	}

    public JsonObject getBuilder( String searchquery) throws Exception {
    	System.out.println("entrou");
        HttpGet httpGet;
       
            httpGet = new HttpGet("https://swapi.dev/api/planets/?search=" + searchquery);
       
        return getRequest(httpGet);
    }

    public JsonObject getRequest(HttpGet getRequest) throws IOException {
    	System.out.println("entrou2");

        HttpClient httpClient = HttpClientBuilder.create().build();
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = httpClient.execute(getRequest);
        System.out.println("response"+response);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JsonObject jsonObject = deserialize(stringBuilder.toString());
        bufferedReader.close();

        return jsonObject;
    }

    public JsonObject deserialize(String json) {
        Gson gson = new Gson();
        JsonObject jsonClass = gson.fromJson(json, JsonObject.class);
        return jsonClass;
    }

    public JsonObject innerRequest(String uri) throws IOException {
        HttpGet httpGet = new HttpGet(uri);
        return getRequest(httpGet);
    }
}