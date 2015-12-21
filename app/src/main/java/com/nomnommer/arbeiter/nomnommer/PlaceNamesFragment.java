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

            //Try out http requests
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            //Result string
            String forecastJsonStr = null;
            try {

                Uri.Builder builder = new Uri.Builder();
                builder.scheme("http")
                        .authority("api.openweathermap.org")
                        .appendPath("data")
                        .appendPath("2.5")
                        .appendPath("forecast")
                        .appendPath("daily")
                        .appendQueryParameter("q","94043")
                        .appendQueryParameter("mode","json")
                        .appendQueryParameter("units","metric")
                        .appendQueryParameter("cnt","7")
                        .appendQueryParameter("APPID", "0b56807a9fe0bcf6c79681caa394658c");

                URL url = new URL(builder.toString());

                //Create a get request and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                //Read input stream to string
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                forecastJsonStr = buffer.toString();
                //Get result for the first day
                //int max = getMaxTemp(forecastJsonStr, 0);
                String[] arr = new String[10];
                return arr;
            } catch (Exception e) {
                Log.d(LOG_TAG, "exception in here");
                Log.e(LOG_TAG, "Generic exception here", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
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

    private int getMaxTemp(String forecastJsonStr, int dayIndex) throws JSONException {
        String val = Integer.toString(dayIndex);
        JSONObject mainObject = new JSONObject(forecastJsonStr);
        JSONArray list = new JSONObject(forecastJsonStr).getJSONArray("list");

        JSONObject listObject = (JSONObject)list.get(dayIndex);
        JSONObject tempObject = listObject.getJSONObject("temp");
        int max = tempObject.getInt("max");
        return max;
    }
}
