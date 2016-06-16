package com.nomnommer.arbeiter.nomnommer.Models.Models.Noms;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.nomnommer.arbeiter.nomnommer.Adapters.FancyAdapter;
import com.nomnommer.arbeiter.nomnommer.YelpApiHandler.YelpApi;
import com.nomnommer.arbeiter.nomnommer.YelpApiHandler.YelpParser.ParseHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Nom
{
        public String name;
        public String snippet_image_url;
        public Bitmap photo;
}
