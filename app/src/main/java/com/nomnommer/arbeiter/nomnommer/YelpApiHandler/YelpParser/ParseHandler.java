package com.nomnommer.arbeiter.nomnommer.YelpApiHandler.YelpParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nomnommer.arbeiter.nomnommer.Models.Models.Noms.Nom;
import com.nomnommer.arbeiter.nomnommer.YelpApiHandler.YelpParser.Adapters.BusinessesAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by GMan on 12/22/15.
 */
public class ParseHandler {
    public String[] getBusinessDataFromJson(String jsonStr){
        //Esta codigo puedo o puedo que no funcionar segun mi plano. Pero ahora, esta es suficiente
        //Manana, fijare todos ;)
        //Segun mi plano, ya sea este o la adapter del tipos(TypeAdapter) puedan funcionar
        Gson gson = new Gson();
        Nom[] nomArray = gson.fromJson(jsonStr, Nom[].class);
        List<Nom> noms = Arrays.asList(nomArray);

        //Esta es el plano
        //Primeramente, obtener su data en la forma de arrays del objetos
        //A estos efetos, analizar granalicamente sus datos a un objeto que se ha definido antes
        return new String[10];
    }
}
