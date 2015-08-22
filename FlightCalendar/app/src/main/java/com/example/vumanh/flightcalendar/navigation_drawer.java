package com.example.vumanh.flightcalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class navigation_drawer extends Activity implements View.OnClickListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView leftDrawerList;
    private ArrayAdapter<String> navigationDrawerAdapter;
    private String[] leftSliderData = {"Calendar", "Contacts", "About us"};

    String selected_ID = "";
    ListView listFlight;

    DBHelper helper;
    SQLiteDatabase db;

    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        nitView();
        if (toolbar != null) {
            toolbar.setTitle("Flight calendar");
        }
        initDrawer();

        FloatingActionButton addCalendar = (FloatingActionButton) findViewById(R.id.addCalendarButton);

        addCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(navigation_drawer.this, add_calendar.class);
                startActivity(intent);
            }
        });

        leftDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    leftDrawerList.setItemChecked(position, true);
                    drawerLayout.closeDrawers();
                } else if (position == 1) {
                    Intent intent = new Intent(navigation_drawer.this, contact_page.class);
                    startActivity(intent);
                    leftDrawerList.setItemChecked(position, true);
                    drawerLayout.closeDrawers();
                } else if (position == 2) {
                    leftDrawerList.setItemChecked(position, true);
                    drawerLayout.closeDrawers();
                    AlertDialog.Builder builder = new AlertDialog.Builder(navigation_drawer.this);
                    builder.setTitle("About us");
                    builder.setMessage("Nguyen Hoang Ha \nBui Duc Phuc \nVu Manh Hung \nTrinh Duc Long \nTran Van Dat\n\nCopyright 2015. All right reserved");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setIcon(android.R.drawable.ic_dialog_info);
                    builder.show();
                }
            }
        });

        helper = new DBHelper(this);
        listFlight = (ListView) findViewById(R.id.listFlight);

        listFlight.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                String name, departure, destination, date, time;
                Cursor list = (Cursor) adapter.getItemAtPosition(position);
                selected_ID = list.getString(0);
                name = list.getString(1);
                departure = list.getString(2);
                destination = list.getString(3);
                date = list.getString(4);
                time = list.getString(5);

            }
        });

        fetchData();
    }

    private void nitView() {
        leftDrawerList = (ListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationDrawerAdapter=new ArrayAdapter<String>( navigation_drawer.this, android.R.layout.simple_list_item_1, leftSliderData);
        leftDrawerList.setAdapter(navigationDrawerAdapter);
    }

    private void initDrawer() {

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    private void fetchData() {
        db = helper.getReadableDatabase();
        Cursor c = db.query(DBHelper.TABLE2, null, null, null, null, null, null);
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.flight_row,
                c,
                new String[]{DBHelper.F_flightName, DBHelper.F_departDate},
                new int[]{R.id.flightName, R.id.flightDate});

        listFlight.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }
}
