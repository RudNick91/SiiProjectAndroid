package com.example.programista.siimusicapp;

/**
 * Created by Programista on 2015-11-20.
 */

import android.media.ToneGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

public class Parser {

    static String jObj;

    public static final Charset UTF8 = Charset.forName("UTF-8");

    //konstruktor

    public Parser() {

    }

    //główna metoda obsługująca zapytania

    public String makeHttpRequest(String url, String method, HashMap<String, String> params) {

        String response = "";

        try {

            URL new_url = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) new_url.openConnection();

           // conn.setRequestProperty("Content-Type", "application/json");

            conn.setReadTimeout(100000);
            conn.setConnectTimeout(150000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod(method);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(params));

            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="BŁĄD POŁĄCZENIA";

            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jObj = response;

        return jObj;

    }


    //pomocnicza metoda generująca url

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{

        StringBuilder result = new StringBuilder();
        boolean first = true;

        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

}
