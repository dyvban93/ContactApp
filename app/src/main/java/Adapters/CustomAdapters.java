package Adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projects.bakota.contactapp.R;

import java.util.ArrayList;

/**
 * Created by Bakota on 04/03/2019.
 */

public class CustomAdapters extends BaseAdapter {

    ArrayList<Contacts> mContacts ;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public CustomAdapters(ArrayList<Contacts> mContacts, Context mContext) {
        this.mContacts = mContacts;
        this.mContext = mContext;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return (mContacts == null ? 0 : mContacts.size());
    }

    @Override
    public Object getItem(int index) {
        if(mContacts == null || mContacts.size() < index) return null;
        return mContacts.get(index);
    }

    @Override
    public long getItemId(int index) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder = new ViewHolder();

        if(view == null){
            holder = new ViewHolder();
            view = mLayoutInflater.inflate(R.layout.item,null);
            holder.txtNom = (TextView) view.findViewById(R.id.nom);
            holder.txtAdresse = (TextView) view.findViewById(R.id.adresse);
            holder.txtNumero = (TextView) view.findViewById(R.id.phone);
            holder.img = (ImageView) view.findViewById(R.id.img) ;


            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }


























        Contacts contact = mContacts.get(position);
        if (contact == null ) return view;

        holder.txtNom.setText(contact.getNom());
        holder.txtAdresse.setText(contact.getAdresse());
        holder.img.setBackgroundResource(R.drawable.ic_person_black_24dp);
        holder.txtNumero.setText(contact.getNumero());

        return view;
    }


    static class ViewHolder {
        ImageView img;
        TextView txtNom;
        TextView txtNumero;
        TextView txtAdresse;

    }


}
