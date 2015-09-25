package com.example.galaxy.contactsbook;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by galaxy on 25/09/15.
 */
public class ButtonAdapter extends BaseAdapter
{

    private Context context;
    LayoutInflater inflater;

    final String alphabets[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    final ArrayList<String> list = new ArrayList<String>(Arrays.asList(alphabets));

    public ButtonAdapter(Context context)
    {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class ViewHolder
    {
        public Button button;
    }

    @Override
    public int getCount() {
        return 26;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder viewHolder;

        if (view == null)
        {
            view = inflater.inflate(R.layout.button,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.button = (Button) view.findViewById(R.id.button_alpha);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.button.setText(list.get(position));

        viewHolder.button.clearFocus();
        Log.i("position", "" + position);

        return view;
    }
}
