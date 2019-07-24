package hotel.cyut.im.o_lock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Hotelsa1Activity extends Activity implements View.OnClickListener {

    Button butt;
    TextView pri;
    String start, end, room_type,room_num, price, no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotelsa1);

        pri = findViewById(R.id.textView40);
        butt = findViewById(R.id.butt);

        butt.setOnClickListener(this);

        String price = getSharedPreferences("room",MODE_PRIVATE)
                .getString("price","");

        pri.setText("NT."+price);

    }

    @Override
    public void onClick(View v) {
        start = getSharedPreferences("room",MODE_PRIVATE)
                .getString("start","");
        end = getSharedPreferences("room",MODE_PRIVATE)
                .getString("end","");
        room_type = room_type = getSharedPreferences("room",MODE_PRIVATE)
                .getString("room_type","");
        room_num = getSharedPreferences("room",MODE_PRIVATE)
                .getString("room_num","");
        price = getSharedPreferences("room",MODE_PRIVATE)
                .getString("price","");
        no = getSharedPreferences("userdata",MODE_PRIVATE)
                .getString("no","");
        Information information = new Information(Hotelsa1Activity.this);
        information.execute(room_type,start,end,room_num,price,no);
    }
}
