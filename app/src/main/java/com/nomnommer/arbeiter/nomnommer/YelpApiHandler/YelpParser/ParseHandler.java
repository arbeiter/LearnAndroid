package com.nomnommer.arbeiter.nomnommer.YelpApiHandler.YelpParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.nomnommer.arbeiter.nomnommer.Models.Models.Noms.Nom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GMan on 12/22/15.
 */
public class ParseHandler {

    public String[] getBusinessDataFromJson(String jsonStr) throws JSONException {
        JSONArray jsonArray = new JSONObject(jsonStr).getJSONArray("businesses");
        String[] debugArr = new String[20];

        List<Nom> nomList = new ArrayList<Nom>();
        for(int i=0;i<jsonArray.length();i++){
            Nom nomObj = new Nom();
            JSONObject business = jsonArray.getJSONObject(i);
            //public String name;
            //public List<String> categories = new ArrayList<String>();
            //public String snippet_image_url;
            // public String address;
            nomObj.name = business.get("name").toString();
            nomObj.snippet_image_url = business.get("snippet_image_url").toString();
           // JSONArray categoryArray = business.getJSONArray("categories");
           // List<String> categoryList = new ArrayList<String>();
           // for(int j = 0; j<categoryArray.length();j++){
             //   categoryList.add(categoryArray.getString(j));
           // }
            nomList.add(nomObj);
            debugArr[i] = nomObj.name;
        }

        //Esta es el plano
        //Primeramente, obtener su data en la forma de arrays del objetos
        //A estos efetos, analizar granalicamente sus datos a un objeto que se ha definido antes
        return debugArr;
    }

    public List<Nom> GetNoms(String jsonStr) throws JSONException
    {
        JSONArray jsonArray = new JSONObject(jsonStr).getJSONArray("businesses");

        List<Nom> nomList = new ArrayList<Nom>();
        for(int i=0;i<jsonArray.length();i++){
            Nom nomObj = new Nom();
            JSONObject business = jsonArray.getJSONObject(i);
            //public String name;
            //public List<String> categories = new ArrayList<String>();
            //public String snippet_image_url;
            // public String address;
            nomObj.name = business.get("name").toString();
            nomObj.snippet_image_url = business.get("snippet_image_url").toString();
            nomObj.photo = new GetImageTask().doInBackground(nomObj.snippet_image_url);
            nomList.add(nomObj);
        }

        //Esta es el plano
        //Primeramente, obtener su data en la forma de arrays del objetos
        //A estos efetos, analizar granalicamente sus datos a un objeto que se ha definido antes
        return nomList;
    }

    private class GetImageTask extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            }
            catch(MalformedURLException ex) {
                return null;
            }
            catch(IOException ex) {
                return null;
            }
        }
    }


    /*
    private int getMaxTemp(String forecastJsonStr, int dayIndex) throws JSONException {
        String val = Integer.toString(dayIndex);
        JSONObject mainObject = new JSONObject(forecastJsonStr);
        JSONArray list = new JSONObject(forecastJsonStr).getJSONArray("list");

        JSONObject listObject = (JSONObject)list.get(dayIndex);
        JSONObject tempObject = listObject.getJSONObject("temp");
        int max = tempObject.getInt("max");

        return max;
    }
    */
}
