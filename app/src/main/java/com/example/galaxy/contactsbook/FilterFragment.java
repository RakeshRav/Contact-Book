package com.example.galaxy.contactsbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by galaxy on 24/09/15.
 */
public class FilterFragment extends Fragment
{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View filterView = inflater.inflate(R.layout.filter_fragment,container,false);


        ButtonAdapter adapter = new ButtonAdapter(getActivity());
        GridView gridView = (GridView) filterView.findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
        Log.i("gridView","adapter");

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(position +"", "onItemClick ");
                Toast.makeText(getActivity(),position+" Position clicked",Toast.LENGTH_LONG).show();
                Log.i("Clicked","hii");
            }
        });

        return filterView;
    }
}
