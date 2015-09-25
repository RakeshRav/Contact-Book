package com.example.galaxy.contactsbook;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by galaxy on 24/09/15.
 */
public class ContactsAdapter extends BaseAdapter
{
    private final Context context;
    ArrayList<String> nListIds;
    public LayoutInflater inflater;

    public ContactsAdapter(Context context, ArrayList<String> nListIds)
    {

        this.context = context;
        this.nListIds=nListIds;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public class ViewHolder {
        TextView name;
        TextView phNumber;
        TextView email;

    }

    @Override
    public int getCount() {
        return nListIds.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        final ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.single_contact_view, parent, false);
            holder = new ViewHolder();

            holder.name = (TextView) view
                    .findViewById(R.id.name);

            holder.phNumber = (TextView) view.findViewById(R.id.contactNumber);

            holder.email = (TextView) view.findViewById(R.id.email);

            view.setTag(holder);
        } else {

            holder = (ViewHolder) view.getTag();
        }

        holder.name.setVisibility(View.VISIBLE);
        holder.phNumber.setVisibility(View.VISIBLE);
        holder.email.setVisibility(View.VISIBLE);

        holder.name.setText("");
        holder.phNumber.setText("");
        holder.email.setText("");

        ContentResolver contentResolver = context.getContentResolver();

        String str = "";
        String email = "";
        String name ="";
        String phNumber="";

                Cursor cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                new String[]{nListIds.get(position)}, null);

                Log.i("email count", "" + cursor.getCount());

                if (cursor.moveToNext()) {
                    //to get the contact names
//                    String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//                    Log.e("Name :", name);
                    email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    Log.e("Email", email);
                    str = email;

                        holder.email.setText(email);

                }
                else {
                    holder.email.setVisibility(View.GONE);
                }
                cursor.close();

                Cursor cursor1 = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{nListIds.get(position)}, null);

                Log.i("phone count", "" + cursor1.getCount());

                if (cursor1.moveToNext()) {
                    //to get the contact names

                     phNumber = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                      holder.phNumber.setText(phNumber);

                    Log.e("phone", phNumber);
                    str = str+" "+phNumber;

                }
                 else {
                    holder.phNumber.setVisibility(View.GONE);
                }
                cursor1.close();

        Cursor cursor2 = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, ContactsContract.Contacts._ID+" = ?", new String[]{nListIds.get(position)}, null);


        if (cursor2.moveToNext())
        {
            name=cursor2.getString(cursor2.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));


                holder.name.setText(name);

            Log.e("Name :", name);
        }
        else
        {
            holder.name.setVisibility(View.GONE);
        }
        cursor2.close();

                Log.i("Final String", str);




        return view;

    }
}
