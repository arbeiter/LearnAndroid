package com.nomnommer.arbeiter.nomnommer;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<String> outputList = new ArrayList<String>();
        outputList = new ArrayList<String>(Arrays.asList("Qdoba", "McDonalds", "Burga King"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.fetched_item_display, R.id.fetched_item_text_view, outputList);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.list_view_fetchedItems);
        listView.setAdapter(arrayAdapter);

        return rootView;
    }
}
