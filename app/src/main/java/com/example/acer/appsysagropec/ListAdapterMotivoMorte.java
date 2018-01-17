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

public class ListAdapterMotivoMorte extends ArrayAdapter<Morte> {

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView codigosincronizado;
        TextView animal;
        TextView motivo;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public ListAdapterMotivoMorte(Context context, int resource, ArrayList<Morte> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        long idmorte = getItem(position).getIdMorte();
        String animal = getItem(position).getDescricaoanimal();
        int idAnimal = getItem(position).getIdAnimal();
        String motivo = getItem(position).getDescricao();
        int enviado = getItem(position).getEnviado();


        //Create the person object with the information
        Morte morte = new Morte(idmorte,idAnimal,motivo, enviado,animal);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ListAdapterMotivoMorte.ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ListAdapterMotivoMorte.ViewHolder();

            holder.codigosincronizado = (TextView) convertView.findViewById(R.id.textView1);
            holder.animal = (TextView) convertView.findViewById(R.id.textView2);
            holder.motivo = (TextView) convertView.findViewById(R.id.textView3);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ListAdapterMotivoMorte.ViewHolder) convertView.getTag();
            result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.codigosincronizado.setText("Cód solicitação: " + morte.getIdMorte() + " | Não sincronizado");
        holder.animal.setText("Animal: "+ morte.getDescricaoanimal());
        holder.motivo.setText(("Motivo morte: " + morte.getDescricao()));

        return convertView;
    }
}
