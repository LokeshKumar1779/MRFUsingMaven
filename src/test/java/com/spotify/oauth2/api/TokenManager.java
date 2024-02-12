package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.RestResource.postAccount;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken(){

        try {
            if (access_token == null || Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing token....");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            } else {
                System.out.println("Token is good to use...");
            }
        } catch (Exception e){
            throw new RuntimeException("ABORT!! Access token renew failed..");
        }
        return  access_token;

    }
    private static Response renewToken(){
        HashMap<String,String> formParams = new HashMap<>();
        formParams.put("client_id", ConfigLoader.getInstance().getProperty("client_id"));
        formParams.put("client_secret",ConfigLoader.getInstance().getProperty("client_secret"));
        formParams.put("refresh_token",ConfigLoader.getInstance().getProperty("refresh_token"));
        formParams.put("grant_type",ConfigLoader.getInstance().getProperty("grant_type"));

        Response response = postAccount(formParams);

        if (response.getStatusCode() !=200){
            throw new RuntimeException("ABORT!! Renew token failed..");
        }

//        System.out.println("access_token : " +response.path("access_token"));
        return response;
    }
}
