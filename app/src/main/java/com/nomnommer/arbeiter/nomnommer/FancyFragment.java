package com.nomnommer.arbeiter.nomnommer;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.nomnommer.arbeiter.nomnommer.Adapters.FancyAdapter;
import com.nomnommer.arbeiter.nomnommer.Models.Models.Noms.Nom;
import com.nomnommer.arbeiter.nomnommer.YelpApiHandler.YelpApi;
import com.nomnommer.arbeiter.nomnommer.YelpApiHandler.YelpParser.ParseHandler;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

/**
/**
 * Created by chowman on 2/20/16.
 */
public class FancyFragment extends Fragment{


    //Move to a separate constants class
    private static final String CONSUMER_KEY = "vt69-7nz0OaKtpCng18x8g";
    private static final String CONSUMER_SECRET = "mrOpQ1YV-Zblqiag-mdTH54u3Vo";
    private static final String TOKEN = "yXyEzTQ32dl1jAww3JNiGuIRFVGuJVf1";
    private static final String TOKEN_SECRET = "uwFDFWa2Itesy0A-e-pa3ZvFeK8";

    ArrayList<Nom> outputList = new ArrayList<Nom>();
    FancyAdapter fancyArrayAdapter;
    View rootView;
    ListView listView;

    public FancyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //Invocar la funcion de su codigo
        new GetPlacesTask().execute(101);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        ArrayList<Nom> outputList = new ArrayList<Nom>();
        fancyArrayAdapter = new FancyAdapter(getActivity(), outputList);

        rootView = inflater.inflate(R.layout.fragment_fancy, container, false);
        listView = (ListView)rootView.findViewById(R.id.fancy_fetched_items);
        listView.setAdapter(fancyArrayAdapter);

        return rootView;
    }

    //Initiate this on activity launch
    //Async task to fetch my contents
    private class GetPlacesTask extends AsyncTask<Integer, Void, List<Nom>>
    {
        private final String LOG_TAG = GetPlacesTask.class.getSimpleName();

        @Override
        protected List<Nom> doInBackground(Integer... urls) {

            Nom nom = new Nom();
            nom.snippet_image_url =  "http://ycombinator.com/images/yc500.gif";
            nom.name = "Sup";
            List<Nom> lol = new ArrayList<Nom>();
            lol.add(nom);
            fancyArrayAdapter = new FancyAdapter(getActivity(), lol);


            Log.d(LOG_TAG, "doInBackground Invoked with param" + urls[0]);

            YelpApi requestYelpHelper = new YelpApi(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
            String response = requestYelpHelper.searchForBusinessesByLocation("food", "San Francisco", 20);

            ParseHandler handler = new ParseHandler();
            List<Nom> responseObjects = new ArrayList<>();

            try
            {
                responseObjects = handler.GetNoms(response);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            return responseObjects;
        }

        @Override
        protected void onPostExecute(List<Nom> noms) {
            super.onPostExecute(noms);
            fancyArrayAdapter = new FancyAdapter(getActivity(), noms);
            listView.setAdapter(fancyArrayAdapter);
        }
    }
}
