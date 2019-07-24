package hotel.cyut.im.o_lock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class InformationActivity extends Activity implements View.OnClickListener {

    Button bbuutt;
    TextView tx, tx1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                .Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);

        bbuutt = findViewById(R.id.button4);
        bbuutt.setOnClickListener(this);

        tx = findViewById(R.id.textView22);
        tx1 = findViewById(R.id.textView35);

        String jsonText = "";
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://163.17.9.125/olock_app/new33.php");
            HttpResponse response = client.execute(post);
            jsonText = EntityUtils.toString(response.getEntity());
        } catch (Exception e){
            e.printStackTrace();
        }
        showJson(jsonText);
    }
    public void showJson(String jsonText)
    {
        String s = "";
        String s1 = "";

        try {
            JSONArray array = new JSONArray(jsonText);
            for (int i = 0;i<array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                String num = obj.getString("r_room");
                String pass = obj.getString("o_password");


                s= num;
                s1= pass;


            }
        } catch (Exception e){
            e.printStackTrace();
        }
        tx.setText(s);
        tx1.setText(s1);


    }

    @Override
    public void onClick(View v) {

        startActivity(new Intent(this, QrcodeActivity.class));

    }
}
