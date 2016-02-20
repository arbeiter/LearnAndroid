package com.nomnommer.arbeiter.nomnommer.Adapters;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.nomnommer.arbeiter.nomnommer.Models.Models.Noms.Nom;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by aureliano on 12/23/15.
 */
public class BusinessesAdapter implements JsonSerializer<List<Nom>>, JsonDeserializer<List<Nom>>
{
    @Override
    public List<Nom> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        JsonObject obj = json.getAsJsonObject();
        List<Nom> nomList = new ArrayList<Nom>();
        for(Map.Entry<String, JsonElement> entry: obj.entrySet()) {
            String name = entry.getKey();
            Nom nom = gson.fromJson(entry.getValue(), Nom.class);
            nomList.add(nom);
        }
        return nomList;
    }

    @Override
    public JsonElement serialize(List<Nom> src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
