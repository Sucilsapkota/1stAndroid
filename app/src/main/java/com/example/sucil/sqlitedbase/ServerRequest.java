package com.example.sucil.sqlitedbase; /**
 * Created by sucil on 6/19/16.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class ServerRequest {

    Context context;
    String action;
    public ServerRequest(Context context){
        this.context = context;
    }

    public static boolean isNetworkConnected(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null;
    }



    private String getUrlEncodeData(HashMap<String, String> params) {
        if(params == null){
            return "";
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            try {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(entry.getKey());
                result.append("=");
                result.append(entry.getValue());
                Log.i("server ",   entry.getKey() + " = " + entry.getValue());
            }
            catch(Exception e){e.printStackTrace();}
        }
        return result.toString();
    }

    public String httpGetData(String url){
        return httpGetData(url, null);
    }
    public String httpGetData(String url, HashMap<String, String> params){
        Log.i("com.example.sucil.sqlitedbase.ServerRequest",  "GET :: url = " + url + "?" + getUrlEncodeData(params));
        StringBuffer response = new StringBuffer();
        try {
            URLConnection conn = new URL(url).openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(getUrlEncodeData(params));
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));
            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
        }
        catch(Exception e){e.printStackTrace();}
        Log.i("com.example.sucil.sqlitedbase.ServerRequest", "GET :: url : " + url + getUrlEncodeData(params) + " Response : " + response.toString());
        return response.toString();
    }

    public String httpPostData(String url, HashMap<String, String> params){
        Log.i("com.example.sucil.sqlitedbase.ServerRequest", "POST :: url = " + url + getUrlEncodeData(params));
        StringBuffer response = new StringBuffer();
        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)u.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            writer.write(getUrlEncodeData(params));
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));
            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
        }
        catch(Exception e){e.printStackTrace();}
        Log.i("com.example.sucil.sqlitedbase.ServerRequest", "POST :: url : " + url + " Params : " + getUrlEncodeData(params) + " Response = " + response.toString());
        return response.toString();
    }


}
