package com.rrouton.happybrain.utils;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtils {

    private static String TAG = "HappyBrain/NetworkUtils";

    public static void testHttpUrlConnection() {

        new AsyncTask<Void, Void, Void> () {
            @Override
            protected Void doInBackground(Void... voids) {
                testConnection();
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    private static void testDigestAuth() {
        URL url;
        HttpsURLConnection urlConnection = null;
        InputStream in;

        try {
            url = new URL("https://postman-echo.com/digest-auth");
            urlConnection = (HttpsURLConnection) url.openConnection();

            //encode auth string and set
            String authorizationString = "postman:password";
            byte[] data = authorizationString.getBytes("UTF-8");
            String encodedAuthString = Base64.encodeToString(data, Base64.DEFAULT);
            //urlConnection.setRequestProperty("Authorization", "Basic " + encodedAuthString);

            //Response code for debugging
            int responseCode = urlConnection.getResponseCode();
            String responseMessage = urlConnection.getResponseMessage();
            Log.d(TAG,"response code=" + responseCode + " message = " + responseMessage);

            in = new BufferedInputStream(urlConnection.getInputStream());
            String test = readInputStream(in);
            int l = test.length();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return;
    }

    private static void testBasicAuth() {
        URL url;
        HttpsURLConnection urlConnection = null;
        InputStream in;

        try {
            url = new URL("https://postman-echo.com/basic-auth");
            urlConnection = (HttpsURLConnection) url.openConnection();

            //encode auth string and set
            String authorizationString = "postman:password";
            byte[] data = authorizationString.getBytes("UTF-8");
            String encodedAuthString = Base64.encodeToString(data, Base64.DEFAULT);
            urlConnection.setRequestProperty("Authorization", "Basic " + encodedAuthString);

            //Response code for debugging
            int responseCode = urlConnection.getResponseCode();
            String responseMessage = urlConnection.getResponseMessage();
            Log.d(TAG,"response code=" + responseCode + " message = " + responseMessage);

            in = new BufferedInputStream(urlConnection.getInputStream());
            String test = readInputStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return;
    }

    private static void testConnection() {
        URL url = null;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.getPopular&api_key=7" +
                    "71121adec7d520d28cf0340574e93f9&user_id=66956608@N06&format=json&nojsoncallback=1");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String jsonStr = readInputStream(in);

            //Hand parse json
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONObject photos = jsonObj.getJSONObject("photos");
            JSONArray photo = photos.getJSONArray("photo");
            for(int i = 0; i < photo.length(); i++) {
                JSONObject p = photo.getJSONObject(i);
                int id = p.getInt("id");
                String owner = p.getString("owner");
                String secret = p.getString("secret");
                String server = p.getString("server");
                Log.d(TAG, "id=" + id + " owner=" + owner + " secret=" + secret + " server=" + server);
            }

            Log.d(TAG, jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return;
    }

    private static String readInputStream(InputStream inputStream) {
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total.toString();
    }
}
