package com.example.vumanh.flightcalendar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    EditText txtUser, txtPass;
    Button btnLogin, btnCreate;
    DBHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);

        txtUser = (EditText) findViewById(R.id.usernametxt);
        txtPass = (EditText) findViewById(R.id.passwordtxt);

        btnLogin = (Button) findViewById(R.id.button);
        btnCreate = (Button) findViewById(R.id.button2);

        btnLogin.setOnClickListener(this);
        btnCreate.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) try {
            String name = txtUser.getText().toString();
            String pass = txtPass.getText().toString();
            db = helper.getReadableDatabase();
            Cursor cu = db.rawQuery("SELECT * FROM users WHERE _username = ? and _password =?", new String[]{name,pass});
            if(cu!=null)
            {
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        if (v == btnCreate) {
            try {
                Intent intent = new Intent(this, Create.class);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
