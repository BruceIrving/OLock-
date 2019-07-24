package hotel.cyut.im.o_lock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HotelsaActivity extends Activity implements View.OnClickListener {

    ImageButton imb;
    TextView pr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotelsa);

        imb = findViewById(R.id.imBut);

        imb.setOnClickListener(this);

        pr =findViewById(R.id.textView37);

        String start = getIntent().getStringExtra("start");
        String end = getIntent().getStringExtra("end");
        String room_type = getIntent().getStringExtra("room_type");
        String room_num = getIntent().getStringExtra("room_num");
        String price = getIntent().getStringExtra("price");
      /*  TextView Message = findViewById(R.id.textView36);
        Message.setText(start+"\n"+end+"\n"+room_type+"\n"+room_num+"\n"+price);*/

        SharedPreferences SPF = getSharedPreferences("room",MODE_PRIVATE);
        SPF.edit()
                .putString("start",start)
                .putString("end",end)
                .putString("room_type",room_type)
                .putString("room_num",room_num)
                .putString("price",price)
                .commit();

        pr.setText("NT."+price);

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Hotelsa1Activity.class));
    }
}
