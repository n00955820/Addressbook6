package com.example.terry.addressbook5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String mFilename = "testFile.json";
    public static String message6 = "message6";
    public static String message7 = "message7";
    public static String message8 = "message8";
    public static String message9 = "message9";
    public static String message10 = "message10";
    public static int currentPosition = 0;
    public ArrayList<Contact> todoItems = new ArrayList<Contact>();
    public static ArrayAdapter<Contact> aa;
    public static int numOfContacts = 0;

    ListView mListView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater addMenu = getMenuInflater();
        addMenu.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        startActivityForResult(new Intent(getApplicationContext(), ContactsActivity.class),99);

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        aa = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, todoItems);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(aa);
        readToDoItems();
        //populateList();//----------------------------------
        //aa.notifyDataSetChanged();//-----------------------------
        registerForContextMenu(mListView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact editContact = todoItems.get(i);


                String editName = editContact.getName();
                String editPhone = editContact.getNumber();
                String editEmail = editContact.getEmail();
                String editStreet = editContact.getStreet();
                String editCityStateZip = editContact.getCityStateZip();

                Intent intent = new Intent(MainActivity.this, ViewContactActivity.class);
                intent.putExtra("message11", editName);
                intent.putExtra("message12", editPhone);
                intent.putExtra("message13", editEmail);
                intent.putExtra("message14", editStreet);
                intent.putExtra("message15", editCityStateZip);

                startActivity(intent);

            }
        });

        /*final EditText mEditText = (EditText)findViewById(R.id.add_item_text);
        mEditText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)){
                        //todoItems.add(0, mEditText.getText().toString());
                        aa.notifyDataSetChanged();
                        mEditText.setText("");
                        return true;
                    }
                    return false;
                }
                return false;
            }
        });*/



    }

    private void saveToDoItems(){

        ArrayList<String> GSONContacts = new ArrayList<String>();

        for(int i = 0; i < numOfContacts; i++){
            Gson gson = new Gson();
            GSONContacts.add(gson.toJson(todoItems.get(i)));
        }

        JSONArray array = new JSONArray(GSONContacts);
        Writer writer = null;


        try{
            OutputStream out = openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
            writer.close();
        }catch (IOException e){
            Log.d("ToDoItems", "Writer IO exception: ", e);
        }


        //numOfContacts = 0;
    }

    private void readToDoItems(){
        BufferedReader reader = null;
        try{
            InputStream in = openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null){
                jsonString.append(line);
            }
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();


            for(int i = 0; i < array.length(); i++){
                Gson gson = new Gson();
                Contact readContact = gson.fromJson(array.getString(i), Contact.class);
                //numOfContacts++;
                todoItems.add(readContact);

            }
            aa.notifyDataSetChanged();
            reader.close();
        }catch (IOException e){
            Log.d("ToDoItems", "Reader IO exception: ", e);
        }catch (JSONException e){
            e.printStackTrace();
        }

        populateList();
    }


    private class ContactListAdapter extends ArrayAdapter<Contact>{
        public ContactListAdapter(){
            super(MainActivity.this, R.layout.listview_item, todoItems);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){
            if(view==null){
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);
            }

            Contact currentContact = todoItems.get(position);

            TextView name = (TextView)view.findViewById(R.id.contactName);
            name.setText(currentContact.getName());

            return view;
        }
    }

    //List<Contact> Contacts = new ArrayList<Contact>();


    private void populateList(){
        aa = new ContactListAdapter();
        mListView.setAdapter(aa);
    }

    private void addContact(String name, String number, String email, String street, String cityStateZip){

        todoItems.add(new Contact(name, number, email, street, cityStateZip));
        aa.notifyDataSetChanged();
    }

    //public void clickViewContacts(View view){
        //startActivityForResult(new Intent(getApplicationContext(), ContactsActivity.class),99);
    //}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 99 && resultCode == RESULT_OK){

            String temp1 = data.getStringExtra("message1");
            String temp2 = data.getStringExtra("message2");
            String temp3 = data.getStringExtra("message3");
            String temp4 = data.getStringExtra("message4");
            String temp5 = data.getStringExtra("message5");


            addContact(temp1, temp2, temp3, temp4, temp5);
            populateList();


            aa.notifyDataSetChanged();
            //mEditText.setText("");
        }else if(requestCode == 98 && resultCode == RESULT_OK){


            String temp1 = data.getStringExtra("message1");
            String temp2 = data.getStringExtra("message2");
            String temp3 = data.getStringExtra("message3");
            String temp4 = data.getStringExtra("message4");
            String temp5 = data.getStringExtra("message5");


            Contact contact = new Contact(temp1, temp2, temp3, temp4, temp5);

            todoItems.get(currentPosition)._name = temp1;
            todoItems.get(currentPosition)._number = temp2;
            todoItems.get(currentPosition)._email = temp3;
            todoItems.get(currentPosition)._street = temp4;
            todoItems.get(currentPosition)._cityStateZip = temp5;
            //aa.insert(contact, currentPosition);
            aa.notifyDataSetChanged();
        }


    }





    @Override
    public void onPause(){
        super.onPause();
        saveToDoItems();
    }

    public void clickItem(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        Contact editContact = todoItems.get(position);

        String editName = editContact.getName();
        String editPhone = editContact.getNumber();
        String editEmail = editContact.getEmail();
        String editStreet = editContact.getStreet();
        String editCityStateZip = editContact.getCityStateZip();

        Intent i = new Intent(this, ViewContactActivity.class);
        i.putExtra("message11", editName);
        i.putExtra("message12", editPhone);
        i.putExtra("message13", editEmail);
        i.putExtra("message14", editStreet);
        i.putExtra("message15", editCityStateZip);
        startActivity(i);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        getMenuInflater().inflate(R.menu.list_item_context, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int position = info.position;
        switch(item.getItemId()){
            case R.id.menu_item_delete:

                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("Are you sure? " +
                        "This will permanently delete the contact");
                alert.setCancelable(false);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        todoItems.remove(position);
                        numOfContacts--;
                        aa.notifyDataSetChanged();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alert.create().show();

                return true;

            case R.id.menu_item_edit:
                Contact editContact = todoItems.get(position);
                String editName = editContact.getName();
                String editPhone = editContact.getNumber();
                String editEmail = editContact.getEmail();
                String editStreet = editContact.getStreet();
                String editCityStateZip = editContact.getCityStateZip();
                Intent intent = new Intent(this, ContactsActivity.class);
                intent.putExtra(message6, editName);
                intent.putExtra(message7, editPhone);
                intent.putExtra(message8, editEmail);
                intent.putExtra(message9, editStreet);
                intent.putExtra(message10, editCityStateZip);
                currentPosition = position;
                numOfContacts--;
                //aa.remove(todoItems.get(position));
                //todoItems.remove(position);
                startActivityForResult(intent,98);
                return true;

        }
        return super.onContextItemSelected(item);
    }


}
