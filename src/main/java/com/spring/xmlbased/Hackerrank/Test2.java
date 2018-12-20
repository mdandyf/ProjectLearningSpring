package com.spring.xmlbased.Hackerrank;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import org.json.JSONString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test2 {

    static String query = "https://jsonmock.hackerrank.com/api/movies/search/";
    static String title = "?Title=";
    static String page = "page =";
    static int pageInt = 1;

    public static void main(String[] args) {

        String urlString = query;


        String output = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            output = result.toString();
            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(output != null) {
           Gson gson = new Gson();
           gson.toJson(output);

            JsonArray retArr = new JsonArray();
        }



    }
}
