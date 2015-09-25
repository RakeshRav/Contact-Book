package com.example.galaxy.contactsbook;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by galaxy on 24/09/15.
 */
public class ContactsFragment extends Fragment
{

    ArrayList<String> contact_ids;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contactsView = inflater.inflate(R.layout.contacts_fragment,container,false);


        contact_ids = getIds();

        for (int i = 0; i<contact_ids.size() ; i++ )
        {
            Log.i("ID ","id "+contact_ids.get(i));
        }

        ContactsAdapter adapter = new ContactsAdapter(getActivity(),contact_ids);
        ListView listView = (ListView) contactsView.findViewById(R.id.contactList);
        listView.setAdapter(adapter);
        return contactsView;
    }

    public ArrayList<String> getIds(){
        String str = "";
        ArrayList<String> idList = new ArrayList<String>();

        ArrayList<String> names = new ArrayList<String>();

        ContentResolver contentResolver = getActivity().getContentResolver();

        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME_PRIMARY+" COLLATE NOCASE");

        if (cursor.getCount() > 0) {
            Log.i("Cursor Count", "" + cursor.getCount());
            while (cursor.moveToNext()) {

                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                idList.add(id);

            }
        }
        return idList;
    }
}
