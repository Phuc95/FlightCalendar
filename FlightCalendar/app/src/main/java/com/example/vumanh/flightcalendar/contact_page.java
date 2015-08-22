package com.example.vumanh.flightcalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
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
import android.widget.ListView;


public class contact_page extends Activity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView leftDrawerList;
    private ArrayAdapter<String> navigationDrawerAdapter;
    private String[] leftSliderData = {"Calendar", "Contacts", "About us"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);

        nitView();
        if (toolbar != null) {
            toolbar.setTitle("Contacts");
        }
        initDrawer();

        FloatingActionButton addCalendar = (FloatingActionButton) findViewById(R.id.addContactButton);

        addCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contact_page.this, add_contact.class);
                startActivity(intent);
            }
        });

        leftDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(contact_page.this, navigation_drawer.class);
                    startActivity(intent);
                    leftDrawerList.setItemChecked(position, true);
                    drawerLayout.closeDrawers();
                } else if (position == 1) {
                    leftDrawerList.setItemChecked(position, true);
                    drawerLayout.closeDrawers();
                } else if (position == 2) {
                    leftDrawerList.setItemChecked(position, true);
                    drawerLayout.closeDrawers();
                    AlertDialog.Builder builder = new AlertDialog.Builder(contact_page.this);
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
    }

    private void nitView() {
        leftDrawerList = (ListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationDrawerAdapter=new ArrayAdapter<String>(contact_page.this, android.R.layout.simple_list_item_1, leftSliderData);
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
        getMenuInflater().inflate(R.menu.menu_contact_page, menu);
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
}
