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

import java.util.ArrayList;

/**
 * Created by Acer on 06/12/2017.
 */

public class ListAdapterAnimal extends ArrayAdapter<Animal> {

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView raca;
        TextView livroregistro;
        TextView apelido;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public ListAdapterAnimal(Context context, int resource, ArrayList<Animal> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information


        int id = getItem(position).getId();
        String raca = getItem(position).getRaca();
        String livro = getItem(position).getLivro();
        String registro = getItem(position).getRegistro();
        String apelido = getItem(position).getApelido();

        //Create the person object with the information
        Animal animal = new Animal(id,registro,livro, raca,apelido);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ListAdapterAnimal.ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ListAdapterAnimal.ViewHolder();
            holder.raca = (TextView) convertView.findViewById(R.id.textView1);
            holder.livroregistro = (TextView) convertView.findViewById(R.id.textView2);
            holder.apelido = (TextView) convertView.findViewById(R.id.textView3);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ListAdapterAnimal.ViewHolder) convertView.getTag();
            result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.raca.setText(animal.getRaca());
        holder.livroregistro.setText(animal.getLivro()+ animal.getRegistro());
        holder.apelido.setText((animal.getApelido()));

        return convertView;
    }
}

