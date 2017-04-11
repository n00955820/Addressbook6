package com.example.terry.addressbook5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ContactsActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "message1";
    private EditText nameTxt;
    private EditText phoneTxt;
    private EditText emailTxt;
    private EditText streetTxt;
    private EditText cityStateZipTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        nameTxt = (EditText)findViewById(R.id.name);
        phoneTxt = (EditText)findViewById(R.id.phoneNumber);
        emailTxt = (EditText)findViewById(R.id.email);
        streetTxt = (EditText)findViewById(R.id.street);
        cityStateZipTxt = (EditText)findViewById(R.id.cityStateZip);

        Intent i = getIntent();

        nameTxt.setText(i.getStringExtra(MainActivity.message6));
        phoneTxt.setText(i.getStringExtra(MainActivity.message7));
        emailTxt.setText(i.getStringExtra(MainActivity.message8));
        streetTxt.setText(i.getStringExtra(MainActivity.message9));
        cityStateZipTxt.setText(i.getStringExtra(MainActivity.message10));
    }


    public void clickedSaveContact(View view){

        MainActivity.numOfContacts++;
        Intent intent = new Intent();
        intent.putExtra("message1", nameTxt.getText().toString());
        intent.putExtra("message2", phoneTxt.getText().toString());
        intent.putExtra("message3", emailTxt.getText().toString());
        intent.putExtra("message4", streetTxt.getText().toString());
        intent.putExtra("message5", cityStateZipTxt.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }


}
