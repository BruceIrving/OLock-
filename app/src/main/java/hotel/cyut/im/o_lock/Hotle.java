package hotel.cyut.im.o_lock;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class Hotle extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    String start,end;
    Button sa;
    TextView startDateDisplay, endDateDisplay, tt;
    Calendar startDate, endDate;
    static  final int DATE_DIALOG_ID = 0;
    private TextView activeDateDisplay;
    private Calendar activeDate;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotle);

        session = new Session(this);
        if (!session.loggedin()){
            logout();
        }

        tt=findViewById(R.id.textView37);

        sa = findViewById(R.id.button2);
        sa.setOnClickListener(this);

        startDateDisplay = findViewById(R.id.o_in2);
        startDate = Calendar.getInstance();
        startDateDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(startDateDisplay, startDate);
            }
        });

        endDateDisplay = findViewById(R.id.o_out2);
        endDate = Calendar.getInstance();
        endDateDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(endDateDisplay, endDate);
            }
        });
        updateDisplay(startDateDisplay, startDate);
        updateDisplay(endDateDisplay, endDate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String name = getIntent().getStringExtra("name");
        String pass = getIntent().getStringExtra("pass");
        String email = getIntent().getStringExtra("email");
        String tel = getIntent().getStringExtra("tel");
        String no = getIntent().getStringExtra("no");

        SharedPreferences pres = getSharedPreferences("userdata",MODE_PRIVATE);
        pres.edit()
                .putString("name",name)
                .putString("pass",pass)
                .putString("email",email)
                .putString("tel",tel)
                .putString("no",no)
                .commit();

    }

    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(Hotle.this,MainActivity.class));
    }

    private void updateDisplay(TextView dateDisplay, Calendar date) {
        dateDisplay.setText(
                new StringBuffer()
                        // Month is 0 based so add 1
                        .append(date.get(Calendar.YEAR)).append("-")
                        .append(date.get(Calendar.MONTH) + 1).append("-")
                        .append(date.get(Calendar.DAY_OF_MONTH)).append(" "));
    }

    private void showDateDialog(TextView dateDisplay, Calendar date) {
        activeDateDisplay = dateDisplay;
        activeDate = date;
        showDialog(DATE_DIALOG_ID);
    }
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            activeDate.set(Calendar.YEAR, year);
            activeDate.set(Calendar.MONTH, monthOfYear);
            activeDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDisplay(activeDateDisplay, activeDate);
            unregisterDateDisplay();
        }
    };

    private void unregisterDateDisplay() {
        activeDateDisplay = null;
        activeDate = null;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateSetListener, activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        start = ((TextView) findViewById(R.id.o_in2)).getText().toString();
        end = ((TextView) findViewById(R.id.o_out2)).getText().toString();
        Backgroundworker1 backgroundworker1 = new Backgroundworker1(Hotle.this);
        backgroundworker1.execute(start,end);
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
            startActivity(new Intent(Hotle.this, Hotle.class));
            finish();
        } else if (id == R.id.nav_user) {
            startActivity(new Intent(Hotle.this, User.class));
            finish();

        } else if (id == R.id.nav_manage) {

            startActivity(new Intent(Hotle.this, Question.class));
            finish();

        } else if (id == R.id.nav_logout) {

            new AlertDialog.Builder(Hotle.this)
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
}
