package com.example.vumanh.flightcalendar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by VuManh on 8/21/2015.
 */
public class Create extends Activity implements View.OnClickListener {

    EditText txtUser, txtPass, txtRepass;
    Button btnAdd, btnCancel;
    DBHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createuser_layout);
        helper = new DBHelper(this);

        txtUser = (EditText) findViewById(R.id.usernametxt);
        txtPass = (EditText) findViewById(R.id.passwordtxt);
        txtRepass = (EditText) findViewById(R.id.repasswordtxt);

        btnAdd = (Button) findViewById(R.id.createbt);
        btnCancel = (Button) findViewById(R.id.cancelbt);

        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void clearFields() {
        txtUser.setText("");
        txtPass.setText("");
        txtRepass.setText("");
    }

    private void wrongPass() {
        txtPass.setText("");
        txtRepass.setText("");
    }

    @Override
    public void onClick(View v) {
        try {
            if (v == btnAdd) {
                ContentValues values = new ContentValues();
                String user = txtUser.getText().toString();
                String pass = txtPass.getText().toString();
                String repass = txtRepass.getText().toString();
                if (pass.equalsIgnoreCase(repass)) {
                    values.put(DBHelper.U_USERNAME, txtUser.getText().toString());
                    values.put(DBHelper.U_PASSWORD, txtPass.getText().toString());
                    db = helper.getWritableDatabase();
                    db.insert(DBHelper.TABLE, null, values);
                    db.close();
                    Toast.makeText(this, "Employee Added Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                if (pass == null || repass == null || user == null) {
                    Toast.makeText(this, "No infor to create Account", Toast.LENGTH_LONG).show();
                    clearFields();
                } else {
                    Toast.makeText(this, "Your password is not match pls try again !", Toast.LENGTH_LONG).show();
                    wrongPass();
                }
            }

            if(v == btnCancel){
                onBackPressed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
