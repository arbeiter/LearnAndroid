package com.nomnommer.arbeiter.nomnommer;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by chowman on 2/14/16.
 */
public class NomDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomdetail);
        if (savedInstanceState == null) {
            //Estos son mas importantes, las importaciones
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new NomDetailFragment()).
                    commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle app bar item clicks here. The app bar
        // automatically handles clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class NomDetailFragment extends Fragment {

        public NomDetailFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_nomdetail,
                    container, false);

            //Inicializar los valores del intenciones en la lanzamiento fragmentos
            Intent intent = getActivity().getIntent();
            if(intent!=null && intent.hasExtra(Intent.EXTRA_TEXT))
            {
                String forecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
                //Establacer las valores a algo
                //Inicializar una veo de texto con la valor del cuerda
                ((TextView) rootView.findViewById(R.id.detail_text))
                        .setText(forecastStr);
            }
            return rootView;
        }
    }
}
