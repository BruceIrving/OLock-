package hotel.cyut.im.o_lock;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String etemail;
    String etpassword;
    ImageButton butLinkToRegister;
    Button login;
    String type;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        butLinkToRegister = (ImageButton) findViewById(R.id.regg);
        login = (Button) findViewById(R.id.login);
        session = new Session(this);


        login.setOnClickListener(this);
        butLinkToRegister.setOnClickListener(this);

        if (session.loggedin()){
            startActivity(new Intent(MainActivity.this, Hotle.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login:
                type="login";
                etemail = ((EditText) findViewById(R.id.email2)).getText().toString();
                etpassword = ((EditText) findViewById(R.id.m_paaword)).getText().toString();
                session.setLoggedin(true);
                if (!etemail.isEmpty() || !etpassword.isEmpty()) {
                    Backgroundworker backgorundwork = new Backgroundworker(MainActivity.this);
                    backgorundwork.execute(type, etemail, etpassword);

                }else {
                    Toast.makeText(this, "有欄位上未填寫唷", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.regg:
                startActivity(new Intent(this, Main1Activity.class));
                break;

        }

    }
}