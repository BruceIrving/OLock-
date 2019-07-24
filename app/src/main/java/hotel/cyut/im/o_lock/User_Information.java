package hotel.cyut.im.o_lock;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class User_Information extends AppCompatActivity {

    TextView txt, txt1, txt2, txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_user);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                .Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);

        txt = findViewById(R.id.textView24);
        txt1 = findViewById(R.id.pw);
        txt2 = findViewById(R.id.na);
        txt3 = findViewById(R.id.tel1);

        String jsonText = "";
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://163.17.9.125/olock_app/new22.php");
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
        String s2 = "";
        String s3 = "";
        try {
            JSONArray array = new JSONArray(jsonText);
            for (int i = 0;i<array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                String name = obj.getString("m_name");
                String pw = obj.getString("m_pw");
                String tel = obj.getString("m_tel");
                String email = obj.getString("m_email");

                s= email;
                s1= pw;
                s2= name;
                s3= tel;

            }
        } catch (Exception e){
            e.printStackTrace();
        }
        txt.setText(s);
        txt1.setText(s1);
        txt2.setText(s2);
        txt3.setText(s3);

    }
}
