package hotel.cyut.im.o_lock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Test1Activity extends Activity {

    TextView tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);

        tt = findViewById(R.id.textView38);

        String start = getSharedPreferences("room",MODE_PRIVATE)
                .getString("start","");
        String end = getSharedPreferences("room",MODE_PRIVATE)
                .getString("end","");
        String room_type = getSharedPreferences("room",MODE_PRIVATE)
                .getString("room_type","");
        String room_num = getSharedPreferences("room",MODE_PRIVATE)
                .getString("room_num","");
        String price = getSharedPreferences("room",MODE_PRIVATE)
                .getString("price","");

        tt.setText(start+"\n"+"\n"+end+"\n"+room_type+"\n"+room_num+"\n"+price);

    }
}
