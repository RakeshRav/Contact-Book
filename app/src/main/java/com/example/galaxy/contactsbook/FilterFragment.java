package com.example.galaxy.contactsbook;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;

/**
 * Created by galaxy on 24/09/15.
 */
public class FilterFragment extends Fragment
{

    public ActionBar actionBar;

    ListViewSelection mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View filterView = inflater.inflate(R.layout.filter_fragment,container,false);


        ButtonAdapter adapter = new ButtonAdapter(getActivity());
        GridView gridView = (GridView) filterView.findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
        Log.i("gridView", "adapter");

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//                Log.d(position + "", "onItemClick ");
//                Toast.makeText(getActivity(), id+ " Position clicked", Toast.LENGTH_SHORT).show();
//                Log.i("Clicked", "hii");

                Button button = (Button) view.findViewById(R.id.button_alpha);
                String buttonText = button.getText().toString();
//                Log.i("button Text",buttonText);
                ((MainActivity) getActivity()).setActionBarTitle("Alphabet : " + buttonText);
                mCallback.changeListPosition(buttonText);

            }
        });

        SearchView searchView = (SearchView) filterView.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
                mCallback.searchContacts(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

//                Toast.makeText(getActivity(), newText, Toast.LENGTH_SHORT).show();
                mCallback.searchContacts(newText);
                return false;
            }
        });


        return filterView;
    }

    public interface ListViewSelection
    {
        public void changeListPosition(String letter);

        public void searchContacts(String query);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (ListViewSelection) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ListViewSelection");
        }
    }
}
