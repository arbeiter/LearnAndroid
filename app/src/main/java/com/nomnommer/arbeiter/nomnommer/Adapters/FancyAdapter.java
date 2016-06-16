package com.nomnommer.arbeiter.nomnommer.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nomnommer.arbeiter.nomnommer.Models.Models.Noms.Nom;
import com.nomnommer.arbeiter.nomnommer.R;

import java.util.List;

/**
 * Created by chowman on 2/19/16.
 */
public class FancyAdapter extends BaseAdapter
{

    private Context mContext;
    private List<Nom> nomList;

    public FancyAdapter(Context context, List<Nom> list)
    {
        mContext = context;
        nomList = list;
    }

    @Override
    public int getCount()
    {
        return nomList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return nomList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Nom entry = nomList.get(position);
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.fancy_list_item, null);
        }

        //Get image bitmap
        ImageView icon = (ImageView)convertView.findViewById(R.id.placePicture);
        icon.setImageBitmap(entry.photo);
        //set image

        TextView text = (TextView)convertView.findViewById(R.id.placeText);
        text.setText(entry.name);

        return  convertView;
    }
}
