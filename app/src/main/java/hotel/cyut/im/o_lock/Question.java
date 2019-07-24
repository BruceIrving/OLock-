package hotel.cyut.im.o_lock;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class Question extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    Session session;
    EditText username, email, content;
    Button butto;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        username = findViewById(R.id.editText6);
        email = findViewById(R.id.editText);
        content = findViewById(R.id.editText7);
        butto = findViewById(R.id.button5);

        butto.setOnClickListener(this);

        session = new Session(this);
        if (!session.loggedin()){
            logout();
        }
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(Question.this,MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_hotel) {
            // Handle the camera action
            startActivity(new Intent(Question.this, Hotle.class));
            finish();
        } else if (id == R.id.nav_user) {
            startActivity(new Intent(Question.this, User.class));
            finish();

        } else if (id == R.id.nav_manage) {

            startActivity(new Intent(Question.this, Question.class));
            finish();

        } else if (id == R.id.nav_logout) {

            new AlertDialog.Builder(Question.this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to Logout?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            logout();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        type="question";
        username = findViewById(R.id.editText6);
        email = findViewById(R.id.editText);
        content = findViewById(R.id.editText7);
        if (!username.getText().toString().equals("") && !email.getText().toString().equals("") && !content.getText().toString().equals("")){
            Backgroundworker backgorundwork = new Backgroundworker(Question.this);
            backgorundwork.execute(type,username.getText().toString(),email.getText().toString(),content.getText().toString());
        }else {
            Toast.makeText(this, "有欄位上未填寫", Toast.LENGTH_LONG).show();
        }
    }

}
