package com.example.galaxy.contactsbook;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by HPLAP on 9/27/2015.
 */
public class FetchContacts extends AsyncTask<String, Void, Void>
{

    public Context context;
    ArrayList<String> nListIds;
    ContactsAdapter.ViewHolder viewHolder;
    String name="";
    String phNumber="";
    String email="";


    public FetchContacts(Context context, ArrayList<String> nListIds, ContactsAdapter.ViewHolder viewHolder)
    {
        this.context = context;
        this.nListIds = nListIds;
        this.viewHolder = viewHolder;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        viewHolder.name.setVisibility(View.VISIBLE);
        viewHolder.phNumber.setVisibility(View.VISIBLE);
        viewHolder.email.setVisibility(View.VISIBLE);

        viewHolder.name.setText("");
        viewHolder.phNumber.setText("");
        viewHolder.email.setText("");

    }

    @Override
    protected Void doInBackground(String... params) {

        ContentResolver contentResolver = context.getContentResolver();

        int position = Integer.parseInt(params[0]);

        Cursor cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                new String[]{nListIds.get(position)}, null);

//                Log.i("email count", "" + cursor.getCount());

        if (cursor.moveToNext()) {
            //to get the contact names
//                    String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//                    Log.e("Name :", name);
            email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
//                    Log.e("Email", email);
//            str = email;
        }
        cursor.close();

        Cursor cursor1 = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                new String[]{nListIds.get(position)}, null);

//                Log.i("phone count", "" + cursor1.getCount());

        if (cursor1.moveToNext()) {
            //to get the contact names

            phNumber = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

//                    Log.e("phone", phNumber);
//            str = str+" "+phNumber;

        }
        cursor1.close();

        Cursor cursor2 = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, ContactsContract.Contacts._ID+" = ?", new String[]{nListIds.get(position)}, null);


        if (cursor2.moveToNext())
        {
            name=cursor2.getString(cursor2.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));



            // Log.e("Name :", name);
        }
        cursor2.close();

        //  Log.i("Final String", str);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if (name.equals(""))
        {
            viewHolder.name.setVisibility(View.GONE);
        }
        else
        {
            viewHolder.name.setText(name);
        }
        if (phNumber.equals(""))
        {
            viewHolder.phNumber.setVisibility(View.GONE);
        }
        else
        {
            viewHolder.phNumber.setText(phNumber);
        }
        if (email.equals(""))
        {
            viewHolder.email.setVisibility(View.GONE);
        }
        else
        {
            viewHolder.email.setText(email);
        }

    }
}