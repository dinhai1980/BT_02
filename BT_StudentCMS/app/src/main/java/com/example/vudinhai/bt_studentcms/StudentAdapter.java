package com.example.vudinhai.bt_studentcms;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vudinhai on 3/14/18.
 */

public class StudentAdapter extends ArrayAdapter<Students> {

    Context context;
    ArrayList<Students> arrayList;
    int layout;

    public StudentAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Students> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = objects;
        this.layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Students students = arrayList.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout,null);
        }

        TextView txtName = convertView.findViewById(R.id.text1);
        txtName.setText(students.getName());
        TextView txtAddress = convertView.findViewById(R.id.text2);
        txtAddress.setText(students.getAddress());
        ImageView imgGender = convertView.findViewById(R.id.image);
        if(students.gender == 1){
            imgGender.setImageResource(R.drawable.male);
        }else {
            imgGender.setImageResource(R.drawable.female);
        }




        return convertView;
    }


}
