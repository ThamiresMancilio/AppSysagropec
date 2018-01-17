package com.example.acer.appsysagropec;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Acer on 06/12/2017.
 */

public class ListAdapterPro extends ArrayAdapter<Producao> {

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView data;
        TextView animal;
        TextView quantidade;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public ListAdapterPro(Context context, int resource, ArrayList<Producao> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String animal = getItem(position).getAnimal();
        double quantidade = getItem(position).getQuantidade();
        int id = getItem(position).getId();

        //Create the person object with the information
        Producao producao = new Producao(id,animal,quantidade);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.data = (TextView) convertView.findViewById(R.id.textView1);
            holder.animal = (TextView) convertView.findViewById(R.id.textView2);
            holder.quantidade = (TextView) convertView.findViewById(R.id.textView3);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;
        Date currentTime = Calendar.getInstance().getTime();

        holder.data.setText(currentTime.toString());
        holder.animal.setText(producao.getAnimal());

        String qtds = String.valueOf(producao.getQuantidade());

        holder.quantidade.setText(qtds + " kg");

        return convertView;
    }
}
