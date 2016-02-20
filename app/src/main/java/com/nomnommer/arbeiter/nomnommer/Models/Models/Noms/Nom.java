package com.nomnommer.arbeiter.nomnommer.Models.Models.Noms;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aureliano on 12/23/15.
 *    a. id string
 b. name string
 d. Categories list<String>
 e. snippet_image_url string
 f. location.coordinate dict
 g. address
 */
public class Nom
{
        public String name;
        public List<String> categories = new ArrayList<String>();
        public String snippet_image_url;
        public String address;
        public Bitmap getPicture(){
            try{
                URL url = new URL(this.snippet_image_url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            }
            catch(Exception e){
                Log.e("merde", e.getMessage());
                return null;
            }
        }
}
