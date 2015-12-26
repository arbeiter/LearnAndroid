package com.nomnommer.arbeiter.nomnommer.YelpApiHandler.YelpParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nomnommer.arbeiter.nomnommer.Models.Models.Noms.Nom;
import com.nomnommer.arbeiter.nomnommer.YelpApiHandler.YelpParser.Adapters.BusinessesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by GMan on 12/22/15.
 */
public class ParseHandler {

    public String[] getBusinessDataFromJson(String jsonStr) throws JSONException {

        //Esta codigo puedo o puedo que no funcionar segun mi plano. Pero ahora, esta es suficiente
        //Manana, fijare todos ;)
        //Segun mi plano, ya sea este o la adapter del tipos(TypeAdapter) puedan funcionar
        Gson gson = new Gson();
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
            nomList.add(nomObj);
        }

        //Esta es el plano
        //Primeramente, obtener su data en la forma de arrays del objetos
        //A estos efetos, analizar granalicamente sus datos a un objeto que se ha definido antes
        return new String[10];
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
