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

//        holder.name.setVisibility(View.GONE);
//        holder.phNumber.setVisibility(View.GONE);
//        holder.email.setVisibility(View.GONE);


        ContentResolver contentResolver = context.getContentResolver();

        String str = "";
        Cursor cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                new String[]{nListIds.get(position)}, null);

                Log.i("email count", "" + cursor.getCount());

                while (cursor.moveToNext()) {
                    //to get the contact names
//                    String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//                    Log.e("Name :", name);
                    String email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    Log.e("Email", email);
                    str = email;


                    holder.email.setText(email);

                }
                cursor.close();

                Cursor cursor1 = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{nListIds.get(position)}, null);

                Log.i("phone count", "" + cursor1.getCount());

                while (cursor1.moveToNext()) {
                    //to get the contact names
                    String name=cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                    holder.name.setText(name);
                    Log.e("Name :", name);

                    String phone = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    holder.phNumber.setText(phone);

                    Log.e("phone", phone);
                    str = str+" "+phone;

                }
                cursor1.close();
                Log.i("Final String", str);




        return view;

    }
}
