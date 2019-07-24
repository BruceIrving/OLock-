package hotel.cyut.im.o_lock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static android.support.constraint.Constraints.TAG;

public class RoomInformation extends AsyncTask<String,Void,String> {
    private final Context context;
    private static final String ACTIVITY_TAG ="Log";
    public RoomInformation(Context ctx){ this.context = ctx; }

    @Override
    protected String doInBackground(String... params) {
        String roomUrl = "http://163.17.9.125/olock_app/booking_record.php";
        Log.d(RoomInformation.ACTIVITY_TAG,"Room information");
        try {
            String no = params[0];
            URL url = new URL(roomUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("m_no", "UTF-8")+"="+URLEncoder.encode(no, "UTF-8");
            Log.d("POST_DATA", "doInBackground: "+ post_data);

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String result="";
            String line=null;
            while ((line = bufferedReader.readLine())!= null){
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String jsontext) {
        Log.d(TAG, "onPostExecute"+jsontext);

        String s = "";
        String s1 = "";
        String s2 = "";
        String s3 = "";
        try {
            JSONObject object = new JSONObject(jsontext);
            String room_num = object.getString("r_room");
            String start = object.getString("o_in");
            String end = object.getString("o_out");
            String room_pass = object.getString("o_password");

            s=start;
            s1=end;
            s2=room_num;
            s3=room_pass;

        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent =new Intent(context,User.class).putExtra("switch",1);
        Bundle bundle = new Bundle();
        bundle.putString("start", s);
        bundle.putString("end", s1);
        bundle.putString("room_num", s2);
        bundle.putString("room_pass", s3);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
