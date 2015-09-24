package com.example.galaxy.contactsbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by galaxy on 24/09/15.
 */
public class ContactsAdapter extends ArrayAdapter<String>
{
    private final Context context;
    ArrayList<String> nList;


    public ContactsAdapter(Context context, ArrayList<String> nList)
    {
        super(context, R.layout.single_contact_view, nList);
        this.context = context;
        this.nList=nList;
    }

    public class ViewHolder {
        TextView description;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = inflater.inflate(R.layout.single_contact_view, parent, false);
            holder = new ViewHolder();
            holder.description = (TextView) view
                    .findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

//        // get a single data from your list.
//        NewsDto newsDto= nlist.get(position);
//
//        //initialization of Image
//        holder.image1.setImageBitmap(newsDto.getImage());
        // get a single data from your list.
        holder.description.setText("abc");
        return view;

    }
}
