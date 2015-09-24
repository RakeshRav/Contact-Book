package com.example.galaxy.contactsbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by galaxy on 24/09/15.
 */
public class FilterFragment extends Fragment
{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View filterView = inflater.inflate(R.layout.filter_fragment,container,false);
        return filterView;
    }
}
