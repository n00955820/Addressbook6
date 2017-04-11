package com.example.terry.addressbook5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ViewContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        EditText name = (EditText)findViewById(R.id.name);
        EditText phone = (EditText)findViewById(R.id.phoneNumber);
        EditText email = (EditText)findViewById(R.id.email);
        EditText street = (EditText)findViewById(R.id.street);
        EditText cityStateZip = (EditText)findViewById(R.id.cityStateZip);

        Intent i = getIntent();
        name.setText(i.getStringExtra("message11"));
        phone.setText(i.getStringExtra("message12"));
        email.setText(i.getStringExtra("message13"));
        street.setText(i.getStringExtra("message14"));
        cityStateZip.setText(i.getStringExtra("message15"));
    }
}
