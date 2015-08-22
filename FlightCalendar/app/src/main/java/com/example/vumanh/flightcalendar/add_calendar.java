package com.example.vumanh.flightcalendar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class add_calendar extends Activity implements View.OnClickListener{

    EditText txtName, txtDeparture, txtDestination;
    TextView txtDate, txtTime;
    Button btnCreate, btnCancel;

    DBHelper helper;
    SQLiteDatabase db;

    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calendar);

        helper = new DBHelper(this);
        txtName = (EditText) findViewById(R.id.txtName);
        txtDeparture = (EditText) findViewById(R.id.txtDeparture);
        txtDestination = (EditText) findViewById(R.id.txtDestination);
        txtDate=(TextView) findViewById(R.id.txtDate);
        txtTime=(TextView) findViewById(R.id.txtTime);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnCreate.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        Button cancelButton = (Button) findViewById(R.id.cancel_button_add);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_calendar, menu);
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
    public void onClick(View view) {
        if (view == btnCreate) {
            try {
                ContentValues values = new ContentValues();
                values.put(DBHelper.F_flightName, txtName.getText().toString());
                values.put(DBHelper.F_departure, txtDeparture.getText().toString());
                values.put(DBHelper.F_destination, txtDestination.getText().toString());
                values.put(DBHelper.F_departDate,txtDate.getText().toString());
                values.put(DBHelper.F_departTime,txtTime.getText().toString());

                db = helper.getWritableDatabase();
                db.insert(DBHelper.TABLE2, null, values);
                db.close();

                clearFields();
                Toast.makeText(this, "Flight Added Successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtDeparture.setText("");
        txtDestination.setText("");
    }
}
