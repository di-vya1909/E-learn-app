package com.example.educationnotes.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.educationnotes.R;

public class HomeListAdapter extends ArrayAdapter<String>
{
    private final Activity context;
    private final String[] className;
    private final String[] fullName;
    private final Integer[] imgid;

    public HomeListAdapter(Activity context, String[] className, String[] fullName, Integer[] imgid){
        super(context, R.layout.home_card,className);

        this.context = context;
        this.fullName = fullName;
        this.className = className;
        this.imgid = imgid;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.home_card, null, true);
        TextView classText = (TextView)rowView.findViewById(R.id.txtClassName);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.class_pic);
        TextView fullNameText = (TextView)rowView.findViewById(R.id.txtFullName);

        classText.setText(className[position]);
        imageView.setImageResource(imgid[position]);
        fullNameText.setText(fullName[position]);

        return rowView;
    };
}