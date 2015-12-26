package com.nomnommer.arbeiter.nomnommer;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nomnommer.arbeiter.nomnommer.YelpApiHandler.YelpApi;
import com.nomnommer.arbeiter.nomnommer.YelpApiHandler.YelpParser.ParseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceNamesFragment extends Fragment {

    //Move to a separate constants class
    private static final String CONSUMER_KEY = "vt69-7nz0OaKtpCng18x8g";
    private static final String CONSUMER_SECRET = "mrOpQ1YV-Zblqiag-mdTH54u3Vo";
    private static final String TOKEN = "yXyEzTQ32dl1jAww3JNiGuIRFVGuJVf1";
    private static final String TOKEN_SECRET = "uwFDFWa2Itesy0A-e-pa3ZvFeK8";

    ArrayList<String> outputList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;

    public PlaceNamesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        outputList = new ArrayList<String>(Arrays.asList("Qdoba", "McDonalds", "Burga King"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.fetched_item_display, R.id.fetched_item_text_view, outputList);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.list_view_fetchedItems);
        listView.setAdapter(arrayAdapter);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.placenamesmenu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_refresh:
                new GetPlacesTask().execute(101);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class GetPlacesTask extends AsyncTask<Integer, String, String[]> {
        private final String LOG_TAG = GetPlacesTask.class.getSimpleName();

        @Override
        protected String[] doInBackground(Integer... urls) {
            Log.d(LOG_TAG, "doInBackground Invoked with param" + urls[0]);

            YelpApi requestYelpHelper = new YelpApi(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
            String response = requestYelpHelper.searchForBusinessesByLocation("food", "San Francisco", 3);
            if(response!=null)
            {
                response = response.concat("y");
            }

            ParseHandler handler = new ParseHandler();
            String[] responseObjects = new String[0];

            try
            {
                responseObjects = handler.getBusinessDataFromJson(response);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            return responseObjects;
        }

        @Override
        protected void onPostExecute(String[] s) {
            super.onPostExecute(s);
            arrayAdapter.clear();
            for (String datum:s) {
                arrayAdapter.add(datum);
            }
        }
    }


}
