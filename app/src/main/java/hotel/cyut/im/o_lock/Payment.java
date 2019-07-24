package hotel.cyut.im.o_lock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Payment extends AppCompatActivity implements View.OnClickListener {

    Button bbut;
    TextView pric;
    String no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        getSupportActionBar().hide();

        pric = findViewById(R.id.textView18);
        bbut = findViewById(R.id.button3);

        bbut.setOnClickListener(this);

        String price = getSharedPreferences("room",MODE_PRIVATE)
                .getString("price","");

        pric.setText("NT."+price);


    }

    @Override
    public void onClick(View v) {
        //startActivity(new Intent(this,User.class).putExtra("switch",1));
        no = getSharedPreferences("userdata",MODE_PRIVATE)
                .getString("no","");
        RoomInformation roomInformation = new RoomInformation(Payment.this);
        roomInformation.execute(no);

    }
}
