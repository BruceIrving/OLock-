package hotel.cyut.im.o_lock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

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

public class Backgroundworker1 extends AsyncTask<String,Void,String> {
    private final Context context;
    private static final String ACTIVITY_TAG ="Log";
    public Backgroundworker1(Context ctx){
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... params){
        String hotleUrl = "http://163.17.9.125/olock_app/APP_booking_res.php";
        Log.d(Backgroundworker1.ACTIVITY_TAG,"Conducting hotel search");
        try {
            String start = params[0];
            String end = params[1];
            URL url = new URL(hotleUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("start", "UTF-8")+"="+URLEncoder.encode(start, "UTF-8")
                    +"&"+URLEncoder.encode("end", "UTF-8")+"="+URLEncoder.encode(end, "UTF-8");
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

    }

    @Override
    protected void onPostExecute(String jsontext){
        Log.d(TAG, "onPostExecute"+jsontext);

        String s = "";
        String s1 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";
        try {
            JSONObject obj = new JSONObject(jsontext);
            String start = obj.getString("start");
            String end = obj.getString("end");
            String diff = obj.getString("diff");
            String room_type = obj.getString("room_type");
            String room_num = obj.getString("r_room");
            String price = obj.getString("price");

            s=start;
            s1=end;
            s3=room_type;
            s4=room_num;
            s5=price;



        }catch (Exception e) {
                e.printStackTrace();
        }

        Intent intent=new Intent(context,HotelsaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("start", s);
        bundle.putString("end",s1);
        bundle.putString("room_type", s3);
        bundle.putString("room_num", s4);
        bundle.putString("price", s5);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity)context).finish();
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
