package hotel.cyut.im.o_lock;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class Main1Activity extends AppCompatActivity implements View.OnClickListener {

    EditText username, email, password, password1, tel;
    ImageButton back;
    Button signup;

    String type;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        getSupportActionBar().hide();

        username = findViewById(R.id.name);
        email = findViewById(R.id.email2);
        password = findViewById(R.id.password2);
        password1 = findViewById(R.id.password3);
        tel = findViewById(R.id.tel);
        signup = findViewById(R.id.signup);
        back = findViewById(R.id.back);


        signup.setOnClickListener(this);
        back.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup:
                type="signup";
                password1 = findViewById(R.id.password3);
                tel = findViewById(R.id.tel);
                if (password1.getText().toString().equals(password.getText().toString())){
                    if (!username.getText().toString().equals("") && !email.getText().toString().equals("") && !password.getText().toString().equals("") && !tel.getText().toString().equals("")){
                        Backgroundworker backgorundwork = new Backgroundworker(Main1Activity.this);
                        backgorundwork.execute(type,username.getText().toString(),email.getText().toString(),password.getText().toString(),tel.getText().toString());
                    }else{
                        Toast.makeText(this, "有欄位上未填寫唷", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(this, "註冊失敗檢查兩次輸入的密碼是否相同", Toast.LENGTH_LONG).show();
                }
                break;
            case  R.id.back:

                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }
}